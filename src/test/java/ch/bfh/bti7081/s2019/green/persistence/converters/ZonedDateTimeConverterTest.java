package ch.bfh.bti7081.s2019.green.persistence.converters;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ZonedDateTimeConverterTest {
    private ZonedDateTimeConverter converter = new ZonedDateTimeConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        ZonedDateTime date = ZonedDateTime.now();
        String result = converter.convertToDatabaseColumn(date);
        assertThat(result, is(date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
    }

    @Test
    public void testConvertToEntityAttribute() {
        String date = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime result = converter.convertToEntityAttribute(date);
        assertThat(result, is(ZonedDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
    }
}