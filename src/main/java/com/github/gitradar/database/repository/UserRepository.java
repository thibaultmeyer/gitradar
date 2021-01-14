package com.github.gitradar.database.repository;

import com.github.gitradar.database.model.UserModel;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * This repository contains all methods to handle {@link UserModel}.
 */
@Repository
public interface UserRepository {

    /**
     * Find a user from their unique ID.
     *
     * @param userId The user unique ID
     * @return The user
     */
    Optional<UserModel> findById(final UUID userId);

    /**
     * Save user into database.
     *
     * @param userModel The user to save
     * @return The saved user
     */
    UserModel save(final UserModel userModel);

    /**
     * Update user into database.
     *
     * @param userModel The user to update
     * @return The updated user
     */
    UserModel update(final UserModel userModel);
}
