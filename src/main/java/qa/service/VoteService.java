package qa.service;

import qa.dao.VoteDao;
import qa.domain.Vote;

public class VoteService {
    private VoteDao voteDao;

    public VoteService(VoteDao voteDao) {
        this.voteDao = voteDao;
    }

    public Vote addOneVote(Vote vote) {
        return voteDao.persist(vote);
    }
}
