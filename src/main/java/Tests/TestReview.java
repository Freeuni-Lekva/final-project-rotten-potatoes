package Tests;

import Models.Review;
import junit.framework.TestCase;

public class TestReview extends TestCase {
    private Review r ;

    @Override
    protected void setUp() throws Exception {
        r = new Review("BO_test_2005", "qeto", 7, "Good book", "BOOKS");
    }

      public void testAddReview() throws SQLException {
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
    
    //Test getter methods

    public void testGetItemId(){
        assertEquals("BO_test_2005", r.getItemID());
    }

    public void testGetUsername(){
        assertEquals("qeto", r.getUsername());
    }

    public void testGetScore(){
        assertEquals(7.0, r.getScore());
    }

    public void testGetReview(){
        assertEquals("Good book", r.getReview());
    }

    public void testGetCategory(){
        assertEquals("BOOKS", r.getCategory());
    }

    //Test setter methods

    public void testSetItemId(){
        r.setItemID("MO_test_2001");
        assertEquals("MO_test_2001", r.getItemID());
    }

    public void testSetUsername(){
        r.setUsername("katie");
        assertEquals("katie", r.getUsername());
    }

    public void testSetScore(){
        r.setScore(5);
        assertEquals(5.0, r.getScore());
    }

    public void testSetReview(){
        r.setReview("not so good");
        assertEquals("not so good", r.getReview());
    }

    public void testSetCategory(){
        r.setCategory("MOVIES");
        assertEquals("MOVIES", r.getCategory());
    }

}
