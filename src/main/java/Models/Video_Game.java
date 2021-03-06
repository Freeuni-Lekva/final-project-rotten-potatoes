package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Video_Game {
    // !!!! ---------------------------------------------- !!!!
    // იმ ფუნქციების საბოლოო ფორმები, რომლებიც SQL ბრძანებებს იძახებენ არ არის
    // გადაწყვეტილი. შესაძლოა, რომ მათი პარამეტრების სიას DB db გამოაკლდეს. თუმცა,
    // იგი ამჟამად წერია, რათა მეთოდების გამოძახება მოვახერხოთ.

    // Constant variable declaration.
    public static final String ATTRIBUTE = "VIDEO_GAMES";
    public static final String TABLE_NAME = "VIDEO_GAMES";

    // Instance variable declarations.
    private String videoGameID;
    private String title;
    private int releaseDate;
    private String developers;
    private String coverURL;
    private String summary;
    private String uploader;
    private double score;
    private int numOfReviews;

    // Constructor.
    public Video_Game(String videoGameID, String title, int releaseDate, String developers, String coverURL,
                      String summary, String uploader, double score, int numOfReviews){
        this.videoGameID = videoGameID;
        this.title = title;
        this.releaseDate = releaseDate;
        this.developers = developers;
        this.coverURL = coverURL;
        this.summary = summary;
        this.uploader = uploader;
        this.score = score;
        this.numOfReviews = numOfReviews;
    }

    // მეთოდს გადაეცემა videoGameID. მეთოდი იძახებს SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი
    // ცხრილში და წარმატების შემთხვევაში, აბრუნებს Video_Game ობიექტს.
    public static Video_Game getVideoGameByID(DB db, String videoGameID) throws SQLException {
        ResultSet singleVideoGameRow = db.conditionedSelect(TABLE_NAME, "video_game_id",
                Item.surroundWithSingleQuotes(videoGameID));

        while(singleVideoGameRow.next()){
            Video_Game videoGame = new Video_Game
                    (singleVideoGameRow.getString(1), singleVideoGameRow.getString(2),
                    singleVideoGameRow.getInt(3), singleVideoGameRow.getString(4),
                    singleVideoGameRow.getString(5), singleVideoGameRow.getString(6),
                    singleVideoGameRow.getString(7), singleVideoGameRow.getDouble(8),
                    singleVideoGameRow.getInt(9));
            return videoGame;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        Video_Game v = (Video_Game) o;
        if(v.getVideoGameID().equals(this.getVideoGameID())
                && v.getTitle().equals(this.getTitle())
                && v.getReleaseDate() == this.getReleaseDate()
                && v.getDevelopers().equals(this.getDevelopers())
                && v.getCoverURL().equals(this.getCoverURL())
                && v.getSummary().equals(this.getSummary())
                && v.getUploader().equals(this.getUploader())
                && v.getScore() == this.getScore()
                && v.getNumOfReviews() == this.getNumOfReviews()) return true;
        return false;
    }

    // Getter methods.
    public String getVideoGameID(){
        return videoGameID;
    };

    public String getTitle(){
        return title;
    };

    public int getReleaseDate(){
        return releaseDate;
    };

    public String getDevelopers(){
        return developers;
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
    public void setVideoGameID(String videoGameID){
        this.videoGameID = videoGameID;
    };

    public void setTitle(String title){
        this.title = title;
    };

    public void setReleaseDate(int releaseDate){
        this.releaseDate = releaseDate;
    };

    public void setDevelopers(String developers){
        this.developers = developers;
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