package qa.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import qa.domain.Tag;

import java.util.List;

public class TagDao {
    private HibernateTemplate template;
    private MongoTemplate mongoTemplate;

    public TagDao(HibernateTemplate template, MongoTemplate mongoTemplate) {
        this.template = template;
        this.mongoTemplate = mongoTemplate;
    }

    public TagDao(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    public TagDao(HibernateTemplate template) {
        this.template = template;
    }

    @SuppressWarnings("unchecked")
    public Tag find(String name) {
        List<Tag> tags = (List<Tag>) template.find("FROM Tag WHERE name = ?", name);
        List<Tag> tagsMg = (List<Tag>) mongoTemplate.find(new Query(Criteria.where("name").is(name)),Tag.class);
        System.out.println(tagsMg.toString()+"((((((((((((((((((((((((((((((((((((((");
        return tags.isEmpty() ? null : tags.get(0);
    }
}
