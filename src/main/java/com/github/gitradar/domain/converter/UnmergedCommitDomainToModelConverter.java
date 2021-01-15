package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.ScanModel;
import com.github.gitradar.database.model.UnmergedCommitModel;
import com.github.gitradar.domain.entity.UnmergedCommit;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * Converts an unmerged commit domain into a model unmerged commit.
 */
@Singleton
public final class UnmergedCommitDomainToModelConverter implements TypeConverter<UnmergedCommit, UnmergedCommitModel> {

    @Override
    public Optional<UnmergedCommitModel> convert(final UnmergedCommit unmergedCommit, final Class<UnmergedCommitModel> targetType) {
        return convert(unmergedCommit, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<UnmergedCommitModel> convert(final UnmergedCommit unmergedCommit,
                                                 final Class<UnmergedCommitModel> targetType,
                                                 final ConversionContext context) {
        if (unmergedCommit == null) {
            return Optional.empty();
        }

        final ScanModel scanModel;
        if (unmergedCommit.getScanId() == null) {
            scanModel = null;
        } else {
            scanModel = new ScanModel();
            scanModel.setId(unmergedCommit.getScanId());
        }

        final UnmergedCommitModel unmergedCommitModel = new UnmergedCommitModel();
        unmergedCommitModel.setId(unmergedCommit.getId());
        unmergedCommitModel.setScan(scanModel);
        unmergedCommitModel.setBranch(unmergedCommit.getBranch());
        unmergedCommitModel.setCommitSha(unmergedCommit.getCommitSha());
        unmergedCommitModel.setCommitSubject(unmergedCommit.getCommitSubject());
        unmergedCommitModel.setCreatedAt(unmergedCommit.getCreatedAt());
        unmergedCommitModel.setUpdatedAt(unmergedCommit.getUpdatedAt());

        return Optional.of(unmergedCommitModel);
    }
}
