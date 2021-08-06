package Models;

public class Item {
    // Instance variable declarations.
    private String itemID;
    private String category;
    private String uploader;
    private double score;
    private String coverURL;

    // Constructor.
    public Item(String itemID, String category, String uploader, double score, String coverURL){
        this.itemID = itemID;
        this.category = category;
        this.uploader = uploader;
        this.score = score;
        this.coverURL = coverURL;
    }

    public String getTitle(){
        // IMPLEMENT
        return null;
    }

    public String getCoverURL(){
        // IMPLEMENT
        return null;
    }

    public int getScore(){
        // IMPLEMENT
        return -1;
    }
}