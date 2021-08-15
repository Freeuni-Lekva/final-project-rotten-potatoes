package Tests;

import Database.DB;
import Database.SQL;
import Models.Book;
import Models.Item;
import Models.Movie;
import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMovies extends TestCase {

    private Movie m;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        m = new Movie("MO_A Big Betray_2021" , "A Big Betray" ,2021, "Shawn Joly" ,"mario chimaro , rosaura"  ,url , "A movie about a big betray" , "qeto" , 0 , 0);
    }

    public void testGetMovieById() throws SQLException, IllegalAccessException {
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
        ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_A Big Betray_2021")
                ,Item.surroundWithSingleQuotes("A Big Betray"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("qeto"), "0", Item.surroundWithSingleQuotes(url), "2021", "0" )));
        db.insert("MOVIES" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_A Big Betray_2021")
                ,Item.surroundWithSingleQuotes("A Big Betray") , "2021"
                ,Item.surroundWithSingleQuotes("Shawn Joly"),Item.surroundWithSingleQuotes("mario chimaro , rosaura")
                ,Item.surroundWithSingleQuotes(url), Item.surroundWithSingleQuotes("A movie about a big betray"), Item.surroundWithSingleQuotes("qeto"), "0" ,"0")));

        Movie movieById = Movie.getMovieByID(db,"MO_A Big Betray_2021");
        assertTrue(Movie.getMovieByID(db,"not existed")==null);
        assertTrue(m.equals(movieById));
        m.setScore(5);
        assertFalse(m.equals(movieById));
    }

    public void testGetMovieID(){
        assertEquals("MO_A Big Betray_2021" , m.getMovieID());
    }

    public void testGetTitle(){
        assertEquals("A Big Betray" , m.getTitle());
    }

    public void testGetReleaseDate(){
        assertEquals(2021 , m.getReleaseDate());
    }

    public void testGetDirector(){
        assertEquals("Shawn Joly" , m.getDirector());
    }

    public void getMovieCast(){
        assertEquals("mario chimaro , rosaura" , m.getMovieCast());
    }

    public void testGetCoverURL(){
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        assertEquals(url , m.getCoverURL());
    }

    public void testGetSummary(){
        String summary = "A movie about a big betray";
        assertEquals(summary , m.getSummary());
    }

    public void testGetUploader(){
        assertEquals("qeto" , m.getUploader());
    }

    public void testGetScore(){
        assertEquals(0.0 , m.getScore());
    }

    public void testGetNumOfReviews(){
        assertEquals(0 , m.getNumOfReviews());
    }


    public void testSetMovieID(){
        String changedBookId = "MO_Fair_2020";
        m.setMovieID(changedBookId);
        assertEquals(changedBookId , m.getMovieID());
    }

    public void testSetTitle(){
        String newTitle = "Fair";
        m.setTitle(newTitle);
        assertEquals(newTitle , m.getTitle());
    }

    public void testSetReleaseDate(){
        m.setReleaseDate(2020);
        assertEquals(2020 , m.getReleaseDate());
    }

    public void testSetDirector(){
        m.setDirector("Elon Musk");
        assertEquals("Elon Musk" , m.getDirector());
    }

    public void testSetMovieCast(){
        m.setMovieCast("petre , pavle");
        assertEquals("petre , pavle" , m.getMovieCast());
    }

    public void testSetCoverUR(){
        String url = "https://staticg.sportskeeda.com/editor/2021/07/fc7da-16254085781511-800.jpg";
        m.setCoverURL(url);
        assertEquals(url , m.getCoverURL());
    }


    public void testSetSummary(){
        String newSummary = "A movie about a fair person";
        m.setSummary(newSummary);
        assertEquals(newSummary, m.getSummary());
    }

    public void testSetUploader(){
        m.setUploader("wineLover");
        assertEquals("wineLover" , m.getUploader());
    }

    public void testSetScore(){
        double newScore = m.getScore() + 1;
        m.setScore(newScore);
        assertEquals(newScore, m.getScore());

        m.setScore(9);
        assertEquals(9.0 , m.getScore());
    }

    public void testSetNumOfReviews(){
        int newReview = m.getNumOfReviews() + 1;
        m.setNumOfReviews(newReview);
        assertEquals(newReview, m.getNumOfReviews());

        m.setNumOfReviews(12);
        assertEquals(12 , m.getNumOfReviews());
    }
}
