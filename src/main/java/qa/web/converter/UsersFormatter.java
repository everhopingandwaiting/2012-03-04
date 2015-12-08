package qa.web.converter;

import org.springframework.format.Formatter;
import org.springframework.util.Assert;
import qa.domain.QaUser;
import qa.domain.Tag;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersFormatter implements Formatter<Set<QaUser>> {
    @Override
    public Set<QaUser> parse(String text, Locale locale) throws ParseException {
        Assert.hasLength(text);

        String[] names = text.split(";");
        return Arrays.stream(names)
                .distinct().map(QaUser::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String print(Set<QaUser> tags, Locale locale) {
        return tags.stream()
                .map(QaUser::getName).distinct()
                .collect(Collectors.joining(";"));
    }
}
