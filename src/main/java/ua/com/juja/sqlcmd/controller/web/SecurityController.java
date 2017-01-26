package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.juja.sqlcmd.controller.validator.UserValidator;
import ua.com.juja.sqlcmd.model.Role;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserRole;
import ua.com.juja.sqlcmd.service.LogService;
import ua.com.juja.sqlcmd.service.SecurityService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SecurityController {
    @Autowired
    private LogService logService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
//        logService.saveUserAction("get login");
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginning() {
        return "redirect:menu";
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public String register() {
//    //TODO make all logs
////        logService.saveUserAction("get registration");
//        return "registration";
//    }

    //    Thank to https://hellokoding.com/registration-and-login-example-with-spring-xml-configuration-maven-jsp-and-mysql/
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        UserRole role = new UserRole();
        role.setRole(Role.ROLE_USER);
        role.setUser(user);
        List<String> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER.name());
        //TODO don't write automatically
        user.setEnabled(true);
        logService.saveUser(user);

        //TODO
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:menu";
    }
}
