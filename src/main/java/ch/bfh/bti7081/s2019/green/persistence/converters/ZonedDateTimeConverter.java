package ch.bfh.bti7081.s2019.green.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, String> {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public String convertToDatabaseColumn(ZonedDateTime attribute) {
        return attribute.format(FORMATTER);
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(String dbData) {
        return ZonedDateTime.parse(dbData, FORMATTER);
    }
}
