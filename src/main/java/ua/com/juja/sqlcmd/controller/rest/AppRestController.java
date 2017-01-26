package ua.com.juja.sqlcmd.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;
import ua.com.juja.sqlcmd.model.UserRole;
import ua.com.juja.sqlcmd.service.DatabaseService;
import ua.com.juja.sqlcmd.service.LogService;
import ua.com.juja.sqlcmd.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AppRestController {
    @Autowired
    LogService logService;

    @Autowired
    UserService userService;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "/rest/databases", method = RequestMethod.GET)
    public Set<String> databases() {
        return databaseService.getDatabaseNames();
    }

    @RequestMapping(value = "/rest/current_database", method = RequestMethod.GET)
    public String currentDatabase() {
        return databaseService.currentDatabase();
    }
    @RequestMapping(value = "/rest/tables", method = RequestMethod.GET)
    public Set<String> tables(){
        return databaseService.getTableNames();
    }

    @RequestMapping(value = "/rest/table/{name}", method = RequestMethod.GET)
    public List<List<String>> table(@PathVariable String name){
        return databaseService.getTableData(name);
    }

    //TODO add rest XML
    @RequestMapping(value = "/rest/actions", method = RequestMethod.GET)
    public List<UserAction> actions() {
        return logService.getUserActions();
    }

    @RequestMapping(value = "/rest/user/{name}", method = RequestMethod.GET)
    public User user(@PathVariable String name){
        User user = userService.getUser(name);
        return user;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleCustomException(Exception e) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("message", e.getMessage());
        return model;
    }
}
