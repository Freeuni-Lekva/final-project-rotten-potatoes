package Servlets;

import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChooseNewItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // თათია, ასე ამოიღე არჩეული კატეგორია:
        String categoryName = req.getParameter("NEW_ITEM_CATEGORY");
        HttpSession session = req.getSession();
        session.setAttribute("NEW_ITEM_CATEGORY", categoryName);
        req.getRequestDispatcher("WEB-INF/addNewItem.jsp").forward(req, resp);
    }
}