package com.github.gitradar.domain.service;

import com.github.gitradar.database.model.GitRepoModel;
import com.github.gitradar.database.repository.GitRepoRepository;
import com.github.gitradar.domain.entity.GitRepo;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.core.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service: Git Repository.
 */
@Singleton
public final class GitRepoService {

    private final ConversionService<?> conversionService;
    private final GitRepoRepository gitRepoRepository;

    /**
     * Build a new instance.
     *
     * @param conversionService Handle to the conversion service
     * @param gitRepoRepository Handle to the Git repository repository
     */
    @Inject
    public GitRepoService(final ConversionService<?> conversionService,
                          final GitRepoRepository gitRepoRepository) {
        this.conversionService = conversionService;
        this.gitRepoRepository = gitRepoRepository;
    }

    /**
     * Find all Git repositories.
     *
     * @return All Git repositories
     */
    public List<GitRepo> findAll() {
        return gitRepoRepository.findAll().stream()
            .map(gitRepoModel -> conversionService.convert(gitRepoModel, GitRepo.class).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * Find all Git repositories.
     *
     * @return All Git repositories
     */
    public Stream<GitRepo> findAllStream() {
        return gitRepoRepository.findAll().stream()
            .map(gitRepoModel -> conversionService.convert(gitRepoModel, GitRepo.class).orElse(null))
            .filter(Objects::nonNull);
    }

    /**
     * Find a Git repository from their unique ID.
     *
     * @param gitRepoId The Git repository unique ID
     * @return The Git repository
     */
    public Optional<GitRepo> findById(final UUID gitRepoId) {
        if (gitRepoId == null) {
            return Optional.empty();
        }

        return gitRepoRepository.findById(gitRepoId)
            .flatMap(gitRepoModel -> conversionService.convert(gitRepoModel, GitRepo.class));
    }

    /**
     * Find a Git repository from their unique slug.
     *
     * @param gitRepoSlug The Git repository unique slug
     * @return The Git repository
     */
    public Optional<GitRepo> findBySlug(final String gitRepoSlug) {
        if (StringUtils.isEmpty(gitRepoSlug)) {
            return Optional.empty();
        }

        return gitRepoRepository.findBySlug(gitRepoSlug)
            .flatMap(gitRepoModel -> conversionService.convert(gitRepoModel, GitRepo.class));
    }

    /**
     * Persists Git repository.
     *
     * @param gitRepo The Git repository to persist
     * @return The persisted Git repository
     */
    public GitRepo persist(final GitRepo gitRepo) {
        return conversionService.convert(gitRepo, GitRepoModel.class)
            .map(gitRepoModel -> gitRepoModel.getId() == null ? gitRepoRepository.save(gitRepoModel) : gitRepoRepository.update(gitRepoModel))
            .flatMap(gitRepoModel -> conversionService.convert(gitRepoModel, GitRepo.class))
            .orElse(null);
    }
}
