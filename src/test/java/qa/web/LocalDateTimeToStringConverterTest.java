package qa.web;

import org.junit.Before;
import org.junit.Test;
import qa.web.converter.LocalDateTimeToStringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class LocalDateTimeToStringConverterTest {
    private LocalDateTime now;
    private LocalDateTimeToStringConverter converter;

    @Before
    public void setUp() {
        now = LocalDateTime.now();
        converter = new LocalDateTimeToStringConverter();
    }

    @Test
    public void testSecsAgoDateTime() {
        LocalDateTime dateTime = now.minusSeconds(30);
        assertThat(converter.convert(dateTime), equalTo("30 secs ago"));
    }

    @Test
    public void testMinAgoDateTime() {
        LocalDateTime dateTime = now.minusMinutes(2);
        assertThat(converter.convert(dateTime), equalTo("2 mins ago"));
    }

    @Test
    public void testHoursAgoDateTime() {
        LocalDateTime dateTime = now.minusHours(2);
        assertThat(converter.convert(dateTime), equalTo("2 hrs ago"));
    }

    @Test
    public void testDaysAgoDateTime() {
        LocalDateTime dateTime = now.minusDays(2);
        assertThat(converter.convert(dateTime), equalTo("2 days ago"));
    }

    @Test
    public void testMonthsAgoDateTime() {
        LocalDateTime dateTime = now.minusMonths(2);
        assertThat(converter.convert(dateTime), equalTo("2 months ago"));
    }

    @Test
    public void testYearAgoDateTime() {
        LocalDateTime dateTime = now.minusYears(2);
        String expected = DateTimeFormatter.ISO_LOCAL_DATE.format(dateTime);
        assertThat(converter.convert(dateTime), equalTo(expected));
    }
}
