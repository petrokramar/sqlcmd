package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.juja.sqlcmd.model.UserAction;
import ua.com.juja.sqlcmd.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserActionsController {
    @Autowired
    private LogService logService;

    //TODO add filters by user, period ...
    @RequestMapping(value = "/actions", method = RequestMethod.GET)
    public ModelAndView userActions() {
        List<UserAction> userActions = logService.getUserActions();
        ModelAndView model = new ModelAndView("userActions");
        model.addObject("actions", userActions);
//        logService.saveUserAction("get user actions");
        return model;
    }

    @RequestMapping(value = "/deleteactions", method = RequestMethod.GET)
    public ModelAndView deleteUserActions(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("deleteUserActions");
        return model;
    }

    @RequestMapping(value = "/deleteactions", method = RequestMethod.POST)
    public String deletingUserActions() {
        logService.deleteUserActions();
        return "redirect:menu";
    }
}
