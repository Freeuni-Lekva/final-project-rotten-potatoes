package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Book {
    // !!!! ---------------------------------------------- !!!!
    // იმ ფუნქციების საბოლოო ფორმები, რომლებიც SQL ბრძანებებს იძახებენ არ არის
    // გადაწყვეტილი. შესაძლოა, რომ მათი პარამეტრების სიას DB db გამოაკლდეს. თუმცა,
    // იგი ამჟამად წერია, რათა მეთოდების გამოძახება მოვახერხოთ.

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

    // მეთოდს გადაეცემა bookID. მეთოდი იძახებს SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი
    // ცხრილში და წარმატების შემთხვევაში, აბრუნებს Book ობიექტს.
    public static Book getBookByID(DB db, String bookID) throws SQLException {
        ResultSet singleBookRow = db.conditionedSelect(TABLE_NAME, "book_id",
                Item.surroundWithSingleQuotes(bookID));
        while(singleBookRow.next()){
            Book book = new Book(singleBookRow.getString(1), singleBookRow.getString(2),
                                singleBookRow.getInt(3), singleBookRow.getString(4),
                                singleBookRow.getString(5), singleBookRow.getString(6),
                                singleBookRow.getString(7), singleBookRow.getDouble(8),
                                singleBookRow.getInt(9));
            return book;
        }
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