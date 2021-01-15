package com.github.gitradar.domain.converter;

import com.github.gitradar.database.model.ScanModel;
import com.github.gitradar.domain.entity.Scan;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * Converts a Git repository scan model into a domain Git repository scan.
 */
@Singleton
public final class ScanModelToDomainConverter implements TypeConverter<ScanModel, Scan> {

    @Override
    public Optional<Scan> convert(final ScanModel scanModel, final Class<Scan> targetType) {
        return convert(scanModel, targetType, ConversionContext.DEFAULT);
    }

    @Override
    public Optional<Scan> convert(final ScanModel scanModel, final Class<Scan> targetType, final ConversionContext context) {
        if (scanModel == null) {
            return Optional.empty();
        }

        final Scan scan = new Scan(
            scanModel.getId(),
            scanModel.getGitRepo() == null ? null : scanModel.getGitRepo().getId(),
            scanModel.getStatus(),
            scanModel.getUnmergedCommitCount(),
            scanModel.getCreatedAt(),
            scanModel.getUpdatedAt()
        );

        return Optional.of(scan);
    }
}
