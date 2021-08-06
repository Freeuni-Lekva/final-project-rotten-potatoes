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

    public TV_Show getTVShowByID(String tvShowID){
        // TO BE IMPLEMENTED
        return null;
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