package qa.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;
import qa.dao.WordsDao;
import qa.domain.Answer;
import qa.domain.Question;
import qa.domain.Vote;
import qa.domain.Words;

@Aspect
public class PointsService {
    private WordsDao wordsDao;
    private Environment environment;

    public PointsService(WordsDao wordsDao, Environment environment) {
        this.wordsDao = wordsDao;
        this.environment = environment;
    }

    @Pointcut("bean(voteService) && execution(* addOneVote(..))")
    private void voteUp() {
        //Empty.
    }

    @AfterReturning(value = "voteUp() && target(service)", returning = "vote")
    public void afterVoteUp(VoteService service, Vote vote) {
        Words words = vote.getWords();

        String property = "";
        if (words instanceof Question) {
            property = vote.isUpVoted() ? "points.add.answer.voteup"
                    : "points.minus.answer.votedown";
        }

        if (words instanceof Answer) {
            property = vote.isUpVoted() ? "points.add.answer.voteup"
                    : "points.minus.answer.votedown";
        }

        int point = environment.getProperty(property, Integer.class);

        words.addPoint(vote.isUpVoted() ? point : -point);

        wordsDao.update(words);
    }
}
