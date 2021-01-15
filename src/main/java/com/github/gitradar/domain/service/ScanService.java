package com.github.gitradar.domain.service;

import com.github.gitradar.database.model.ScanModel;
import com.github.gitradar.database.repository.ScanRepository;
import com.github.gitradar.domain.entity.Scan;
import io.micronaut.core.convert.ConversionService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service: Git Repository Scan.
 */
@Singleton
public final class ScanService {

    private final ConversionService<?> conversionService;
    private final ScanRepository scanRepository;

    /**
     * Build a new instance.
     *
     * @param conversionService Handle to the conversion service
     * @param scanRepository    Handle to the scan repository
     */
    @Inject
    public ScanService(final ConversionService<?> conversionService,
                       final ScanRepository scanRepository) {
        this.conversionService = conversionService;
        this.scanRepository = scanRepository;
    }

    /**
     * Find all scans for a given Git repository.
     *
     * @param gitRepoId The Git repository unique ID
     * @return All match scans
     */
    public List<Scan> findAllByGitRepoId(final UUID gitRepoId) {
        if (gitRepoId == null) {
            return Collections.emptyList();
        }

        return scanRepository.findAllByGitRepoId(gitRepoId).stream()
            .map(gitRepoModel -> conversionService.convert(gitRepoModel, Scan.class).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * Find a Git repository scan from their unique ID.
     *
     * @param scanId The Git repository scan unique ID
     * @return The scan
     */
    public Optional<Scan> findById(final UUID scanId) {
        if (scanId == null) {
            return Optional.empty();
        }

        return scanRepository.findById(scanId)
            .flatMap(scanModel -> conversionService.convert(scanModel, Scan.class));
    }

    /**
     * Find the last Git repository scan for the given repository.
     *
     * @param gitRepoId The Git repository unique ID
     * @return The last scan
     */
    public Optional<Scan> findLastByRepoId(final UUID gitRepoId) {
        if (gitRepoId == null) {
            return Optional.empty();
        }

        return scanRepository.findFirstByGitRepoIdOrderByCreatedAtDesc(gitRepoId)
            .flatMap(scanModel -> conversionService.convert(scanModel, Scan.class));
    }

    /**
     * Persists Git repository scan.
     *
     * @param scan The Git repository scan to persist
     * @return The persisted Git repository scan
     */
    public Scan persist(final Scan scan) {
        return conversionService.convert(scan, ScanModel.class)
            .map(scanModel -> scanModel.getId() == null ? scanRepository.save(scanModel) : scanRepository.update(scanModel))
            .flatMap(scanModel -> conversionService.convert(scanModel, Scan.class))
            .orElse(null);
    }
}
