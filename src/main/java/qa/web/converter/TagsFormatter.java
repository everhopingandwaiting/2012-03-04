package qa.web.converter;

import org.springframework.format.Formatter;
import org.springframework.util.Assert;
import qa.domain.Tag;
import qa.service.TagService;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class TagsFormatter implements Formatter<Set<Tag>> {
    @Override
    public Set<Tag> parse(String text, Locale locale) throws ParseException {
        Assert.hasLength(text);

        String[] names = text.split(";");
        return Arrays.stream(names)
                .distinct().map(Tag::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String print(Set<Tag> tags, Locale locale) {
        return tags.stream()
                .map(Tag::getName).distinct()
                .collect(Collectors.joining(";"));
    }
}
