package ua.com.juja.sqlcmd.controller.web;

import ua.com.juja.sqlcmd.service.Service;
import ua.com.juja.sqlcmd.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = new ServiceImpl();
        String action = getAction(req);

        if (action.startsWith("/menu") || action.equals("/")) {
            req.setAttribute("commands", service.commandList());
            req.getRequestDispatcher("jsp/menu.jsp").forward(req, resp);
        } else if (action.startsWith("/help")) {
            req.getRequestDispatcher("jsp/help.jsp").forward(req, resp);
        } else if (action.startsWith("/connect")) {
            req.getRequestDispatcher("jsp/connect.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("jsp/menu.jsp").forward(req, resp);
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
        }

    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
