package qa.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.QaUser;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserDao {
    private HibernateTemplate template;

    public UserDao(HibernateTemplate template) {
        this.template = template;
    }

    @Transactional(readOnly = true)
    public QaUser findByName(String username) {
        List<QaUser> users = (List < QaUser >)template.find("FROM QaUser where name = ?", username);
        return users.isEmpty() ? null : users.get(0);
    }

    public QaUser persist(QaUser user) {
        template.persist(user);
        return user;
    }
}
