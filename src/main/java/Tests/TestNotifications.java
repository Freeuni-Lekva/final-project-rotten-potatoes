package Tests;

import Database.DB;
import Database.SQL;
import Models.Item;
import Models.Notification;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestNotifications extends TestCase {

    private Notification n;

    @Override
    protected void setUp() throws Exception {
        n = new Notification(3, "qeto", "beto", "MO_test_2002", "UPLOAD");
    }

    public void testAddAndGetNotification() throws SQLException {
        DB db = new SQL();
        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("qeto"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("noah123"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("USERS", new ArrayList<>(Arrays.asList( Item.surroundWithSingleQuotes("JacobElordi16"), Item.surroundWithSingleQuotes("admin")
                ,Item.surroundWithSingleQuotes("admin"), Item.surroundWithSingleQuotes("2001-10-10"), Item.surroundWithSingleQuotes("admin"))));

        db.insert("ITEMS" , new ArrayList<>(Arrays.asList(Item.surroundWithSingleQuotes("MO_Kissing Booth_2018")
                ,Item.surroundWithSingleQuotes("Kissing Booth"),Item.surroundWithSingleQuotes("MOVIES")
                ,Item.surroundWithSingleQuotes("qeto"), "0", Item.surroundWithSingleQuotes(".png"), "2018", "0" )));


        Notification.addNotification(db, "qeto", "noah123", null, "FOLLOW");
        Notification.addNotification(db, "qeto", "JacobElordi16", "MO_Kissing Booth_2018","UPLOAD" );
        List<Notification> notifications = Notification.getNotifications(db,"qeto");
        assertEquals(notifications.get(0).getReceiverUsername(), "qeto");
        assertEquals(notifications.get(0).getNotificationType(), "FOLLOW");
        assertEquals(notifications.get(1).getNotificationType(), "UPLOAD");
    }


    //Test getter methods

    public void testGetNotificationID(){
        assertEquals(3, n.getNotificationID());
    }

    public void testGetReceiverUsername(){
        assertEquals("qeto", n.getReceiverUsername());
    }

    public void testGetSenderUsername(){
        assertEquals("beto", n.getSenderUsername());
    }

    public void testGetItemId(){
        assertEquals("MO_test_2002", n.getItemId());
    }

    public void testGetNotificationType(){
        assertEquals("UPLOAD", n.getNotificationType());
    }

    //Test setter methods

    public void testSetNotificationID(){
        n.setNotificationID(9);
        assertEquals(9, n.getNotificationID());
    }

    public void testSetReceiverUsername(){
        n.setReceiverUsername("beto");
        assertEquals("beto", n.getReceiverUsername());
    }

    public void testSetSenderUsername(){
        n.setSenderUsername("keto");
        assertEquals("keto", n.getSenderUsername());
    }

    public void testSetItemId(){
        n.setItemId("VI_test_2002");
        assertEquals("VI_test_2002", n.getItemId());
    }

    public void testSetNotificationType(){
        n.setNotificationType("FOLLOW");
        assertEquals("FOLLOW", n.getNotificationType());
    }
}
