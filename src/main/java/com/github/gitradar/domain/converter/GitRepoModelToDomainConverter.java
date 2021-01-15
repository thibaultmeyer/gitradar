package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.GitRepoModel;
import com.github.gitradar.domain.entity.GitRepo;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * Converts a Git repository model into a domain Git repository.
 */
@Singleton
public final class GitRepoModelToDomainConverter implements TypeConverter<GitRepoModel, GitRepo> {

    @Override
    public Optional<GitRepo> convert(final GitRepoModel gitRepoModel, final Class<GitRepo> targetType) {
        return convert(gitRepoModel, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<GitRepo> convert(final GitRepoModel gitRepoModel, final Class<GitRepo> targetType, final ConversionContext context) {
        if (gitRepoModel == null) {
            return Optional.empty();
        }

        final GitRepo gitRepo = new GitRepo(
            gitRepoModel.getId(),
            gitRepoModel.getSlug(),
            gitRepoModel.getName(),
            gitRepoModel.getUpstream(),
            gitRepoModel.getCloneUrl(),
            gitRepoModel.getCreatedAt(),
            gitRepoModel.getUpdatedAt()
        );

        return Optional.of(gitRepo);
    }
}
