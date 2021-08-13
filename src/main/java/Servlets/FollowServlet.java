package Servlets;

import Database.DB;
import Models.Item;
import Models.User;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class FollowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = (DB) req.getServletContext().getAttribute("db");
        String wannabeFollower = (String) req.getSession().getAttribute("username");
        String userToFollow = req.getParameter("guest_visitor_id");

        try {
            if(!User.isFollowing(db, User.getUserByUsername(db, userToFollow), User.getUserByUsername(db, wannabeFollower))){
                User.follow(db, userToFollow, wannabeFollower);
				Notification.addNotification(db, userToFollow , wannabeFollower , null , "FOLLOW");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        req.setAttribute("guest_visitor_id", userToFollow);
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }
}
