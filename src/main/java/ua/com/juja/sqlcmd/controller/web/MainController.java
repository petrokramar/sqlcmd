package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.juja.sqlcmd.service.AppService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController{

    //TODO Catch exceptions
    @Autowired
    private AppService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
        service.saveUserAction("get menu");
        return "menu";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        service.saveUserAction("get menu");
        return "menu";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        service.saveUserAction("get login");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginning() {
        return "redirect:menu";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register() {
        service.saveUserAction("get registration");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration() {
        return "redirect:menu";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect() {
        service.saveUserAction("get connect");
        return "connect";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connecting(HttpServletRequest req) {
        String databaseName = req.getParameter("dbname");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        service.connect(databaseName, userName, password);
        service.saveUserAction("connected. database name - " + databaseName + ". username - " + userName);
        return "redirect:databases";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        service.saveUserAction("get help");
        return "help";
    }

    @RequestMapping(value = "/databases", method = RequestMethod.GET)
    public String databases(Model model) {
        service.saveUserAction("get databases");
        if (!service.isConnected()) {
            return "connect";
        }
        String currentDatabase = service.currentDatabase();
        Set<String> databases= service.getDatabaseNames();
        model.addAttribute("databases", databases);
        model.addAttribute("current", currentDatabase);
        service.saveUserAction("get databases");
        return "databaseNames";
    }

    @RequestMapping(value = "/createdatabase", method = RequestMethod.GET)
    public String createDatabase() {
        service.saveUserAction("get createdatabase");
        return "createDatabase";
    }

    @RequestMapping(value = "/createdatabase", method = RequestMethod.POST)
    public String creatingDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("database");
        service.createDatabase(databaseName);
        service.saveUserAction("database " + databaseName + "created");
        return "redirect:databases";
    }

    @RequestMapping(value = "/dropdatabase", method = RequestMethod.GET)
    public String dropDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("name");
        req.setAttribute("database", databaseName);
        service.saveUserAction("get dropdatabase " + databaseName);
        return "dropDatabase";
    }

    @RequestMapping(value = "/dropdatabase", method = RequestMethod.POST)
    public String droppingDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("database");
        service.dropDatabase(databaseName);
        service.saveUserAction("database " + databaseName + "dropped");
        return "redirect:databases";
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String tables(HttpServletRequest req) {
        Set<String> tables = service.getTableNames();
        req.setAttribute("tables", tables);
        service.saveUserAction("get tables");
        return "tableNames";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.GET)
    public String createTable() {
        service.saveUserAction("get create table");
        return "createTable";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    public String creatingTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        String query = req.getParameter("query");
        service.createTable(tableName, query);
        service.saveUserAction("table created " + tableName + " " + query);
        return "redirect:tables";
    }

    @RequestMapping(value = "/cleartable", method = RequestMethod.GET)
    public String clearTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        req.setAttribute("table", tableName);
        service.saveUserAction("clear table " + tableName);
        return "clearTable";
    }

    @RequestMapping(value = "/cleartable", method = RequestMethod.POST)
    public String clearing(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        service.clearTable(tableName);
        service.saveUserAction("table " + tableName + " cleared");
        return "redirect:tables";
    }

    @RequestMapping(value = "/droptable", method = RequestMethod.GET)
    public String dropTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        req.setAttribute("table", tableName);
        service.saveUserAction("drop table " + tableName);
        return "dropTable";
    }

    @RequestMapping(value = "/droptable", method = RequestMethod.POST)
    public String droppingTable(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        service.dropTable(tableName);
        service.saveUserAction("table " + tableName + " dropped");
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
        service.saveUserAction("get table data " + tableName);
        return "tableData";
    }

    @RequestMapping(value = "/createrecord", method = RequestMethod.GET)
    public String createRecord(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        Set<String> columns = service.getTableColumns(tableName);
        columns.remove("id");
        req.setAttribute("table", tableName);
        req.setAttribute("columns", columns);
        service.saveUserAction("get create record. table - " + tableName);
        return "createRecord";
    }

    @RequestMapping(value = "/createrecord", method = RequestMethod.POST)
    public String creatingRecord(HttpServletRequest req) {
        Map<String, String[]> parameters = req.getParameterMap();
        String tableName = req.getParameter("tableName");
        service.createRecord(tableName, parameters);
        service.saveUserAction("record in table " + tableName + " created");
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
        service.saveUserAction("get delete record. table - " + tableName);
        return "deleteRecord";
    }

    @RequestMapping(value = "/deleterecord", method = RequestMethod.POST)
    public String deletingRecord(HttpServletRequest req) {
        String tableName = req.getParameter("tableName");
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteRecord(tableName, id);
        service.saveUserAction("record in table " + tableName + " deleted");
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
        service.saveUserAction("get update record. table - " + tableName);
        return "updateRecord";
    }

    @RequestMapping(value = "/updaterecord", method = RequestMethod.POST)
    public String updatingRecord(HttpServletRequest req) {
        String tableName = req.getParameter("tableName");
        int id = Integer.parseInt(req.getParameter("id"));;
        Map<String, String[]> parameters = req.getParameterMap();
        service.updateRecord(tableName, id, parameters);
        service.saveUserAction("record in table " + tableName + " updated");
        return "redirect:table?name=" + tableName;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    // TODO Remember query before connect
    public String query(HttpServletRequest req) {
        if (!service.isConnected()) {
            return "connect";
        }
        service.saveUserAction("get query");
        return "query";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String querying(HttpServletRequest req) {
        String query = req.getParameter("query");
        List<List<String>> queryData = service.executeQuery(query);
        req.setAttribute("query", query);
        req.setAttribute("querydata", queryData);
        service.saveUserAction("query " + query + " executed");
        return "queryResult";
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }
}
