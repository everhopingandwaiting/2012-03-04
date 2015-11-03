package qa.service;

import qa.dao.QuestionDao;
import qa.domain.Question;

import java.util.List;

public class QuestionService {
    private QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
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

    public Question plusOneVote(Question question) {
        int voteCount = question.getVoteCount();
        question.setVoteCount(++voteCount);
        question = updateInfo(question);
        return question;
    }

    public Question updateInfo(Question question) {
        return questionDao.update(question);
    }
}
