<%--
  Created by IntelliJ IDEA.
  User: tsakh
  Date: 06.08.21
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.*" %>
<%@ page import="java.awt.desktop.UserSessionEvent" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<form action="" method="POST">
    <button name = "CATEGORY" type = "submit" value = <%=Movie.ATTRIBUTE%> > MOVIES </button>
    <button name = "CATEGORY" type = "submit" value = <%=TV_Show.ATTRIBUTE%> >TV SHOWS </button>
    <button name = "CATEGORY" type = "submit" value = <%=Music.ATTRIBUTE%> > MUSIC </button>
    <button name = "CATEGORY" type = "submit" value = <%=Video_Game.ATTRIBUTE%> > VIDEO GAMES </button>
    <button name = "CATEGORY" type = "submit" value = <%=Book.ATTRIBUTE%> > BOOKS </button> <br/>

    <select name= "SORTING">
        <option value = "RATING ASC">Rating ascending</option>
        <option value = "RATING DESC">Rating descending</option>
        <option value = "REVIEWS ASC">Number of reviews ascending</option>
        <option value = "REVIEWS DESC">Number of reviews descending</option>
        <option value = "DATE ASC">Release date ascending</option>
        <option value = "DATE DESC">Release date descending</option>
    </select>
    <input type = "text" id = "SEARCH" placeholder="search...">
    <button type= "submit">Submit</button><br/>
</form>
<%
    String category = request.getParameter("CATEGORY");
    String sorting = request.getParameter("SORTING");
    String search = request.getParameter("SEARCH");
    for(Item item : Item.getItems()){
        String title = item.getTitle();
        String coverURL = item.getCoverURL();
        String itemId = item.getItemID();
        String individualLink = "show-item.jsp?id=" + itemId;
        double score = item.getScore(); %>

<img src = <%= coverURL %> >
<h3>
    <%= title %> <b>(<%= score %>/10)</b>
</h3>
<% } %>
</body>
</html>
