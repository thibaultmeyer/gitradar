package com.github.gitradar.rest.dto;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO : Repository Information (Response).
 */
@Introspected
public final class RepositoryInformationResponse {

    private String repositoryName;

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(final String repositoryName) {
        this.repositoryName = repositoryName;
    }
}
