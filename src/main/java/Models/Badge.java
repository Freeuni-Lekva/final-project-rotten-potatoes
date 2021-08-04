package Models;

public class Badge {
    private String badgeID;
    private String badgeName;
    private String badgeIcon;
    private String badgeDescription;

    // Constructor.
    public Badge (String badgeID, String badgeName, String badgeIcon, String badgeDescription){
        this.badgeID = badgeID;
        this.badgeName = badgeName;
        this.badgeIcon = badgeIcon;
        this.badgeDescription = badgeDescription;
    }

    // Getter methods.
    public String getBadgeID(){
        return badgeID;
    }

    public String getBadgeName(){
        return badgeName;
    }

    public String badgeIcon(){
        return badgeIcon;
    }

    public String badgeDescription(){
        return badgeDescription;
    }

    // Setter methods.
    public void setBadgeID(String badgeID){
        this.badgeID = badgeID;
    }

    public void setBadgeName(String badgeName){
        this.badgeName = badgeName;
    }

    public void setBadgeIcon(String badgeIcon){
        this.badgeIcon = badgeIcon;
    }

    public void setBadgeDescription(String badgeDescription){
        this.badgeDescription = badgeDescription;
    }
}