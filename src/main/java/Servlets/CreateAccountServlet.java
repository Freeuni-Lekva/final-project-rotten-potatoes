package Servlets;

import Database.DB;
import Database.SQL;
import Models.Item;
import Models.Password;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateAccountServlet extends HttpServlet {
    public static final String DB_ATTRIBUTE = "db";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/createNewAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String birthdate = request.getParameter("birthdate");
        String password = request.getParameter("password");

        request.getSession().setAttribute("emptyFields", "");
        if (username.isEmpty() || firstname.isEmpty() || lastname.isEmpty() ||
                birthdate.isEmpty() || password.isEmpty()) {
            request.getSession().setAttribute("emptyFields", "emptyFields");
            request.getRequestDispatcher("/WEB-INF/createNewAccount.jsp").forward(request, response);
            return;
        }

        String hashPassword = "";
        try {
            hashPassword = Password.getHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        SQL sql = (SQL)getServletContext().getAttribute(DB_ATTRIBUTE);
        List<String> args = new ArrayList<String>();
        String[] elems = new String[] {username, firstname, lastname, birthdate, hashPassword};
        for (int i = 0; i < elems.length; i++) {
            elems[i] = Item.surroundWithSingleQuotes(elems[i]);
        }
        args = Arrays.asList(elems);
        int result = sql.insert("USERS", args);
        if (result == sql.SQL_SUCCESS) {
            request.getSession().setAttribute(User.ATTRIBUTE, username);
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute(User.ATTRIBUTE, username);
            request.getSession().setAttribute("existingacc", "existingacc");
            request.getRequestDispatcher("/WEB-INF/createNewAccount.jsp").forward(request, response);
        }
    }
}
