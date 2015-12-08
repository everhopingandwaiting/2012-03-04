package qa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import qa.domain.QaUser;
import qa.domain.Question;
import qa.service.QuestionService;
import qa.service.UserService;
import qa.service.WordsService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WordsService wordsService;
    @ModelAttribute("user")
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public QaUser showLoginForm() {
        return new QaUser();
    }

    @ModelAttribute("user")
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public QaUser showRegisterForm() {
        return new QaUser();
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") QaUser user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/register";
        }
        userService.addOneUser(user);
        return "redirect:/questions";
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/questions";
    }

    // users   form
//    @RequestMapping(value = "/users",method = RequestMethod.GET)
//    public List<QaUser>
    @ModelAttribute("userlist")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<QaUser> showUsersFoem(Model model) {
/*
        List<QaUser> userList = userService.findAll();
        List<List<QaUser>> lists = new ArrayList<>();
         QaUser users[]=  (QaUser[])userList.stream().toArray();
        for (int i = 0; i < userList.size(); i++) {


        }
*/

        model.addAttribute("userlist", userService.findAll());
//        return "user/userlists";
        return userService.findAll();
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String showSearchForm(@ModelAttribute("tmpuser") QaUser user, Model model) {


        model.addAttribute("userlists", userService.findLikeXXX(user));
        return "user/userlists";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView showThatUserAllQuestion(@PathVariable("id") int id,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        QaUser user = userService.find(id);

        List<Question> questions = wordsService.findByName(Question.class, user.getName());
        model.addAttribute("questions", questions);
        modelAndView.setViewName("questions");

        return modelAndView;
    }
}
