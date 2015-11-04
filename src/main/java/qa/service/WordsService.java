package qa.service;

import qa.dao.WordsDao;
import qa.domain.Answer;
import qa.domain.Question;
import qa.domain.Words;

import javax.persistence.Table;
import java.util.List;

public class WordsService {
    private WordsDao wordsDao;

    public WordsService(WordsDao wordsDao) {
        this.wordsDao = wordsDao;
    }

    public Words find(int id) {
        return wordsDao.find(id);
    }

    public <T extends Words> T find(int id, Class<T> words) {
        return (T) wordsDao.find(id);
    }

    public <T extends Words> T addOne(T words) {
        return wordsDao.persist(words);
    }

    public <T extends Words> List<T> findAll(Class<T> wordsClass) {
        return wordsDao.findAll(wordsClass);
    }
}
