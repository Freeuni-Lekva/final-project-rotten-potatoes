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