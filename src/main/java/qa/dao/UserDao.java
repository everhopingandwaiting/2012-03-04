package qa.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.QaUser;

import java.util.List;

/**
 * @author john
 */
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

    public List<QaUser> findAll() {
        return (List<QaUser>) template.find("FROM QaUser ");
    }

    public List<QaUser> findLikeXXX(QaUser user) {
        String sql = "SELECT * FROM USERS WHERE name LIKE ?1";


        return template.execute(session -> {
            return session.createSQLQuery(sql).addEntity(QaUser.class).setString("1", "%" + user.getName() +
                    "%").list();
        });
    }

    public QaUser findById(int id) {
        return (QaUser) template.find("FROM QaUser WHERE id=?",id).get(0);
    }

}



