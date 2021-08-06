package Models;

public class Item {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "ITEMS";

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

    public Item getItemByID(String itemID){
        // TO BE IMPLEMENTED
        return null;
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