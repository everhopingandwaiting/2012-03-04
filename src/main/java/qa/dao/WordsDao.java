package qa.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Words;

import java.util.List;

@Transactional
public class WordsDao {
    private HibernateTemplate template;
    private MongoTemplate mongoTemplate;

    public WordsDao(HibernateTemplate template, MongoTemplate mongoTemplate) {
        this.template = template;
        this.mongoTemplate = mongoTemplate;
    }

    public WordsDao(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    public WordsDao(HibernateTemplate template) {
        this.template = template;
    }

    @Transactional(readOnly = true)
    public Words find(int id) {
        System.out.println(mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),Words.class)+"************************");
        return template.get(Words.class, id);
    }

    public Words update(Words words) {
        mongoTemplate.save(words);
        return template.merge(words);
    }

    public <T extends Words> T persist(T words) {
        return template.merge(words);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <T extends Words> List<T> findAll(Class<T> wordsClass) {
//         mongoTemplate.findAll(wordsClass.getSimpleName().getClass());
        return (List<T>) template.find("FROM " + wordsClass.getSimpleName());
    }
}
