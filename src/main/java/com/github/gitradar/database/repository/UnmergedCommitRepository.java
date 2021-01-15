package com.github.gitradar.database.repository;

import com.github.gitradar.database.model.UnmergedCommitModel;
import io.micronaut.data.annotation.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This repository contains all methods to handle {@link UnmergedCommitModel}.
 */
@Repository
public interface UnmergedCommitRepository {

    /**
     * Find all unmerged commits for a given scan.
     *
     * @param scanId The scan unique ID
     * @return All match unmerged commits
     */
    List<UnmergedCommitModel> findAllByScanId(final UUID scanId);

    /**
     * Find an unmerged commit from their unique ID.
     *
     * @param unmergedCommitId The unmerged commit unique ID
     * @return The unmerged commit
     */
    Optional<UnmergedCommitModel> findById(final UUID unmergedCommitId);

    /**
     * Save unmerged commit into database.
     *
     * @param unmergedCommitModel The unmerged commit to save
     * @return The saved unmerged commit
     */
    UnmergedCommitModel save(final UnmergedCommitModel unmergedCommitModel);

    /**
     * Update unmerged commit into database.
     *
     * @param unmergedCommitModel The unmerged commit to update
     * @return The updated unmerged commit
     */
    UnmergedCommitModel update(final UnmergedCommitModel unmergedCommitModel);

    List<UnmergedCommitModel> saveAll(List<UnmergedCommitModel> unmergedCommitModelList);
}
