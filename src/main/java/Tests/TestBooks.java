package Tests;

import Database.DB;
import Database.SQL;
import Models.Book;
import Models.Item;
import Models.Movie;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestBooks extends TestCase {

    private Book b;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        b = new Book("BO_A Big Betray_2021" , "A Big Betray" ,2021, "Shawn Joly" , url , "A book about a big betray" , "qeto" , 0 , 0);
    }

    public void testGetBookById() throws SQLException {
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("BO_A Big Betray_2021")
                ,Item.surroundWithSingleQuotes("A Big Betray"),Item.surroundWithSingleQuotes("BOOKS")
                ,Item.surroundWithSingleQuotes("qeto"), "0", Item.surroundWithSingleQuotes(url), "2021", "0" )));
        db.insert("BOOKS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("BO_A Big Betray_2021")
                ,Item.surroundWithSingleQuotes("A Big Betray") , "2021"
                ,Item.surroundWithSingleQuotes("Shawn Joly")
                ,Item.surroundWithSingleQuotes(url), Item.surroundWithSingleQuotes("A book about a big betray"), Item.surroundWithSingleQuotes("qeto"), "0" ,"0")));

        assertTrue(Book.getBookByID(db,"not existed")==null);
        Book bookById = Book.getBookByID(db,"BO_A Big Betray_2021");

        assertTrue(b.equals(bookById));
        b.setScore(5);
        assertFalse(b.equals(bookById));
    }
    public void testGetBookID(){
        assertEquals("BO_A Big Betray_2021" , b.getBookID());
    }
    public void testGetTitle(){
        assertEquals("A Big Betray" , b.getTitle());
    }

    public void testGetReleaseDate(){
        assertEquals(2021 , b.getReleaseDate());
    }

    public void testGetWriter(){
        assertEquals("Shawn Joly" , b.getWriter());
    }

    public void testGetCoverURL(){
        String url = "https://images-na.ssl-images-amazon.com/images/I/31Z5UYB591L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg";
        assertEquals(url , b.getCoverURL());
    }

    public void testGetSummary(){
        String summary = "A book about a big betray";
        assertEquals(summary , b.getSummary());
    }

    public void testGetUploader(){
        assertEquals("qeto" , b.getUploader());
    }

    public void testGetScore(){
        assertEquals(0.0 , b.getScore());
    }

    public void testGetNumOfReviews(){
        assertEquals(0 , b.getNumOfReviews());
    }


    public void testSetBookID(){
        String changedBookId = "BO_Fair_2020";
        b.setBookID(changedBookId);
        assertEquals(changedBookId , b.getBookID());
    }

    public void testSetTitle(){
        String newTitle = "Fair";
        b.setTitle(newTitle);
        assertEquals(newTitle , b.getTitle());
    }

    public void testSetReleaseDate(){
        b.setReleaseDate(2020);
        assertEquals(2020 , b.getReleaseDate());
    }

    public void testSetWriter(){
        b.setWriter("Elon Musk");
        assertEquals("Elon Musk" , b.getWriter());
    }

    public void testSetCoverUR(){
        String url = "https://staticg.sportskeeda.com/editor/2021/07/fc7da-16254085781511-800.jpg";
        b.setCoverURL(url);
        assertEquals(url , b.getCoverURL());
    }


    public void testSetSummary(){
        String newSummary = "A book about a fair person";
        b.setSummary(newSummary);
        assertEquals(newSummary, b.getSummary());
    }

    public void testSetUploader(){
        b.setUploader("wineLover");
        assertEquals("wineLover" , b.getUploader());
    }

    public void testSetScore(){
        double newScore = b.getScore() + 1;
        b.setScore(newScore);
        assertEquals(newScore, b.getScore());

        b.setScore(39);
        assertEquals(39.0 , b.getScore());
    }

    public void testSetNumOfReviews(){
        int newReview = b.getNumOfReviews() + 1;
        b.setNumOfReviews(newReview);
        assertEquals(newReview, b.getNumOfReviews());

        b.setNumOfReviews(12);
        assertEquals(12 , b.getNumOfReviews());
    }

}
