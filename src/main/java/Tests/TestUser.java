package Tests;

import Database.DB;
import Database.SQL;
import Models.*;
import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestUser extends TestCase {

    private User u;

    @Override
    protected void setUp() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date yourDate = sdf.parse("1992-07-26");
        u = new User("qeto", "kate", "centineo", yourDate, "myPass");

    }

    public void testGetUserByUsername() throws ParseException, NoSuchAlgorithmException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date myBirth = sdf.parse("2001-10-16");
        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("Katie")
                ,Item.surroundWithSingleQuotes("Bachalashvili"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes(Password.getHash("admin")))));


        User userByUsername = User.getUserByUsername(db, "qeto");
        assertEquals(null, User.getUserByUsername(db, "notExisted"));
        db.insert("USER_BADGES", new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("qeto"),
                Item.surroundWithSingleQuotes("AM_CRT"))));
        db.insert("USER_BADGES", new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("qeto"),
                Item.surroundWithSingleQuotes("AM_FAN"))));
        List<Badge> badges = userByUsername.getBadges(db);
        assertEquals("AM_CRT", badges.get(0).getBadgeID());
        assertEquals("AM_FAN", badges.get(1).getBadgeID());

    }


    public void testGetItems() throws SQLException, NoSuchAlgorithmException {
        DB db = new SQL();
        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("Katie")
                ,Item.surroundWithSingleQuotes("Bachalashvili"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes(Password.getHash("admin")))));

        String url = "https://images.static-bluray.com/products/20/106357_1_large.jpg";
        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("BO_Emma_1915")
                ,Item.surroundWithSingleQuotes("Emma"),Item.surroundWithSingleQuotes("BOOKS")
                ,Item.surroundWithSingleQuotes("qeto"), "8", Item.surroundWithSingleQuotes(url), "2021", "12" )));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_Holidate_2021")
                ,Item.surroundWithSingleQuotes("Holidate"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("qeto"), "0", Item.surroundWithSingleQuotes(url), "2021", "0" )));

        User userByUsername = User.getUserByUsername(db, "qeto");
        List<Item> items = userByUsername.getItems(db);
        assertEquals(items.get(0).getCategory(), "BOOKS");
        assertEquals(items.get(1).getCategory(), "MOVIES");

    }

    public void testGetReviews() throws NoSuchAlgorithmException, SQLException {
        DB db = new SQL();
        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("Katie")
                ,Item.surroundWithSingleQuotes("Bachalashvili"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes(Password.getHash("admin")))));

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("Katie")
                ,Item.surroundWithSingleQuotes("Bachalashvili"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes(Password.getHash("admin")))));

        String url = "https://images.static-bluray.com/products/20/106357_1_large.jpg";
        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_Holidate_2020")
                ,Item.surroundWithSingleQuotes("Holidate"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("admin"), "0", Item.surroundWithSingleQuotes(url), "2021", "0" )));
        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_Brain_2020")
                    ,Item.surroundWithSingleQuotes("Brain"),Item.surroundWithSingleQuotes("MOVIES")
                    ,Item.surroundWithSingleQuotes("admin"), "0", Item.surroundWithSingleQuotes(url), "2021", "0" )));
        Review.addReview(db, "MO_Holidate_2020" , "qeto", 7, "Nice Movie");
        Review.addReview(db, "MO_Brain_2020" , "qeto", 9, "Very interesting");

        User userByUsername = User.getUserByUsername(db, "qeto");
        List<Review> reviews = userByUsername.getReviews(db);
        assertEquals(reviews.get(0).getCategory(), "MOVIES");
        assertEquals(reviews.get(1).getScore(), 9.0);
        userByUsername.uploadedItemsCount(db);
        userByUsername.writtenReviewsCount(db);
    }

    public void testFollowingSystem() throws SQLException, NoSuchAlgorithmException {
        DB db = new SQL();

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("Katie")
                ,Item.surroundWithSingleQuotes("Bachalashvili"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes(Password.getHash("admin")))));

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("Katie")
                ,Item.surroundWithSingleQuotes("Bachalashvili"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes(Password.getHash("admin")))));

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("noah123"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("JacobElordi16"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        User userQeto = User.getUserByUsername(db, "qeto");
        User userNoah = User.getUserByUsername(db, "noah123");
        User userJacob = User.getUserByUsername(db, "JacobElordi16");
        User.follow(db,"qeto", "JacobElordi16");
        User.follow(db,"qeto", "noah123");
        User.follow(db, "JacobElordi16", "noah123");
        List<User> qetoFollowers = userQeto.getFollowers(db);
        assertTrue(qetoFollowers.size()>=2);
        List<User> noahFollowings = userNoah.getFollowing(db);
        assertTrue(noahFollowings.size()>=2);
        assertTrue(User.isFollowing(db, userJacob, userNoah));
        User.unfollow(db, "JacobElordi16", "noah123");
        assertFalse(User.isFollowing(db,userJacob, userNoah));

    }

    //Test getter methods

    public void testGetUsername(){
        assertEquals("qeto", u.getUsername());
    }

    public void testGetFirstName(){
        assertEquals("kate", u.getFirstName());
    }

    public void testGetLastName(){
        assertEquals("centineo", u.getLastName());
    }

    public void testGetDateOfBirth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date yourDate = sdf.parse("1992-07-26");
        assertEquals(yourDate, u.getDateOfBirth());
    }

    public void testGetPassword(){
        assertEquals("myPass", u.getPassword());
    }

    //Test setter methods

    public void testSetUsername(){
        u.setUsername("lisa");
        assertEquals("lisa", u.getUsername());
    }

    public void testSetFirstName(){
        u.setFirstName("Monica");
        assertEquals("Monica", u.getFirstName());
    }

    public void testSetLastName(){
        u.setLastName("Belluci");
        assertEquals("Belluci", u.getLastName());
    }

    public void testSetDateOfBirth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date myDate = sdf.parse("1962-07-26");
        u.setDateOfBirth(myDate);
        assertEquals(myDate, u.getDateOfBirth());
    }

    public void testSetPassword(){
        u.setPassword("pkCassel");
        assertEquals("pkCassel", u.getPassword());
    }

}
