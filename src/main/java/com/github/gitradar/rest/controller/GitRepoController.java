package com.github.gitradar.rest.controller;

import com.github.gitradar.domain.entity.GitCherryEntry;
import com.github.gitradar.domain.entity.GitRepo;
import com.github.gitradar.domain.service.GitRepoService;
import com.github.gitradar.domain.service.GitService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller: Git repository.
 */
@Controller("/repo")
public final class GitRepoController {

    private final GitRepoService gitRepoService;
    private final GitService gitService;

    /**
     * Build a new instance.
     *
     * @param gitRepoService handle to the Git repository service
     * @param gitService     handle to the Git service
     */
    @Inject
    public GitRepoController(final GitRepoService gitRepoService,
                             final GitService gitService) {
        this.gitRepoService = gitRepoService;
        this.gitService = gitService;
    }

    @Get("/")
    public List<GitRepo> findAll() {
        return gitRepoService.findAll();
    }

    @Get("/{gitRepoSlug}")
    public Optional<GitRepo> findBySlug(final String gitRepoSlug) {
        return gitRepoService.findBySlug(gitRepoSlug);
    }

    @Get("/cherry")
    public List<GitCherryEntry> cherry() {
        return gitService.cherry("C:\\Users\\thiba\\Documents\\Devel\\gitea", "master", "origin/release/v1.3").stream()
            .filter(gitCherryEntry -> !gitCherryEntry.haveEquivalentInUpstream)
            .collect(Collectors.toList());
    }
}
