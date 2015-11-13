package qa.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.QaUser;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserDao {
    private HibernateTemplate template;
    private MongoTemplate mongoTemplate;

    public UserDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public UserDao(HibernateTemplate template) {
        this.template = template;
    }

    public UserDao(HibernateTemplate template, MongoTemplate mongoTemplate) {
        this.template = template;
        this.mongoTemplate = mongoTemplate;
    }

    @Transactional(readOnly = true)
    public QaUser findByName(String username) {
        List<QaUser> users = (List < QaUser >)template.find("FROM QaUser where name = ?", username);
        List<QaUser> usersMg = (List<QaUser>) mongoTemplate.find(new Query(Criteria.where("name").is(username)), QaUser.class);
        System.out.println(usersMg.toString()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return users.isEmpty() ? null : users.get(0);
    }

    public QaUser persist(QaUser user) {
        template.persist(user);
        return user;
    }
}
