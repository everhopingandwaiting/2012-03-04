package qa.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import qa.domain.Tag;

import java.util.List;

public class TagDao {
    private HibernateTemplate template;

    public TagDao(HibernateTemplate template) {
        this.template = template;
    }

    @SuppressWarnings("unchecked")
    public Tag find(String name) {
        List<Tag> tags = (List<Tag>) template.find("FROM Tag WHERE name = ?", name);
        return tags.isEmpty() ? null : tags.get(0);
    }
}
