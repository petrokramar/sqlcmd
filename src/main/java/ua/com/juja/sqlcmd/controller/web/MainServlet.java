package ua.com.juja.sqlcmd.controller.web;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.PostgreSQLManager;
import ua.com.juja.sqlcmd.service.Service;
import ua.com.juja.sqlcmd.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MainServlet extends HttpServlet {

    private final DatabaseManager manager = new PostgreSQLManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Service service = new ServiceImpl();

            String action = getAction(req);

            if (action.startsWith("/menu") || action.equals("/")) {
//            req.setAttribute("commands", service.commandList());
                req.getRequestDispatcher("jsp/menu.jsp").forward(req, resp);
            } else if (action.startsWith("/databases")) {
                manager.connect("sqlcmd", "postgres", "123456");
                Set<String> databases= manager.getDatabaseNames();
                req.setAttribute("databases", databases);
                req.getRequestDispatcher("jsp/databaseNames.jsp").forward(req, resp);
            } else if (action.startsWith("/tables")) {
                manager.connect("sqlcmd", "postgres", "123456");
                Set<String> tables = manager.getTableNames();
                req.setAttribute("tables", tables);
                req.getRequestDispatcher("jsp/tableNames.jsp").forward(req, resp);
            } else if (action.startsWith("/table")) {
                String tableName = (String) req.getParameter("name");
                manager.connect("sqlcmd", "postgres", "123456");
                Set<String> columns = manager.getTableColumns(tableName);
                List<DataSet> data = manager.getTableData(tableName);
                List<List<String>> tableData = new ArrayList<>();
                for (DataSet set : data) {
                    List<Object> row = set.getValues();
                    List<String> tableRow = new ArrayList<>();
                    for (Object o: row) {
                        tableRow.add(o.toString());
                    }
                    tableData.add(tableRow);
                }
                req.setAttribute("table", tableName);
                req.setAttribute("columns", columns);
                req.setAttribute("tableData", tableData);
                req.getRequestDispatcher("jsp/tableData.jsp").forward(req, resp);
            } else if (action.startsWith("/createrecord")) {
                String tableName = (String) req.getParameter("table");
                manager.connect("sqlcmd", "postgres", "123456");
                Set<String> columns = manager.getTableColumns(tableName);
                columns.remove("id");
                req.setAttribute("table", tableName);
                req.setAttribute("columns", columns);
                req.getRequestDispatcher("jsp/createRecord.jsp").forward(req, resp);
            } else if (action.startsWith("/deleterecord")) {
                String tableName = (String) req.getParameter("table");
                int id = Integer.parseInt(req.getParameter("id"));
                manager.connect("sqlcmd", "postgres", "123456");
//                Set<String> columns = manager.getTableColumns(tableName);
                DataSet set = manager.getRecordData(tableName, id);
                Map<String, Object> data = set.getData();
                Map<String, String> record = new TreeMap<>();
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    record.put(entry.getKey(), entry.getValue().toString());
                }
//                List<List<String>> tableRecord = new ArrayList<>();
//                for (Map.Entry<String, Object> entry : data.entrySet())  {
//                    List<String> field = new ArrayList<>();
//                    field.add(entry.getKey());
//                    field.add(entry.getValue().toString());
//                    tableRecord.add(field);
//                }
                req.setAttribute("table", tableName);
                req.setAttribute("id", id);
                req.setAttribute("record", record);
                req.getRequestDispatcher("jsp/deleteRecord.jsp").forward(req, resp);
            } else if (action.startsWith("/help")) {
                req.getRequestDispatcher("jsp/help.jsp").forward(req, resp);
            } else if (action.startsWith("/connect")) {
                req.getRequestDispatcher("jsp/connect.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("jsp/menu.jsp").forward(req, resp);
            }

        } catch(Exception e)  {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("jsp/error.jsp").forward(req, resp);
        }
  }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = new ServiceImpl();
        String action = getAction(req);

        if (action.startsWith("/connect")) {
            String databaseName = req.getParameter("dbname");
            String userName = req.getParameter("username");
            String password = req.getParameter("password");


//            try {
//                service.connect(databaseName, userName, password);
//                resp.sendRedirect(resp.encodeRedirectURL("menu"));
//            } catch (Exception e) {
//                req.setAttribute("message", e.getMessage());
//                req.getRequestDispatcher("error.jsp").forward(req, resp);
//            }
        } else if (action.startsWith("/createrecord")) {
            manager.connect("sqlcmd", "postgres", "123456");
            Map<String, String[]> parameters = req.getParameterMap();
            DataSet dataSet = new DataSet();
            String tableName = "";
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                if ("tableName".equals(entry.getKey())) {
                    tableName = entry.getValue()[0];
                } else{
                    dataSet.put(entry.getKey(), entry.getValue()[0]);
                }
            }
            manager.createRecord(tableName, dataSet);
            resp.sendRedirect(resp.encodeRedirectURL("table?name=" + tableName));
        } else if (action.startsWith("/deleterecord")) {
//            manager.connect("sqlcmd", "postgres", "123456");
//            String tableName = req.getParameter("tableName");
//            Map<String, String[]> parameters = req.getParameterMap();
//            DataSet dataSet = new DataSet();
//            String tableName = "";
//            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
//                if ("tableName".equals(entry.getKey())) {
//                    tableName = entry.getValue()[0];
//                } else{
//                    dataSet.put(entry.getKey(), entry.getValue()[0]);
//                }
//            }
//            manager.deleteRecord(tableName, id);
//            resp.sendRedirect(resp.encodeRedirectURL("table?name=" + tableName));
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
