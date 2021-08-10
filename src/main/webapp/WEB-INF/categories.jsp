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
    <body>
        <form action="guest" method="POST">
            <button name = "CATEGORY" type = "submit" value = <%=Movie.ATTRIBUTE%> > MOVIES </button>
            <button name = "CATEGORY" type = "submit" value = <%=TV_Show.ATTRIBUTE%> >TV SHOWS </button>
            <button name = "CATEGORY" type = "submit" value = <%=Music.ATTRIBUTE%> > MUSIC </button>
            <button name = "CATEGORY" type = "submit" value = <%=Video_Game.ATTRIBUTE%> > VIDEO GAMES </button>
            <button name = "CATEGORY" type = "submit" value = <%=Book.ATTRIBUTE%> > BOOKS </button> <br/>

            <select id = "SORTING" name= "SORTING">
                <option value = "score ASC">Rating ascending</option>
                <option value = "score DESC">Rating descending</option>
                <option value = "num_of_reviews ASC">Number of reviews ascending</option>
                <option value = "num_of_reviews DESC">Number of reviews descending</option>
                <option value = "release_date ASC">Release date ascending</option>
                <option value = "release_date DESC">Release date descending</option>
            </select>
            <input type = "text" name = "SEARCH" id = "SEARCH" placeholder="search...">
            <button type= "submit">Submit</button><br/>
        </form>
        <%

            DB db = (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE);
            String category = (String)request.getSession().getAttribute("CATEGORY");
            String sorting = (String)request.getSession().getAttribute("SORTING");
            String search = (String)request.getSession().getAttribute("SEARCH");
            for(Item item : Item.getItems(db, category, search, sorting)){
                String title = item.getTitle();
                String coverURL = item.getCoverURL();
                String itemId = item.getItemID();
                String individualLink = "show-item.jsp?id=" + itemId;
                double score = item.getScore(); %>
                <a href= <%= individualLink %>> <b> <%= title %> <b>(<%= score %>/10)</b>
                <img src = <%= coverURL %>>
            <% } %>
    </body>
</html>
