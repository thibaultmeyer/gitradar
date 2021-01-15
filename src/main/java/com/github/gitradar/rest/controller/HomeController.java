package com.github.gitradar.rest.controller;

import com.github.gitradar.domain.entity.GitRepo;
import com.github.gitradar.domain.service.GitRepoService;
import com.github.gitradar.domain.service.ScanService;
import com.github.gitradar.rest.dto.HomeResponse;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller: Home.
 */
@Controller
public final class HomeController {

    private final ConversionService<?> conversionService;
    private final GitRepoService gitRepoService;
    private final ScanService scanService;

    /**
     * Build a new instance.
     *
     * @param conversionService Handle to the conversion service
     * @param gitRepoService    Handle to the Git repository service
     * @param scanService       Handle to the Git repository scan service
     */
    @Inject
    public HomeController(final ConversionService<?> conversionService,
                          final GitRepoService gitRepoService,
                          final ScanService scanService) {
        this.conversionService = conversionService;
        this.gitRepoService = gitRepoService;
        this.scanService = scanService;
    }

    /**
     * List all existing repositories.
     *
     * @return Data for the view
     */
    @Get
    @View("Home")
    public HomeResponse listAllRepositories() {
        final List<HomeResponse.Repository> repositoryList = gitRepoService.findAllStream()
            .sorted(Comparator.comparing(GitRepo::getName))
            .map(gitRepo -> {
                final HomeResponse.Repository repositoryResponse = new HomeResponse.Repository();
                repositoryResponse.setName(gitRepo.getName());
                repositoryResponse.setSlug(gitRepo.getSlug());

                scanService.findLastByRepoId(gitRepo.getId())
                    .ifPresent(scan -> {
                        repositoryResponse.setLastScan(scan.getCreatedAt());
                        repositoryResponse.setLastScanStatus(scan.getStatus().name());
                        repositoryResponse.setLastScanUnmergedCommit(scan.getUnmergedCommitCount());
                    });

                return repositoryResponse;
            })
            .collect(Collectors.toList());


        final HomeResponse homeResponse = new HomeResponse();
        homeResponse.setRepositoryList(repositoryList);

        return homeResponse;
    }
}
