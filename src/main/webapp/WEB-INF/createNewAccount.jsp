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
    <style>

      body {
        margin: 0;
        padding: 0;
        /*background-color: #c6ab7e; */
        height: 100vh;
        background-image: url("/images/theme4.png");
        background-position: center
      }
      #create .container #create-row #create-column #create-box {
        margin-top: 50px;
        max-width: 600px;
        height: 500px;
        margin-bottom: 5px;
      }
      #create .container #create-row #create-column #create-box #create-form {
        padding: 20px;
      }
      #create .container #create-row #create-column #create-box #create-form  {
        margin-top: -85px;
      }

    </style>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Create New Account</title>
  </head>

  <body>
  <div id = create>
    <div class="container">
      <div id="create-row" class="row justify-content-center align-items-center">
        <div id="create-column" class="col-md-6">
          <div id="create-box" class="col-md-12">

            <form id = "createacc" action = "createNewAcc" method = "post">
              <h2 class="text-center" style = "color: #1d1d93; font-weight: bold">Please enter proposed information</h2>
              <div class="form-group">
                <label for = "username" style = "color: #1d1d93; font-weight: bold">Username: </label>
                <input type = "text" id = "username" name = "username" class = "form-control"/>
              </div>
              <div class="form-group">
                <label for = "firstname" style = "color: #1d1d93; font-weight: bold">First name: </label>
                <input type = "text" id = "firstname" name = "firstname" class = "form-control"/>
              </div>
              <div class="form-group">
                <label for = "lastname" style = "color: #1d1d93; font-weight: bold">Last name: </label>
                <input type = "text" id = "lastname" name = "lastname" class = "form-control"/>
              </div>
              <div class="form-group">
                <label for = "birthdate" style = "color: #1d1d93; font-weight: bold">Date of Birth: </label>
                <input type = "date" id = "birthdate" name = "birthdate" class = "form-control"/>
              </div>

              <div class="form-group">
                <label for = "password" style = "color: #1d1d93; font-weight: bold">Password: </label>
                <input type = "password" id = "password" name = "password" class = "form-control" /><br/><br/>
              </div>

              <% String info = (String)request.getSession().getAttribute("emptyFields"); %>
              <% if (info != null && !info.isEmpty()) { %>
              <h6 class="text-center" style = "color: red">Some of the fields are empty</h6>
              <h6 class="text-center" style = "color: red">Please, enter the information correctly</h6>
              <% } %>

              <% String accountExists = (String)request.getSession().getAttribute("existingacc"); %>
              <% if (accountExists != null && !accountExists.isEmpty()) { %>
              <h6 class="text-center" style = "color: red">Provided username is either in use or does not meet standards (no spaces allowed).</h6>
              <% } %>

              <div class="form-group">
                <input type = "submit" class = "btn btn-md btn-primary form-control" value= "Create"/>
              </div>
            </form>
           </div>
         </div>
        </div>
      </div>
    </div>
  </body>

</html>
