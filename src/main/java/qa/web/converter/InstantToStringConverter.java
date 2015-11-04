package qa.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;

public class InstantToStringConverter implements Converter<Instant, String> {
    @Override
    public String convert(Instant instant) {
        Instant now = Instant.now();
        Duration duration = Duration.between(instant, now);

//        LocalDate nowDate = LocalDate.now();
//        LocalDate instantDate = instant.to

        if(duration.minusMinutes(1).isNegative()) {
            return String.format("%s secs ago", duration.getSeconds());
        }

        if(duration.minusHours(1).isNegative()) {
            return String.format("%s mins ago", duration.toMinutes());
        }

        if(duration.minusDays(1).isNegative()) {
            return String.format("%s hrs ago", duration.toHours());
        }

//        if(duration.minus.isNegative()) {
//            return String.format("%s days ago", duration.getSeconds());
//        }

        //TODO show days ago and month ago....

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(Date.from(instant));
    }
}
