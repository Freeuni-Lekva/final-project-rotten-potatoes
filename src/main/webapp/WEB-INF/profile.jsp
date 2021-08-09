<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="Database.*" %>
<%@ page import="Models.*" %>
<%@ page import="Servlets.*" %>

<!DOCTYPE html>
<html>
    <%-- Information is transferred using attributes. Get username attribute and search for a user
        with given username. --%>

    <%
        String username = (String) request.getAttribute(User.ATTRIBUTE);
        DB db = (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE);
        User user = User.getUserByUsername(db, username);
    %>

    <head>
        <%-- Greet the user with their first name in the tab window. --%>
        <title>
            Welcome, <%= user.getFirstName() %>!
        </title>
    </head>

    <body>
        <%-- !!! These buttons need reference .jsp names which have been substituted by temporary ones !!! --%>
        <input type = "button" value = "Homepage" onClick = "javascript:window.location='index.jsp';">
        <%-- !!! Need a button that will call a log out servlet !!! --%>
        <button name="button" type="button">Log Out</button>

        <br><br>

        <%-- !!! Temporarily solution for new item creation feature (not sure if works) !!! --%>
        <form action="/addNewItem.jsp" method="post"> <%-- !!! .jsp file name might change !!! --%>
            <select name="NEW_ITEM_CATEGORY">
                <option value= <%= Movie.ATTRIBUTE %> >Movies</option>
                <option value= <%= TV_Show.ATTRIBUTE %> >TV Shows</option>
                <option value= <%= Music.ATTRIBUTE %> >Music</option>
                <option value= <%= Video_Game.ATTRIBUTE %> >Video Games</option>
                <option value= <%= Book.ATTRIBUTE %> >Books</option>
            </select>
            <input type="submit" value="Add New Item"/>
        </form>

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
            for(Badge badge : user.getBadges(db)){
                String badgeIcon = badge.getBadgeIcon(); %>

                <%-- !!! Images will probably have to be resized later and image folder will have to be moved !!! --%>
                <img src = <%= "images/" + badgeIcon %> width = "50">
        <% } %>

        <%-- !!! Add read more property and a limit on the amount of things shown !!! ---%>
        <%-- Display user followers. --%>
        <h2>
            My Followers:
        </h2>
        <%
            for(User follower : user.getFollowers(db)){
                String followerUsername = follower.getUsername(); %>
                <p><%= followerUsername %></p>
        <% } %>

        <%-- Display user following. --%>
        <h2>
            My Following:
        </h2>
        <%
            for(User following : user.getFollowing(db)){
                String followingUsername = following.getUsername(); %>
                <p><%= followingUsername %></p>
        <% } %>

        <%-- Display items uploaded by the user. --%>
        <h2>
            My Items:
        </h2>
        <%
            for(Item item : user.getItems(db)){
                String title = item.getTitle();
                String coverURL = item.getCoverURL();
                double score = item.getScore();
                int releaseDate = item.getReleaseDate(); %>

            <%-- !!! Cover images will probably need resizing !!! --%>
            <img src = <%= coverURL %> width = "100">
            <h3>
                <%= title %> <b>(<%= score %>/10) <%= releaseDate %></b>
            </h3>
        <% } %>

        <%-- Display reviews written by the user. --%>
        <h2>
            My Reviews:
        </h2>
        <%
            for(Review review : user.getReviews(db)){
                String itemID = review.getItemID();
                Item item = Item.getItemByID(db, itemID);
                String userReview = review.getReview();
                double userScore = review.getScore(); %>

            <%-- !!! Cover images will probably need resizing !!! --%>
            <img src = <%= item.getCoverURL() %> >
            <h4>
                <%= item.getTitle() %> <b>(<%= item.getScore() %>/10)</b>
            </h4>
            <h3>
                My Score: <%= userScore %><br>
                My Review: <%= userReview %>
            </h3>
        <% } %>
    </body>
</html>