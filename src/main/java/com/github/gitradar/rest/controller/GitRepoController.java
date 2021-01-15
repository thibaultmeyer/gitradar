package com.github.gitradar.rest.controller;

import com.github.gitradar.domain.entity.GitRepo;
import com.github.gitradar.domain.entity.Scan;
import com.github.gitradar.domain.entity.UnmergedCommit;
import com.github.gitradar.domain.service.GitRepoService;
import com.github.gitradar.domain.service.ScanService;
import com.github.gitradar.domain.service.UnmergedCommitService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller: Git repository.
 */
@Controller("/repo")
public class GitRepoController {

    private final GitRepoService gitRepoService;
    private final ScanService scanService;
    private final UnmergedCommitService unmergedCommitService;

    /**
     * Build a new instance.
     *
     * @param gitRepoService        Handle to the Git repository service
     * @param scanService           Handle to the Git repository scan service
     * @param unmergedCommitService Handle to the unmerged commit service
     */
    @Inject
    public GitRepoController(final GitRepoService gitRepoService,
                             final ScanService scanService,
                             final UnmergedCommitService unmergedCommitService) {
        this.gitRepoService = gitRepoService;
        this.scanService = scanService;
        this.unmergedCommitService = unmergedCommitService;
    }

    @Get("/")
    @Transactional
    public List<GitRepo> findAll() {
        return gitRepoService.findAllStream()
            .sorted(Comparator.comparing(GitRepo::getName))
            .collect(Collectors.toList());
    }

    @Get("/{gitRepoSlug}")
    public Optional<GitRepo> findBySlug(final String gitRepoSlug) {
        return gitRepoService.findBySlug(gitRepoSlug);
    }

    @Get("/{gitRepoSlug}/scan")
    public List<Scan> findScanByRepoSlug(final String gitRepoSlug) {
        return gitRepoService.findBySlug(gitRepoSlug)
            .map(gitRepo -> scanService.findAllByGitRepoId(gitRepo.getId()))
            .orElse(Collections.emptyList());
    }

    @Get("/{gitRepoSlug}/scan/{scandId}")
    public List<UnmergedCommit> findScanByRepoSlug(final String gitRepoSlug, final UUID scandId) {
        return gitRepoService.findBySlug(gitRepoSlug)
            .map(gitRepo -> unmergedCommitService.findAllByScanId(scandId))
            .orElse(Collections.emptyList());
    }
}
