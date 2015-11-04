package qa.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import qa.dao.WordsDao;
import qa.domain.Question;
import qa.domain.Vote;
import qa.domain.Words;

@Aspect
public class PointsService {
    private WordsDao wordsDao;

    public PointsService(WordsDao wordsDao) {
        this.wordsDao = wordsDao;
    }

    @Pointcut("bean(voteService) && execution(* addOneVote(..))")
    private void voteUp() {
        //Empty.
    }

    @AfterReturning(value = "voteUp() && target(service)", returning = "vote")
    public void afterVoteUp(VoteService service, Vote vote) {
        Words words = vote.getWords();
        if (vote.isUpVoted()) {
            words.addPoint(10); //TODO hard code
        } else {
            words.minusPoint(10);
        }
        wordsDao.update(words);
    }
}
