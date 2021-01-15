package com.github.gitradar.rest.controller;

import com.github.gitradar.domain.entity.GitRepo;
import com.github.gitradar.domain.service.GitRepoService;
import com.github.gitradar.domain.service.ScanService;
import com.github.gitradar.rest.dto.RepositoryInformationResponse;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Controller: Repository.
 */
@Controller("repository")
public final class RepositoryController {

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
    public RepositoryController(final ConversionService<?> conversionService,
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
    @Get("{gitRepoSlug}")
    @View("RepositoryInformation")
    public HttpResponse<RepositoryInformationResponse> displayRepositoryInformation(final String gitRepoSlug) throws URISyntaxException {
        final GitRepo gitRepo = gitRepoService.findBySlug(gitRepoSlug).orElse(null);
        if (gitRepo == null) {
            return HttpResponse.temporaryRedirect(new URI("/"));
        }

        final RepositoryInformationResponse repositoryInformationResponse = new RepositoryInformationResponse();
        repositoryInformationResponse.setRepositoryName(gitRepo.getName());

        return HttpResponse.ok(repositoryInformationResponse);
    }
}
