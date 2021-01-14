package com.github.gitradar.database.repository;

import com.github.gitradar.database.model.ScanModel;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * This repository contains all methods to handle {@link ScanModel}.
 */
@Repository
public interface ScanRepository {

    /**
     * Find a scan from their unique ID.
     *
     * @param scanId The scan unique ID
     * @return The scan
     */
    Optional<ScanModel> findById(final UUID scanId);

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
