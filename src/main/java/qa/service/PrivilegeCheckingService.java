package qa.service;

import qa.domain.QaUser;
import qa.domain.Question;

public class PrivilegeCheckingService {
    private PrivilegeCheckingResult canVote(QaUser user, Question question) {
        if (user == null || question == null) {
            return PrivilegeCheckingResult.getNotPassedResult("user or question is null");
        }

        if (question.getWhoCreated().equals(user)) {
            return PrivilegeCheckingResult.getNotPassedResult("cannot voteup a question of yours.");
        }

        if (question.getVotes().stream().filter(vote -> vote.getWhoVoted().equals(user)).count() > 0) {
            return PrivilegeCheckingResult.getNotPassedResult("you cannot voteup a question more than one times.");
        }

        return PrivilegeCheckingResult.getPassedResult();
    }

    public PrivilegeCheckingResult canVoteup(QaUser user, Question question) {
        return canVote(user, question);
    }

    public PrivilegeCheckingResult canVotedown(QaUser user, Question question) {
        PrivilegeCheckingResult result = canVote(user, question);
        if(!result.isPassed()) {
            return result;
        }

        if(user.getReputation() < 50) { //TODO hard code
            return PrivilegeCheckingResult.getNotPassedResult("you must have at least 50 reputation scores.");
        }

        return PrivilegeCheckingResult.getPassedResult();
    }
}
