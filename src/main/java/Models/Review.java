package Models;

public class Review {
    // Instance variable declarations.
    private String itemID;
    private String username;
    private double score;
    private String review;
    private String category;

    // Constructor.
    public Review(String itemID, String username, double score, String review, String category){
        this.itemID = itemID;
        this.username = username;
        this.score = score;
        this.review = review;
        this.category = category;
    }
}