package qa.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import qa.domain.Question;

@Aspect
public class PointsService {
    //Spring AOP (Aspect Oriented Programming)
    @Pointcut("bean(questionService) && execution(* plusOneVote(..))")
    private void voteUp() {
        //Empty.
    }

    @AfterReturning(value = "voteUp() && target(service)", returning = "question")
    public void afterVoteUp(QuestionService service, Question question) {
        question.addPoint(10);
        service.updateInfo(question);
    }
}
