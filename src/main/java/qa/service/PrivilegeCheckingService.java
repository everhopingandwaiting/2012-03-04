package qa.service;

import qa.domain.QaUser;
import qa.domain.Question;
import qa.domain.Words;

public class PrivilegeCheckingService {
    private PrivilegeCheckingResult canVote(QaUser user, Words words) {
        if (user == null || words == null) {
            return PrivilegeCheckingResult.getNotPassedResult("user or question is null");
        }

        if (words.getWhoCreated().equals(user)) {
            return PrivilegeCheckingResult.getNotPassedResult("cannot vote a words of yours.");
        }

        if (words.getVotes().stream().anyMatch(vote -> vote.getWhoVoted().equals(user))) {
            return PrivilegeCheckingResult.getNotPassedResult("you cannot vote a words more than one times.");
        }

        return PrivilegeCheckingResult.getPassedResult();
    }

    public PrivilegeCheckingResult canVoteup(QaUser user, Words words) {
        return canVote(user, words);
    }

    public PrivilegeCheckingResult canVotedown(QaUser user, Words words) {
        PrivilegeCheckingResult result = canVote(user, words);
        if(!result.isPassed()) {
            return result;
        }

        if(user.getReputation() < 50) { //TODO hard code
            return PrivilegeCheckingResult.getNotPassedResult("you must have at least 50 reputation scores.");
        }

        return PrivilegeCheckingResult.getPassedResult();
    }
}
