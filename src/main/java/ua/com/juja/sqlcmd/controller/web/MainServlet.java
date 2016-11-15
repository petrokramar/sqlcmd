package ua.com.juja.sqlcmd.controller.web;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.service.Service;
import ua.com.juja.sqlcmd.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class MainServlet extends HttpServlet {
    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new ServiceImpl();
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = getAction(req);

            if (action.startsWith("/menu") || action.equals("/")) {
                req.getRequestDispatcher("jsp/menu.jsp").forward(req, resp);

            } else if (action.startsWith("/databases")) {
                Set<String> databases= service.getDatabaseNames();
                req.setAttribute("databases", databases);
                req.getRequestDispatcher("jsp/databaseNames.jsp").forward(req, resp);

            } else if (action.startsWith("/tables")) {
                Set<String> tables = service.getTableNames();
                req.setAttribute("tables", tables);
                req.getRequestDispatcher("jsp/tableNames.jsp").forward(req, resp);

            } else if (action.startsWith("/table")) {
                String tableName = req.getParameter("name");
                Set<String> columns = service.getTableColumns(tableName);
                List<List<String>> tableData = service.getTableData(tableName);
                req.setAttribute("table", tableName);
                req.setAttribute("columns", columns);
                req.setAttribute("tableData", tableData);
                req.getRequestDispatcher("jsp/tableData.jsp").forward(req, resp);

            } else if (action.startsWith("/createrecord")) {
                String tableName = req.getParameter("table");
                Set<String> columns = service.getTableColumns(tableName);
                columns.remove("id");
                req.setAttribute("table", tableName);
                req.setAttribute("columns", columns);
                req.getRequestDispatcher("jsp/createRecord.jsp").forward(req, resp);

            } else if (action.startsWith("/deleterecord")) {
                String tableName = req.getParameter("table");
                int id = Integer.parseInt(req.getParameter("id"));
                Map<String, String> record = service.getRecordData(tableName, id);
                req.setAttribute("table", tableName);
                req.setAttribute("id", id);
                req.setAttribute("record", record);
                req.getRequestDispatcher("jsp/deleteRecord.jsp").forward(req, resp);

            } else if (action.startsWith("/updaterecord")) {
                String tableName = req.getParameter("table");
                int id = Integer.parseInt(req.getParameter("id"));
                Map<String, String> record = service.getRecordData(tableName, id);
                req.setAttribute("table", tableName);
                req.setAttribute("id", id);
                req.setAttribute("record", record);
                req.getRequestDispatcher("jsp/updateRecord.jsp").forward(req, resp);

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
            Map<String, String[]> parameters = req.getParameterMap();
            String tableName = req.getParameter("tableName");
            service.createRecord(tableName, parameters);
            resp.sendRedirect(resp.encodeRedirectURL("table?name=" + tableName));
        } else if (action.startsWith("/deleterecord")) {
            String tableName = req.getParameter("tableName");
            int id = Integer.parseInt(req.getParameter("id"));
            service.deleteRecord(tableName, id);
            resp.sendRedirect(resp.encodeRedirectURL("table?name=" + tableName));
        } else if (action.startsWith("/updaterecord")) {
            String tableName = req.getParameter("tableName");
            int id = Integer.parseInt(req.getParameter("id"));;
            Map<String, String[]> parameters = req.getParameterMap();
            service.updateRecord(tableName, id, parameters);
            resp.sendRedirect(resp.encodeRedirectURL("table?name=" + tableName));
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
