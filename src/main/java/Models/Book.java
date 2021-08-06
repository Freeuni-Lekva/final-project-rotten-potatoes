package Models;

import java.util.List;

public class Book {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "BOOKS";
    public static final String TABLE_NAME = "BOOKS";

    // Instance variable declarations.
    private String bookID;
    private String title;
    private int releaseDate;
    private String writer;
    private String coverURL;
    private String summary;
    private String uploader;
    private double score;
    private int numOfReviews;

    // Constructor.
    public Book(String bookID, String title, int releaseDate, String writer, String coverURL, String summary,
            String uploader, double score, int numOfReviews){
        this.bookID = bookID;
        this.title = title;
        this.releaseDate = releaseDate;
        this.writer = writer;
        this.coverURL = coverURL;
        this.summary = summary;
        this.uploader = uploader;
        this.score = score;
        this.numOfReviews = numOfReviews;
    }

    public Book getBookByID(String bookID){
        // TO BE IMPLEMENTED
        return null;
    }

    public List<Book> getBooks(String sortType){
        // TO BE IMPLEMENTED
        return null;
    }

    // Getter methods.
    public String getBookID(){
        return bookID;
    };

    public String getTitle(){
        return title;
    };

    public int getReleaseDate(){
        return releaseDate;
    };

    public String getWriter(){
        return writer;
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
    public void setBookID(String bookID){
        this.bookID = bookID;
    };

    public void setTitle(String title){
        this.title = title;
    };

    public void setReleaseDate(int releaseDate){
        this.releaseDate = releaseDate;
    };

    public void setWriter(String writer){
        this.writer = writer;
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