package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.UnmergedCommitModel;
import com.github.gitradar.domain.entity.UnmergedCommit;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

/**
 * Converts an unmerged commit model into a domain unmerged commit.
 */
@Singleton
public final class UnmergedCommitModelToDomainConverter implements TypeConverter<UnmergedCommitModel, UnmergedCommit> {

    private final ConversionService<?> conversionService;

    @Inject
    public UnmergedCommitModelToDomainConverter(final ConversionService<?> conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Optional<UnmergedCommit> convert(final UnmergedCommitModel unmergedCommitModel, final Class<UnmergedCommit> targetType) {
        return convert(unmergedCommitModel, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<UnmergedCommit> convert(final UnmergedCommitModel unmergedCommitModel,
                                            final Class<UnmergedCommit> targetType,
                                            final ConversionContext context) {
        if (unmergedCommitModel == null) {
            return Optional.empty();
        }

        final UnmergedCommit unmergedCommit = new UnmergedCommit(
            unmergedCommitModel.getId(),
            unmergedCommitModel.getScan().getId(),
            unmergedCommitModel.getBranch(),
            unmergedCommitModel.getCommitSha(),
            unmergedCommitModel.getCommitSubject(),
            unmergedCommitModel.getCreatedAt(),
            unmergedCommitModel.getUpdatedAt()
        );

        return Optional.of(unmergedCommit);
    }
}
