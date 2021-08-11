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
        request.getSession().setAttribute("CATEGORY", Movie.ATTRIBUTE); // default category is movie
        request.getSession().setAttribute("SORTING", "score ASC"); // default sort type is rating ascending
        request.getSession().setAttribute("SEARCH", ""); // searchbar is empty by default
        request.getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("CATEGORY");
        String sorting = request.getParameter("SORTING");
        String search = request.getParameter("SEARCH");

        if(category != null) request.getSession().setAttribute("CATEGORY", category);
        if(sorting != null){
            request.getSession().setAttribute("SORTING", sorting);
        } else {
            request.getSession().setAttribute("SORTING", "score ASC");
        }
        if(search != null){
            request.getSession().setAttribute("SEARCH", search);
        } else {
            request.getSession().setAttribute("SEARCH", "");
        }
        request.getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }
}
