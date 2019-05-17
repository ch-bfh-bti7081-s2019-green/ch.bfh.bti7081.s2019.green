package ch.bfh.bti7081.s2019.green.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalTime attribute) {
        return Timestamp.valueOf(attribute.atDate(LocalDate.now()));
    }

    @Override
    public LocalTime convertToEntityAttribute(Timestamp dbData) {
        return dbData.toLocalDateTime().toLocalTime();
    }
}
