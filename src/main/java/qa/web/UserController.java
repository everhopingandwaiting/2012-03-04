package qa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qa.domain.QaUser;
import qa.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

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
}
