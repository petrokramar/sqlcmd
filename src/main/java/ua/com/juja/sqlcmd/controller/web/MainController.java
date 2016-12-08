package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController{

    //TODO Catch exceptions
    @Autowired
    private Service service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
        return "menu";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        return "menu";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect() {
        return "connect";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connecting(HttpServletRequest req) {
        String databaseName = req.getParameter("dbname");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        service.connect(databaseName, userName, password);
        return "redirect:databases";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/databases", method = RequestMethod.GET)
    public String databases(Model model) {
        if (!service.isConnected()) {
            return "connect";
        }
        String currentDatabase = service.currentDatabase();
        Set<String> databases= service.getDatabaseNames();
        model.addAttribute("databases", databases);
        model.addAttribute("current", currentDatabase);
        return "databaseNames";
    }

    @RequestMapping(value = "/createdatabase", method = RequestMethod.GET)
    public String createDatabase() {
        return "createDatabase";
    }

    @RequestMapping(value = "/createdatabase", method = RequestMethod.POST)
    public String creatingDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("database");
        service.createDatabase(databaseName);
        return "redirect:databases";
    }

    @RequestMapping(value = "/dropdatabase", method = RequestMethod.GET)
    public String dropDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("name");
        req.setAttribute("database", databaseName);
        return "dropDatabase";
    }

    @RequestMapping(value = "/dropdatabase", method = RequestMethod.POST)
    public String droppingDatabase(HttpServletRequest req) {
        String databaseName = req.getParameter("database");
        service.dropDatabase(databaseName);
        return "redirectdatabases";
    }



    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String tables(HttpServletRequest req) {
        Set<String> tables = service.getTableNames();
        req.setAttribute("tables", tables);
        return "tableNames";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.GET)
    public String createTable() {
        return "createTable";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    public String creatingTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        String query = req.getParameter("query");
        service.createTable(tableName, query);
        return "redirect:tables";
    }

    @RequestMapping(value = "/cleartable", method = RequestMethod.GET)
    public String clearTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        req.setAttribute("table", tableName);
        return "clearTable";
    }

    @RequestMapping(value = "/cleartable", method = RequestMethod.POST)
    public String clearing(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        service.clearTable(tableName);
        return "redirect:tables";
    }

    @RequestMapping(value = "/droptable", method = RequestMethod.GET)
    public String dropTable(HttpServletRequest req) {
        String tableName = req.getParameter("name");
        req.setAttribute("table", tableName);
        return "dropTable";
    }

    @RequestMapping(value = "/droptable", method = RequestMethod.POST)
    public String droppingTable(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        service.dropTable(tableName);
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
        return "tableData";
    }

    @RequestMapping(value = "/createrecord", method = RequestMethod.GET)
    public String createRecord(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        Set<String> columns = service.getTableColumns(tableName);
        columns.remove("id");
        req.setAttribute("table", tableName);
        req.setAttribute("columns", columns);
        return "createRecord";
    }

    @RequestMapping(value = "/createrecord", method = RequestMethod.POST)
    public String creatingRecord(HttpServletRequest req) {
        Map<String, String[]> parameters = req.getParameterMap();
        String tableName = req.getParameter("tableName");
        service.createRecord(tableName, parameters);
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
        return "deleteRecord";
    }

    @RequestMapping(value = "/deleterecord", method = RequestMethod.POST)
    public String deletingRecord(HttpServletRequest req) {
        String tableName = req.getParameter("tableName");
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteRecord(tableName, id);
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
        return "updateRecord";
    }

    @RequestMapping(value = "/updaterecord", method = RequestMethod.POST)
    public String updatingRecord(HttpServletRequest req) {
        String tableName = req.getParameter("tableName");
        int id = Integer.parseInt(req.getParameter("id"));;
        Map<String, String[]> parameters = req.getParameterMap();
        service.updateRecord(tableName, id, parameters);
        return "redirect:table?name=" + tableName;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    // TODO Remember query before connect
    public String query(HttpServletRequest req) {
        if (!service.isConnected()) {
            return "connect";
        }
        return "query";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String querying(HttpServletRequest req) {
        String query = req.getParameter("query");
        List<List<String>> queryData = service.executeQuery(query);
        req.setAttribute("query", query);
        req.setAttribute("querydata", queryData);
        return "queryResult";
    }
}
