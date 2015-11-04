package qa.web;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import qa.domain.*;
import qa.service.QuestionService;
import qa.service.UserService;
import qa.service.VoteService;
import qa.service.WordsService;

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

    @Autowired
    private WordsService wordsService;

    @ModelAttribute("emptyanswer")
    public Answer emptyAnswer() {
        return new Answer();
    }

    @ModelAttribute("questions")
    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public List<Question> topQuestions() {
        return wordsService.findAll(Question.class);
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
        wordsService.addOne(question);
        return "redirect:/questions";
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public String showQuestion(@PathVariable("id") int id, Model model, HttpSession session) {
        Question question = wordsService.find(id, Question.class);

        List<String> pages = (List<String>) session.getAttribute("visitedPages");
        if (pages == null) {
            pages = new ArrayList<>();
        }

        String currentUrl = "/question/" + id;
        if (pages.stream().anyMatch(page -> page.endsWith(currentUrl))) {
            questionService.plusOneView(question);

            pages.add(currentUrl);
            session.setAttribute("visitedPages", pages);
        }

        model.addAttribute("question", question);
        return "question/show";
    }
}
