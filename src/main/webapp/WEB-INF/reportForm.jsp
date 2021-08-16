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
        <style>
            body {
                margin-top: 100px;
                font-family: "Arial", sans-serif;
            }
            .btn-success{
                color: chocolate;
                border-color: #ffffe6;
            }
        </style>
    </head>
    <body style="background-color:Linen;">

<% String category=(String) request.getSession().getAttribute("CATEGORY"); %>
<% String itemID=(String) request.getSession().getAttribute("id"); %>
<% DB db= (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE); %>
<%  Item currItem= null;
    try {
     currItem= Item.getItemByID(db,itemID);
} catch (SQLException throwables) {
    throwables.printStackTrace();
} %>



<form name="reportForm" method="POST" action="reportForm">

    <div class="col-xs-1" align="center">
    <h1><b>REPORT FORM:</b> </h1>



    <center><div class="form-group">
    <label for="title"> Title :</label>
    <input type="text" id="title" name="title" value=<%= currItem.getTitle()%>> <br/>
    </div></center><br>

    <% if (category!=null && category.equals(Movie.ATTRIBUTE)) { %>
    <% Movie currMovie= Movie.getMovieByID(db,itemID); %>
        <center><div class="form-group">
    <label for="movieDirector"> Director: </label>
    <input type="text" id="movieDirector" name="movieDirector" value= <%=Item.surroundWithSingleQuotes(currMovie.getDirector())%>> <br/>
            </div></center><br>
                <center><div class="form-group">
    <label for="movieCast"> Cast: </label>
    <input type="text" id="movieCast" name="movieCast" value=<%= Item.surroundWithSingleQuotes(currMovie.getMovieCast())%>> <br/>
                </div></center><br>
        <center><div class="form-group">
    <label for="movieSummary"> Summary: </label>
    <input type="text" id="movieSummary" name="movieSummary" value=<%= Item.surroundWithSingleQuotes(currMovie.getSummary())%>> <br/>
        </div></center><br>
    <% } %>


    <% if(category!=null && category.equals(Music.ATTRIBUTE)){  %>

    <% Music currMusic= Music.getMusicByID(db, itemID); %>
        <center><div class="form-group">
    <label for="musicArtist"> Artist: </label>
    <input type="text" id="musicArtist" name="musicArtist" value=<%= Item.surroundWithSingleQuotes(currMusic.getArtist())%>>  <br/>
        </div></center><br>

        <center><div class="form-group">
    <label for="musicLabel">Label: </label>
    <input type="text" id="musicLabel" name="musicLabel" value=<%= Item.surroundWithSingleQuotes(currMusic.getLabel())%>>  <br/>
        </div></center><br>

        <center><div class="form-group">
    <label for="musicGenre" > Genre: </label>
    <input type="text" id="musicGenre" name="musicGenre" value=<%= Item.surroundWithSingleQuotes(currMusic.getGenre())%>>  <br/>
        </div></center><br>
    <%} %>


    <% if(category!=null && category.equals(TV_Show.ATTRIBUTE)){  %>

    <% TV_Show currShow= TV_Show.getTVShowByID(db,itemID);%>
        <center><div class="form-group">
    <label for="showDirector"> Director: </label>
    <input type="text" id="showDirector" name="showDirector" value=<%=Item.surroundWithSingleQuotes(currShow.getDirector())%>> <br/>
        </div></center><br>

        <center><div class="form-group">
    <label for="showCast"> Cast: </label>
    <input type="text" id="showCast" name="showCast" value=<%=Item.surroundWithSingleQuotes(currShow.getTVShowCast())%>> <br/>
        </div></center><br>

        <center><div class="form-group">
    <label for="showSummary"> Summary: </label>
    <input type="text" id="showSummary" name="showSummary" value=<%=Item.surroundWithSingleQuotes(currShow.getSummary()) %>> <br/>
        </div></center><br>

        <center><div class="form-group">
    <label for="showDate"> Airing year: </label>
    <input type="text" id="showDate" name="showDate" value=<%= Item.surroundWithSingleQuotes(String.valueOf(currShow.getAiringYear()))%>> <br/>
        </div></center><br>
    <%} %>


    <% if(category!=null &&category.equals(Book.ATTRIBUTE)){  %>

    <% Book currBook = Book.getBookByID(db, itemID); %>
        <center><div class="form-group">
    <label for="bookWriter"> Writer: </label>
    <input type="text" id="bookWriter" name="bookWriter" value=<%=Item.surroundWithSingleQuotes(currBook.getWriter()) %>> <br/>
        </div></center><br>

            <center><div class="form-group">
    <label for="bookSummary"> Summary: </label>
    <input type="text" id="bookSummary" name="bookSummary" value=<%= Item.surroundWithSingleQuotes(currBook.getSummary()) %>> <br/>
        </div></center><br>
    <%} %>

    <% if(category!=null &&category.equals(Video_Game.ATTRIBUTE)){  %>

    <% Video_Game currGame= Video_Game.getVideoGameByID(db,itemID);%>
        <center><div class="form-group">
    <label for="gameDevelopers"> Developers: </label>
    <input type="text" id="gameDevelopers" name="gameDevelopers" value=<%= Item.surroundWithSingleQuotes(currGame.getDevelopers())%>> <br/>
        </div></center><br>

            <center><div class="form-group">
    <label for="gameSummary"> Summary: </label>
    <input type="text" id="gameSummary" name="gameSummary" value=<%= Item.surroundWithSingleQuotes(currGame.getSummary())%>> <br/>
        </div></center><br>
    <%} %>


    <% if(category!=null && (category.equals(Video_Game.ATTRIBUTE) || category.equals(Movie.ATTRIBUTE)||
            category.equals(Music.ATTRIBUTE) || category.equals(Book.ATTRIBUTE))){  %>
        <center><div class="form-group">
    <label for="date"> Release date:</label>
    <input type="text" id="date" name="date" value=<%=Item.surroundWithSingleQuotes(String.valueOf(currItem.getReleaseDate()))%>> <br/>
        </div></center><br>
    <%} %>

        <center><div class="form-group">
    <label for="cover"> Cover url:</label>
    <input type="text" id="cover" name="cover" value=<%=Item.surroundWithSingleQuotes(currItem.getCoverURL())%>> <br/>
        </div></center><br>

        <center><div class="form-group">
    <label for="messageToAdmin"> Message: </label>
    <input type="text" id="messageToAdmin" name="messageToAdmin" placeholder=  "type text here... "> <br/>
        </div></center><br>


    <button class="btn btn-success btn-lg" type="submit"> REPORT </button>

    <button class="btn btn-success btn-lg" type="reset"> RESET </button> <br/>

        <p> <em>Tap reset to go back to original values</em> </p>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
        integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>
