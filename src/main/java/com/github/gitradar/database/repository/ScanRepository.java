package com.github.gitradar.database.repository;

import com.github.gitradar.database.model.ScanModel;
import io.micronaut.data.annotation.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This repository contains all methods to handle {@link ScanModel}.
 */
@Repository
public interface ScanRepository {

    /**
     * Find all scans for a given Git repository.
     *
     * @param gitRepoId The Git repository unique ID
     * @return All match scans
     */
    List<ScanModel> findAllByGitRepoId(final UUID gitRepoId);

    /**
     * Find a scan from their unique ID.
     *
     * @param scanId The scan unique ID
     * @return The scan
     */
    Optional<ScanModel> findById(final UUID scanId);

    /**
     * Find the last Git repository scan for the given repository.
     *
     * @param repoId The Git repository unique ID
     * @return The last scan
     */
    Optional<ScanModel> findFirstByGitRepoIdOrderByCreatedAtDesc(final UUID gitRepoId);

    /**
     * Save scan into database.
     *
     * @param scanModel The scan to save
     * @return The saved scan
     */
    ScanModel save(final ScanModel scanModel);

    /**
     * Update scan into database.
     *
     * @param scanModel The scan to update
     * @return The updated scan
     */
    ScanModel update(final ScanModel scanModel);
}
