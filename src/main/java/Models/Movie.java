package Models;

public class Movie {
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
}