package Servlets;

import Database.DB;
import Database.SQL;
import Models.Notification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserNotificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notificationId = request.getParameter("notificationID");
        DB db = (DB) request.getServletContext().getAttribute("db");
        db.delete("NOTIFICATIONS", "notificationID", notificationId,"notificationID", notificationId);
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
