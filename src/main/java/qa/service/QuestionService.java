package qa.service;

import qa.dao.WordsDao;
import qa.domain.Question;
import qa.domain.Words;

public class QuestionService {
    private WordsDao wordsDao;

    public QuestionService(WordsDao wordsDao) {
        this.wordsDao = wordsDao;
    }

    public Words plusOneView(Question question) {
        int viewCount = question.getViewCount();
        question.setViewCount(++viewCount);
        return wordsDao.update(question);
    }
}
