package qa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import qa.domain.QaUser;
import qa.domain.Question;
import qa.domain.Words;
import qa.service.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/check",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckingPrivilegeController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeCheckingService privilegeService;

    @Autowired
    private WordsService wordsService;

    @RequestMapping(value = "voteup/{question|answer}/{id}")
    public PrivilegeCheckingResult checkVoteup(@PathVariable("id") int id, HttpServletRequest request) {
        Words words = wordsService.find(id);
        QaUser user = userService.find(request.getRemoteUser());

        return privilegeService.canVoteup(user, words);
    }

    @RequestMapping(value = "votedown/{question|answer}/{id}")
    public PrivilegeCheckingResult checkVotedown(@PathVariable("id") int id, HttpServletRequest request) {
        Words words = wordsService.find(id);
        QaUser user = userService.find(request.getRemoteUser());

        return privilegeService.canVotedown(user, words);
    }
}
