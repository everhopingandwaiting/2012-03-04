package qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import qa.dao.QuestionDao;
import qa.domain.Question;
import qa.domain.Vote;

import java.util.List;

public class QuestionService {
    private QuestionDao questionDao;
    private VoteService voteService;

    public QuestionService(QuestionDao questionDao, VoteService voteService) {
        this.questionDao = questionDao;
        this.voteService = voteService;
    }

    public List<Question> findAll() {
        return questionDao.findAll();
    }

    public Question addOneQuestion(Question question) {
        return questionDao.persist(question);
    }

    public Question find(int id) {
        return questionDao.find(id);
    }

    public Question plusOneVote(Question question, Vote vote) {
        question.addVote(voteService.addOneVote(vote));
        question = updateInfo(question);
        return question;
    }

    public Question updateInfo(Question question) {
        return questionDao.update(question);
    }
}
