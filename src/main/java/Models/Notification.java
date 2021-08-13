package Models;

public class Notification {

    private int notificationID;
    private String receiver_username;
    private String sender_username;
    private String item_id;
    private String notification_type;

    public Notification(int notificationID, String receiver_username, String sender_username, String item_id, String notification_type) {
        this.notificationID = notificationID;
        this.receiver_username = receiver_username;
        this.sender_username = sender_username;
        this.item_id = item_id;
        this.notification_type = notification_type;
    }


    //getter methods
    public int getNotificationID(){
        return notificationID;
    }

    public String getReceiver_username() {
        return receiver_username;
    }

    public String getSender_username() {
        return sender_username;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getNotification_type() {
        return notification_type;
    }


    //setter methods
    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public void setReceiver_username(String receiver_username) {
        this.receiver_username = receiver_username;
    }

    public void setSender_username(String sender_username) {
        this.sender_username = sender_username;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }
}
