package Servlets;

import Database.DB;
import  Database.SQL;

import Models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class addNewItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hasError = null;
        String category= (String) request.getSession().getAttribute("NEW_ITEM_CATEGORY");
        String categoryWithoutQuotes=category;
        String uploader= (String) request.getSession().getAttribute("username");
        String uploaderWithout=uploader;
        String smallVersion=category.substring(0,2);
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
        String item_id_without=item_id;
        item_id= Item.surroundWithSingleQuotes(item_id);
        forCurrTable.add(item_id);

        uploader=Item.surroundWithSingleQuotes(uploader);
        title=Item.surroundWithSingleQuotes(title);
        cover_url=Item.surroundWithSingleQuotes(cover_url);
        category=Item.surroundWithSingleQuotes(category);


        if(categoryWithoutQuotes.equals(Music.ATTRIBUTE)){

            String artist=request.getParameter("musicArtist");
            artist=Item.surroundWithSingleQuotes(artist);
            forCurrTable.add(artist);
            forCurrTable.add(title);
            String label= request.getParameter("musicLabel");
            label=Item.surroundWithSingleQuotes(label);
            forCurrTable.add(label);
            forCurrTable.add(date);
            String genre = request.getParameter("musicGenre");
            genre=Item.surroundWithSingleQuotes(genre);
            forCurrTable.add(genre);
            forCurrTable.add(cover_url);
        }

        if(categoryWithoutQuotes.equals(Movie.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String director= request.getParameter("movieDirector");
            director=Item.surroundWithSingleQuotes(director);
            forCurrTable.add(director);
            String cast= request.getParameter("movieCast");
            cast=Item.surroundWithSingleQuotes(cast);
            forCurrTable.add(cast);
            forCurrTable.add(cover_url);
            String summary= request.getParameter("movieSummary");
            summary=Item.surroundWithSingleQuotes(summary);
            forCurrTable.add(summary);
        }


        if(categoryWithoutQuotes.equals(TV_Show.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String showDirector= request.getParameter("showDirector");
            showDirector=Item.surroundWithSingleQuotes(showDirector);
            forCurrTable.add(showDirector);
            String showCast= request.getParameter("showCast");
            showCast=Item.surroundWithSingleQuotes(showCast);
            forCurrTable.add(showCast);
            forCurrTable.add(cover_url);
            String showSummary= request.getParameter("showSummary");
            showSummary=Item.surroundWithSingleQuotes(showSummary);
            forCurrTable.add(showSummary);
        }


        if(categoryWithoutQuotes.equals(Book.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String writer= request.getParameter("bookWriter");
            writer=Item.surroundWithSingleQuotes(writer);
            forCurrTable.add(writer);
            forCurrTable.add(cover_url);
            String bookSummary= request.getParameter("bookSummary");
            bookSummary=Item.surroundWithSingleQuotes(bookSummary);
            forCurrTable.add(bookSummary);
        }

        if(categoryWithoutQuotes.equals(Video_Game.ATTRIBUTE)){

            forCurrTable.add(title);
            forCurrTable.add(date);
            String developers= request.getParameter("gameDevelopers");
            developers=Item.surroundWithSingleQuotes(developers);
            forCurrTable.add(developers);
            forCurrTable.add(cover_url);
            String gameSummary= request.getParameter("gameSummary");
            gameSummary=Item.surroundWithSingleQuotes(gameSummary);
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

       DB db= (DB) getServletContext().getAttribute(ContextListener.DB_ATTRIBUTE);

       int insertInItems= db.insert(Item.TABLE_NAME, forItem);
       int insertInCategory=-1;
       if(insertInItems == SQL.SQL_SUCCESS) {
           insertInCategory = db.insert(categoryWithoutQuotes, forCurrTable);
       }

       //?????? ???????????? ???????????????????????? ????????????????????????????????? ????????????????????? ????????????????????????????????? ??????????????? ????????? ?????????????????? ??????????????????, ???????????? ??????????????????????????? ??????????????????????????? ?????? ??????????????????????????? ??????????????????
        if (insertInItems == SQL.SQL_SUCCESS && insertInItems==insertInCategory){
            request.getSession().setAttribute("id",item_id_without);
            User currUser=null;
            try {
                currUser=User.getUserByUsername(db,uploaderWithout);
                for(User follower : currUser.getFollowers(db)){
                    Notification.addNotification(db, follower.getUsername(), currUser.getUsername()
                            , item_id, "UPLOAD");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                currUser.updateBadges(db);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String sendNotification= request.getParameter("sendNotification");
            if(sendNotification != null){
                try {
                    List<User> following= currUser.getFollowing(db);
                    for(User currFollowing : following){
                        Notification.addNotification(db, currFollowing.getUsername(),currUser.getUsername(),
                                item_id, "REQUEST");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request,response);

        } else{
            hasError="YES";
            request.getSession().setAttribute("hasError",hasError);
            request.getRequestDispatcher("/WEB-INF/addNewItem.jsp").forward(request,response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("NEW_ITEM_CATEGORY");
        request.getSession().setAttribute("NEW_ITEM_CATEGORY", categoryName);
        request.getSession().setAttribute("hasError", null);
        request.getRequestDispatcher("/WEB-INF/addNewItem.jsp").forward(request,response);
    }
}
