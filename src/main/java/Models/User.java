package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class User {
    // !!!! ---------------------------------------------- !!!!
    // იმ ფუნქციების საბოლოო ფორმები, რომლებიც SQL ბრძანებებს იძახებენ არ არის
    // გადაწყვეტილი. შესაძლოა, რომ მათი პარამეტრების სიას DB db გამოაკლდეს. თუმცა,
    // იგი ამჟამად წერია, რათა მეთოდების გამოძახება მოვახერხოთ.

    // Constant variable declaration.
    public static final String ATTRIBUTE = "username";
    public static final String TABLE_NAME = "USERS";
    public static final String FOLLOWERS_TABLE = "FOLLOWERS";
    public static final String USER_BADGES_TABLE = "USER_BADGES";
    public static final String BADGES_TABLE = "BADGES";
    public static final String ITEMS_TABLE = "ITEMS";
    public static final String REVIEWS_TABLE = "REVIEWS";

    // Instance variable declarations.
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String password;

    // Constructor.
    public User(String username, String firstName, String lastName, Date dateOfBirth, String password){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    // მეთოდს გადაეცემა username. მეთოდი იძახებს SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი
    // ცხრილში და წარმატების შემთხვევაში, აბრუნებს User ობიექტს.
    public static User getUserByUsername(DB db, String username) throws SQLException {
        ResultSet singleUserRow = db.conditionedSelect(TABLE_NAME, "username",
                Item.surroundWithSingleQuotes(username));

        while(singleUserRow.next()){
            User user = new User
                    (singleUserRow.getString(1), singleUserRow.getString(2),
                    singleUserRow.getString(3), singleUserRow.getDate(4),
                    singleUserRow.getString(5));
            return user;
        }
        return null;
    }

    public List<Badge> getBadges(DB db) throws SQLException {
        List<Badge> badges = new ArrayList<>();
        List<String> userBadgeIDs = new ArrayList<>();
        ResultSet userBadgesWithID = db.conditionedSelect(USER_BADGES_TABLE, "username",
                Item.surroundWithSingleQuotes(username));

        while(userBadgesWithID.next()) {
            userBadgeIDs.add(userBadgesWithID.getString("badge_id"));
        }

        for (int i = 0; i < userBadgeIDs.size(); i++) {
            ResultSet singleBadgeRow = db.conditionedSelect(BADGES_TABLE, "badge_id",
                    Item.surroundWithSingleQuotes(userBadgeIDs.get(i)));
            while(singleBadgeRow.next()) {
                badges.add(new Badge(singleBadgeRow.getString(1),
                                     singleBadgeRow.getString(2),
                                     singleBadgeRow.getString(3),
                                     singleBadgeRow.getString(4)));
            }
        }
        return badges;
    }

    public List<Item> getItems(DB db) throws SQLException {
        List<Item> items = new ArrayList<>();
        ResultSet itemsRow = db.conditionedSelect(ITEMS_TABLE, "uploader",
                Item.surroundWithSingleQuotes(username));
        while(itemsRow.next()) {
            items.add(new Item(itemsRow.getString(1), itemsRow.getString(2),
                    itemsRow.getString(3), itemsRow.getString(4),
                    itemsRow.getDouble(5), itemsRow.getString(6),
                    itemsRow.getInt(7), itemsRow.getInt(8))); //გადასამოწმებელია year როგორ ამოვიღო ცხრილიდან
        }
        return items;
    }

    public List<Review> getReviews(DB db) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        ResultSet reviewsRow = db.conditionedSelect(REVIEWS_TABLE, "username",
                Item.surroundWithSingleQuotes(username));
        while (reviewsRow.next()) {
            reviews.add(new Review(reviewsRow.getString(1),
                                   reviewsRow.getString(2),
                                   reviewsRow.getDouble(3),
                                   reviewsRow.getString(4),
                                   reviewsRow.getString(5)));
        }
        return reviews;
    }

    //აბრუნებს კონკრეტული მომხმარებლის მიერ დაწერილი review-ების რაოდენობას.
    public int writtenReviewsCount(DB db) throws SQLException {
        return getReviews(db).size();
    }

    //აბრუნებს კონკრეტული მომხმარებლის მიერ დამატებული item-ების რაოდენობას.
    public int uploadedItemsCount(DB db) throws SQLException {
        return getItems(db).size();
    }

    // აბრუნებს იმ User ობიექტების სიას, რომლებიც ა-follower-ებენ მოცემულ მომხმარებელს.
    public List<User> getFollowers(DB db) throws SQLException {
        List<User> followers = new ArrayList<User>();
        ResultSet rowsOfFollowers = db.conditionedSelect(FOLLOWERS_TABLE, "user_username",
                                                        Item.surroundWithSingleQuotes(username));
        while(rowsOfFollowers.next()){
            followers.add(getUserByUsername(db, rowsOfFollowers.getString("follower_username")));
        }
        return followers;
    }

    // ამატებს FOLLOWER ცხრილში მომხმარებელს და ამ მომხმარებლის გამომწერს , შედეგად მომხმარებელს ემატება ახალი გამომწერი.
    public static int follow(DB db ,String user, String wannabeFollower){
        return db.insert(FOLLOWERS_TABLE , new ArrayList<String>(Arrays.asList(Item.surroundWithSingleQuotes(user) , Item.surroundWithSingleQuotes(wannabeFollower))));
    }

    // მოცემულ მომხმარებელს აკლდება მოცემული გამომწერი.
    public static int unfollow(DB db, String user, String wannabeUnfollower){
        return db.delete(FOLLOWERS_TABLE , "user_username" , Item.surroundWithSingleQuotes(user) ,
                "follower_username" , Item.surroundWithSingleQuotes(wannabeUnfollower));
    }

    // აბრუნებს true-ს ან false-ს იმის მიხედვით , მოცემული შესაძლო გამომწერი არის თუ არა მოცემული მომხმარებლის გამომწერი.
    public static boolean isFollowing(DB db ,User user, User possibleFollower) throws SQLException {
        ResultSet followers = db.conditionedSelect(FOLLOWERS_TABLE, "user_username",
                Item.surroundWithSingleQuotes(user.username));
        while(followers.next()){
            if(followers.getString("follower_username").equals(possibleFollower.username)) return true;
        }
        return false;
    }

    // აბრუნებს იმ User ობიექტების სიას, რომლებსაც ა-follower-ებს მოცემული მომხმარებელი.
    public List<User> getFollowing(DB db) throws SQLException {
        List<User> following = new ArrayList<User>();
        ResultSet rowsOfFollowing = db.conditionedSelect(FOLLOWERS_TABLE, "follower_username",
                                                        Item.surroundWithSingleQuotes(username));
        while(rowsOfFollowing.next()){
            following.add(getUserByUsername(db, rowsOfFollowing.getString("user_username")));
        }
        return following;
    }

    public void updateBadges(DB db) throws SQLException {
        int numReviews = db.count(Review.TABLE_NAME, "username", getUsername());
        int numUploadedItems = db.count(Item.TABLE_NAME, "uploader", getUsername());
        List<Badge> currentBadges = getBadges(db);
        List<String> currentBadgesIDs = new ArrayList<>();
        for (int i = 0; i < currentBadges.size(); i++) {
            currentBadgesIDs.add(currentBadges.get(i).getBadgeID());
        }

        ArrayList<String> newBadges = new ArrayList<>();
        if (numReviews > 0) newBadges.add("AM_CRT");
        if (numUploadedItems > 0) newBadges.add("AM_FAN");
        if (numReviews > 5) newBadges.add("EX_CRT");
        if (numUploadedItems > 5) newBadges.add("EX_FAN");
        if (numReviews > 10) newBadges.add("TP_CRT");
        if (numUploadedItems > 10) newBadges.add("TP_FAN");

        for (int i = 0; i < newBadges.size(); i++) {
            String badge = newBadges.get(i);
            if (!currentBadgesIDs.contains(badge)) {
                List<String> values = new ArrayList<>();
                values.add(Item.surroundWithSingleQuotes(username));
                values.add(Item.surroundWithSingleQuotes(badge));
                db.insert("USER_BADGES", values);
            }
        }

    }

    // Getter methods.
    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public String getPassword(){
        return password;
    }

    // Setter methods.
    public void setUsername(String username){
        this.username = username;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
