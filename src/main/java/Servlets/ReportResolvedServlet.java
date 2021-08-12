package Servlets;

import Database.DB;
import Models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ReportResolvedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = (DB) req.getServletContext().getAttribute("db");
        String consensus = (String) req.getParameter("RESOLVED");
        String reportID = (String) req.getParameter("REPORT_ID");

        Report report = null;
        Item item = null;

        if(consensus.equals("APPROVE")){
            try {
                report = Report.getReportById(db, Integer.valueOf(reportID));
                String category = report.getCategory();
                String itemID = report.getItemId();

                if(category.equals(Movie.ATTRIBUTE)){
                    Movie movie = Movie.getMovieByID(db, itemID);
                    String newID = "MO_" + report.getTitle() + "_" + report.getReleaseDate();

                    List<String> newMovieValues = Arrays.asList(Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                Item.surroundWithSingleQuotes(report.getProducer()),
                                                                Item.surroundWithSingleQuotes(report.getMembers()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                Item.surroundWithSingleQuotes(report.getInfo()),
                                                                Item.surroundWithSingleQuotes(movie.getUploader()),
                                                                String.valueOf(movie.getScore()),
                                                                String.valueOf(movie.getNumOfReviews()));

                    List<String> newItemValues = Arrays.asList( Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                Item.surroundWithSingleQuotes(category),
                                                                Item.surroundWithSingleQuotes(movie.getUploader()),
                                                                String.valueOf(movie.getScore()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                String.valueOf(movie.getNumOfReviews()));

                    db.delete(Movie.TABLE_NAME, "movie_id", Item.surroundWithSingleQuotes(itemID),
                            "movie_id", Item.surroundWithSingleQuotes(itemID));
                    db.delete("REPORTS", "reportID", reportID, "reportID", reportID);

                    db.insert(Item.TABLE_NAME, newItemValues);
                    // UPDATE REVIEWS
                    db.update("REVIEWS", "item_id", Item.surroundWithSingleQuotes(newID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    // UPDATE NOTIFICATIONS

                    db.delete(Item.TABLE_NAME, "item_id", Item.surroundWithSingleQuotes(itemID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    db.insert(Movie.TABLE_NAME, newMovieValues);
                } else if(category.equals(TV_Show.ATTRIBUTE)){
                    TV_Show tvShow = TV_Show.getTVShowByID(db, itemID);
                    String newID = "TV_" + report.getTitle() + "_" + report.getReleaseDate();

                    List<String> newTVShowValues = Arrays.asList(Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                Item.surroundWithSingleQuotes(report.getProducer()),
                                                                Item.surroundWithSingleQuotes(report.getMembers()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                Item.surroundWithSingleQuotes(report.getInfo()),
                                                                Item.surroundWithSingleQuotes(tvShow.getUploader()),
                                                                String.valueOf(tvShow.getScore()),
                                                                String.valueOf(tvShow.getNumOfReviews()));

                    List<String> newItemValues = Arrays.asList( Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                Item.surroundWithSingleQuotes(category),
                                                                Item.surroundWithSingleQuotes(tvShow.getUploader()),
                                                                String.valueOf(tvShow.getScore()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                String.valueOf(tvShow.getNumOfReviews()));

                    db.delete(TV_Show.TABLE_NAME, "tv_show_id", Item.surroundWithSingleQuotes(itemID),
                            "tv_show_id", Item.surroundWithSingleQuotes(itemID));
                    db.delete("REPORTS", "reportID", reportID, "reportID", reportID);

                    db.insert(Item.TABLE_NAME, newItemValues);
                    // UPDATE REVIEWS
                    db.update("REVIEWS", "item_id", Item.surroundWithSingleQuotes(newID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    // UPDATE NOTIFICATIONS

                    db.delete(Item.TABLE_NAME, "item_id", Item.surroundWithSingleQuotes(itemID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    db.insert(TV_Show.TABLE_NAME, newTVShowValues);
                } else if(category.equals(Music.ATTRIBUTE)){
                    Music music = Music.getMusicByID(db, itemID);
                    String newID = "MU_" + report.getTitle() + "_" + report.getReleaseDate();

                    List<String> newMusicValues = Arrays.asList(Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getMembers()),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                Item.surroundWithSingleQuotes(report.getProducer()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                Item.surroundWithSingleQuotes(report.getInfo()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                Item.surroundWithSingleQuotes(music.getUploader()),
                                                                String.valueOf(music.getScore()),
                                                                String.valueOf(music.getNumOfReviews()));

                    List<String> newItemValues = Arrays.asList( Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                Item.surroundWithSingleQuotes(category),
                                                                Item.surroundWithSingleQuotes(music.getUploader()),
                                                                String.valueOf(music.getScore()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                String.valueOf(music.getNumOfReviews()));

                    db.delete(Music.TABLE_NAME, "music_id", Item.surroundWithSingleQuotes(itemID),
                            "music_id", Item.surroundWithSingleQuotes(itemID));
                    db.delete("REPORTS", "reportID", reportID, "reportID", reportID);

                    db.insert(Item.TABLE_NAME, newItemValues);
                    // UPDATE REVIEWS
                    db.update("REVIEWS", "item_id", Item.surroundWithSingleQuotes(newID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    // UPDATE NOTIFICATIONS

                    db.delete(Item.TABLE_NAME, "item_id", Item.surroundWithSingleQuotes(itemID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    db.insert(Music.TABLE_NAME, newMusicValues);
                } else if(category.equals(Book.ATTRIBUTE)){
                    Book book = Book.getBookByID(db, itemID);
                    String newID = "BO_" + report.getTitle() + "_" + report.getReleaseDate();

                    List<String> newBookValues = Arrays.asList( Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                Item.surroundWithSingleQuotes(report.getProducer()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                Item.surroundWithSingleQuotes(report.getInfo()),
                                                                Item.surroundWithSingleQuotes(book.getUploader()),
                                                                String.valueOf(book.getScore()),
                                                                String.valueOf(book.getNumOfReviews()));

                    List<String> newItemValues = Arrays.asList( Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                Item.surroundWithSingleQuotes(category),
                                                                Item.surroundWithSingleQuotes(book.getUploader()),
                                                                String.valueOf(book.getScore()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                String.valueOf(book.getNumOfReviews()));

                    db.delete(Book.TABLE_NAME, "book_id", Item.surroundWithSingleQuotes(itemID),
                            "book_id", Item.surroundWithSingleQuotes(itemID));
                    db.delete("REPORTS", "reportID", reportID, "reportID", reportID);

                    db.insert(Item.TABLE_NAME, newItemValues);
                    // UPDATE REVIEWS
                    db.update("REVIEWS", "item_id", Item.surroundWithSingleQuotes(newID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    // UPDATE NOTIFICATIONS

                    db.delete(Item.TABLE_NAME, "item_id", Item.surroundWithSingleQuotes(itemID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    db.insert(Book.TABLE_NAME, newBookValues);
                } else if(category.equals(Video_Game.ATTRIBUTE)) {
                    Video_Game videoGame = Video_Game.getVideoGameByID(db, itemID);
                    String newID = "VI_" + report.getTitle() + "_" + report.getReleaseDate();

                    List<String> newVideoGameValues = Arrays.asList(Item.surroundWithSingleQuotes(newID),
                                                                    Item.surroundWithSingleQuotes(report.getTitle()),
                                                                    String.valueOf(report.getReleaseDate()),
                                                                    Item.surroundWithSingleQuotes(report.getProducer()),
                                                                    Item.surroundWithSingleQuotes(report.getURL()),
                                                                    Item.surroundWithSingleQuotes(report.getInfo()),
                                                                    Item.surroundWithSingleQuotes(videoGame.getUploader()),
                                                                    String.valueOf(videoGame.getScore()),
                                                                    String.valueOf(videoGame.getNumOfReviews()));

                    List<String> newItemValues = Arrays.asList( Item.surroundWithSingleQuotes(newID),
                                                                Item.surroundWithSingleQuotes(report.getTitle()),
                                                                Item.surroundWithSingleQuotes(category),
                                                                Item.surroundWithSingleQuotes(videoGame.getUploader()),
                                                                String.valueOf(videoGame.getScore()),
                                                                Item.surroundWithSingleQuotes(report.getURL()),
                                                                String.valueOf(report.getReleaseDate()),
                                                                String.valueOf(videoGame.getNumOfReviews()));

                    db.delete(Video_Game.TABLE_NAME, "video_game_id", Item.surroundWithSingleQuotes(itemID),
                            "video_game_id", Item.surroundWithSingleQuotes(itemID));
                    db.delete("REPORTS", "reportID", reportID, "reportID", reportID);

                    db.insert(Item.TABLE_NAME, newItemValues);
                    // UPDATE REVIEWS
                    db.update("REVIEWS", "item_id", Item.surroundWithSingleQuotes(newID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    // UPDATE NOTIFICATIONS

                    db.delete(Item.TABLE_NAME, "item_id", Item.surroundWithSingleQuotes(itemID),
                            "item_id", Item.surroundWithSingleQuotes(itemID));
                    db.insert(Video_Game.TABLE_NAME, newVideoGameValues);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            db.delete("REPORTS", "reportID", reportID, "reportID", reportID);
        }

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}