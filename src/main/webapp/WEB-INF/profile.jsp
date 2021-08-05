<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>

    <%-- Information is transferred using attributes. Get username attribute and search for a user
        with given username. --%>
    <%
        String username = (String) request.getSession().getAttribute(User.ATTRIBUTE);
        User user = User.getUserByUsername(username);
    %>

    <head>
        <%-- Greet the user with their first name in the tab window. --%>
        <title>
            Welcome, <%= user.getFirstName() %>!
        </title>
    </head>

    <body>
        <%-- !!! Display log out and homepage buttons !!! --%>

        <%-- Display username at top with big chunky letters. --%>
        <h1>
            <%= user.getUsername() %>
        </h1>

        <%-- Display additional information about the user. --%>
        <p>
            First Name: <%= user.getFirstName() %> <br>
            Last Name: <%= user.getLastName() %> <br>
            Date of Birth: <%= user.getDateOfBirth() %>
        </p>

        <%-- Display all user badges. --%>
        <h2>
            My Badges:
        </h2>
        <%
            for(Badge badge : user.getBadges()){
                String badgeIcon = badge.getBadgeIcon(); %>

                <%-- !!! Images will probably have to be resized later and image folder will have to be moved !!! --%>
                <img src = <%= "images/" + badgeIcon %> >
        <%> } <%>

        <%-- !!! Add read more property and a limit on the amount of things shown !!! ---%>
        <%-- Display user followers. --%>
        <h2>
            My Followers:
        </h2>
        <%
            for(User follower : user.getFollowers()){
                String followerUsername = follower.getUsername(); %>
                <%= followerUsername %>
        <%> } <%>

        <%-- Display user following. --%>
        <h2>
            My Following:
        </h2>
        <%
            for(User following : user.getFollowing()){
                String followingUsername = following.getUsername(); %>
                <%= followingUsername %>
        <%> } <%>
    </body>
</html>