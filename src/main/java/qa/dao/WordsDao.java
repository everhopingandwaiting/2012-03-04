package qa.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Words;

import java.util.List;

@Transactional
public class WordsDao {
    private HibernateTemplate template;

    public WordsDao(HibernateTemplate template) {
        this.template = template;
    }

    @Transactional(readOnly = true)
    public Words find(int id) {
        return template.get(Words.class, id);
    }

    public Words update(Words words) {
        return template.merge(words);
    }

    public <T extends Words> T persist(T words) {
        return template.merge(words);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <T extends Words> List<T> findAll(Class<T> wordsClass) {
        return (List<T>) template.find("FROM " + wordsClass.getSimpleName());
    }
}
