<%@ page import="Models.*" %>
<%@ page import="Database.DB" %>
<%@ page import="Servlets.ContextListener" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: tsakh
  Date: 13.08.21
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Report page</title>
    </head>
    <body>

<% String category=(String) request.getSession().getAttribute("CATEGORY"); %>
<% String itemID=(String) request.getSession().getAttribute("id"); %>
<% DB db= (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE); %>
<%  Item currItem= null;
    try {
     currItem= Item.getItemByID(db,itemID);
} catch (SQLException throwables) {
    throwables.printStackTrace();
} %>



<form name="reportForm" method="POST" action="">


    <p> REPORT FORM:</p>

    <label for="title"> Title :</label>
    <input type="text" id="title" name="title" value=<%=  currItem.getTitle()%>> <br/>


    <% if (category!=null && category.equals(Movie.ATTRIBUTE)) { %>
    <% Movie currMovie= Movie.getMovieByID(db,itemID); %>
    <label for="movieDirector"> Director: </label>
    <input type="text" id="movieDirector" name="movieDirector" value=<%= currMovie.getDirector()%>> <br/>

    <label for="movieCast"> Cast: </label>
    <input type="text" id="movieCast" name="movieCast" value=<%= currMovie.getMovieCast()%>> <br/>

    <label for="movieSummary"> Summary: </label>
    <input type="text" id="movieSummary" name="movieSummary" value=<%= currMovie.getSummary()%>> <br/>
    <% } %>


    <% if(category!=null && category.equals(Music.ATTRIBUTE)){  %>

    <% Music currMusic= Music.getMusicByID(db, itemID); %>
    <label for="musicArtist"> Artist: </label>
    <input type="text" id="musicArtist" name="musicArtist" value=<%= currMusic.getArtist()%>>  <br/>

    <label for="musicLabel">Label: </label>
    <input type="text" id="musicLabel" name="musicLabel" value=<%= currMusic.getLabel()%>>  <br/>

    <label for="musicGenre" > Genre: </label>
    <input type="text" id="musicGenre" name="musicGenre" value=<%=currMusic.getGenre()%>>  <br/>
    <%} %>


    <% if(category!=null && category.equals(TV_Show.ATTRIBUTE)){  %>

    <% TV_Show currShow= TV_Show.getTVShowByID(db,itemID);%>
    <label for="showDirector"> Director: </label>
    <input type="text" id="showDirector" name="showDirector" value=<%=currShow.getDirector()%>> <br/>

    <label for="showCast"> Cast: </label>
    <input type="text" id="showCast" name="showCast" value=<%=currShow.getTVShowCast()%>> <br/>


    <label for="showSummary"> Summary: </label>
    <input type="text" id="showSummary" name="showSummary" value=<%= currShow.getSummary()%>> <br/>

    <label for="showDate"> Airing year: </label>
    <input type="text" id="showDate" name="showDate" value=<%= currShow.getAiringYear()%>> <br/>

    <%} %>


    <% if(category!=null &&category.equals(Book.ATTRIBUTE)){  %>

    <% Book currBook = Book.getBookByID(db, itemID); %>
    <label for="bookWriter"> Writer: </label>
    <input type="text" id="bookWriter" name="bookWriter" value=<%= currBook.getWriter()%>> <br/>


    <label for="bookSummary"> Summary: </label>
    <input type="text" id="bookSummary" name="bookSummary" value=<%= currBook.getSummary()%>> <br/>

    <%} %>

    <% if(category!=null &&category.equals(Video_Game.ATTRIBUTE)){  %>

    <% Video_Game currGame= Video_Game.getVideoGameByID(db,itemID);%>

    <label for="gameDevelopers"> Developers: </label>
    <input type="text" id="gameDevelopers" name="gameDevelopers" value=<%= currGame.getDevelopers()%>> <br/>


    <label for="gameSummary"> Summary: </label>
    <input type="text" id="gameSummary" name="gameSummary" value=<%= currGame.getSummary()%>> <br/>

    <%} %>


    <% if(category!=null && (category.equals(Video_Game.ATTRIBUTE) || category.equals(Movie.ATTRIBUTE)||
            category.equals(Music.ATTRIBUTE) || category.equals(Book.ATTRIBUTE))){  %>

    <label for="date"> Release date:</label>
    <input type="text" id="date" name="date" value=<%=currItem.getReleaseDate()%>> <br/>

    <%} %>

    <label for="cover"> Cover url:</label>
    <input type="text" id="cover" name="cover" value=<%=currItem.getCoverURL()%>> <br/>


    <button type="submit"> REPORT </button>

    <button type="reset"> RESET </button> <br/>

    <p> Tap reset to go back to original values </p>
</form>


    </body>
</html>
