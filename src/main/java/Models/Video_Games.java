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