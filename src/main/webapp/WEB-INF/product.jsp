<%@ page import="Models.*" %>
<%@ page import="Database.DB" %>
<%@ page import="Servlets.ContextListener" %><%--
  Created by IntelliJ IDEA.
  User: tsakh
  Date: 12.08.21
  Time: 01:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
            <%String itemId = (String) request.getParameter("id");
            String itemCategory = Item.getCategoryByItemID(itemId);
            DB db = (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE);
            if(itemCategory == Movie.TABLE_NAME){
                Movie movie = Movie.getMovieByID(db, itemId);%>
                <h2>
                    <%=movie.getTitle()%> <br>
                </h2>
                <img src = <%= movie.getCoverURL() %> width="300" height="300"> <br/>
                <h3>
                    <b>This movie has been uploaded by: </b> <%= movie.getUploader() %> <br>
                    <b>Score: </b> <%= movie.getScore() %> <br>
                    <b>Number of reviews: </b> <%= movie.getNumOfReviews() %> <br>
                    <b>Release date: </b> <%= movie.getReleaseDate() %> <br>
                    <b>Director: </b> <%= movie.getDirector() %> <br>
                    <b>Movie cast: </b> <%= movie.getMovieCast() %> <br>
                    <b>Summary: </b> <%= movie.getSummary() %> <br>
                </h3>

            <% } else if (itemCategory == Music.TABLE_NAME){
                Music music = Music.getMusicByID(db, itemId);%>
                <h2>
                    <%=music.getTitle()%> <br>
                </h2>
                <img src = <%= music.getAlbumCoverURL() %> width="300" height="300"> <br/>
                <h3>
                    <b>This music has been uploaded by: </b> <%= music.getUploader() %> <br>
                    <b>Score: </b> <%= music.getScore() %> <br>
                    <b>Number of reviews: </b> <%= music.getNumOfReviews() %> <br>
                    <b>Release year: </b> <%= music.getReleaseYear() %> <br>
                    <b>Artist: </b> <%= music.getArtist() %> <br>
                    <b>Music genre: </b> <%= music.getGenre() %> <br>
                    <b>Label: </b> <%= music.getLabel() %> <br>
                </h3>

            <% } else if (itemCategory == TV_Show.TABLE_NAME){
                TV_Show TVShow = TV_Show.getTVShowByID(db, itemId);%>
                <h2>
                    <%=TVShow.getTitle()%> <br>
                </h2>
                <img src = <%= TVShow.getCoverURL() %> width="300" height="300"> <br/>
                <h3>
                    <b>This Tv show has been uploaded by: </b> <%= TVShow.getUploader() %> <br>
                    <b>Score: </b> <%= TVShow.getScore() %> <br>
                    <b>Number of reviews: </b> <%= TVShow.getNumOfReviews() %> <br>
                    <b>Airing year: </b> <%= TVShow.getAiringYear() %> <br>
                    <b>Director: </b> <%= TVShow.getDirector() %> <br>
                    <b>Tv show cast: </b> <%= TVShow.getTVShowCast() %> <br>
                    <b>Summary: </b> <%= TVShow.getSummary() %> <br>
                </h3>

            <% } else if (itemCategory == Video_Game.TABLE_NAME){
                Video_Game videoGame = Video_Game.getVideoGameByID(db, itemId);%>
                <h2>
                    <%=videoGame.getTitle()%> <br>
                </h2>
                <img src = <%= videoGame.getCoverURL() %> width="300" height="300"> <br/>
                <h3>
                    <b>This video game has been uploaded by: </b> <%= videoGame.getUploader() %> <br>
                    <b>Score: </b> <%= videoGame.getScore() %> <br>
                    <b>Number of reviews: </b> <%= videoGame.getNumOfReviews() %> <br>
                    <b>Release date: </b> <%= videoGame.getReleaseDate() %> <br>
                    <b>Developers: </b> <%= videoGame.getDevelopers() %> <br>
                    <b>Summary: </b> <%= videoGame.getSummary() %> <br>
                </h3>

            <% } else {
                Book book = Book.getBookByID(db, itemId);%>
                <h2>
                    <%=book.getTitle()%> <br>
                </h2>
                <img src = <%= book.getCoverURL() %> width="300" height="300"> <br/>
                <h3>
                    <b>This book has been uploaded by: </b> <%= book.getUploader() %> <br>
                    <b>Score: </b> <%= book.getScore() %> <br>
                    <b>Number of reviews: </b> <%= book.getNumOfReviews() %> <br>
                    <b>Release date: </b> <%= book.getReleaseDate() %> <br>
                    <b>Writer: </b> <%= book.getWriter() %> <br>
                    <b>Summary: </b> <%= book.getSummary() %> <br>
                </h3>
            <% }%>

    </body>
</html>