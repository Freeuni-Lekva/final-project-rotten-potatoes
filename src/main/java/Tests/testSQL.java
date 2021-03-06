package Tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import Database.DB;
import Database.SQL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testSQL {
    private DB db;

    /* რატომღაც არ მუშაობს, მოგვიანებით მივხედავ.
    @BeforeAll
    public void init(){
        db = new SQL();
    } */

    // insert ოპერაციების სისწორის შემოწმება უკეთ მოხდება, როცა search-ის იმპლემენტაციაც მოხდება. ამჟამად, მთავარი მიზანია,
    // რომ დაინახოთ, როგორ ხდება insert ოპერაციაში პარამეტრების გადაცემა.
    @Test
    public void testInsert() throws ClassNotFoundException {
        db = new SQL();
        // სხვადასხვა ცხრილებზე insert ოპერაციის გაშვების მაგალითები. დააკვირდით, რომ TEXT, CHAR(n), DATE და ა.შ. ტიპის
        // სვეტების შესაბამისი მნიშვნელობები დამატებით მოქცეულია ერთხაზიან ბრჭყალებში ('...'), ხოლო ასეთი ბრჭყალები აღარ
        // სჭირდება, მაგ; INT, DOUBLE და ა.შ. ტიპის სვეტების შესაბამის მნიშვნელობებს. model კლასების ფუნქციების იმპლემენტაციის
        // დროს ყურადღება მიაქციეთ, რომ სწორად გადააწოდოთ SQL-ს პარამეტრები.
        List<String> user = Arrays.asList("'sjanj'", "'Shorena'", "'Janjghava'", "'2001-05-31'", "'RottenPotatoes'");
        List<String> item1 = Arrays.asList("'MO_Cruella_2021'", "'Cruella'", "'MOVIES'", "'sjanj'", "0", "'cruella.jpg'");

        // ასევე: ჯერ items ცხრილში უნდა ჩაამატოთ ნივთი და შემდეგ მისი კონკრეტული კატეგორიის ცხრილში, რადგან კატეგორიების
        // ცხრილები items ცხრილიდან იყენებენ id ველს foreign key-დ.
        List<String> item2 = Arrays.asList("'BO_Cosmos_1980'", "'Cosmos'", "'BOOKS'", "'sjanj'", "0", "'cosmos.jpg'");
        List<String> book = Arrays.asList("'BO_Cosmos_1980'", "'Cosmos'", "1980", "'Carl Sagan'", "'cosmos.jpg'",
                                            "'A must-read.'", "'sjanj'", "0", "0");

        db.insert("USERS", user);
        db.insert("ITEMS", item1);
        db.insert("ITEMS", item2);
        db.insert("BOOKS", book);
    }

    @Test
    public void testConditionedSelect() throws SQLException, ClassNotFoundException {
        db = new SQL();
        int numOfRowsReturned = 0;

        // დააკვირდით, რომ conditionedSelect ფუნქციას სწორად გადასცეთ მესამე პარამეტრი. აქაც მაღლა აღწერილი სიტუაციის
        // ანალოგიური შემთხვევაა ერთხაზიან ბრჭყალებზე (მაგ: ...where name = 'admin', მაგრამ ...where age = 5).
        ResultSet user = db.conditionedSelect("USERS", "username", "'admin'");
        while(user.next()){
            numOfRowsReturned++;
            assertEquals("admin", user.getString("username"));
            assertEquals("Giorgi", user.getString("first_name"));
            assertEquals("Meore", user.getString("last_name"));
            assertEquals("1900-03-07", user.getString("date_of_birth"));
            assertEquals("70e55e37480aafbb55d697f1dc7dcfb6b70ca342", user.getString("hash_password"));
        }
        assertEquals(1, numOfRowsReturned);
    }

    @Test
    public void testDoublyConditionedSelect() throws SQLException, ClassNotFoundException {
        db = new SQL();
        int numOfRowsReturned = 0;

        // დააკვირდით, რომ conditionedSelect ფუნქციას სწორად გადასცეთ მესამე პარამეტრი. აქაც მაღლა აღწერილი სიტუაციის
        // ანალოგიური შემთხვევაა ერთხაზიან ბრჭყალებზე (მაგ: ...where name = 'admin', მაგრამ ...where age = 5).
        ResultSet user = db.doublyConditionedSelect("USERS", "username", "'admin'",
                "hash_password", "'70e55e37480aafbb55d697f1dc7dcfb6b70ca342'");
        while(user.next()){
            numOfRowsReturned++;
            assertEquals("admin", user.getString("username"));
            assertEquals("Giorgi", user.getString("first_name"));
            assertEquals("Meore", user.getString("last_name"));
            assertEquals("1900-03-07", user.getString("date_of_birth"));
            assertEquals("70e55e37480aafbb55d697f1dc7dcfb6b70ca342", user.getString("hash_password"));
        }
        assertEquals(1, numOfRowsReturned);
    }

    @Test
    public void testConditionedAndOrderedSelect() throws SQLException, ClassNotFoundException {
        db = new SQL();
        int numOfRowsReturned = 0;

        ResultSet items = db.conditionedAndOrderedSelect("ITEMS", "CATEGORY", "'MUSIC'",
                                                        "TITLE", "'%y%'", "TITLE", "ASC");
        while(items.next()){
            numOfRowsReturned++;
            if(numOfRowsReturned == 1){
                assertEquals("MU_Magical Mystery Tour_1967", items.getString("item_id"));
                assertEquals("Magical Mystery Tour", items.getString("title"));
                assertEquals("MUSIC", items.getString("category"));
                assertEquals("admin", items.getString("uploader"));
                assertEquals("0.0", items.getString("score"));
                assertEquals("https://upload.wikimedia.org/wikipedia/en/thumb/e/e8/MagicalMysteryTourDoubleEPcover.jpg" +
                        "/220px-MagicalMysteryTourDoubleEPcover.jpg", items.getString("cover_url"));
            } else if(numOfRowsReturned == 2){
                /*
                assertEquals("MU_The Rise and Fall of Ziggy Stardust and the Spiders from Mars_1972",
                            items.getString("item_id"));
                assertEquals("The Rise and Fall of Ziggy Stardust and the Spiders from Mars",
                            items.getString("title"));
                assertEquals("MUSIC", items.getString("category"));
                assertEquals("admin", items.getString("uploader"));
                assertEquals("0.0", items.getString("score"));
                assertEquals("https://i.scdn.co/image/ab67616d0000b273ce928bc5dc2ed4d8e6d82366",
                            items.getString("cover_url")); */
            }
        }
        assertEquals(1, numOfRowsReturned);
    }

    @Test
    public void testUpdate(){
        db = new SQL();
        db.update("MUSIC", "release_year", "'1982'",
                "music_id", "'MU_Dangerous_1991'");
        // test increment by 1:
        db.update("MUSIC", "release_year", "release_year + 1",
                "music_id", "'MU_Magical Mystery Tour_1967'");
    }

    @Test
    public void testGetConnection(){
        db = new SQL();
        Connection con = db.getConnection();
        Assert.assertNotEquals(null, con);
    }

    @Test
    public void testDelete() throws SQLException {
        db = new SQL();
        db.delete("MUSIC", "music_id", "'MU_Dangerous_1991'", "music_id", "'MU_Dangerous_1991'");
        ResultSet result1 = db.conditionedSelect("MUSIC", "music_id", "'MU_Dangerous_1991'");
        assertEquals(false, result1.next());
    }

    @Test
    public void testCount(){
        db = new SQL();
        int res1 = db.count("CATEGORIES", "category_name", "'BOOKS'");
        assertEquals(1, res1);
    }

    @Test
    public void testSelectAll() throws SQLException {
        db = new SQL();
        ResultSet results = db.selectAll("CATEGORIES");
        List<String> resultsList = new ArrayList<String>();
        while(results.next()){
            resultsList.add(results.getString("category_name"));
        }
        assertEquals(5, resultsList.size());
        assertEquals("BOOKS", resultsList.get(0));
        assertEquals("MOVIES", resultsList.get(1));
        assertEquals("MUSIC", resultsList.get(2));
        assertEquals("TV_SHOWS", resultsList.get(3));
        assertEquals("VIDEO_GAMES", resultsList.get(4));
    }
}