package Models;

import Database.DB;
import Database.SQL;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item extends SQL {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "ITEMS";
    public static final String TABLE_NAME = "ITEMS";
    public static final String EQUALITY_COLUMN = "CATEGORY";
    public static final String LIKENESS_COLUMN = "TITLE";

    // Instance variable declarations.
    private String itemID;
    private String title;
    private String category;
    private String uploader;
    private double score;
    private String coverURL;

    // Constructor.
    public Item(String itemID, String title, String category, String uploader, double score, String coverURL){
        this.itemID = itemID;
        this.title = title;
        this.category = category;
        this.uploader = uploader;
        this.score = score;
        this.coverURL = coverURL;
    }

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

    public Item getItemByID(String itemID){
        // TO BE IMPLEMENTED
        return null;
    }

    // ******* TO BE TESTED
    public List<Item> getItems(DB db, String category, String searchFieldValue, String orderByValue) throws SQLException {
        List<Item> items = new ArrayList<Item>();

        // Reformat parameters.
        String[] split = orderByValue.split("\\s+");

        StringBuilder EQUALITY_VALUE = new StringBuilder();
        EQUALITY_VALUE.append("'");
        EQUALITY_VALUE.append(category.toUpperCase()); // .toUpperCase() might be redundant
        EQUALITY_VALUE.append("'");

        StringBuilder LIKENESS_VALUE = new StringBuilder();
        EQUALITY_VALUE.append("'%");
        EQUALITY_VALUE.append(searchFieldValue);
        EQUALITY_VALUE.append("%'");

        String ORDER_COLUMN = split[0];
        String DESC_OR_ASC = split[1];

        ResultSet rowsOfItems = db.conditionedAndOrderedSelect(TABLE_NAME, EQUALITY_COLUMN, EQUALITY_VALUE.toString(),
                                                                LIKENESS_COLUMN, LIKENESS_VALUE.toString(),
                                                                ORDER_COLUMN, DESC_OR_ASC);

        while(rowsOfItems.next()){
            Item item = new Item(rowsOfItems.getString(0), rowsOfItems.getString(1),
                                rowsOfItems.getString(2), rowsOfItems.getString(3),
                                rowsOfItems.getDouble(4), rowsOfItems.getString(5));
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
}