package Servlets;

import Database.DB;
import Models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class reportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/reportForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DB db= (DB) getServletContext().getAttribute(ContextListener.DB_ATTRIBUTE);

        String currUser=(String) request.getSession().getAttribute("username");
        String item_id=(String) request.getSession().getAttribute("id");
        String category=(String) request.getSession().getAttribute("CATEGORY");
        String title=request.getParameter("title");
        String date;

        if(category.equals(TV_Show.ATTRIBUTE)){
            date=request.getParameter("showDate");
        } else {
            date=request.getParameter("date");
        }

        String cover_url=request.getParameter("cover");

        String producer = "";
        String info="";
        String members="";

        if(category.equals(Music.ATTRIBUTE)){
            producer= request.getParameter("musicLabel");
            info=request.getParameter("musicGenre");
            members=request.getParameter("musicArtist");
        }
        if(category.equals(TV_Show.ATTRIBUTE)){
            producer=request.getParameter("showDirector");
            info=request.getParameter("showSummary");
            members=request.getParameter("showCast");
        }
        if(category.equals(Book.ATTRIBUTE)) {
            producer= request.getParameter("bookWriter");
            info=request.getParameter("bookSummary");
            members="";
        }
        if(category.equals(Video_Game.ATTRIBUTE)){
            producer=request.getParameter("gameDevelopers");
            info=request.getParameter("gameSummary");
            members="";
        }
        if(category.equals(Movie.ATTRIBUTE)){
            producer=request.getParameter("movieDirector");
            info= request.getParameter("movieSummary");
            members=request.getParameter("movieCast");
        }

        String comment=request.getParameter("messageToAdmin");

        List<String> forReport= Arrays.asList(null, Item.surroundWithSingleQuotes(currUser),
                Item.surroundWithSingleQuotes(item_id),
                Item.surroundWithSingleQuotes(category),
                Item.surroundWithSingleQuotes(title),
                date,
                Item.surroundWithSingleQuotes(cover_url),
                Item.surroundWithSingleQuotes(producer),
                Item.surroundWithSingleQuotes(info),
                Item.surroundWithSingleQuotes(members),
                Item.surroundWithSingleQuotes(comment));


        db.insert(Report.REPORTS,forReport);
        
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
