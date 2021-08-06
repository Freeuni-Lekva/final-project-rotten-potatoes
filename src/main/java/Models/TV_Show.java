package Models;

public class TV_Show {
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
}