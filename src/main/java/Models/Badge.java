package Models;

public class Badge {
    // Constant variable declaration.
    public static final String ATTRIBUTE = "BADGES";
    public static final String TABLE_NAME = "BADGES";

    // Instance variable declarations.
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

   /* public static Badge getBadgeByID(String id) {
        if (id.equals("AM_CRT"))
            return new Badge("AM_CRT", "Amateur Critic", "Amateur_Critic.png",
                    "Badge is given when the user has given a single review.");
        if (id.equals("AM_FAN"))
            return new Badge("AM_FAN", "Amateur Fan", "Amateur_Fan.png",
                    "Badge is given when the user has uploaded a single item.");
        if (id.equals("EX_CRT"))
            return new Badge("EX_CRT", "Experienced Critic", "Experienced_Critic.png",
                    "Badge is given when the user has given more than 5 reviews.");
        if (id.equals("EX_FAN"))
            return new Badge("EX_FAN", "Experienced Fan", "Experienced_Fan.png",
                    "Badge is given when the user has uploaded more than 5 items.");
        if (id.equals("TP_CRT"))
            return new Badge("TP_CRT", "Top Critic", "Top_Critic.png",
                    "Badge is given when the user has given more than 10 reviews.");
        if (id.equals("TP_FAN"))
            return new Badge("TP_FAN", "Top Fan", "Top_Fan.png",
                    "Badge is given when the user has uploaded more than 10 items.");
        return null;
    } */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Badge)) {
            return false;
        }
        Badge badge = (Badge) o;
        if (badge.getBadgeID().equals(this.getBadgeID())) return true;
        return false;
    }

    // Getter methods.
    public String getBadgeID(){
        return badgeID;
    }

    public String getBadgeName(){
        return badgeName;
    }

    public String getBadgeIcon(){
        return badgeIcon;
    }

    public String getBadgeDescription(){
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