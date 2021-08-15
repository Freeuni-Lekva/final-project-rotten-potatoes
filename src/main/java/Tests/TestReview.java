package Tests;

import Models.Review;
import junit.framework.TestCase;

public class TestReview extends TestCase {
    private Review r ;

    @Override
    protected void setUp() throws Exception {
        r = new Review("BO_test_2005", "qeto", 7, "Good book", "BOOKS");
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
