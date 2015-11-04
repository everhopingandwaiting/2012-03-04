package qa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qa.domain.Answer;
import qa.domain.Words;
import qa.service.UserService;
import qa.service.WordsService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AnswerController {
    @Autowired
    private UserService userService;

    @Autowired
    private WordsService wordsService;

    @RequestMapping(value = "/answer/question/{id}", method = RequestMethod.POST)
    public String postAnswer(@PathVariable("id") int id,
                             @RequestParam("content") String content,
                             HttpServletRequest request) {
        Answer answer = new Answer();
        answer.setContent(content);
        Words question = wordsService.find(id);
        answer.setWhoCreated(userService.find(request.getRemoteUser()));
        answer.setQuestion(question);
        wordsService.addOne(answer);

        return "redirect:/question/" + id;
    }
}
