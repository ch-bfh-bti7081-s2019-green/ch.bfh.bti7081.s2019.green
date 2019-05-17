package ch.bfh.bti7081.s2019.green.persistence.converters;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocalTimeConverterTest {
    private LocalTimeConverter converter = new LocalTimeConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        LocalTime time = LocalTime.now();
        Timestamp result = converter.convertToDatabaseColumn(time);
        assertThat(result, is(Timestamp.valueOf(time.atDate(LocalDate.now()))));
    }

    @Test
    public void testConvertToEntityAttribute() {
        Timestamp time = Timestamp.valueOf(LocalTime.now().atDate(LocalDate.now()));
        LocalTime result = converter.convertToEntityAttribute(time);
        assertThat(result, is(time.toLocalDateTime().toLocalTime()));
    }
}