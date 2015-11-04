package qa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.domain.Vote;
import qa.domain.Words;
import qa.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping(method = RequestMethod.POST)
public class VoteController {
    private static final String UP = "up";
    private static final String DOWN = "down";

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private WordsService wordsService;

    //vote/question/1/up(down)
    //vote/answer/1/up(down)
    @RequestMapping(value = "/vote/{question|answer}/{id}/{direction:up|down}")
    public int vote(@PathVariable("id") int id,
                    @PathVariable("direction") String direction,
                    HttpServletRequest request) {
        Words words = wordsService.find(id);

        Vote vote = new Vote();
        vote.setUpVoted(direction.equals(UP));
        vote.setWhoVoted(userService.find(request.getRemoteUser()));
        vote.setWords(words);
        voteService.addOneVote(vote);

        return wordsService.find(id).getVoteCount();
    }

}
