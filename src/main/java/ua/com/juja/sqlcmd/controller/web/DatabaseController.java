package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.juja.sqlcmd.service.DatabaseService;
import ua.com.juja.sqlcmd.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class DatabaseController {
    @Autowired
    private DatabaseService service;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect() {
        logService.saveUserAction("get connect");
        return "connect";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connecting(HttpServletRequest req) {
        String databaseName = req.getParameter("dbname");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        service.connect(databaseName, userName, password);
        logService.saveUserAction("connected. database name - " + databaseName + ". username - " + userName);
        return "redirect:databases";
    }

    @RequestMapping(value = "/databases", method = RequestMethod.GET)
    public String databases(Model model) {
        logService.saveUserAction("get databases");
        if (!service.isConnected()) {
            return "connect";
        }
        String currentDatabase = service.currentDatabase();
        Set<String> databases = service.getDatabaseNames();
        model.addAttribute("databases", databases);
        model.addAttribute("current", currentDatabase);
        logService.saveUserAction("get databases");
        return "databaseNames";
    }

    @RequestMapping(value = "/createdatabase", method = RequestMethod.GET)
    public String createDatabase() {
        logService.saveUserAction("get createdatabase");
        return "createDatabase";
    }

    @RequestMapping(value = "/createdatabase", method = RequestMethod.POST)
    public String creatingDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("database");
        service.createDatabase(databaseName);
        logService.saveUserAction("database " + databaseName + "created");
        return "redirect:databases";
    }

    @RequestMapping(value = "/dropdatabase", method = RequestMethod.GET)
    public String dropDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("name");
        req.setAttribute("database", databaseName);
        logService.saveUserAction("get dropdatabase " + databaseName);
        return "dropDatabase";
    }

    @RequestMapping(value = "/dropdatabase", method = RequestMethod.POST)
    public String droppingDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("database");
        service.dropDatabase(databaseName);
        logService.saveUserAction("database " + databaseName + "dropped");
        return "redirect:databases";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    // TODO Remember query before connect
    public String query(HttpServletRequest req) {
        if (!service.isConnected()) {
            return "connect";
        }
        logService.saveUserAction("get query");
        return "query";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String querying(HttpServletRequest req) {
        String query = req.getParameter("query");
        List<List<String>> queryData = service.executeQuery(query);
        req.setAttribute("query", query);
        req.setAttribute("querydata", queryData);
        logService.saveUserAction("query " + query + " executed");
        return "queryResult";
    }
}
