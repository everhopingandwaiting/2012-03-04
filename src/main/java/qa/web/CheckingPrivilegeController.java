package qa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import qa.domain.QaUser;
import qa.domain.Question;
import qa.service.PrivilegeCheckingResult;
import qa.service.PrivilegeCheckingService;
import qa.service.QuestionService;
import qa.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckingPrivilegeController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeCheckingService privilegeService;

    @RequestMapping(value = "voteup/question/{id}")
    public PrivilegeCheckingResult checkVoteup(@PathVariable("id") int id, HttpServletRequest request) {
        Question question = questionService.find(id);
        QaUser user = userService.find(request.getRemoteUser());

        return privilegeService.canVoteup(user, question);
    }
}
