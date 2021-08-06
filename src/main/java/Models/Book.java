package Models;

public class Book {
    // Instance variable declarations.
    String bookID;
    String title;
    int releaseDate;
    String writer;
    String coverURL;
    String summary;
    String uploader;
    double score;
    int numOfReviews;

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
}