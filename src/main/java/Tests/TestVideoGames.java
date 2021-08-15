package Tests;

import Database.DB;
import Database.SQL;
import Models.Book;
import Models.Item;
import Models.Video_Game;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestVideoGames extends TestCase {

    private Video_Game vg;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Typical_Tetris_Game.svg/800px-Typical_Tetris_Game.svg.png";
        String summary = "Tetris, video game that allows players to rotate falling blocks strategically to clear levels." +
                "The goal of the game is to prevent the blocks from stacking up to the top of the screen for as long as possible. ";

        vg = new Video_Game("VI_Tetris_1984" ,"Tetris" , 1984 , "Alexey Pajitnov, Vladimir Pokhilko", url, summary , "qeto" , 0, 12);
    }

    public void testGetVideoGameById() throws SQLException {
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Typical_Tetris_Game.svg/800px-Typical_Tetris_Game.svg.png";
        String summary = "Tetris, video game that allows players to rotate falling blocks strategically to clear levels." +
                "The goal of the game is to prevent the blocks from stacking up to the top of the screen for as long as possible. ";

        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("VI_Tetris_1984")
                ,Item.surroundWithSingleQuotes("Tetris"),Item.surroundWithSingleQuotes("VIDEO_GAMES")
                ,Item.surroundWithSingleQuotes("qeto"), "0", Item.surroundWithSingleQuotes(url), "1984", "0" )));
        db.insert("VIDEO_GAMES" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("VI_Tetris_1984")
                ,Item.surroundWithSingleQuotes("Tetris") , "1984"
                ,Item.surroundWithSingleQuotes("Alexey Pajitnov, Vladimir Pokhilko")
                ,Item.surroundWithSingleQuotes(url), Item.surroundWithSingleQuotes(summary), Item.surroundWithSingleQuotes("qeto"), "0" ,"12")));

        assertTrue(Video_Game.getVideoGameByID(db,"not existed")==null);
        Video_Game videoGameById = Video_Game.getVideoGameByID(db,"VI_Tetris_1984");

        System.out.println(vg.getVideoGameID());
        assertTrue(vg.equals(videoGameById));
        vg.setScore(5);
        assertFalse(vg.equals(videoGameById));
    }
    public void testGetVideoGameID(){
        assertEquals("VI_Tetris_1984", vg.getVideoGameID());
    }

    public void testGetTitle(){
        assertEquals("Tetris", vg.getTitle());
    }

    public void testGetReleaseDate(){
        assertEquals(1984, vg.getReleaseDate());
    }

    public void testGetDevelopers(){
        assertEquals("Alexey Pajitnov, Vladimir Pokhilko" , vg.getDevelopers());
    }

    public void testGetCoverURL(){
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Typical_Tetris_Game.svg/800px-Typical_Tetris_Game.svg.png";
        assertEquals(url, vg.getCoverURL());
    }

    public void testGetSummary(){
        String summary = "Tetris, video game that allows players to rotate falling blocks strategically to clear levels." +
                "The goal of the game is to prevent the blocks from stacking up to the top of the screen for as long as possible. ";
        assertEquals(summary, vg.getSummary());
    }

    public void testGetUploader(){
        assertEquals("qeto", vg.getUploader());
    }

    public void testGetScore(){
        assertEquals(0.0, vg.getScore());
    }

    public void testGetNumOfReviews(){
        assertEquals(12, vg.getNumOfReviews());
    }

    public void testSetVideoGameID(){
        vg.setVideoGameID("VI_Minecraft_2011");
        assertEquals("VI_Minecraft_2011" , vg.getVideoGameID());
    }

    public void testSetTitle(){
        vg.setTitle("Minecraft");
        assertEquals("Minecraft" , vg.getTitle());
    }

    public void testSetReleaseDate(){
        vg.setReleaseDate(2011);
        assertEquals(2011, vg.getReleaseDate());
    }

    public void testSetDevelopers(){
        vg.setDevelopers("Mojang Studios");
        assertEquals("Mojang Studios", vg.getDevelopers());
    }

    public void testSetCoverURL(){
        String changedUrl = "https://upload.wikimedia.org/wikipedia/en/5/51/Minecraft_cover.png";
        vg.setCoverURL(changedUrl);
        assertEquals(changedUrl, vg.getCoverURL());
    }

    public void testSetSummary(){
        String summary = "Minecraft is a video game in which players create and break apart various kinds of blocks in three-dimensional worlds.";
        vg.setSummary(summary);
        assertEquals(summary, vg.getSummary());
    }

    public void testSetUploader(){
        vg.setUploader("Katie");
        assertEquals("Katie", vg.getUploader());
    }

    public void testSetScore(){
        vg.setScore(8);
        assertEquals(8.0, vg.getScore());
    }

    public void testSetNumOfReviews(){
        vg.setNumOfReviews(17);
        assertEquals(17, vg.getNumOfReviews());
    }
}
