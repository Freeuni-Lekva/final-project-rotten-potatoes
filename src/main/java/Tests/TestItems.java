package Tests;

import Database.DB;
import Database.SQL;
import Models.Book;
import Models.Item;
import Models.Review;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestItems extends TestCase {

    private Item item;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "https://i.pinimg.com/originals/e5/75/24/e575246ab2b9487c171ce93cca646f3c.jpg";
        item = new Item("BO_Le Rouge et le Noir_1930", "Le Rouge et le Noir", "BOOKS","qeto",10,url ,1930, 100);

    }

    public void testGetCategoryByItemID(){
        assertEquals("BOOKS" , Item.getCategoryByItemID("BO_Black Mirror_2010"));
        assertEquals("MOVIES" , Item.getCategoryByItemID("MO_Black Mirror_2010"));
        assertEquals("MUSIC" , Item.getCategoryByItemID("MU_Black Mirror_2010"));
        assertEquals("VIDEO_GAMES" , Item.getCategoryByItemID("VI_Black Mirror_2010"));
        assertEquals("TV_SHOWS" , Item.getCategoryByItemID("TV_Black Mirror_2010"));
        assertEquals(null , Item.getCategoryByItemID("NO_Black Mirror_2010"));

    }

    public void testGetColumnByCategory(){
        assertEquals("movie_id" , Item.getColumnByCategory("MOVIES"));
        assertEquals("book_id" , Item.getColumnByCategory("BOOKS"));
        assertEquals("music_id" , Item.getColumnByCategory("MUSIC"));
        assertEquals("tv_show_id" , Item.getColumnByCategory("TV_SHOWS"));
        assertEquals("video_game_id" , Item.getColumnByCategory("VIDEO_GAMES"));
        assertEquals(null, Item.getColumnByCategory("NOT_EXISTED"));
    }

    public void testGetItemByID() throws SQLException {
        String url = "https://i.pinimg.com/originals/e5/75/24/e575246ab2b9487c171ce93cca646f3c.jpg";
        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("BO_Le Rouge et le Noir_1930")
                ,Item.surroundWithSingleQuotes("Le Rouge et le Noir"),Item.surroundWithSingleQuotes("BOOKS")
                ,Item.surroundWithSingleQuotes("qeto"), "10", Item.surroundWithSingleQuotes(url), "1930", "100" )));
     assertTrue(Item.getItemByID(db,"not existed")==null);
        Item itemById = Item.getItemByID(db,"BO_Le Rouge et le Noir_1930");

        assertTrue(item.equals(itemById));
        item.setScore(5);
        assertFalse(item.equals(itemById));
    }

    public void testGetReviewsSorted() throws SQLException {
        DB db = new SQL();
        String url = "https://i.pinimg.com/originals/e5/75/24/e575246ab2b9487c171ce93cca646f3c.jpg";

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qetobeto1"), Item.surroundWithSingleQuotes("admin")
                        ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));


        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_test_1950")
                ,Item.surroundWithSingleQuotes("test"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("qeto"), "5", Item.surroundWithSingleQuotes(url), "1950", "10" )));

        Review.addReview(db,"MO_test_1950", "qeto",7 , "Nice movie");
        Review.addReview(db,"MO_test_1950", "qetobeto1",3 , "Bad Movie");

        List<Review> r = Item.getReviewsSorted(db , "MO_test_1950", "SCORE DESC");
        assertEquals(r.get(0).getItemID(),r.get(1).getItemID());
        assertTrue(r.get(0).getScore() >= r.get(1).getScore());
    }

    public void testGetItems() throws SQLException {
        String url = "https://i.pinimg.com/originals/e5/75/24/e575246ab2b9487c171ce93cca646f3c.jpg";
        DB db = new SQL();
        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_Perfect Strangers_1950")
                ,Item.surroundWithSingleQuotes("Perfect Strangers"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("qeto"), "5", Item.surroundWithSingleQuotes(url), "1950", "10" )));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_Perfect Strangers_2016")
                ,Item.surroundWithSingleQuotes("Perfect Strangers"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("qeto"), "9.7", Item.surroundWithSingleQuotes(url), "2016", "110" )));

        List<Item> resultItems = Item.getItems(db, "MOVIES", "Perfect Strangers" , "SCORE ASC");
        assertEquals(resultItems.size() , 2);
        assertTrue(resultItems.get(0).getScore() < resultItems.get(1).getScore());

    }

    public void testGetItemIdWithoutSpaces(){
        assertEquals("BO_Black_Mirror_2010", Item.getItemIdWithoutSpaces("BO_Black Mirror_2010"));
    }

    public void testGetOriginalItemId(){
        assertEquals("BO_Black Mirror_2010", Item.getOriginalItemId("BO_Black_Mirror_2010"));
    }

    //test getter methods
    public void testGetItemId(){
        assertEquals("BO_Le Rouge et le Noir_1930", item.getItemID());
    }

    public void testGetTitle(){
        assertEquals("Le Rouge et le Noir" , item.getTitle());
    }

    public void testGetCategory(){
        assertEquals("BOOKS" , item.getCategory());
    }

    public void testGetUploader(){
        assertEquals("qeto" , item.getUploader());
    }

    public void testGetScore(){
        assertEquals(10.0 , item.getScore());
    }

    public void testGetCoverUrl(){
        String url = "https://i.pinimg.com/originals/e5/75/24/e575246ab2b9487c171ce93cca646f3c.jpg";
        assertEquals(url, item.getCoverURL());
    }

    public void testGetReleaseDate(){
        assertEquals(1930, item.getReleaseDate());
    }

    public void testGetNumOfReviews(){
        assertEquals(100, item.getNumOfReviews());
    }

    //test setter methods


    public void testSetItemId(){
        item.setItemID("MO_Book_1300");
        assertEquals("MO_Book_1300", item.getItemID());
    }

    public void testSetTitle(){
        item.setTitle("Book");
        assertEquals("Book", item.getTitle());
    }

    public void testSetCategory(){
        item.setCategory("MOVIES");
        assertEquals("MOVIES", item.getCategory());
    }

    public void testSetUploader(){
        item.setUploader("katie");
        assertEquals(item.getUploader(), "katie");
    }

    public void testSetScore(){
        item.setScore(9);
        assertEquals(9.0, item.getScore());
    }

    public void testSetCoverUrl(){
        String changedUrl = "https://m.media-amazon.com/images/I/51zeZd6abCL._SX260_.jpg";
        item.setCoverURL(changedUrl);
        assertEquals(changedUrl, item.getCoverURL());
    }

    public void testSetReleaseDate(){
        item.setReleaseDate(1300);
        assertEquals(1300, item.getReleaseDate());
    }

    public void testSetNumOfReviews(){
        item.setNumOfReviews(50);
        assertEquals(50 , item.getNumOfReviews());
    }

}
