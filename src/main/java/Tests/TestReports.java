package Tests;

import Database.DB;
import Database.SQL;
import Models.Item;
import Models.Report;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestReports extends TestCase {

    private Report r;

    @Override
    protected void setUp() throws Exception {
        String url = "https://static.vecteezy.com/system/resources/thumbnails/001/209/957/small_2x/square.png";
        r = new Report(2, "qeto", "MO_test_1950", "MOVIES", "test", 1950,url, "test", "testInfo", "testMembers", "incorrect" );
    }

    public void testGetReportById() throws SQLException {
        String url = "https://static.vecteezy.com/system/resources/thumbnails/001/209/957/small_2x/square.png";
        DB db = new SQL();
        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));


        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_test_2002")
                ,Item.surroundWithSingleQuotes("test"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("qeto"), "5", Item.surroundWithSingleQuotes(url), "2002", "10" )));
        List<String> values = new ArrayList<>(Arrays.asList("null", Item.surroundWithSingleQuotes("qeto")
                ,Item.surroundWithSingleQuotes("MO_test_2002"), Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("test"), "2002", Item.surroundWithSingleQuotes(url)
                ,Item.surroundWithSingleQuotes("testProducer"), Item.surroundWithSingleQuotes("testInfo")
                ,Item.surroundWithSingleQuotes("testMembers"), Item.surroundWithSingleQuotes("incorrect title!")));
        db.insert("REPORTS", values);

        List<Report> reports = Report.getReports(db);
        int id = reports.get(reports.size() - 1).getReportId();
        assertEquals(null,Report.getReportById(db, id + 1000));
        Report reportById = Report.getReportById(db, id);
        assertEquals("MO_test_2002", reportById.getItemId());
        assertEquals("incorrect title!", reportById.getComment());
    }

    public void testGetReports(){

    }

    //test getter methods

    public void testGetReportId(){
        assertEquals(2, r.getReportId());
    }

    public void testGetReporterUsername(){
        assertEquals("qeto", r.getReporterUsername());
    }

    public void testGetItemId(){
        assertEquals("MO_test_1950", r.getItemId());
    }

    public void testGetCategory(){
        assertEquals("MOVIES", r.getCategory());
    }

    public void testGetTitle(){
        assertEquals("test", r.getTitle());
    }

    public void testGetReleaseDate(){
        assertEquals(1950, r.getReleaseDate());
    }

    public void testGetUrl(){
        String url = "https://static.vecteezy.com/system/resources/thumbnails/001/209/957/small_2x/square.png";
        assertEquals(url, r.getURL());
    }

    public void testGetProducer(){
        assertEquals("test" , r.getProducer());
    }

    public void testGetInfo(){
        assertEquals("testInfo", r.getInfo());
    }

    public void testGetMembers(){
        assertEquals("testMembers", r.getMembers());
    }

    public void testGetComment(){
        assertEquals("incorrect", r.getComment());
    }

    //Test setter methods

    public void testSetReportId(){
        r.setReportId(3);
        assertEquals(3, r.getReportId());
    }

    public void testSetReporterUsername(){
        r.setReporterUsername("katie");
        assertEquals("katie", r.getReporterUsername());
    }

    public void testSetItemId(){
        r.setItemId("BO_test_1970");
        assertEquals("BO_test_1970", r.getItemId());
    }

    public void testSetCategory(){
        r.setCategory("BOOKS");
        assertEquals("BOOKS", r.getCategory());
    }

    public void testSetTitle(){
        r.setTitle("testTitle");
        assertEquals("testTitle", r.getTitle());
    }

    public void testSetReleaseDate(){
        r.setReleaseDate(1970);
        assertEquals(1970, r.getReleaseDate());
    }

    public void testSetUrl(){
        String changedUrl = "https://static.vecteezy.com/system/resources/thumbnails.png";
        r.setURL(changedUrl);
        assertEquals(changedUrl, r.getURL());
    }

    public void testSetProducer(){
        r.setProducer("new prod");
        assertEquals("new prod", r.getProducer());
    }

    public void testSetInfo(){
        r.setInfo("setInfo");
        assertEquals("setInfo", r.getInfo());
    }

    public void testSetMembers(){
        r.setMembers("setMembers");
        assertEquals("setMembers", r.getMembers());
    }

    public void testSetComment(){
        r.setComment("not incorrect");
        assertEquals("not incorrect", r.getComment());
    }
}
