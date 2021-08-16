package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notification {

    private static final String NOTIFICATIONS_TABLE = "NOTIFICATIONS";

    private int notificationID;
    private String receiverUsername;
    private String senderUsername;
    private String itemId;
    private String notificationType;


    public Notification(int notificationID, String receiverUsername, String senderUsername, String itemId, String notificationType) {
        this.notificationID = notificationID;
        this.receiverUsername = receiverUsername;
        this.senderUsername = senderUsername;
        this.itemId = itemId;
        this.notificationType = notificationType;
    }

    public static int addNotification(DB db ,String receiverUsername, String senderUsername , String itemId , String notificationType){
        if(itemId != null) itemId = Item.surroundWithSingleQuotes(itemId);
        return db.insert(NOTIFICATIONS_TABLE , new ArrayList<>(Arrays.asList(null, Item.surroundWithSingleQuotes(receiverUsername)
                ,Item.surroundWithSingleQuotes(senderUsername) , itemId
                ,Item.surroundWithSingleQuotes(notificationType))));
    }

    public static int notificationExists(DB db, String receiverUsername, String senderUsername, String itemID, String notificationType) throws SQLException {
        String columnClause = "";
        if(itemID == null){
            columnClause = " item_id is null ";
        } else {
            columnClause = " item_id = " + Item.surroundWithSingleQuotes(itemID);
        }

        ResultSet notifications = db.quadrupleConditionedSelect(NOTIFICATIONS_TABLE,"receiver_username", Item.surroundWithSingleQuotes(receiverUsername)
        ,"sender_username", Item.surroundWithSingleQuotes(senderUsername),"notification_type",
                Item.surroundWithSingleQuotes(notificationType), columnClause);
       if(notifications.next()){
           int notification_ID = notifications.getInt("notificationID");
           return notification_ID;
       }
       return -1;
    }

    public static List<Notification> getNotifications(DB db, String username) throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        ResultSet notifications_rows = db.conditionedSelect(NOTIFICATIONS_TABLE , "receiver_username"
                , Item.surroundWithSingleQuotes(username));
        while (notifications_rows.next()){
            Notification notification = new Notification(notifications_rows.getInt(1)
                    , notifications_rows.getString(2) , notifications_rows.getString(3)
                    ,notifications_rows.getString(4) , notifications_rows.getString(5));
            notifications.add(notification);
        }
        return notifications;
    }

    //getter methods
    public int getNotificationID(){
        return notificationID;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getItemId() {
        return itemId;
    }

    public String getNotificationType() {
        return notificationType;
    }


    //setter methods
    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}
