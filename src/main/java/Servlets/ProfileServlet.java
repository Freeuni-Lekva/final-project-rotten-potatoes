package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String guestUsername = req.getParameter("guest_visitor_id");
        /*
        if(req.getSession().getAttribute("username").equals(guestUsername)){
            req.setAttribute("guest_visitor_id", null);
        } else {
            req.setAttribute("guest_visitor_id", guestUsername);
        } */
        req.setAttribute("guest_visitor_id", guestUsername);
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }
}