package Servlets;

import Database.DB;
import Models.User;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FollowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String wannabeFollower = (String) httpServletRequest.getServletContext().getAttribute("username");
        // String userToFollow = (String) httpServletRequest.getServletContext().getAttribute("guest");
        String userToFollow = (String) httpServletRequest.getParameter("guest_visitor_id");
        DB db = (DB) httpServletRequest.getServletContext().getAttribute("db");
        User.follow(db, userToFollow, wannabeFollower);
        httpServletRequest.getRequestDispatcher("/WEB-INF/index.jsp").forward(httpServletRequest, httpServletResponse);
    }
}