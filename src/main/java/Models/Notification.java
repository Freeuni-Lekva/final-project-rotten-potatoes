package Models;

public class Notification {

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
