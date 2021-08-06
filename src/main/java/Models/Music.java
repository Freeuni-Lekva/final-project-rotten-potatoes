package Models;

public class Music {
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
}