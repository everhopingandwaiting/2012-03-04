package qa.service;

import qa.domain.QaUser;
import qa.domain.Question;

public class PrivilegeCheckingService {
    public PrivilegeCheckingResult canVoteup(QaUser user, Question question) {
        if (user == null || question == null) {
            return PrivilegeCheckingResult.getNotPassedResult("user or question is null");
        }

        if(question.getWhoCreated().equals(user)) {
            return PrivilegeCheckingResult.getNotPassedResult("cannot voteup a question of yours.");
        }

        //TODO one user cannot voteup a question more than one times.

        return PrivilegeCheckingResult.getPassedResult();
    }
}
