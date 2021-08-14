package Servlets;

import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String reportID = httpServletRequest.getParameter("report_id");
        httpServletRequest.getSession().setAttribute("report_id", reportID);
        httpServletRequest.getRequestDispatcher("/WEB-INF/report.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String reportID = httpServletRequest.getParameter("report_id");
        httpServletRequest.getSession().setAttribute("report_id", reportID);
        httpServletRequest.getRequestDispatcher("/WEB-INF/report.jsp").forward(httpServletRequest, httpServletResponse);
    }
}