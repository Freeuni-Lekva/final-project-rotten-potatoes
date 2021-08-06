<%--
  Created by IntelliJ IDEA.
  User: tsakh
  Date: 06.08.21
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<form action="" method="POST">
    <button name = "CATEGORY" type = "submit" value = "MOVIES"> MOVIES </button>
    <button name = "CATEGORY" type = "submit" value = "TV SHOWS">TV SHOWS </button>
    <button name = "CATEGORY" type = "submit" value = "MUSIC"> MUSIC </button>
    <button name = "CATEGORY" type = "submit" value = "VIDEO GAMES"> VIDEO GAMES </button>
    <button name = "CATEGORY" type = "submit" value = "BOOKS"> BOOKS </button>
    <input type = "text" placeholder="search...">
    <button type= "submit">search</button><br/>
    <select name= "SORTING">
        <option value = "">Select</option>
        <option value = "RATING ASC">Rating ascending</option>
        <option value = "RATING DESC">Rating descending</option>
        <option value = "REWS ASC">Number of reviews ascending</option>
        <option value = "REWS DESC">Number of reviews descending</option>
        <option value = "DATE ASC">Date added ascending</option>
        <option value = "DATE DESC">Date added descending</option>
    </select>
    <button type = "submit">Sort</button>
</form>
</body>
</html>
