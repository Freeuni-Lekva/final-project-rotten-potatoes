package Models;

import Database.DB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Review {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "REVIEWS";
    public static final String TABLE_NAME = "REVIEWS";

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

    // ამატებს review-ს, რომელიც მომხმარებელმა გააკეთა, შესაბამის ცხრილში.
    public int addReview(DB db, String itemID, String username, double score, String review){
        String category = Item.getCategoryByItemID(itemID);
        String columnName = Item.getColumnByCategory(category);
        List<String> values = Arrays.asList(Item.surroundWithSingleQuotes(itemID),
                                            Item.surroundWithSingleQuotes(username),
                                            String.valueOf(score), Item.surroundWithSingleQuotes(review),
                                            Item.surroundWithSingleQuotes(category));
        int insertResult = db.insert(TABLE_NAME, values);
        if(insertResult == Database.SQL.SQL_SUCCESS){
            String computeAverage = "(num_of_reviews * score + " + score + ") / (num_of_reviews + 1)";
            // Update average score in the specific category.
            db.update(category, "score", computeAverage, columnName,
                    Item.surroundWithSingleQuotes(itemID));
            // Update average score in the Items' table.
            db.update(Item.TABLE_NAME, "score", computeAverage, columnName,
                    Item.surroundWithSingleQuotes(itemID));
            // Increment number of reviews in the specific category.
            db.update(category, "num_of_reviews", "num_of_reviews + 1", columnName,
                    Item.surroundWithSingleQuotes(itemID));
            // Increment number of reviews in the Items' table.
            db.update(Item.TABLE_NAME, "num_of_reviews", "num_of_reviews + 1", columnName,
                    Item.surroundWithSingleQuotes(itemID));
        }
        return insertResult;
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