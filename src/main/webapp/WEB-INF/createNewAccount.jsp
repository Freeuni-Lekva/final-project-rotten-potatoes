<%--
  Created by IntelliJ IDEA.
  User: tbasi
  Date: 8/9/2021
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Create New Account</title>
  </head>

  <body>
    <h1>Please enter proposed information</h1>
  </body>

  <form action = "createNewAcc" method = "post">
    <label for = "username">User name: </label>
    <input type = "text" id = "username" name = "username" /><br/><br/>
    <label for = "firstname">First name: </label>
    <input type = "text" id = "firstname" name = "firstname" /><br/><br/>
    <label for = "lastname">Last name: </label>
    <input type = "text" id = "lastname" name = "lastname" /><br/><br/>
    <label for = "birthdate">Date of Birth: </label>
    <input type = "date" id = "birthdate" name = "birthdate" /><br/><br/>
    <label for = "password">Password: </label>
    <input type = "password" id = "password" name = "password" /><br/><br/>
    <input type = "submit" value= "Create" /><br/><br/>
  </form>

  <% String info = (String)request.getSession().getAttribute("emptyFields"); %>
  <% if (info != null && !info.isEmpty()) { %>
  <p>Some of the fields are empty</p>
  <p>Please, enter the information correctly</p>
  <% } %>

  <% String accountExists = (String)request.getSession().getAttribute("existingacc"); %>
  <% if (accountExists != null && !accountExists.isEmpty()) { %>
  <p>Provided username is either in use or does not meet standards (no spaces allowed).</p>
  <% } %>

</html>
