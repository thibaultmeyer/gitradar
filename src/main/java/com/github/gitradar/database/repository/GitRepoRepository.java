package com.github.gitradar.database.repository;

import com.github.gitradar.database.model.GitRepoModel;
import io.micronaut.data.annotation.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This repository contains all methods to handle {@link GitRepoModel}.
 */
@Repository
public interface GitRepoRepository {

    /**
     * Find all Git repositories.
     *
     * @return All Git repositories
     */
    List<GitRepoModel> findAll();

    /**
     * Find a Git repository from their unique ID.
     *
     * @param gitRepoId The Git repository unique ID
     * @return The Git repository
     */
    Optional<GitRepoModel> findById(final UUID gitRepoId);

    /**
     * Find a Git repository from their unique slug.
     *
     * @param gitRepoSlug The Git repository unique slug
     * @return The Git repository
     */
    Optional<GitRepoModel> findBySlug(final String gitRepoSlug);

    /**
     * Save Git repository into database.
     *
     * @param gitRepoModel The Git repository to save
     * @return The saved Git repository
     */
    GitRepoModel save(final GitRepoModel gitRepoModel);

    /**
     * Update Git repository into database.
     *
     * @param gitRepoModel The Git repository to update
     * @return The updated Git repository
     */
    GitRepoModel update(final GitRepoModel gitRepoModel);
}
