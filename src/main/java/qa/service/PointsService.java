package qa.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import qa.domain.Question;
import qa.domain.Vote;

@Aspect
public class PointsService {
    //Spring AOP (Aspect Oriented Programming)
    @Pointcut("bean(questionService) && execution(* plusOneVote(..))")
    private void voteUp() {
        //Empty.
    }

    @AfterReturning(value = "voteUp() && target(service) && args(*, vote)", returning = "question")
    public void afterVoteUp(QuestionService service, Question question, Vote vote) {
        if (vote.isUpVoted()) {
            question.addPoint(10); //TODO hard code
        } else {
            question.minusPoint(10);
        }
        service.updateInfo(question);
    }
}
