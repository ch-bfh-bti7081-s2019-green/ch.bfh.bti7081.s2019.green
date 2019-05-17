package ch.bfh.bti7081.s2019.green.persistence.converters;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocalDateConverterTest {
    private LocalDateConverter converter = new LocalDateConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        LocalDate date = LocalDate.now();
        Date result = converter.convertToDatabaseColumn(date);
        assertThat(result, is(Date.valueOf(date)));
    }

    @Test
    public void testConvertToEntityAttribute() {
        Date date = Date.valueOf(LocalDate.now());
        LocalDate result = converter.convertToEntityAttribute(date);
        assertThat(result, is(date.toLocalDate()));
    }
}