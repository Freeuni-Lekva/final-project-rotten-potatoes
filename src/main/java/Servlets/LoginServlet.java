package Servlets;

import Database.SQL;
import Models.Password;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isEmpty() || password.isEmpty()) {
            request.getSession().setAttribute("invalidinfo", "invalidinfo");
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            return;
        }

        SQL sql = (SQL)getServletContext().getAttribute(ContextListener.DB_ATTRIBUTE);
        try {
            if (Password.validInfo(sql, username, password)) {
                request.getSession().setAttribute(User.ATTRIBUTE, username);
                request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("invalidinfo", "invalidinfo");
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
