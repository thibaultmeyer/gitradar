package com.github.gitradar.domain.service;

import com.github.gitradar.database.model.UnmergedCommitModel;
import com.github.gitradar.database.repository.UnmergedCommitRepository;
import com.github.gitradar.domain.entity.UnmergedCommit;
import io.micronaut.core.convert.ConversionService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service: Unmerged commit.
 */
@Singleton
public final class UnmergedCommitService {

    private final ConversionService<?> conversionService;
    private final UnmergedCommitRepository unmergedCommitRepository;

    /**
     * Build a new instance.
     *
     * @param conversionService        Handle to the conversion service
     * @param unmergedCommitRepository Handle to the unmerged commit repository
     */
    @Inject
    public UnmergedCommitService(final ConversionService<?> conversionService,
                                 final UnmergedCommitRepository unmergedCommitRepository) {
        this.conversionService = conversionService;
        this.unmergedCommitRepository = unmergedCommitRepository;
    }

    /**
     * Find all unmerged commits for a given scan.
     *
     * @param scanId The scan unique ID
     * @return All match unmerged commits
     */
    public List<UnmergedCommit> findAllByScanId(final UUID scanId) {
        if (scanId == null) {
            return Collections.emptyList();
        }

        return unmergedCommitRepository.findAllByScanId(scanId).stream()
            .map(unmergedCommitModel -> conversionService.convert(unmergedCommitModel, UnmergedCommit.class).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * Find an unmerged commit from their unique ID.
     *
     * @param unmergedCommitId The unmerged commit unique ID
     * @return The unmerged commit
     */
    public Optional<UnmergedCommit> findById(final UUID unmergedCommitId) {
        if (unmergedCommitId == null) {
            return Optional.empty();
        }

        return unmergedCommitRepository.findById(unmergedCommitId)
            .flatMap(userModel -> conversionService.convert(userModel, UnmergedCommit.class));
    }

    /**
     * Persists unmerged commit.
     *
     * @param unmergedCommit The unmerged commit to persist
     * @return The persisted unmerged commit
     */
    public UnmergedCommit persist(final UnmergedCommit unmergedCommit) {
        return conversionService.convert(unmergedCommit, UnmergedCommitModel.class)
            .map(unmergedCommitModel -> unmergedCommitModel.getId() == null
                ? unmergedCommitRepository.save(unmergedCommitModel) : unmergedCommitRepository.update(unmergedCommitModel))
            .flatMap(unmergedCommitModel -> conversionService.convert(unmergedCommitModel, UnmergedCommit.class))
            .orElse(null);
    }

    /**
     * Persists unmerged commit.
     *
     * @param unmergedCommit The unmerged commit to persist
     * @return The persisted unmerged commit
     */
    public List<UnmergedCommit> persistAll(final List<UnmergedCommit> unmergedCommitList) {
        List<UnmergedCommitModel> unmergedCommitModelList = unmergedCommitList.stream()
            .map(unmergedCommit -> conversionService.convert(unmergedCommit, UnmergedCommitModel.class).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        unmergedCommitModelList = unmergedCommitRepository.saveAll(unmergedCommitModelList);

        return unmergedCommitModelList.stream()
            .map(unmergedCommit -> conversionService.convert(unmergedCommit, UnmergedCommit.class).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
