package ch.bfh.bti7081.s2019.green.persistence.converters;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocalDateTimeConverterTest {
    private LocalDateTimeConverter converter = new LocalDateTimeConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        LocalDateTime date = LocalDateTime.now();
        Timestamp result = converter.convertToDatabaseColumn(date);
        assertThat(result, is(Timestamp.valueOf(date)));
    }

    @Test
    public void testConvertToEntityAttribute() {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime result = converter.convertToEntityAttribute(date);
        assertThat(result, is(date.toLocalDateTime()));
    }
}