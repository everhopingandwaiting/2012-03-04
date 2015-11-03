package qa.web;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import qa.domain.QaUser;
import qa.domain.Question;
import qa.domain.Vote;
import qa.service.QuestionService;
import qa.service.UserService;
import qa.service.VoteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @ModelAttribute("questions")
    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public List<Question> topQuestions() {
        return questionService.findAll();
    }

    @ModelAttribute("question")
    @RequestMapping(value = "/question/ask", method = RequestMethod.GET)
    public Question showAskQuestionForm() {
        return new Question();
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public String addOneQuestion(@Valid @ModelAttribute("question") Question question,
                                 BindingResult result,
                                 HttpServletRequest request) {
        if (result.hasErrors()) {
            return "question/ask";
        }

        question.setWhenCreated(Instant.now());
        question.setWhoCreated(userService.find(request.getRemoteUser()));
        questionService.addOneQuestion(question);
        return "redirect:/questions";
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public String showQuestion(@PathVariable("id") int id, Model model, HttpSession session) {
        Question question = questionService.find(id);

        List<String> pages = (List<String>) session.getAttribute("visitedPages");
        if (pages == null) {
            pages = new ArrayList<>();
        }

        String currentUrl = "/question/" + id;
        if (pages.stream().filter(page -> page.endsWith(currentUrl)).count() == 0) {
            int viewCount = question.getViewCount();
            question.setViewCount(++viewCount);
            questionService.updateInfo(question);

            pages.add(currentUrl);
            session.setAttribute("visitedPages", pages);
        }

        model.addAttribute("question", question);
        return "question/show";
    }

    @RequestMapping(value = "/question/{id}/voteup", method = RequestMethod.POST)
    @ResponseBody
    public int voteup(@PathVariable("id") int id, HttpServletRequest request) {
        Question question = questionService.find(id);

        Vote vote = new Vote();
        vote.setWhoVoted(userService.find(request.getRemoteUser()));
        vote.setQuestion(question);
        vote.setUpVoted(true);

        return questionService.plusOneVote(question, vote).getVoteCount();
    }

    @RequestMapping(value = "/question/{id}/votedown", method = RequestMethod.POST)
    @ResponseBody
    public int votedown(@PathVariable("id") int id, HttpServletRequest request) {
        Question question = questionService.find(id);

        Vote vote = new Vote();
        vote.setWhoVoted(userService.find(request.getRemoteUser()));
        vote.setQuestion(question);
        vote.setUpVoted(false);

        return questionService.plusOneVote(question, vote).getVoteCount();
    }
}
