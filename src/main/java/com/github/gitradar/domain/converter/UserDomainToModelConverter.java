package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.UserModel;
import com.github.gitradar.domain.entity.User;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * Converts a domain user into a user model.
 */
@Singleton
public final class UserDomainToModelConverter implements TypeConverter<User, UserModel> {

    @Override
    public Optional<UserModel> convert(final User user, final Class<UserModel> targetType) {
        return convert(user, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<UserModel> convert(final User user, final Class<UserModel> targetType, final ConversionContext context) {
        if (user == null) {
            return Optional.empty();
        }

        final UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setDisplayName(user.getDisplayName());
        userModel.setPassword(user.getPassword());
        userModel.setCreatedAt(user.getCreatedAt() == null ? LocalDateTime.now(ZoneOffset.UTC) : user.getCreatedAt());
        userModel.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

        return Optional.of(userModel);
    }
}
