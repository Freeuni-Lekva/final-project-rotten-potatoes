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
        <style>

            body {
                margin: 0;
                padding: 0;
                /*background-color: #c6ab7e; */
                height: 100vh;
                background-image: url("/images/theme4.png");
                background-position: center
            }
            #login .container #login-row #login-column #login-box {
                margin-top: 110px;
                max-width: 600px;
                height: 340px;
                border: 3px solid #1d1d93;
            }
            #login .container #login-row #login-column #login-box #login-form {
                padding: 20px;
            }
            #login .container #login-row #login-column #login-box #login-form #register-link {
                margin-top: -85px;
            }

        </style>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>Welcome</title>
    </head>

    <body style = "background-color: #916341;">
    <div id="login">
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="index.jsp" method="post">
                            <h3 class="text-center" style = "color: #1d1d93; font-weight: bold">Welcome</h3>
                            <div class="form-group">
                                <label for = "username" style = "color: #1d1d93; font-weight: bold">User name: </label><br/>
                                <input type = "text" id = "username" name = "username" class = "form-control text-center"/>
                            </div>
                            <div class="form-group">
                                <label for = "password" style = "color: #1d1d93; font-weight: bold">Password: </label><br/>
                                <input type = "password" id = "password" name = "password" class = "form-control"/>
                            </div>
                            <div class="form-group">
                                <input type = "submit" class = "btn btn-md btn-primary form-control" value= "Login"/>
                            </div>
                            <% String info = (String) request.getSession().getAttribute("invalidinfo"); %>
                            <% if (info != null && !info.isEmpty()) { %>
                            <h6 style = "color: red">Either your user name or password is incorrect or empty. Please try again.</h6>
                            <% } %>
                        </form>
                        <div class="form-group">
                            <form action = "guest" method = "get">
                                <input type = "submit" class = "btn btn-md btn-primary form-control" value= "Continue as a guest"/>
                            </form>
                        </div>
                        <div class="form-group">
                            <form action = "createNewAcc" method = "get">
                                <input type = "submit" class = "btn btn-md btn-primary form-control" value= "Create new account" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
