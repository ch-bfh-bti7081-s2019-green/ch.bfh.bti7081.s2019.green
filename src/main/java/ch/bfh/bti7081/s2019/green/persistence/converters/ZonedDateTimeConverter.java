package ch.bfh.bti7081.s2019.green.persistence.converters;

import com.vaadin.flow.server.VaadinSession;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        return Timestamp.valueOf(attribute.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData.toLocalDateTime().atOffset(ZoneOffset.of(VaadinSession.getCurrent().getBrowser().getTimeZoneId())).toZonedDateTime();
    }
}
