package Models;

import java.util.List;

public class User {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "username";

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

    public static User getUserByUsername(String username){
        // TO BE IMPLEMENTED
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

    public List<User> getFollowers(){
        // TO BE IMPLEMENTED
        return null;
    }

    public List<User> getFollowing(){
        // TO BE IMPLEMENTED
        return null;
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