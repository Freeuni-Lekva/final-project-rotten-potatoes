package Servlets;

import Database.DB;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnfollowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = (DB) req.getServletContext().getAttribute("db");
        String wannabeUnfollower = (String) req.getSession().getAttribute("username");
        String userToFollow = req.getParameter("guest_visitor_id");
        User.unfollow(db, userToFollow, wannabeUnfollower);
        req.setAttribute("guest_visitor_id", userToFollow);
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }
}