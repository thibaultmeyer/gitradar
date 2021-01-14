package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.UserModel;
import com.github.gitradar.domain.entity.User;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * Converts a user model into a domain user.
 */
@Singleton
public final class UserModelToDomainConverter implements TypeConverter<UserModel, User> {

    @Override
    public Optional<User> convert(final UserModel userModel, final Class<User> targetType) {
        return convert(userModel, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<User> convert(final UserModel userModel, final Class<User> targetType, final ConversionContext context) {
        if (userModel == null) {
            return Optional.empty();
        }

        final User user = new User(
            userModel.getId(),
            userModel.getEmail(),
            userModel.getFirstName(),
            userModel.getLastName(),
            userModel.getDisplayName(),
            userModel.getPassword(),
            userModel.getCreatedAt(),
            userModel.getUpdatedAt()
        );

        return Optional.of(user);
    }
}
