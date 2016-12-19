package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;
import ua.com.juja.sqlcmd.service.DatabaseService;
import ua.com.juja.sqlcmd.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    //TODO Catch exceptions
    //TODO Replace all with ModelAndView
    @Autowired
    private DatabaseService service;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
//        service.saveUserAction("get menu");
        return "menu";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
//        service.saveUserAction("get menu");
        return "menu";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logService.saveUserAction("get login");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginning() {
        return "redirect:menu";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register() {
        logService.saveUserAction("get registration");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration() {
        return "redirect:menu";
    }

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

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        logService.saveUserAction("get help");
        return "help";
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

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String tables(HttpServletRequest req) {
        Set<String> tables = service.getTableNames();
        req.setAttribute("tables", tables);
        logService.saveUserAction("get tables");
        return "tableNames";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.GET)
    public String createTable() {
        logService.saveUserAction("get create table");
        return "createTable";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    public String creatingTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        String query = req.getParameter("query");
        service.createTable(tableName, query);
        logService.saveUserAction("table created " + tableName + " " + query);
        return "redirect:tables";
    }

    @RequestMapping(value = "/cleartable", method = RequestMethod.GET)
    public String clearTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        req.setAttribute("table", tableName);
        logService.saveUserAction("clear table " + tableName);
        return "clearTable";
    }

    @RequestMapping(value = "/cleartable", method = RequestMethod.POST)
    public String clearing(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        service.clearTable(tableName);
        logService.saveUserAction("table " + tableName + " cleared");
        return "redirect:tables";
    }

    @RequestMapping(value = "/droptable", method = RequestMethod.GET)
    public String dropTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        req.setAttribute("table", tableName);
        logService.saveUserAction("drop table " + tableName);
        return "dropTable";
    }

    @RequestMapping(value = "/droptable", method = RequestMethod.POST)
    public String droppingTable(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        service.dropTable(tableName);
        logService.saveUserAction("table " + tableName + " dropped");
        return "redirect:tables";
    }

    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public String table(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        Set<String> columns = service.getTableColumns(tableName);
        int idIndex = -1;
        int index = 0;
        for (String column : columns) {
            if ("id".equals(column)) {
                idIndex = index;
                break;
            }
            index++;
        }
        List<List<String>> tableData = service.getTableData(tableName);
        req.setAttribute("table", tableName);
        req.setAttribute("columns", columns);
        req.setAttribute("idindex", idIndex);
        req.setAttribute("tableData", tableData);
        logService.saveUserAction("get table data " + tableName);
        return "tableData";
    }

    @RequestMapping(value = "/createrecord", method = RequestMethod.GET)
    public String createRecord(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        Set<String> columns = service.getTableColumns(tableName);
        columns.remove("id");
        req.setAttribute("table", tableName);
        req.setAttribute("columns", columns);
        logService.saveUserAction("get create record. table - " + tableName);
        return "createRecord";
    }

    @RequestMapping(value = "/createrecord", method = RequestMethod.POST)
    public String creatingRecord(HttpServletRequest req) {
        Map<String, String[]> parameters = req.getParameterMap();
        String tableName = req.getParameter("tableName");
        service.createRecord(tableName, parameters);
        logService.saveUserAction("record in table " + tableName + " created");
        return "redirect:table?name=" + tableName;
    }

    @RequestMapping(value = "/deleterecord", method = RequestMethod.GET)
    public String deleteRecord(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        int id = Integer.parseInt(req.getParameter("id"));
        Map<String, String> record = service.getRecordData(tableName, id);
        req.setAttribute("table", tableName);
        req.setAttribute("id", id);
        req.setAttribute("record", record);
        logService.saveUserAction("get delete record. table - " + tableName);
        return "deleteRecord";
    }

    @RequestMapping(value = "/deleterecord", method = RequestMethod.POST)
    public String deletingRecord(HttpServletRequest req) {
        String tableName = req.getParameter("tableName");
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteRecord(tableName, id);
        logService.saveUserAction("record in table " + tableName + " deleted");
        return "redirect:table?name=" + tableName;
    }

    @RequestMapping(value = "/updaterecord", method = RequestMethod.GET)
    public String updateRecord(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        int id = Integer.parseInt(req.getParameter("id"));
        Map<String, String> record = service.getRecordData(tableName, id);
        req.setAttribute("table", tableName);
        req.setAttribute("id", id);
        req.setAttribute("record", record);
        logService.saveUserAction("get update record. table - " + tableName);
        return "updateRecord";
    }

    @RequestMapping(value = "/updaterecord", method = RequestMethod.POST)
    public String updatingRecord(HttpServletRequest req) {
        String tableName = req.getParameter("tableName");
        int id = Integer.parseInt(req.getParameter("id"));
        ;
        Map<String, String[]> parameters = req.getParameterMap();
        service.updateRecord(tableName, id, parameters);
        logService.saveUserAction("record in table " + tableName + " updated");
        return "redirect:table?name=" + tableName;
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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users() {
        List<User> users = logService.getUsers();
        ModelAndView model = new ModelAndView("users");
        model.addObject("users", users);
        logService.saveUserAction("get users");
        return model;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        ModelAndView model = new ModelAndView("createUser");
        model.addObject("user", new User());
        logService.saveUserAction("get add user");
        return model;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String creatingUser(User user) {
        logService.saveUser(user);
        logService.saveUserAction("add user " + user.getName());
        return "redirect:users";
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    //TODO boolean do not transferred to wiev
    //TODO add roles
    //TODO validate email
    public ModelAndView updateUser(HttpServletRequest req) {
        String name = req.getParameter("name");
//    @RequestMapping(value = "/updateuser/{name}", method = RequestMethod.GET)
//    public ModelAndView updateUser(@PathVariable String name) {
        User user = logService.getUser(name);
        ModelAndView model = new ModelAndView("updateUser");
        model.addObject("user", user);
        logService.saveUserAction("get update user " + user.getName());
        return model;
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String updatingUser(User user){
        logService.saveUser(user);
        logService.saveUserAction("update user " + user.getName());
        return "redirect:users";
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        User user = logService.getUser(name);
        ModelAndView model = new ModelAndView("deleteUser");
        model.addObject("user", user);
        logService.saveUserAction("get delete user " + user.getName());
        return model;
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public String deletingUser(User user) {
        logService.deleteUser(user);
        logService.saveUserAction("delete user " + user.getName());
        return "redirect:users";
    }

    //TODO add filters by user, period ...
    //TODO make pages
    @RequestMapping(value = "/actions", method = RequestMethod.GET)
    public ModelAndView userActions() {
        List<UserAction> userActions = logService.getUserActions();
        ModelAndView model = new ModelAndView("userActions");
        model.addObject("actions", userActions);
        logService.saveUserAction("get user actions");
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

    @RequestMapping("/db")
    public String getProduct(Model model) {
        model.addAttribute("databases", service.getDatabaseNames());
        return "databases_t";
    }
}