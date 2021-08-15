package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TV_Show {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "TV_SHOWS";
    public static final String TABLE_NAME = "TV_SHOWS";

    // Instance variable declarations.
    private String tvShowID;
    private String title;
    private int airingYear;
    private String director;
    private String tvShowCast;
    private String coverURL;
    private String summary;
    private String uploader;
    private double score;
    private int numOfReviews;

    // Constructor.
    public TV_Show(String tvShowID, String title, int airingYear, String director, String tvShowCast, String coverURL,
                   String summary, String uploader, double score, int numOfReviews){
        this.tvShowID = tvShowID;
        this.title = title;
        this.airingYear = airingYear;
        this.director = director;
        this.tvShowCast = tvShowCast;
        this.coverURL = coverURL;
        this.summary = summary;
        this.uploader = uploader;
        this.score = score;
        this.numOfReviews = numOfReviews;
    }

    // მეთოდს გადაეცემა tvShowID. მეთოდი იძახებს SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი
    // ცხრილში და წარმატების შემთხვევაში, აბრუნებს TV_Show ობიექტს.
    public static TV_Show getTVShowByID(DB db, String tvShowID) throws SQLException {
        ResultSet singleTVShowRow = db.conditionedSelect(TABLE_NAME, "tv_show_id",
                Item.surroundWithSingleQuotes(tvShowID));

        while(singleTVShowRow.next()){
            TV_Show tvShow = new TV_Show
                    (singleTVShowRow.getString(1), singleTVShowRow.getString(2),
                    singleTVShowRow.getInt(3), singleTVShowRow.getString(4),
                    singleTVShowRow.getString(5), singleTVShowRow.getString(6),
                    singleTVShowRow.getString(7), singleTVShowRow.getString(8),
                    singleTVShowRow.getDouble(9), singleTVShowRow.getInt(10));
            return tvShow;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        TV_Show t = (TV_Show) o;
        if(t.getTVShowID().equals(this.getTVShowID())
                && t.getTitle().equals(this.getTitle())
                && t.getAiringYear() == this.getAiringYear()
                && t.getDirector().equals(this.getDirector())
                && t.getTVShowCast().equals(this.getTVShowCast())
                && t.getCoverURL().equals(this.getCoverURL())
                && t.getSummary().equals(this.getSummary())
                && t.getUploader().equals(this.getUploader())
                && t.getScore() == this.getScore()
                && t.getNumOfReviews() == this.getNumOfReviews()) return true;
        return false;
    }

    // Getter methods.
    public String getTVShowID(){
        return tvShowID;
    };

    public String getTitle(){
        return title;
    };

    public int getAiringYear(){
        return airingYear;
    };

    public String getDirector(){
        return director;
    };

    public String getTVShowCast(){
        return tvShowCast;
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
    public void setTVShowID(String tvShowID){
        this.tvShowID = tvShowID;
    };

    public void setTitle(String title){
        this.title = title;
    };

    public void setAiringYear(int airingYear){
        this.airingYear = airingYear;
    };

    public void setDirector(String director){
        this.director = director;
    };

    public void setTVShowCast(String tvShowCast){
        this.tvShowCast = tvShowCast;
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