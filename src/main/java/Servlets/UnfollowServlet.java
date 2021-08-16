package Servlets;

import Database.DB;
import Models.Item;
import Models.Notification;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UnfollowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = (DB) req.getServletContext().getAttribute("db");
        String wannabeUnfollower = (String) req.getSession().getAttribute("username");
        String userToFollow = req.getParameter("guest_visitor_id");
        try {
            if(User.isFollowing(db, User.getUserByUsername(db, userToFollow), User.getUserByUsername(db, wannabeUnfollower))){
                User.unfollow(db, userToFollow, wannabeUnfollower);
                int notificationID = Notification.notificationExists(db, userToFollow, wannabeUnfollower, null, "FOLLOW");
                if(-1 != notificationID){
                    String notifID = String.valueOf(notificationID);
                    db.delete("notifications", "notificationID", Item.surroundWithSingleQuotes(notifID),
                                            "notificationID", Item.surroundWithSingleQuotes(notifID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("guest_visitor_id", userToFollow);
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }
}