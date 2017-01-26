package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.juja.sqlcmd.controller.form.UserForm;
import ua.com.juja.sqlcmd.controller.validator.UserValidator;
import ua.com.juja.sqlcmd.model.Role;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;
import ua.com.juja.sqlcmd.model.UserRole;
import ua.com.juja.sqlcmd.service.DatabaseService;
import ua.com.juja.sqlcmd.service.LogService;
import ua.com.juja.sqlcmd.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
//@ControllerAdvice
public class MainController {
    //TODO Replace all with ModelAndView
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
//        service.saveUserAction("get menu");
        return "home";
    }

//    @RequestMapping(value = "/menu", method = RequestMethod.GET)
//    public String menu() {
////        service.saveUserAction("get menu");
//        return "home";
//    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        logService.saveUserAction("get help");
        return "help";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;
    }

//    @RequestMapping("/db")
//    public String getProduct(Model model) {
//        model.addAttribute("databases", service.getDatabaseNames());
//        return "databases_t";
//    }

//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleCustomException(Exception e) {
//        ModelAndView model = new ModelAndView("error");
//        model.addObject("message", e.getMessage());
//        return model;
//    }

    //TODO not work
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView pageNotFound() {
        ModelAndView model = new ModelAndView();
        model.setViewName("404");
        return model;
    }
}