<%--
  Created by IntelliJ IDEA.
  User: tbasi
  Date: 8/8/2021
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Servlets.*" %>
<%@ page import="Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Welcome</title>
    </head>

    <body>
    <p>Welcome</p>
    </body>

    <form action = "index.jsp" method = "post">
        <label for = "username">User name: </label>
        <input type = "text" id = "username" name = "username" /><br/><br/>
        <label for = "password">Password: </label>
        <input type = "password" id = "password" name = "password"  />
        <input type = "submit" value= "Login" /><br/><br/>
        <% String info = (String) request.getSession().getAttribute("invalidinfo"); %>
        <% if (info != null && !info.isEmpty()) { %>
            <p>Either your user name or password is incorrect or empty. Please try again.</p>
        <% } %>
    </form>

    <form action = "guest" method = "get">
        <input type = "submit" value= "Continue as a guest" />
    </form>

    <form action = "createNewAcc" method = "get">
        <input type = "submit" value= "Create new account" />
    </form>

</html>
