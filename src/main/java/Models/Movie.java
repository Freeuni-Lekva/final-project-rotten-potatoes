package Models;

public class Movie {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "MOVIES";

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

    public Movie getMovieByID(String movieID){
        // TO BE IMPLEMENTED
        return null;
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