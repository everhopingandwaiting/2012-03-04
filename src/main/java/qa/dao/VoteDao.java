package qa.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Vote;

@Transactional
public class VoteDao {
    private HibernateTemplate template;

    public VoteDao(HibernateTemplate template) {
        this.template = template;
    }

    public Vote persist(Vote vote) {
        return template.merge(vote);
    }
}
