package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    // Instance variable declarations.
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    // Constructor.
    public User(String username, String password, String firstName, String lastName, String dateOfBirth){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // მეთოდს გადაეცემა username. მეთოდი იძახებს SQL ფუნქციას, რათა მოძებნოს ასეთი ნივთი
    // ცხრილში და წარმატების შემთხვევაში, აბრუნებს User ობიექტს.
    public static User getUserByUsername(DB db, String username) throws SQLException {
        ResultSet singleUserRow = db.conditionedSelect(TABLE_NAME, "username",
                Item.surroundWithSingleQuotes(username));

        while(singleUserRow.next()){
            User user = new User
                    (singleUserRow.getString(0), singleUserRow.getString(1),
                    singleUserRow.getString(2), singleUserRow.getString(3),
                    singleUserRow.getString(4));
            return user;
        }

        return null;
    }

    public List<Badge> getBadges(){
        // TO BE IMPLEMENTED
        return null;
    }

    public List<Item> getItems(){
        // TO BE IMPLEMENTED
        return null;
    }

    public List<Review> getReviews(){
        // TO BE IMPLEMENTED
        return null;
    }

    // აბრუნებს იმ User ობიექტების სიას, რომლებიც ა-follower-ებენ მოცემულ მომხმარებელს.
    public List<User> getFollowers(DB db) throws SQLException {
        List<User> followers = new ArrayList<User>();
        ResultSet rowsOfFollowers = db.conditionedSelect(FOLLOWERS_TABLE, "user_username",
                                                        username);
        while(rowsOfFollowers.next()){
            followers.add(getUserByUsername(db, rowsOfFollowers.getString("follower_username")));
        }
        return followers;
    }

    // აბრუნებს იმ User ობიექტების სიას, რომლებსაც ა-follower-ებს მოცემული მომხმარებელი.
    public List<User> getFollowing(DB db) throws SQLException {
        List<User> following = new ArrayList<User>();
        ResultSet rowsOfFollowing = db.conditionedSelect(FOLLOWERS_TABLE, "follower_username",
                username);
        while(rowsOfFollowing.next()){
            following.add(getUserByUsername(db, rowsOfFollowing.getString("user_username")));
        }
        return following;
    }

    // Getter methods.
    public String getUsername(){
        return username;
    }

    public String password(){
        return password;
    }

    public String firstName(){
        return firstName;
    }

    public String lastName(){
        return lastName;
    }

    public String dateOfBirth(){
        return dateOfBirth;
    }

    // Setter methods.
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}