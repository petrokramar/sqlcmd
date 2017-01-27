package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.juja.sqlcmd.service.DatabaseService;
import ua.com.juja.sqlcmd.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Controller
public class RecordController {
    @Autowired
    private DatabaseService service;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/createrecord", method = RequestMethod.GET)
    public String createRecord(HttpServletRequest req) {
        String tableName = req.getParameter("table");
        Set<String> columns = service.getTableColumns(tableName);
        columns.remove("id");
        req.setAttribute("table", tableName);
        req.setAttribute("columns", columns);
//        logService.saveUserAction("get create record. table - " + tableName);
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
//        logService.saveUserAction("get delete record. table - " + tableName);
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
//        logService.saveUserAction("get update record. table - " + tableName);
        return "updateRecord";
    }

    @RequestMapping(value = "/updaterecord", method = RequestMethod.POST)
    public String updatingRecord(HttpServletRequest req) {
        String tableName = req.getParameter("tableName");
        int id = Integer.parseInt(req.getParameter("id"));
        Map<String, String[]> parameters = req.getParameterMap();
        service.updateRecord(tableName, id, parameters);
        logService.saveUserAction("record in table " + tableName + " updated");
        return "redirect:table?name=" + tableName;
    }
}
