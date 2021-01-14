package com.github.gitradar.domain.service;

import com.github.gitradar.database.model.UserModel;
import com.github.gitradar.database.repository.UserRepository;
import com.github.gitradar.domain.entity.User;
import io.micronaut.core.convert.ConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Optional;
import java.util.UUID;

/**
 * Service: User.
 */
@Singleton
public final class UserService {

    private static final int PASSWORD_KEY_ITERATION_COUNT = 10000;
    private static final int PASSWORD_KEY_LENGTH = 160;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final ConversionService<?> conversionService;

    private final UserRepository userRepository;

    /**
     * Build a new instance.
     *
     * @param conversionService handle to the conversion service
     * @param userRepository    handle to the user repository
     */
    @Inject
    public UserService(final ConversionService<?> conversionService,
                       final UserRepository userRepository) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
    }

    /**
     * Find a user from their unique ID.
     *
     * @param userId The user unique ID
     * @return The user
     */
    public Optional<User> findById(final UUID userId) {
        if (userId == null) {
            return Optional.empty();
        }

        return userRepository.findById(userId)
            .flatMap(userModel -> conversionService.convert(userModel, User.class));
    }

    /**
     * Persists user.
     *
     * @param user The user to persist
     * @return The persisted user
     */
    public User persist(final User user) {
        return conversionService.convert(user, UserModel.class)
            .map(userModel -> userModel.getId() == null ? userRepository.save(userModel) : userRepository.update(userModel))
            .flatMap(userModel -> conversionService.convert(userModel, User.class))
            .orElse(null);
    }

    /**
     * Update the user password with a new one.
     *
     * @param user        The user instance
     * @param newPassword The new password
     * @return {@code true} in case of success, otherwise, {@code false}
     */
    public boolean updatePassword(final User user, final String newPassword) {
        try {
            // Generate random salt
            final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            final byte[] salt = new byte[8];
            random.nextBytes(salt);

            final StringBuilder sb_salt = new StringBuilder();
            for (final byte aSalt : salt) {
                sb_salt.append(Integer.toString((aSalt & 0xFF) + 0x100, 16).substring(1));
            }

            // Encrypt password
            final KeySpec spec = new PBEKeySpec(
                newPassword.toCharArray(),
                sb_salt.toString().getBytes(),
                PASSWORD_KEY_ITERATION_COUNT,
                PASSWORD_KEY_LENGTH
            );
            final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            final byte[] digest = secretKeyFactory.generateSecret(spec).getEncoded();
            final StringBuilder sb_password = new StringBuilder();

            // Assign password
            for (final byte aDigest : digest) {
                sb_password.append(Integer.toString((aDigest & 0xFF) + 0x100, 16).substring(1));
            }

            user.setPassword(
                String.format(
                    "pbkdf2_hmacsha256$%d$%s$%s",
                    PASSWORD_KEY_ITERATION_COUNT,
                    sb_salt.toString(),
                    sb_password.toString()
                )
            );
        } catch (final NoSuchAlgorithmException | InvalidKeySpecException ex) {
            LOGGER.error("Cannot update password for user {}", user.getId(), ex);
            return false;
        }

        return true;
    }

    /**
     * Verify if the given password if correct.
     *
     * @param user             The user instance
     * @param passwordToVerify The password to verify
     * @return {@code true} is the password is correct, otherwise, {@code false}
     */
    public boolean verifyPassword(final User user, final String passwordToVerify) {
        final String[] password_x = user.getPassword().split("\\$");

        try {
            final KeySpec spec = new PBEKeySpec(
                passwordToVerify.toCharArray(),
                password_x[2].getBytes(),
                Integer.parseInt(password_x[1]),
                PASSWORD_KEY_LENGTH
            );
            final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            final byte[] digest = secretKeyFactory.generateSecret(spec).getEncoded();
            final StringBuilder sb_password = new StringBuilder();
            for (final byte aDigest : digest) {
                sb_password.append(Integer.toString((aDigest & 0xFF) + 0x100, 16).substring(1));
            }

            return password_x[3].compareTo(sb_password.toString()) == 0;
        } catch (final NoSuchAlgorithmException | InvalidKeySpecException ex) {
            LOGGER.error("Cannot verify password for user {}", user.getId(), ex);
            return false;
        }
    }
}
