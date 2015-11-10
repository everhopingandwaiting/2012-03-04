package qa.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {
    @Override
    public String convert(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        Period period = Period.between(dateTime.toLocalDate(), now.toLocalDate());

        if (duration.toMinutes() < 1) {
            return String.format("%s secs ago", duration.getSeconds());
        }

        if (duration.toHours() < 1) {
            return String.format("%s mins ago", duration.toMinutes());
        }

        if (duration.toDays() < 1) {
            return String.format("%s hrs ago", duration.toHours());
        }

        if (period.getYears() > 0) {
            return DateTimeFormatter.ISO_LOCAL_DATE.format(dateTime);
        }

        if (period.getMonths() > 0) {
            return String.format("%s months ago", period.getMonths());
        }

        return String.format("%s days ago", period.getDays());
    }
}
