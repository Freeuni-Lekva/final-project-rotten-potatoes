<%--
  Created by IntelliJ IDEA.
  User: ttata
  Date: 8/6/2021
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD NEW ITEM</title>
</head>
<body>

<%--this and second line should be changed--%>
<% String category="VIDEO GAMES"; %>
<form name="insertForm" method="post" action="">
    <label >YOUR CHOSEN CATEGORY IS : <%= category %></label> <br/>
    <label for="title"> Title :</label>
    <input type="text" id="title" name="title"> <br/>


    <% if (category.equals("MOVIES")) { %>
    <label for="movieDirector"> Director: </label>
    <input type="text" id="movieDirector" name="movieDirector"> <br/>

    <label for="movieCast">Cast: </label>
    <input type="text" id="movieCast" name="movieCast"> <br/>

    <label for="movieSummery"> Summary: </label>
    <input type="text" id="movieSummery" name="movieSummery"> <br/>
<% } %>


    <% if(category.equals("MUSIC")){  %>
    <label for="movieArtist"> Artist: </label>
    <input type="text" id="movieArtist" name="movieArtist">  <br/>


    <label for="movieLabel">Label: </label>
    <input type="text" id="movieLabel" name="movieLabel">  <br/>

    <label for="musicGenre" > Genre: </label>
    <input type="text" id="musicGenre" name="musicGenre">  <br/>
    <%} %>


    <% if(category.equals("TV SHOWS")){  %>

    <label for="showDirector"> Director: </label>
    <input type="text" id="showDirector" name="showDirector"> <br/>

    <label for="showCast"> Cast: </label>
    <input type="text" id="showCast" name="showCast"> <br/>


    <label for="showSummery"> Summary: </label>
    <input type="text" id="showSummery" name="showSummery"> <br/>

    <label for="showDate"> Airing year: </label>
    <input type="text" id="showDate" name="showDate"> <br/>

    <%} %>


    <% if(category.equals("BOOKS")){  %>

    <label for="bookWriter"> Writer: </label>
    <input type="text" id="bookWriter" name="bookWriter"> <br/>


    <label for="bookSummery"> Summary: </label>
    <input type="text" id="bookSummery" name="bookSummery"> <br/>

    <%} %>

    <% if(category.equals("VIDEO GAMES")){  %>

    <label for="gameDevelopers"> Developers: </label>
    <input type="text" id="gameDevelopers" name="gameDevelopers"> <br/>


    <label for="gameSummery"> Summary: </label>
    <input type="text" id="gameSummery" name="gameSummery"> <br/>

    <%} %>


    <% if(category.equals("VIDEO GAMES") || category.equals("MOVIES")||
            category.equals("MUSIC") || category.equals("BOOKS")){  %>
    <label for="date"> Release date:</label>
    <input type="text" id="date" name="date"> <br/>

    <%} %>


    <label for="cover"> Cover url:</label>
    <input type="text" id="cover" name="cover"> <br/>

    <button type="submit"> REGISTER </button>
    <button type="reset"> RESET </button>
</form>

</body>
</html>
