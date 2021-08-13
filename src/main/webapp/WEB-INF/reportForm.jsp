<%--
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
        <form action="reportServlet" method="POST">
            <input type="text" id="messageToAdmin" name="messageToAdmin" placeholder=  "type text here... "> <br/>
            <button type="submit" > Send message to admin </button> <br/>

        </form>
    </body>
</html>
