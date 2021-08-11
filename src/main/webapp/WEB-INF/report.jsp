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

        <h2>Release Date</h2>
        <%
        StringBuilder oReleaseDate = new StringBuilder();
        StringBuilder rReleaseDate = new StringBuilder();

        LinkedList<DiffMatchPatch.Diff> releaseDateDiffs = dmp.diffMain(originalReleaseDate, reportedReleaseDate, false);

        for(DiffMatchPatch.Diff diff : releaseDateDiffs){
            if(diff.operation.equals(DiffMatchPatch.Operation.EQUAL)){
                oReleaseDate.append(diff.text);
                rReleaseDate.append(diff.text);
            } else if(diff.operation.equals(DiffMatchPatch.Operation.DELETE)){
                oReleaseDate.append("<mark>" + diff.text + "</mark>");
            } else if(diff.operation.equals(DiffMatchPatch.Operation.INSERT)){
                rReleaseDate.append("<mark>" + diff.text + "</mark>");
            }
        }
        %>

        <h3>Original: <%= oReleaseDate.toString() %></h3>
        <h3>Reported: <%= rReleaseDate.toString() %></h3>

        <%
        if(category != null){
            if(category.equals(Movie.TABLE_NAME) || category.equals(TV_Show.TABLE_NAME)){ %>
                <h2>Director</h2>
            <% } else if(category.equals(Music.TABLE_NAME)){ %>
                <h2>Label</h2>
            <% } else if(category.equals(Book.TABLE_NAME)){ %>
                <h2>Writer</h2>
            <% } else if(category.equals(Video_Game.TABLE_NAME)){ %>
                <h2>Developers</h2>
            <% }
        } %>

        <%
        StringBuilder oProducer = new StringBuilder();
        StringBuilder rProducer = new StringBuilder();

        LinkedList<DiffMatchPatch.Diff> producerDiffs = dmp.diffMain(originalProducer, reportedProducer, false);

        for(DiffMatchPatch.Diff diff : producerDiffs){
            if(diff.operation.equals(DiffMatchPatch.Operation.EQUAL)){
                oProducer.append(diff.text);
                rProducer.append(diff.text);
            } else if(diff.operation.equals(DiffMatchPatch.Operation.DELETE)){
                oProducer.append("<mark>" + diff.text + "</mark>");
            } else if(diff.operation.equals(DiffMatchPatch.Operation.INSERT)){
                rProducer.append("<mark>" + diff.text + "</mark>");
            }
        }
        %>

        <h3>Original: <%= oProducer.toString() %></h3>
        <h3>Reported: <%= rProducer.toString() %></h3>

        <%
        if(category != null){
            if(category.equals(Movie.TABLE_NAME) || category.equals(TV_Show.TABLE_NAME) ||
               category.equals(Book.TABLE_NAME) || category.equals(Video_Game.TABLE_NAME)){ %>
                <h2>Summary</h2>
            <% } else if(category.equals(Music.TABLE_NAME)){ %>
                <h2>Genre</h2>
            <% }
        } %>

        <%
        StringBuilder oInfo = new StringBuilder();
        StringBuilder rInfo = new StringBuilder();

        LinkedList<DiffMatchPatch.Diff> infoDiffs = dmp.diffMain(originalInfo, reportedInfo, false);

        for(DiffMatchPatch.Diff diff : infoDiffs){
            if(diff.operation.equals(DiffMatchPatch.Operation.EQUAL)){
                oInfo.append(diff.text);
                rInfo.append(diff.text);
            } else if(diff.operation.equals(DiffMatchPatch.Operation.DELETE)){
                oInfo.append("<mark>" + diff.text + "</mark>");
            } else if(diff.operation.equals(DiffMatchPatch.Operation.INSERT)){
                rInfo.append("<mark>" + diff.text + "</mark>");
            }
        }
        %>

        <h3>Original: <%= oInfo.toString() %></h3>
        <h3>Reported: <%= rInfo.toString() %></h3>

    </body>
</html>