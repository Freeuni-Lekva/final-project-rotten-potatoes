<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="Database.*" %>
<%@ page import="Models.*" %>
<%@ page import="Servlets.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch" %>

<!DOCTYPE html>
<html>
    <head>
        <%
        int reportID = (int) Integer.valueOf(request.getParameter("report_id")); %>
        <title>Report #<%= reportID %></title>
    </head>
    <body>
        <%
        DB db = (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE);
        Report report = Report.getReportById(db, reportID);
        DiffMatchPatch dmp = new DiffMatchPatch();

        // Fetch report info.
        String reporterUsername = report.getReporterUsername();
        String itemID = report.getItemId();
        String category = report.getCategory();
        String reportedTitle = report.getTitle();
        String reportedReleaseDate = String.valueOf(report.getReleaseDate());
        String reportedURL = report.getURL();
        String reportedProducer = report.getProducer();
        String reportedInfo = report.getInfo();
        String reportedMembers = report.getMembers();
        String reportedComment = report.getComment();

        Item item = Item.getItemByID(db, itemID);

        // Fetch original item info.
        String originalTitle = item.getTitle();
        String originalURL = item.getCoverURL();
        String originalReleaseDate = String.valueOf(item.getReleaseDate());
        String originalProducer = "";
        String originalInfo = "";
        String originalMembers = "";

        if(category != null){
            if(category.equals(Movie.TABLE_NAME)){
                Movie movie = Movie.getMovieByID(db, itemID);

                originalProducer = movie.getDirector();
                originalInfo = movie.getSummary();
                originalMembers = movie.getMovieCast();
            } else if(category.equals(TV_Show.TABLE_NAME)){
                TV_Show tvShow = TV_Show.getTVShowByID(db, itemID);

                originalProducer = tvShow.getDirector();
                originalInfo = tvShow.getSummary();
                originalMembers = tvShow.getTVShowCast();
            } else if(category.equals(Music.TABLE_NAME)){
                Music music = Music.getMusicByID(db, itemID);

                originalProducer = music.getLabel();
                originalInfo = music.getGenre();
                originalMembers = music.getArtist();
            } else if(category.equals(Book.TABLE_NAME)){
                Book book = Book.getBookByID(db, itemID);

                originalProducer = book.getWriter();
                originalInfo = book.getSummary();
                originalMembers = null;
            } else if(category.equals(Video_Game.TABLE_NAME)){
                Video_Game videoGame = Video_Game.getVideoGameByID(db, itemID);

                originalProducer = videoGame.getDevelopers();
                originalInfo = videoGame.getSummary();
                originalMembers = null;
            }
        } %>

        <h2>Cover Picture</h2>
        <h3>Original: <img src = <%= originalURL %> width = "100"></h3>
        <h3>Reported: <img src = <%= reportedURL %> width = "100"></h3>

        <h2>Title</h2>
        <%
        StringBuilder oTitle = new StringBuilder();
        StringBuilder rTitle = new StringBuilder();

        LinkedList<DiffMatchPatch.Diff> titleDiffs = dmp.diffMain(originalTitle, reportedTitle, false);

        for(DiffMatchPatch.Diff diff : titleDiffs){
            if(diff.operation.equals(DiffMatchPatch.Operation.EQUAL)){
                oTitle.append(diff.text);
                rTitle.append(diff.text);
            } else if(diff.operation.equals(DiffMatchPatch.Operation.DELETE)){
                oTitle.append("<mark>" + diff.text + "</mark>");
            } else if(diff.operation.equals(DiffMatchPatch.Operation.INSERT)){
                rTitle.append("<mark>" + diff.text + "</mark>");
            }
        }
        %>

        <h3>Original: <%= oTitle.toString() %></h3>
        <h3>Reported: <%= rTitle.toString() %></h3>

    </body>
</html>