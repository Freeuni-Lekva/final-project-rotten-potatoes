package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseNewItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        // თათია, ასე ამოიღე არჩეული კატეგორია:
        String categoryName = httpServletRequest.getParameter("NEW_ITEM_CATEGORY");

        httpServletRequest.getRequestDispatcher("WEB-INF/addNewItem.jsp").forward(httpServletRequest, httpServletResponse);
    }
}