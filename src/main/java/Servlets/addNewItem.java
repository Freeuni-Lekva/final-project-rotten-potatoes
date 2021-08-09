package Servlets;

import Database.DB;
import Models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addNewItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String category= request.getParameter("hiddenCategory");
        String smallVersion=category.substring(0,2);
        String uploader= request.getParameter("hiddenUser");
        String title=request.getParameter("title");
        String cover_url=request.getParameter("cover");
        String date;
        String score="0";
        String numReviews="0";
        List<String> forItem= new ArrayList<>();

        List<String> forCurrTable= new ArrayList<>();

        if(category.equals(TV_Show.ATTRIBUTE)){
            date=request.getParameter("showDate");
        } else {
            date=request.getParameter("date");

        }

        String item_id= smallVersion+ "_" + title+ "_" +date;
        forCurrTable.add(item_id);

        if(category.equals(Music.ATTRIBUTE)){

            String artist=request.getParameter("musicArtist");
            forCurrTable.add(artist);
            forCurrTable.add(title);
            String label= request.getParameter("musicLabel");
            forCurrTable.add(label);
            forCurrTable.add(date);
            String genre= request.getParameter("musicGenre");
            forCurrTable.add(genre);
            forCurrTable.add(cover_url);
        }

        if(category.equals(Movie.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String director= request.getParameter("movieDirector");
            forCurrTable.add(director);
            String cast= request.getParameter("movieCast");
            forCurrTable.add(cover_url);
            String summary= request.getParameter("movieSummary");
            forCurrTable.add(summary);
        }


        if(category.equals(TV_Show.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String showDirector= request.getParameter("showDirector");
            forCurrTable.add(showDirector);
            String showCast= request.getParameter("showCast");
            forCurrTable.add(showCast);
            forCurrTable.add(cover_url);
            String showSummary= request.getParameter("showSummary");
            forCurrTable.add(showSummary);
        }


        if(category.equals(Book.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String writer= request.getParameter("bookWriter");
            forCurrTable.add(writer);
            forCurrTable.add(cover_url);
            String bookSummary= request.getParameter("bookSummary");
            forCurrTable.add(bookSummary);

        }

        if(category.equals(Video_Game.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String developers= request.getParameter("gameDevelopers");
            forCurrTable.add(developers);
            forCurrTable.add(cover_url);
            String gameSummary= request.getParameter("gameSummary");
            forCurrTable.add(gameSummary);
        }

        forCurrTable.add(uploader);
        forCurrTable.add(score);
        forCurrTable.add(numReviews);

        forItem.add(item_id);
        forItem.add(title);
        forItem.add(category);
        forItem.add(uploader);
        forItem.add(score);
        forItem.add(cover_url);
        forItem.add(date);
        forItem.add(numReviews);

       DB db= (DB) getServletContext().getAttribute("DB_ATTRIBUTE");
       db.insert(category,forCurrTable);
       db.insert(Item.TABLE_NAME, forItem);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
