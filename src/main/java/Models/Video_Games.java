package Models;

public class Video_Games {
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
    public Video_Games(String videoGameID, String title, int releaseDate, String developers, String coverURL,
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
}