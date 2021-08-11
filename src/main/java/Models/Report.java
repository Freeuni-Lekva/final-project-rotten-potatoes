package Models;

public class Report {

    private int reportId;
    private String reporterUsername;
    private String itemId;
    private String category;
    private String title;
    private int releaseDate;
    private String URL;
    private String producer;
    private String info;
    private String members;
    private String comment;
    public Report(int reportId ,String reporterUsername ,String itemId, String category, String title,
                  int releaseDate, String URL, String producer, String info , String members , String comment){

        this.reportId = reportId;
        this.reporterUsername = reporterUsername;
        this.itemId = itemId;
        this.category = category;
        this.title = title;
        this.releaseDate = releaseDate;
        this.URL = URL;
        this.producer = producer;
        this.info = info;
        this.members = members;
        this.comment = comment;

    }

    //getter methods
    public int getReportId() {
        return reportId;
    }

    public String getReporterUsername() {
        return reporterUsername;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String getURL() {
        return URL;
    }

    public String getProducer() {
        return producer;
    }

    public String getInfo() {
        return info;
    }

    public String getMembers() {
        return members;
    }

    public String getComment() {
        return comment;
    }

    //Setter methods
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setReporterUsername(String reporterUsername) {
        this.reporterUsername = reporterUsername;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
