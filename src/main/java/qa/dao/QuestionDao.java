package qa.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Question;

import java.util.List;

@Transactional
public class QuestionDao {
    private HibernateTemplate template;

    public QuestionDao(HibernateTemplate template) {
        this.template = template;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Question> findAll() {
        return (List<Question>)template.find("FROM Question");
    }

    public Question persist(Question question) {
        template.saveOrUpdate(question); //Java Persistence API
        return question;
    }

    public Question find(int id) {
        return template.get(Question.class, id);
    }

    public Question update(Question question) {
        template.update(question);
        return question;
    }
}
