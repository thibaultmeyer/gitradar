package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.GitRepoModel;
import com.github.gitradar.database.model.ScanModel;
import com.github.gitradar.domain.entity.Scan;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * Converts a Git repository scan domain into a model Git repository scan.
 */
@Singleton
public final class ScanDomainToModelConverter implements TypeConverter<Scan, ScanModel> {

    @Override
    public Optional<ScanModel> convert(final Scan scan, final Class<ScanModel> targetType) {
        return convert(scan, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<ScanModel> convert(final Scan scan, final Class<ScanModel> targetType, final ConversionContext context) {
        if (scan == null) {
            return Optional.empty();
        }

        final GitRepoModel gitRepo;
        if (scan.getGitRepoId() == null) {
            gitRepo = null;
        } else {
            gitRepo = new GitRepoModel();
            gitRepo.setId(scan.getGitRepoId());
        }

        final ScanModel scanModel = new ScanModel();
        scanModel.setId(scan.getId());
        scanModel.setGitRepo(gitRepo);
        scanModel.setStatus(scan.getStatus());
        scanModel.setUnmergedCommitCount(scan.getUnmergedCommitCount());
        scanModel.setCreatedAt(scan.getCreatedAt());
        scanModel.setUpdatedAt(scan.getUpdatedAt());

        return Optional.of(scanModel);
    }
}
