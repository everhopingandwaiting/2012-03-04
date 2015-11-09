package qa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.domain.QaUser;
import qa.domain.Words;
import qa.service.PrivilegeCheckingResult;
import qa.service.PrivilegeCheckingService;
import qa.service.UserService;
import qa.service.WordsService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckingPrivilegeController {
    private static final String UP = "up";
    private static final String DOWN = "down";

    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeCheckingService privilegeService;

    @Autowired
    private WordsService wordsService;

    @RequestMapping(value = "/check/vote/{question|answer}/{id}/{direction:up|down}")
    public PrivilegeCheckingResult checkVote(@PathVariable("id") int id,
                                             @PathVariable("direction") String direction,
                                             HttpServletRequest request) {
        Words words = wordsService.find(id);
        QaUser user = userService.find(request.getRemoteUser());

        return direction.equals(UP) ?
                privilegeService.canVoteup(user, words) :
                privilegeService.canVotedown(user, words);
    }

}
