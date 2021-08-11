package Models;

import Database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private static final String REPORTS = "REPORTS";
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

    public static Report getReportById(DB db, int reportId) throws SQLException {
        ResultSet eachReportRow = db.conditionedSelect(REPORTS, "reportId",
                Item.surroundWithSingleQuotes(String.valueOf(reportId)));

        while(eachReportRow.next()){
            Report report = new Report
                    (eachReportRow.getInt(1), eachReportRow.getString(2),
                            eachReportRow.getString(3), eachReportRow.getString(4),
                            eachReportRow.getString(5) , eachReportRow.getInt(6) ,
                            eachReportRow.getString(7), eachReportRow.getString(8),
                            eachReportRow.getString(9), eachReportRow.getString(10),
                            eachReportRow.getString(11));
            return report;
        }
        return null;
    }

    public static List<Report> getReports(DB db) throws SQLException {
        List<Report> allReports = new ArrayList<>();

        ResultSet reports = db.selectAll("REPORTS");
        while(reports.next()){
            allReports.add(getReportById(db, reports.getInt(6)));
        }
        return allReports;
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
