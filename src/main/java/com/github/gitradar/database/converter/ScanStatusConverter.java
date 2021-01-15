package com.github.gitradar.database.converter;

import com.github.gitradar.domain.enumeration.ScanStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ScanStatusConverter implements AttributeConverter<ScanStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final ScanStatus attribute) {
        return attribute == null
            ? null
            : attribute.getDatabaseValue();
    }

    @Override
    public ScanStatus convertToEntityAttribute(final Integer dbData) {
        return dbData == null
            ? null
            : ScanStatus.fromDatabaseValue(dbData);
    }
}
