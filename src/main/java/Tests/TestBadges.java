package Tests;

import Models.Badge;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TestBadges extends TestCase {

    private ArrayList<String> badgeIds = new ArrayList<>(Arrays.asList("AM_CRT" , "AM_FAN" , "EX_CRT" , "EX_FAN" , "TP_CRT" , "TP_FAN"));
    private ArrayList<String> badgeNames =  new ArrayList<>(Arrays.asList("Amateur Critic" , "Amateur Fan" , "Experienced Critic" , "Experienced Fan" , "Top Critic" , "Top Fan"));
    private ArrayList<String> images =  new ArrayList<>(Arrays.asList("Amateur_Critic.png" , "Amateur_Fan.png" , "Experienced_Critic.png" , "Experienced_Fan.png" , "Top_Critic.png" ,"Top_Fan.png"));


    public void testConstructor(){
        Badge b = new Badge( "AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , "Badge is given when the user has uploaded a single item." );
        assertEquals("AM_FAN" , b.getBadgeID());
        assertEquals("Amateur Fan" , b.getBadgeName());
        assertEquals("Amateur_Fan.png" , b.getBadgeIcon());
        assertEquals("Badge is given when the user has uploaded a single item." , b.getBadgeDescription());
    }

    public void testGetBadgeID(){
        for(int i = 0 ; i < badgeIds.size() ; i++){
            String id = badgeIds.get(i);
            Badge b = new Badge( id ,badgeNames.get(i) , images.get(i) , "Badge is given when the user has uploaded a single item." );
            assertEquals(id , b.getBadgeID());
        }
    }

    public void testGetBadgeName(){
        for(int i = 0 ; i < badgeNames.size() ; i++){
            String name = badgeNames.get(i);
            Badge b = new Badge(badgeIds.get(i) ,name, images.get(i) , "Badge is given when the user has uploaded a single item." );
            assertEquals(name , b.getBadgeName());
        }
    }
    public void testGetBadgeIcon(){
        for(int i = 0 ; i < images.size() ; i++) {
            String image = images.get(i);
            Badge b = new Badge(badgeIds.get(i), badgeNames.get(i), image, "Badge is given when the user has uploaded a single item.");
            assertEquals(image, b.getBadgeIcon());
        }
    }
    public void testGetBadgeDescription(){
        String description = "";
        description = "Badge is given when the user has given a single review.";
        Badge b = new Badge("AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , description);
        assertEquals(description , b.getBadgeDescription());

        description = "Badge is given when the user has uploaded a single item.";
        b = new Badge("AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , description);
        assertEquals(description , b.getBadgeDescription());

        description = "Badge is given when the user has given more than 5 reviews.";
        b = new Badge("AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , description);
        assertEquals(description , b.getBadgeDescription());

        description = "Badge is given when the user has uploaded more than 5 items.";
        description = "Badge is given when the user has given more than 5 reviews.";
        b = new Badge("AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , description);
        assertEquals(description , b.getBadgeDescription());

        description = "Badge is given when the user has given more than 10 reviews.";
        description = "Badge is given when the user has given more than 5 reviews.";
        b = new Badge("AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , description);
        assertEquals(description , b.getBadgeDescription());

        description = "Badge is given when the user has uploaded more than 10 items.";
        description = "Badge is given when the user has given more than 5 reviews.";
        b = new Badge("AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , description);
        assertEquals(description , b.getBadgeDescription());



    }
    public void testSetBadgeID(){
        for(int i = 0 ; i < badgeIds.size() ; i++){
            String id = badgeIds.get(i);
            Badge b = new Badge( id ,badgeNames.get(i) , images.get(i) , "Badge is given when the user has uploaded a single item." );
            Random rand = new Random();
            id = badgeIds.get(rand.nextInt(badgeIds.size()));
            b.setBadgeID(id);
            assertEquals(id , b.getBadgeID());
        }
    }
    public void testSetBadgeName(){
        for(int i = 0 ; i < badgeNames.size() ; i++){
            String name = badgeNames.get(i);
            Badge b = new Badge(badgeIds.get(i) ,name, images.get(i) , "Badge is given when the user has uploaded a single item." );
            Random rand = new Random();
            name = badgeIds.get(rand.nextInt(badgeIds.size()));
            b.setBadgeName(name);
            assertEquals(name , b.getBadgeName());
        }
    }

    public void testSetBadgeIcon(){
        for(int i = 0 ; i < images.size() ; i++){
            String image = images.get(i);
            Badge b = new Badge(badgeIds.get(i) ,badgeNames.get(i) , image, "Badge is given when the user has uploaded a single item." );
            Random rand = new Random();
            image = badgeIds.get(rand.nextInt(badgeIds.size()));
            b.setBadgeIcon(image);
            assertEquals(image , b.getBadgeIcon());
        }
    }
    public void testSetBadgeDescription(){
        String description = "";
        description = "Badge is given when the user has given a single review.";
        Badge b = new Badge("AM_FAN" ,"Amateur Fan" , "Amateur_Fan.png" , description);
        assertEquals(description , b.getBadgeDescription());
        description = "Badge is given when the user has uploaded a single item.";
        b.setBadgeDescription(description);
        assertEquals(description , b.getBadgeDescription());

        description = "Badge is given when the user has given more than 5 reviews.";
        b.setBadgeDescription(description);
        assertEquals(description , b.getBadgeDescription());
    }
}
