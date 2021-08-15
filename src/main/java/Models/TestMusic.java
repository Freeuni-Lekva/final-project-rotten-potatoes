package Models;

import Database.DB;
import Database.SQL;
import Models.Book;
import Models.Item;
import Models.Music;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMusic extends TestCase {

    private Music m;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Anewflame.jpg/220px-Anewflame.jpg";
        m = new Music("MU_A New Flame_1989" , "Simply Red" ,"A New Flame" , "Elektra, WEA" ,1989,"Blue-eyed soul",url , "qeto" , 9 , 1);
    }


    public void testGetMusicById() throws SQLException {
        String url = "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Anewflame.jpg/220px-Anewflame.jpg";
        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MU_A New Flame_1989")
                ,Item.surroundWithSingleQuotes("A New Flame"),Item.surroundWithSingleQuotes("MUSIC")
                ,Item.surroundWithSingleQuotes("qeto"), "9", Item.surroundWithSingleQuotes(url), "1989", "1" )));
        db.insert("MUSIC" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MU_A New Flame_1989")
                ,Item.surroundWithSingleQuotes("Simply Red"),Item.surroundWithSingleQuotes("A New Flame")
                ,Item.surroundWithSingleQuotes("Elektra, WEA"), "1989"
                ,Item.surroundWithSingleQuotes("Blue-eyed soul")
                ,Item.surroundWithSingleQuotes(url), Item.surroundWithSingleQuotes("qeto"), "9" ,"1")));

        assertTrue(Music.getMusicByID(db,"not existed")==null);
        Music musicByid = Music.getMusicByID(db,"MU_A New Flame_1989");

        assertTrue(m.equals(musicByid));
        m.setScore(5);
        assertFalse(m.equals(musicByid));
    }

    public void testGetMusicId(){
        assertEquals("MU_A New Flame_1989" , m.getMusicID());
    }

    public void testGetArtist(){
        assertEquals("Simply Red" , m.getArtist());
    }

    public void testGetTitle(){
        assertEquals("A New Flame" , m.getTitle());
    }

    public void testGetLabel(){
        assertEquals("Elektra, WEA" , m.getLabel());
    }

    public void testGetReleaseYear(){
        assertEquals(1989, m.getReleaseYear());
    }

    public void testGetGenre(){
        assertEquals("Blue-eyed soul" , m.getGenre());
    }

    public void testGetAlbumCoverURL(){
        String url = "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Anewflame.jpg/220px-Anewflame.jpg";
        assertEquals(url , m.getAlbumCoverURL());
    }

    public void testGetUploader(){
        assertEquals("qeto" , m.getUploader());
    }

    public void testGetScore(){
        assertEquals(9.0, m.getScore());
    }

    public void testGetNumOfReviews(){
        assertEquals(1, m.getNumOfReviews());
    }

    public void testSetMusicId(){
        m.setMusicID("MO_Bad_1987");
        assertEquals("MO_Bad_1987" , m.getMusicID());
    }

    public void testSetArtist(){
        m.setArtist("Michael Jackson");
        assertEquals("Michael Jackson" , m.getArtist());
    }

    public void testSetTitle(){
        m.setTitle("Bad");
        assertEquals("Bad" , m.getTitle());
    }

    public void testSetLabel(){
        m.setLabel("Epic, CBS");
        assertEquals("Epic, CBS" , m.getLabel());
    }

    public void testSetReleaseYear(){
        m.setReleaseYear(1987);
        assertEquals(1987 , m.getReleaseYear());
    }

    public void testSetGenre(){
        m.setGenre("Pop, Rock, Funk, R&B, Dance, Soul, Hard Rock");
        assertEquals("Pop, Rock, Funk, R&B, Dance, Soul, Hard Rock", m.getGenre());
    }

    public void testSetAlbumCoverURL(){
        String changedUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/5/51/Michael_Jackson_-_Bad.png/220px-Michael_Jackson_-_Bad.png";
        m.setAlbumCoverURL(changedUrl);
        assertEquals(changedUrl , m.getAlbumCoverURL());
    }

    public void testSetUploader(){
        m.setUploader("KateB");
        assertEquals("KateB" , m.getUploader());
    }

    public void testSetScore(){
        m.setScore(95);
        assertEquals(95.0 , m.getScore());
    }

    public void testSetNumOfReviews(){
        m.setNumOfReviews(3);
        assertEquals(3, m.getNumOfReviews());
    }
}
