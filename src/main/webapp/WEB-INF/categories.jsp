<%--
  Created by IntelliJ IDEA.
  User: tsakh
  Date: 06.08.21
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.*" %>
<%@ page import="Database.*" %>
<%@ page import="Servlets.*" %>

<%@ page import="java.awt.desktop.UserSessionEvent" %>
<html>
    <head>
        <title>Categories</title>
    </head>
    <body style="background-color:#ffffe6;">
        <body style="background-color:AliceBlue;">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

        <div class="container">
            <br>
            <div class="row">
                <div class="col-10">
                    <form action="guest" method="POST">
                        <button name = "CATEGORY" class="btn btn-warning btn-lg" style="margin:5px;" type = "submit" value = <%=Movie.ATTRIBUTE%> > MOVIES </button>
                        <button name = "CATEGORY" class="btn btn-warning btn-lg" style="margin:5px;" type = "submit" value = <%=TV_Show.ATTRIBUTE%> >TV SHOWS </button>
                        <button name = "CATEGORY" class="btn btn-warning btn-lg" style="margin:5px;" type = "submit" value = <%=Music.ATTRIBUTE%> > MUSIC </button>
                        <button name = "CATEGORY" class="btn btn-warning btn-lg" style="margin:5px;" type = "submit" value = <%=Video_Game.ATTRIBUTE%> > VIDEO GAMES </button>
                        <button name = "CATEGORY" class="btn btn-warning btn-lg" style="margin:5px;" type = "submit" value = <%=Book.ATTRIBUTE%> > BOOKS </button> <br/>
                        <br>
                        <select id = "SORTING" name= "SORTING">
                            <option value = "score ASC">Rating ascending</option>
                            <option value = "score DESC">Rating descending</option>
                            <option value = "num_of_reviews ASC">Number of reviews ascending</option>
                            <option value = "num_of_reviews DESC">Number of reviews descending</option>
                            <option value = "release_date ASC">Release date ascending</option>
                            <option value = "release_date DESC">Release date descending</option>
                        </select>
                        <input type = "text" name = "SEARCH" id = "SEARCH" placeholder="search...">
                        <button class="btn btn-danger btn-lg" type= "submit">Submit</button><br/>
                    </form>
                </div>

                <%String username = (String) request.getSession().getAttribute("username");
                    if (username == null){ %>
                    <div class="col">
                        <form action="createNewAcc" method="GET">
                            <button style="margin:5px;" type = "submit" class="btn btn-primary btn-lg"> REGISTER </button>
                        </form>
                        <form action="/index.jsp" method="get">
                            <button style="margin:5px;" type = "submit" class="btn btn-primary btn-lg"> LOGIN </button>
                        </form>
                    </div>
                <%} else { %>
                    <div class="col">
                        <form action="profile.jsp" method="POST">
                            <button style="margin:5px;" name = "username" type= "submit" class="btn btn-primary btn-lg" value = <%=username%> > MY PROFILE </button>
                        </form>
                    </div>
                <%}%>
            </div>
            <div class="row">

            </div>
            <%
            DB db = (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE);
            String category = (String)request.getSession().getAttribute("CATEGORY");
            String sorting = (String)request.getSession().getAttribute("SORTING");
            String search = (String)request.getSession().getAttribute("SEARCH");

            for(Item item : Item.getItems(db, category, search, sorting)){
                String title = item.getTitle();
                String coverURL = item.getCoverURL();
                String itemId = item.getItemID();
                String individualLink = "product?id=" + Item.getItemIdWithoutSpaces(itemId);
                double score = item.getScore(); %>

                <%--
                <a href= <%= individualLink %>> <b> <%= title %> <b>(<%= score %>/10)</b>
                <img src = <%= coverURL %> width="300" height="300"> <br/> --%>

                <div class="figure" style="width:300px;">
                    <% if(category != null && category.equals("VIDEO_GAMES") || category.equals("MUSIC")){ %>
                        <img src=<%= coverURL %> style="width: 300px; height: 300px; object-fit: fill;" class="img-thumbnail" alt="logo" />
                    <% } else { %>
                        <img src=<%= coverURL %> style="width: 300px; height: 400px; object-fit: fill;" class="img-thumbnail" alt="logo" />
                    <% } %>
                    <div class="caption"><a href= <%= individualLink %> class="link-primary"
                    style="text-decoration: none; font-size: 20px;">
                    <%= title %><br>
                    <strong>(<%= score %>/10)</strong></a></p></div>
                </div>
            <% } %>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
        integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>
