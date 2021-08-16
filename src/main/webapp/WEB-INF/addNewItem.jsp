<%@ page import="Models.*" %><%--
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
        <% String category=(String) request.getSession().getAttribute("NEW_ITEM_CATEGORY"); %>
    </head>

    <% if(category!=null && category.equals(Movie.ATTRIBUTE)){ %>
        <body style="background-color:#ffcfcc;">
    <% } else if(category!=null && category.equals(TV_Show.ATTRIBUTE)){ %>
        <body style="background-color:#ccf1ff;">
    <% } else if(category!=null && category.equals(Music.ATTRIBUTE)){ %>
        <body style="background-color:#ebebeb;">
    <% } else if(category!=null && category.equals(Video_Game.ATTRIBUTE)){ %>
        <body style="background-color:#d9f6ff;">
    <% } else if(category!=null && category.equals(Book.ATTRIBUTE)){ %>
        <body style="background-color:#f2e3ce;">
    <% } %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <%--this and second line should be changed--%>
        <%--<% String category= (String) request.getAttribute("category"); %>--%>
        <%--<% String currUser= (String) request.getAttribute("currUser"); %>--%>

        <div class="has-bg-img">
            <% if(category!=null && category.equals(Movie.ATTRIBUTE)){ %>
                <img class="bg-img" src="../banners/movies.png" alt="">
            <% } else if(category!=null && category.equals(TV_Show.ATTRIBUTE)){ %>
                <img class="bg-img" src="../banners/tv_shows.png" alt="">
            <% } else if(category!=null && category.equals(Music.ATTRIBUTE)){ %>
                <img class="bg-img" src="../banners/music.png" alt="">
            <% } else if(category!=null && category.equals(Video_Game.ATTRIBUTE)){ %>
                <img class="bg-img" src="../banners/video_games.png" alt="">
            <% } else if(category!=null && category.equals(Book.ATTRIBUTE)){ %>
                <img class="bg-img" src="../banners/books.png" alt="">
            <% } %>

            <form name="insertForm" method="POST" action="addNewItem">
                <br><br>
                <%--
                <div class="row">
                    <h2><center><label>YOUR CHOSEN CATEGORY IS: <strong><%= category %></strong></label></center></h2><br/>
                </div> --%>

                <center><div class="form-group">
                    <label for="title"><h3>Title: </h3></label>
                    <input type="text" id="title" size="25" name="title">
                </div></center><br>

                <% if (category!=null && category.equals(Movie.ATTRIBUTE)) { %>
                    <center><div class="form-group">
                        <label for="movieDirector"><h3>Director: </h3></label>
                        <input type="text" id="movieDirector" size="25" name="movieDirector"> <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="movieCast"><h3>Cast: </h3></label>
                        <input type="text" id="movieCast" size="25" name="movieCast"> <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="movieSummary"><h3>Summary: </h3></label>
                        <input type="text" id="movieSummary" size="120" name="movieSummary"> <br/>
                    </div></center><br>
                <% } %>


                <% if(category!=null && category.equals(Music.ATTRIBUTE)){  %>
                    <center><div class="form-group">
                        <label for="musicArtist"><h3>Artist: </h3></label>
                        <input type="text" id="musicArtist" size="25" name="musicArtist">  <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="musicLabel"><h3>Label: </h3></label>
                        <input type="text" id="musicLabel" size="25" name="musicLabel">  <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="musicGenre"><h3>Genre: </h3></label>
                        <input type="text" id="musicGenre" size="25" name="musicGenre">  <br/>
                    </div></center><br>
                <%} %>


                <% if(category!=null && category.equals(TV_Show.ATTRIBUTE)){  %>

                    <center><div class="form-group">
                        <label for="showDirector"><h3>Director: </h3></label>
                        <input type="text" id="showDirector" size="25" name="showDirector"> <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="showCast"><h3>Cast: </h3></label>
                        <input type="text" id="showCast" size="25" name="showCast"> <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="showSummary"><h3>Summary: </h3></label>
                        <input type="text" id="showSummary" size="120" name="showSummary"> <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="showDate"><h3>Airing year: </h3></label>
                        <input type="text" id="showDate" size="25" name="showDate"> <br/>
                    </div></center><br>
                <%} %>


                <% if(category!=null &&category.equals(Book.ATTRIBUTE)){  %>
                    <center><div class="form-group">
                        <label for="bookWriter"><h3>Writer: </h3></label>
                        <input type="text" id="bookWriter" size="25" name="bookWriter"> <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="bookSummary"><h3>Summary: </h3></label>
                        <input type="text" id="bookSummary" size="120" name="bookSummary"> <br/>
                    </div></center><br>
                <%} %>

                <% if(category!=null &&category.equals(Video_Game.ATTRIBUTE)){  %>
                    <center><div class="form-group">
                        <label for="gameDevelopers"><h3>Developers: </h3></label>
                        <input type="text" id="gameDevelopers" size="25" name="gameDevelopers"> <br/>
                    </div></center><br>

                    <center><div class="form-group">
                        <label for="gameSummary"><h3>Summary: </h3></label>
                        <input type="text" id="gameSummary" size="120" name="gameSummary"> <br/>
                    </div></center><br>
                <%} %>


                <% if(category!=null && (category.equals(Video_Game.ATTRIBUTE) || category.equals(Movie.ATTRIBUTE)||
                        category.equals(Music.ATTRIBUTE) || category.equals(Book.ATTRIBUTE))){  %>

                    <center><div class="form-group">
                        <label for="date"><h3>Release date: </h3></label>
                        <input type="text" id="date" size="15" name="date"> <br/>
                    </div></center><br>
                <%} %>

                <center><div class="form-group">
                    <label for="cover"><h3>Cover url: </h3></label>
                    <input type="text" id="cover" size="120" name="cover"> <br/>
                </div></center><br>

                <center><div class="form-group">
                <input type="checkbox" id="sendNotification" name="sendNotification">
                <label for="sendNotification">SEND NOTIFICATION TO USERS I FOLLOW</label>
                </div></center><br>

                <% String error = (String) request.getSession().getAttribute("hasError"); %>
                <% if (error != null) { %>
                <center><h2>YOUR ENTERED INFORMATION IS INCORRECT, PLEASE CHECK</h2></center>
                <% } %>

                <center><button class="btn btn-success btn-lg" type="submit"> REGISTER </button>
                <button class="btn btn-danger btn-lg" type="reset"> RESET </button></center>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
        integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>
