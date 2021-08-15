package Servlets;

import Database.DB;
import Models.Review;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class newReview extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String score = request.getParameter("SCORE");
        if (score == null) score = "0";
        String itemId = (String)request.getSession().getAttribute("id");
        String username = (String)request.getSession().getAttribute("username");
        String review = (String)request.getParameter("newReview");
        DB db = (DB) request.getServletContext().getAttribute(ContextListener.DB_ATTRIBUTE);
        Review.addReview(db, itemId, username, Double.valueOf(score), review);
        request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
    }
}
