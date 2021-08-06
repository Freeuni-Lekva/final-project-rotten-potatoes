package Models;

public class Review {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "REVIEWS";

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

    // Getter methods.
    public String getItemID(){
        return itemID;
    };

    public String getUsername(){
        return username;
    };

    public double getScore(){
        return score;
    };

    public String getReview(){
        return review;
    };

    public String getCategory(){
        return category;
    };

    // Setter methods.
    public void getItemID(String itemID){
        this.itemID = itemID;
    };

    public void getUsername(String username){
        this.username = username;
    };

    public void getScore(double score){
        this.score = score;
    };

    public void getReview(String review){
        this.review = review;
    };

    public void getCategory(String category){
        this.category = category;
    };
}