package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.GitRepoModel;
import com.github.gitradar.domain.entity.GitRepo;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * Converts a Git repository domain into a model Git repository.
 */
@Singleton
public final class GitRepoDomainlToModelConverter implements TypeConverter<GitRepo, GitRepoModel> {

    @Override
    public Optional<GitRepoModel> convert(final GitRepo gitRepo, final Class<GitRepoModel> targetType) {
        return convert(gitRepo, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<GitRepoModel> convert(final GitRepo gitRepo, final Class<GitRepoModel> targetType, final ConversionContext context) {
        if (gitRepo == null) {
            return Optional.empty();
        }

        final GitRepoModel gitRepoModel = new GitRepoModel();
        gitRepoModel.setId(gitRepo.getId());
        gitRepoModel.setSlug(gitRepo.getSlug());
        gitRepoModel.setName(gitRepo.getName());
        gitRepoModel.setUpstream(gitRepo.getUpstream());
        gitRepoModel.setCloneUrl(gitRepo.getCloneUrl());
        gitRepoModel.setCreatedAt(gitRepo.getCreatedAt());
        gitRepoModel.setUpdatedAt(gitRepo.getUpdatedAt());

        return Optional.of(gitRepoModel);
    }
}
