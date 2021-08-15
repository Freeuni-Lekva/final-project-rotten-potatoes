package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Music {
    // !!!! ---------------------------------------------- !!!!
    // იმ ფუნქციების საბოლოო ფორმები, რომლებიც SQL ბრძანებებს იძახებენ არ არის
    // გადაწყვეტილი. შესაძლოა, რომ მათი პარამეტრების სიას DB db გამოაკლდეს. თუმცა,
    // იგი ამჟამად წერია, რათა მეთოდების გამოძახება მოვახერხოთ.

    // Constant variable declaration.
    public static final String ATTRIBUTE = "MUSIC";
    public static final String TABLE_NAME = "MUSIC";

    // Instance variable declarations.
    private String musicID;
    private String artist;
    private String title;
    private String label;
    private int releaseYear;
    private String genre;
    private String albumCoverURL;
    private String uploader;
    private double score;
    private int numOfReviews;

    // Constructor.
    public Music(String musicID, String artist, String title, String label, int releaseYear, String genre,
                 String albumCoverURL, String uploader, double score, int numOfReviews){
        this.musicID = musicID;
        this.artist = artist;
        this.title = title;
        this.label = label;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.albumCoverURL = albumCoverURL;
        this.uploader = uploader;
        this.score = score;
        this.numOfReviews = numOfReviews;
    }

    // მეთოდს გადაეცემა musicID. მეთოდი იძახებს SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი
    // ცხრილში და წარმატების შემთხვევაში, აბრუნებს Music ობიექტს.
    public static Music getMusicByID(DB db, String musicID) throws SQLException {
        ResultSet singleMusicRow = db.conditionedSelect(TABLE_NAME, "music_id",
                Item.surroundWithSingleQuotes(musicID));

        while(singleMusicRow.next()){
            Music music = new Music
                    (singleMusicRow.getString(1), singleMusicRow.getString(2),
                    singleMusicRow.getString(3), singleMusicRow.getString(4),
                    singleMusicRow.getInt(5), singleMusicRow.getString(6),
                    singleMusicRow.getString(7), singleMusicRow.getString(8),
                    singleMusicRow.getDouble(9), singleMusicRow.getInt(10));
            return music;
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        Music m = (Music) o;
        if(m.getMusicID().equals(this.getMusicID())
                && m.getArtist().equals(this.getArtist())
                && m.getTitle().equals(this.getTitle())
                && m.getLabel().equals(this.getLabel())
                && m.getReleaseYear() == this.getReleaseYear()
                && m.getGenre().equals(this.getGenre())
                && m.getAlbumCoverURL().equals(this.getAlbumCoverURL())
                && m.getUploader().equals(this.getUploader())
                && m.getScore() == this.getScore()
                && m.getNumOfReviews() == this.getNumOfReviews()) return true;
        return false;
    }

    // Getter methods.
    public String getMusicID(){
        return musicID;
    };

    public String getArtist(){
        return artist;
    };

    public String getTitle(){
        return title;
    };

    public String getLabel(){
        return label;
    };

    public int getReleaseYear(){
        return releaseYear;
    };

    public String getGenre(){
        return genre;
    };

    public String getAlbumCoverURL(){
        return albumCoverURL;
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
    public void setMusicID(String musicID){
        this.musicID = musicID;
    };

    public void setArtist(String artist){
        this.artist = artist;
    };

    public void setTitle(String title){
        this.title = title;
    };

    public void setLabel(String label){
        this.label = label;
    };

    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
    };

    public void setGenre(String genre){
        this.genre = genre;
    };

    public void setAlbumCoverURL(String albumCoverURL){
        this.albumCoverURL = albumCoverURL;
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