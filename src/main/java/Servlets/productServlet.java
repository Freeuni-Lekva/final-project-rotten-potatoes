package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class productServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sorting = request.getParameter("REVIEW_SORTING");
        if (sorting == null){
            request.getSession().setAttribute("REVIEW_SORTING", "score ASC");
        } else {
            request.getSession().setAttribute("REVIEW_SORTING", sorting);
        }
        request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/reportForm.jsp").forward(request, response);
    }
}
