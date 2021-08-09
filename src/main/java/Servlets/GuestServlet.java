package Servlets;

import Models.Movie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("CATEGORY", Movie.ATTRIBUTE); // default category is movie
        request.setAttribute("SORTING", "RATING ASC"); // default sort type is rating ascending
        request.setAttribute("SEARCH", ""); // searchbar is empty by default
        request.getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("CATEGORY");
        String sorting = request.getParameter("SORTING");
        String search = request.getParameter("SEARCH");
        request.setAttribute("CATEGORY", category);
        request.setAttribute("SORTING", sorting);
        request.setAttribute("SEARCH", search);
        request.getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }
}
