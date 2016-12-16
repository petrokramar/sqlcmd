package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;
import ua.com.juja.sqlcmd.model.UserRole;
import ua.com.juja.sqlcmd.service.LogService;

import java.util.HashSet;
import java.util.List;

@RestController
public class AppRestController {

    @Autowired
    LogService logService;

    //TODO add rest XML
    @RequestMapping(value = "/rest/actions", method = RequestMethod.GET)
    public List<UserAction> actions() {
        return logService.getUserActions();
    }

    @RequestMapping(value = "/rest/user/{name}", method = RequestMethod.GET)
    public User user(@PathVariable String name){
        User user = logService.getUser(name);
        return user;
    }


}
