package Models;

import Database.DB;
import Database.SQL;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item extends SQL {
    // !!!! ---------------------------------------------- !!!!
    // იმ ფუნქციების საბოლოო ფორმები, რომლებიც SQL ბრძანებებს იძახებენ არ არის
    // გადაწყვეტილი. შესაძლოა, რომ მათი პარამეტრების სიას DB db გამოაკლდეს. თუმცა,
    // იგი ამჟამად წერია, რათა მეთოდების გამოძახება მოვახერხოთ.

    // Constant variable declaration.
    public static final String ATTRIBUTE = "ITEMS"; // ამ ცვლადს ძირითადად Attribute-ის სახელად გამოვიყენებთ.
    public static final String TABLE_NAME = "ITEMS"; // ამ ცვლადს ძირითადად SQL ბრძანებებს გადავცემთ პარამეტრად.
    private static final String REVIEWS = "REVIEWS";
    public static final String EQUALITY_COLUMN = "CATEGORY";
    public static final String LIKENESS_COLUMN = "TITLE";

    // Instance variable declarations.
    private String itemID;
    private String title;
    private String category;
    private String uploader;
    private double score;
    private String coverURL;
    private int releaseDate;
    private int numOfReviews;

    // Constructor.
    public Item(String itemID, String title, String category, String uploader, double score, String coverURL,
                int releaseDate, int numOfReviews){
        this.itemID = itemID;
        this.title = title;
        this.category = category;
        this.uploader = uploader;
        this.score = score;
        this.coverURL = coverURL;
        this.releaseDate = releaseDate;
        this.numOfReviews = numOfReviews;
    }

    // ბევრ SQL ბრძანებაში გვიწევს სტრინგის ერთხაზიან ბრჭყალებში მოქცევა. ფუნქციას გადაეცემა სტრინგი, ის აბრუნებს იგივე
    // სტრინგს ბრჭყალებით (მაგ; pie -> 'pie').
    public static String surroundWithSingleQuotes(String value){
        StringBuilder quotedValue = new StringBuilder();
        quotedValue.append("'");
        quotedValue.append(value);
        quotedValue.append("'");
        return quotedValue.toString();
    }

    // მეთოდს გადაეცემა itemID (რომელიც შედგენილია შემდეგი წესით: კატეგორიის პირველი ორი ასო_სათაური_გამოშვების წელი.
    // მაგ; MO_Cruella_2021. მეთოდი აბრუნებს იმ კატეგორიას, რომელსაც ეს ნივთი ეკუთვნის.
    public static String getCategoryByItemID(String itemID){
        StringBuilder categoryPrefix = new StringBuilder(itemID);
        String prefix = categoryPrefix.substring(0, 2);
        if(prefix.equals("MO")){
            return Movie.TABLE_NAME;
        } else if(prefix.equals("TV")){
            return TV_Show.TABLE_NAME;
        } else if(prefix.equals("MU")){
            return Music.TABLE_NAME;
        } else if(prefix.equals("BO")){
            return Book.TABLE_NAME;
        } else if(prefix.equals("VI")){
            return Video_Game.TABLE_NAME;
        }
        return null;
    }

    // ფუნქციას გადაეცემა კატეგორიის სახელი და აბრუნებს ამ კატეგორიაში იმ სვეტის სახელს, რომელშიც
    // ნივთის იდენტიფიკატორი ინახება.
    public static String getColumnByCategory(String category){
        if(category.equals(Movie.TABLE_NAME)){
            return "movie_id";
        } else if(category.equals(TV_Show.TABLE_NAME)){
            return "tv_show_id";
        } else if(category.equals(Music.TABLE_NAME)){
            return "music_id";
        } else if(category.equals(Book.TABLE_NAME)){
            return "book_id";
        } else if(category.equals(Video_Game.TABLE_NAME)){
            return "video_game_id";
        }
        return null;
    }

    // მეთოდს გადაეცემა itemID (რომლის შექმნის წესიც წინა მეთოდის კომენტარში ვახსენეთ). მეთოდი იძახებს
    // SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი ცხრილში და წარმატების შემთხვევაში, აბრუნებს Item ობიექტს.
    public static Item getItemByID(DB db, String itemID) throws SQLException {
        ResultSet singleItemRow = db.conditionedSelect(TABLE_NAME, "item_id",
                                                        surroundWithSingleQuotes(itemID));
        while(singleItemRow.next()){
            Item item = new Item(singleItemRow.getString(1), singleItemRow.getString(2),
                                singleItemRow.getString(3), singleItemRow.getString(4),
                                singleItemRow.getDouble(5), singleItemRow.getString(6),
                                singleItemRow.getInt(7), singleItemRow.getInt(8));
            return item;
        }
        return null;
    }

    //აბრუნებს კონკრეტული აითემის შესახებ დაწერილ ყველა review-ს 
    public static List<Review> getReviews(DB db , String itemID) throws SQLException {
        List<Review> itemReviews = new ArrayList<>();
        ResultSet reviews = db.conditionedSelect(REVIEWS, "item_id",
                Item.surroundWithSingleQuotes(itemID));
        while(reviews.next()){
            Review review = new Review(reviews.getString(1) , reviews.getString(2) ,
                    reviews.getDouble(3) , reviews.getString(4) , reviews.getString(5));
            itemReviews.add(review);
        }
        return  itemReviews;
    }
    // აბრუნებს Item-ების სიას შემდეგი მახასიათებლების მიხედვით: სიის ნივთები ერთ გადმოცემულ კატეგორიაში არიან,
    // ასევე, მათი სათაურები გადაცემულ '%searchFieldValue%'-ს ემთხვევა და ეს ნივთები დალაგებულია orderByValue-თი.
    public static List<Item> getItems(DB db, String category, String searchFieldValue, String orderByValue) throws SQLException {
        List<Item> items = new ArrayList<Item>();

        // Reformat parameters.
        String[] split = orderByValue.split(" ");

        String EQUALITY_VALUE = surroundWithSingleQuotes(category.toUpperCase()); // .toUpperCase() might be redundant

        StringBuilder LIKENESS_VALUE = new StringBuilder();
        LIKENESS_VALUE.append("'%");
        LIKENESS_VALUE.append(searchFieldValue);
        LIKENESS_VALUE.append("%'");

        String ORDER_COLUMN = split[0];
        String DESC_OR_ASC = split[1];

        ResultSet rowsOfItems = db.conditionedAndOrderedSelect(TABLE_NAME, EQUALITY_COLUMN, EQUALITY_VALUE,
                                                                LIKENESS_COLUMN, LIKENESS_VALUE.toString(),
                                                                ORDER_COLUMN, DESC_OR_ASC);

        while(rowsOfItems.next()){
            Item item = new Item(rowsOfItems.getString(1), rowsOfItems.getString(2),
                                rowsOfItems.getString(3), rowsOfItems.getString(4),
                                rowsOfItems.getDouble(5), rowsOfItems.getString(6),
                                rowsOfItems.getInt(7), rowsOfItems.getInt(8));
            items.add(item);
        }
        return items;
    }

    // Getter methods.
    public String getItemID(){
        return itemID;
    }

    public String getTitle(){
        return title;
    }

    public String getCategory(){
        return category;
    }

    public String getUploader(){
        return uploader;
    }

    public double getScore(){
        return score;
    }

    public String getCoverURL(){
        return coverURL;
    }

    public int getReleaseDate(){ return releaseDate; }

    public int getNumOfReviews(){ return numOfReviews; }

    // Setter methods.
    public void setItemID(String itemID){
        this.itemID = itemID;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setUploader(String uploader){
        this.uploader = uploader;
    }

    public void setScore(double score){
        this.score = score;
    }

    public void setCoverURL(String coverURL){
        this.coverURL = coverURL;
    }

    public void setReleaseDate(int releaseDate){ this.releaseDate = releaseDate; }

    public void setNumOfReviews(int numOfReviews){ this.numOfReviews = numOfReviews; }
}