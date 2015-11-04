package qa.web;

import org.junit.Before;
import org.junit.Test;
import qa.domain.Tag;
import qa.web.converter.TagsFormatter;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TagsFormatterTest {
    private TagsFormatter formatter = new TagsFormatter();

    @Test
    public void testParse() throws Exception {
        assertThat(formatter.parse("java", null), hasItems(new Tag("java")));
        assertThat(formatter.parse("java;unit test", null), hasItems(new Tag("java"), new Tag("unit test")));
    }
}
