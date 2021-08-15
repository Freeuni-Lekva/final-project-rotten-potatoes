package Tests;

import Database.DB;
import Database.SQL;
import Models.Item;
import Models.Movie;
import Models.TV_Show;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestTVShows extends TestCase {

    private TV_Show t;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        t = new TV_Show("TV_Black_1968" , "Black" ,1968, "Brad Depp" ,"Brad Pitt , Johny Depp"  ,url , "A TV Show about a black  hole" , "qeto" , 0 , 0);
    }

    public void testGetTVShowById() throws SQLException {
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("TV_Black_1968")
                ,Item.surroundWithSingleQuotes("Black"),Item.surroundWithSingleQuotes("TV_SHOWS")
                ,Item.surroundWithSingleQuotes("qeto"), "0", Item.surroundWithSingleQuotes(url), "1968", "0" )));
        db.insert("TV_SHOWS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("TV_Black_1968")
                ,Item.surroundWithSingleQuotes("Black") , "1968"
                ,Item.surroundWithSingleQuotes("Brad Depp"),Item.surroundWithSingleQuotes("Brad Pitt , Johny Depp")
                ,Item.surroundWithSingleQuotes(url), Item.surroundWithSingleQuotes("A TV Show about a black  hole"), Item.surroundWithSingleQuotes("qeto"), "0" ,"0")));

        TV_Show tvShowById = TV_Show.getTVShowByID(db,"TV_Black_1968");
        assertTrue(TV_Show.getTVShowByID(db,"not existed")==null);
        assertTrue(t.equals(tvShowById));
        t.setScore(5);
        assertFalse(t.equals(tvShowById));
    }

    public void testGetTVShowID(){
        assertEquals("TV_Black_1968" , t.getTVShowID());
    }
    public void testGetTitle(){
        assertEquals("Black" , t.getTitle());
    }

    public void testGetAiringYear(){
        assertEquals(1968 , t.getAiringYear());
    }

    public void testGetDirector(){
        assertEquals("Brad Depp" , t.getDirector());
    }

    public void getTVShowCast(){
        assertEquals("Brad Pitt , Johny Depp" , t.getTVShowCast());
    }
    public void testGetCoverURL(){
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        assertEquals(url , t.getCoverURL());
    }

    public void testGetSummary(){
        String summary = "A TV Show about a black  hole";
        assertEquals(summary , t.getSummary());
    }

    public void testGetUploader(){
        assertEquals("qeto" , t.getUploader());
    }

    public void testGetScore(){
        assertEquals(0.0 , t.getScore());
    }

    public void testGetNumOfReviews(){
        assertEquals(0 , t.getNumOfReviews());
    }


    public void testSetTVShowID(){
        String changedBookId = "TV_Fair_2020";
        t.setTVShowID(changedBookId);
        assertEquals(changedBookId , t.getTVShowID());
    }

    public void testSetTitle(){
        String newTitle = "Fair";
        t.setTitle(newTitle);
        assertEquals(newTitle , t.getTitle());
    }

    public void testSetReleaseDate(){
        t.setAiringYear(2020);
        assertEquals(2020 , t.getAiringYear());
    }

    public void testSetDirector(){
        t.setDirector("George Lone");
        assertEquals("George Lone" , t.getDirector());
    }

    public void testSetTVShowCast(){
        t.setTVShowCast("petre , pavle");
        assertEquals("petre , pavle" , t.getTVShowCast());
    }

    public void testSetCoverUR(){
        String url = "https://staticg.sportskeeda.com/editor/2021/07/fc7da-16254085781511-800.jpg";
        t.setCoverURL(url);
        assertEquals(url , t.getCoverURL());
    }


    public void testSetSummary(){
        String newSummary = "A TV Show about a black person";
        t.setSummary(newSummary);
        assertEquals(newSummary, t.getSummary());
    }

    public void testSetUploader(){
        t.setUploader("Brad");
        assertEquals("Brad" , t.getUploader());
    }

    public void testSetScore(){
        double newScore = t.getScore() + 1;
        t.setScore(newScore);
        assertEquals(newScore, t.getScore());

        t.setScore(39);
        assertEquals(39.0 , t.getScore());
    }

    public void testSetNumOfReviews(){
        int newReview = t.getNumOfReviews() + 1;
        t.setNumOfReviews(newReview);
        assertEquals(newReview, t.getNumOfReviews());

        t.setNumOfReviews(12);
        assertEquals(12 , t.getNumOfReviews());
    }
}
