package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Movie {
    // !!!! ---------------------------------------------- !!!!
    // იმ ფუნქციების საბოლოო ფორმები, რომლებიც SQL ბრძანებებს იძახებენ არ არის
    // გადაწყვეტილი. შესაძლოა, რომ მათი პარამეტრების სიას DB db გამოაკლდეს. თუმცა,
    // იგი ამჟამად წერია, რათა მეთოდების გამოძახება მოვახერხოთ.

    // Constant variable declaration.
    public static final String ATTRIBUTE = "MOVIES";
    public static final String TABLE_NAME = "MOVIES";

    // Instance variable declarations.
    private String movieID;
    private String title;
    private int releaseDate;
    private String director;
    private String movieCast;
    private String coverURL;
    private String summary;
    private String uploader;
    private double score;
    private int numOfReviews;

    // Constructor.
    public Movie(String movieID, String title, int releaseDate, String director, String movieCast, String coverURL,
                 String summary, String uploader, double score, int numOfReviews){
        this.movieID = movieID;
        this.title = title;
        this.releaseDate = releaseDate;
        this.director = director;
        this.movieCast = movieCast;
        this.coverURL = coverURL;
        this.summary = summary;
        this.uploader = uploader;
        this.score = score;
        this.numOfReviews = numOfReviews;
    }

    // მეთოდს გადაეცემა movieID. მეთოდი იძახებს SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი
    // ცხრილში და წარმატების შემთხვევაში, აბრუნებს Movie ობიექტს.
    public static Movie getMovieByID(DB db, String movieID) throws SQLException {
        ResultSet singleMovieRow = db.conditionedSelect(TABLE_NAME, "movie_id",
                Item.surroundWithSingleQuotes(movieID));

        while(singleMovieRow.next()){
            Movie movie = new Movie
                    (singleMovieRow.getString(1), singleMovieRow.getString(2),
                    singleMovieRow.getInt(3), singleMovieRow.getString(4),
                    singleMovieRow.getString(5), singleMovieRow.getString(6),
                    singleMovieRow.getString(7), singleMovieRow.getString(8),
                    singleMovieRow.getDouble(9), singleMovieRow.getInt(10));
            return movie;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        Movie m = (Movie) o;
        if(m.getMovieID().equals(this.getMovieID())
           && m.getTitle().equals(this.getTitle())
           && m.getReleaseDate() == this.getReleaseDate()
           && m.getDirector().equals(this.getDirector())
           && m.getMovieCast().equals(this.getMovieCast())
           && m.getCoverURL().equals(this.getCoverURL())
           && m.getSummary().equals(this.getSummary())
           && m.getUploader().equals(this.getUploader())
           && m.getScore() == this.getScore()
           && m.getNumOfReviews() == this.getNumOfReviews()) return true;
        return false;
    }

    // Getter methods.
    public String getMovieID(){
        return movieID;
    };

    public String getTitle(){
        return title;
    };

    public int getReleaseDate(){
        return releaseDate;
    };

    public String getDirector(){
        return director;
    };

    public String getMovieCast(){
        return movieCast;
    };

    public String getCoverURL(){
        return coverURL;
    };

    public String getSummary(){
        return summary;
    };

    public String getUploader(){
        return uploader;
    };

    public double getScore(){
        return score;
    };

    public int getNumOfReviews(){
        return numOfReviews;
    };

    // Setter methods.
    public void setMovieID(String movieID){
        this.movieID = movieID;
    };

    public void setTitle(String title){
        this.title = title;
    }

    public void setReleaseDate(int releaseDate){
        this.releaseDate = releaseDate;
    };

    public void setDirector(String director){
        this.director = director;
    };

    public void setMovieCast(String movieCast){
        this.movieCast = movieCast;
    };

    public void setCoverURL(String coverURL){
        this.coverURL = coverURL;
    };

    public void setSummary(String summary){
        this.summary = summary;
    };

    public void setUploader(String uploader){
        this.uploader = uploader;
    };

    public void setScore(double score){
        this.score = score;
    };

    public void setNumOfReviews(int numOfReviews){
        this.numOfReviews = numOfReviews;
    };
}