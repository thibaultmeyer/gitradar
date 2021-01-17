package com.github.gitradar.rest.controller;

import com.github.gitradar.domain.entity.GitRepo;
import com.github.gitradar.domain.entity.UnmergedCommit;
import com.github.gitradar.domain.service.GitRepoService;
import com.github.gitradar.domain.service.ScanService;
import com.github.gitradar.domain.service.UnmergedCommitService;
import com.github.gitradar.rest.dto.RepositoryInformationResponse;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller: Repository.
 */
@Controller("repository")
public final class RepositoryController {

    private final ConversionService<?> conversionService;
    private final GitRepoService gitRepoService;
    private final ScanService scanService;
    private final UnmergedCommitService unmergedCommitService;

    /**
     * Build a new instance.
     *
     * @param conversionService Handle to the conversion service
     * @param gitRepoService    Handle to the Git repository service
     * @param scanService       Handle to the Git repository scan service
     * @param scanService       Handle to the unmerged commit service
     */
    @Inject
    public RepositoryController(final ConversionService<?> conversionService,
                                final GitRepoService gitRepoService,
                                final ScanService scanService,
                                final UnmergedCommitService unmergedCommitService) {
        this.conversionService = conversionService;
        this.gitRepoService = gitRepoService;
        this.scanService = scanService;
        this.unmergedCommitService = unmergedCommitService;
    }

    /**
     * List all existing repositories.
     *
     * @return Data for the view
     */
    @Get("{gitRepoSlug}")
    @View("RepositoryInformation")
    public HttpResponse<RepositoryInformationResponse> displayRepositoryInformation(final String gitRepoSlug) throws URISyntaxException {
        final GitRepo gitRepo = gitRepoService.findBySlug(gitRepoSlug).orElse(null);
        if (gitRepo == null) {
            return HttpResponse.temporaryRedirect(new URI("/"));
        }

        final RepositoryInformationResponse repositoryInformationResponse = new RepositoryInformationResponse();
        repositoryInformationResponse.setRepositoryName(gitRepo.getName());

        scanService.findLastByRepoId(gitRepo.getId())
            .ifPresent(scan -> {
                final RepositoryInformationResponse.Scan lastScanResponse = new RepositoryInformationResponse.Scan();
                lastScanResponse.setId(scan.getId());
                lastScanResponse.setStatus(scan.getStatus());
                lastScanResponse.setCreatedAt(scan.getCreatedAt());

                final List<RepositoryInformationResponse.UnmergedCommit> unmergedCommitListResponse = unmergedCommitService.findAllByScanId(scan.getId()).stream()
                    .sorted(Comparator.comparing(UnmergedCommit::getBranch))
                    .map(unmergedCommit -> {
                        final RepositoryInformationResponse.UnmergedCommit unmergedCommitResponse = new RepositoryInformationResponse.UnmergedCommit();
                        unmergedCommitResponse.setCommitSha(unmergedCommit.getCommitSha().substring(0, 8));
                        unmergedCommitResponse.setBranchName(unmergedCommit.getBranch());
                        unmergedCommitResponse.setCommitSubject(unmergedCommit.getCommitSubject());

                        return unmergedCommitResponse;
                    })
                    .collect(Collectors.toList());

                lastScanResponse.setUnmergedCommitList(unmergedCommitListResponse);
                lastScanResponse.setUnmergedCommitCount(unmergedCommitListResponse.size());
                repositoryInformationResponse.setLastScan(lastScanResponse);
            });

        return HttpResponse.ok(repositoryInformationResponse);
    }
}
