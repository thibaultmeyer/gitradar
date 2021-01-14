package com.github.gitradar.database.repository;

import com.github.gitradar.database.model.IgnoredCommitModel;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * This repository contains all methods to handle {@link IgnoredCommitModel}.
 */
@Repository
public interface IgnoredCommitRepository {

    /**
     * Find an ignored commit from their unique ID.
     *
     * @param ignoredCommitId The ignored commit unique ID
     * @return The ignored commit
     */
    Optional<IgnoredCommitModel> findById(final UUID ignoredCommitId);
}
