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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
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

        <%
        String producerDisplay = "";
        if(category != null){
            if(category.equals(Movie.TABLE_NAME) || category.equals(TV_Show.TABLE_NAME)){
                producerDisplay = "Director";
            } else if(category.equals(Music.TABLE_NAME)){
                producerDisplay = "Label";
            } else if(category.equals(Book.TABLE_NAME)){
                producerDisplay = "Writer";
            } else if(category.equals(Video_Game.TABLE_NAME)){
                producerDisplay = "Developers";
            }
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

        <%
        String infoDisplay = "";
        if(category != null){
            if(category.equals(Movie.TABLE_NAME) || category.equals(TV_Show.TABLE_NAME) ||
               category.equals(Book.TABLE_NAME) || category.equals(Video_Game.TABLE_NAME)){
                infoDisplay = "Summary";
            } else if(category.equals(Music.TABLE_NAME)){
                infoDisplay = "Genre";
            }
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

        <%
        String memberDisplay = "";
        if(category != null){
            if(category.equals(Movie.TABLE_NAME)){
                memberDisplay = "Movie Cast";
            } else if(category.equals(TV_Show.TABLE_NAME)){
                memberDisplay = "TV Show Cast";
            } else if(category.equals(Music.TABLE_NAME)){
                memberDisplay = "Artist";
            }
        }

        StringBuilder oMembers = new StringBuilder();
        StringBuilder rMembers = new StringBuilder();
        if(category != null){
            if(!category.equals(Book.TABLE_NAME) && !category.equals(Video_Game.TABLE_NAME)){

                LinkedList<DiffMatchPatch.Diff> memberDiffs = dmp.diffMain(originalMembers, reportedMembers, false);

                for(DiffMatchPatch.Diff diff : memberDiffs){
                    if(diff.operation.equals(DiffMatchPatch.Operation.EQUAL)){
                        oMembers.append(diff.text);
                        rMembers.append(diff.text);
                    } else if(diff.operation.equals(DiffMatchPatch.Operation.DELETE)){
                        oMembers.append("<mark>" + diff.text + "</mark>");
                    } else if(diff.operation.equals(DiffMatchPatch.Operation.INSERT)){
                        rMembers.append("<mark>" + diff.text + "</mark>");
                    }
                } %>
            <% }
        } %>

        <div class="container">
            <div class="row">
                <div class="col">
                    <h1><b><center>Original</center></b></h1>
                </div>
                <div class="col">
                    <h1><b><center>Reported</center></b></h1>
                </div>
            </div>
            <div class="row">
                <h2><b><center>Cover Picture</center></b></h2>
                <div class="col">
                    <h3><img src = <%= originalURL %> width = "100"></h3>
                </div>
                <div class="col">
                    <h3><img src = <%= reportedURL %> width = "100"></h3>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <h2><b><center>Title</center></b></h2>
                <div class="col">
                    <h3><%= oTitle.toString() %></h3>
                </div>
                <div class="col">
                    <h3><%= rTitle.toString() %></h3>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <h2><b><center>Release Date</center></b></h2>
                <div class="col">
                    <h3><%= oReleaseDate.toString() %></h3>
                </div>
                <div class="col">
                    <h3><%= rReleaseDate.toString() %></h3>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <h2><b><center><%= producerDisplay %></center></b></h2>
                <div class="col">
                    <h3><%= oProducer.toString() %></h3>
                </div>
                <div class="col">
                    <h3><%= rProducer.toString() %></h3>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <h2><b><center><%= infoDisplay %></center></b></h2>
                <div class="col">
                    <h3><%= oInfo.toString() %></h3>
                </div>
                <div class="col">
                    <h3><%= rInfo.toString() %></h3>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <h2><b><center><%= memberDisplay %></center></b></h2>
                <div class="col">
                    <h3><%= oMembers.toString() %></h3>
                </div>
                <div class="col">
                    <h3><%= rMembers.toString() %></h3>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <center><form action="/report_resolved" method="post"> <%-- !!! .jsp file name might change !!! --%>
                    <select name="RESOLVED">
                        <option value="APPROVE">APPROVE OF CHANGES</option>
                        <option value="DISAPPROVE">DISAPPROVE OF CHANGES</option>
                        <input type="hidden" name="REPORT_ID" value=<%=request.getParameter("report_id")%>>
                    </select>
                    <input type="submit" value="VERIFY"/>
                </form></center>
            </div>
            <br><br><br>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
        integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>