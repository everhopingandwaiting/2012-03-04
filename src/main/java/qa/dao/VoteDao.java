package qa.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Vote;

@Transactional
public class VoteDao {
    private HibernateTemplate template;
    private MongoTemplate mongoTemplate;

    public VoteDao(HibernateTemplate template, MongoTemplate mongoTemplate) {
        this.template = template;
        this.mongoTemplate = mongoTemplate;
    }

    public VoteDao(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    public VoteDao(HibernateTemplate template) {
        this.template = template;
    }

    public Vote persist(Vote vote) {
        mongoTemplate.insert(vote);
        return template.merge(vote);
    }
}
