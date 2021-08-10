<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="Database.*" %>
<%@ page import="Models.*" %>
<%@ page import="Servlets.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <%-- Information is transferred using attributes. Get username attribute and search for a user
        with given username. --%>

    <%
        String USER_VISIT = "USER_VISIT";
        String PERSONAL_VISIT = "PERSONAL_VISIT";
        String GUEST_VISIT = "GUEST_VISIT";

        String VISIT = "";

        /*  სულ მჭირდება ორი ატრიბუტი: username და guest. ოთხი ვარიანტის აღწერა:
            username not null, guest not null: ე.ი. ვიღაცა იუზერი ამჟამად დალოგინებულია და სტუმრობს
            სხვა ტიპის პროფილის გვერდს. ასეთ დროს, ვისაც სტუმრობს, იმის პროფილის გვერდი ოდნავ განსხვავებულად
            უნდა გამოვსახო. [USER_VISIT]
            username not null, guest null: ე.ი. ამჟამად საკუთარ გვერდზე იმყოფება და თავის პროფილის ფეიჯს უყურებს.
            ესეიგი, უნდა გამოისახოს პირადი პროფილის გვერდი. [PERSONAL_VISIT]
            username null, guest not null: ე.ი. დაურეგისტრირებელი მომხმარებელი ამჟამად სტუმრობს სხვა იუზერის
            პროფილს. ასეთ შემთხვევაშიც მესამე ტიპის ვიზუალი არის საჭირო (მაგალითად, აქ follow/unfollow
            ბათონები არ უნდა იყოს). [GUEST_VISIT]
            username null, guest null: წესით, ასეთი შემთხვევა არ უნდა მოხდეს, რადგან, თუ ასეა, ე.ი. profile ფეიჯზე
            არც ვდგავართ.
        */

        String username = (String) request.getAttribute(User.ATTRIBUTE);
        String guestUsername = (String) request.getAttribute("guest");

        /* სანამ სერვლეტ/ჯეესპეებზე არ მაქვს წვდომა, ტესტი:
        String username = "admin";
        String guestUsername = "sjanj19"; */

        // გავარკვიოთ, ვიზიტის რომელ ტიპაჟში ვიმყოფებით.
        if(username != null && guestUsername != null){
            VISIT = USER_VISIT;
        } else if(username != null && guestUsername == null){
            VISIT = PERSONAL_VISIT;
        } else if(username == null && guestUsername != null){
            VISIT = GUEST_VISIT;
        }

        // ბაზასთან დასაკავშირებელი ატრიბუტის ამოღება ნებისმიერ შემთხვევაში გვჭირდება.
        DB db = (DB) application.getAttribute(ContextListener.DB_ATTRIBUTE);

        User user = null;
        User guest = null;

        if(VISIT == USER_VISIT){
            user = User.getUserByUsername(db, username);
            guest = User.getUserByUsername(db, guestUsername);
        } else if(VISIT == PERSONAL_VISIT){
            user = User.getUserByUsername(db, username);
        } else if(VISIT == GUEST_VISIT){
            guest = User.getUserByUsername(db, guestUsername);
        }
    %>

    <%-- tab window-ზე სხვადასხვა რამ დაიწერება ვიზიტის ტიპის შესაბამისად. --%>
    <head>
        <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){ %>
            <title>
                <%-- &#39 is a single quotation mark symbol. --%>
                <%= guest.getUsername() %>&#39s Profile
            </title>
        <% } else if(VISIT == PERSONAL_VISIT){ %>
            <title>
                Your Profile
            </title>
        <% } %>
    </head>

    <body>
        <%-- ეს ბათონები სხვანაირად უნდა იყოს შექმნილი. ჯერ-ჯერობით, კომენტარად მივუწერ, მათზე დაჭერისას რაები უნდა მოხდეს. --%>
        <% if(VISIT == USER_VISIT){ %>
            <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
            <button name="button" type="button">HOMEPAGE</button>

            <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს საკუთარი პროფილის გვერდზე. --%>
            <button name="button" type="button">MY PROFILE</button>

            <%-- guestUsername და username ატრიბუტები უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
            <button name="button" type="button">LOG OUT</button>
        <% } else if(VISIT == PERSONAL_VISIT){ %>
            <%-- მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
            <button name="button" type="button">HOMEPAGE</button>

            <%-- username ატრიბუტები უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
            <button name="button" type="button">LOG OUT</button>
        <% } else if(VISIT == GUEST_VISIT){ %>
            <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
            <button name="button" type="button">HOMEPAGE</button>

            <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
            <button name="button" type="button">LOG IN/REGISTER</button>
        <% } %>

        <br><br>

        <%-- ახალი ნივთის შექმნა მხოლოდ მაშინ შეიძლება, როდესაც გვყავს რეგისტრირებული მომხმარებელი, რომელიც ამჟამად
                     საკუთარი პროფილის გვერდზე იმყოფება. --%>
        <%-- !!! Temporarily solution for new item creation feature (not sure if works) !!! --%>

        <% if(VISIT == PERSONAL_VISIT){ %>
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
        <% } %>

        <%-- პირადი ინფორმაციის გამოსახვა ვიზიტების შესაბამისად. --%>

        <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){ %>
            <%-- Display username at top with big chunky letters. --%>
            <h2>
                YOU ARE VIEWING <b><%= guest.getUsername() %></b>&#39S PROFILE.
            </h2>
        <% } else if(VISIT == PERSONAL_VISIT){ %>
            <%-- Display username at top with big chunky letters. --%>
            <h2>
                WELCOME TO YOUR PROFILE, <b><%= user.getUsername() %></b>!
            </h2>
            <%-- რადგან ეს პირადი გვერდია, გამოვსახავთ დამატებით პირად ინფორმაციას. --%>
            <%-- Display additional information about the user. --%>
            <h3>
                <b>FIRST NAME:</b> <%= user.getFirstName() %> <br>
                <b>LAST NAME:</b> <%= user.getLastName() %> <br>
                <b>DATE OF BIRTH:</b> <%= user.getDateOfBirth() %>
            </h3>
        <% } %>

        <%-- badge-ების გამოსახვა ვიზიტების შესაბამისად. --%>

        <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){
            List<Badge> guestBadges = guest.getBadges(db);

            if(guestBadges.isEmpty()){ %>
                <h2>
                  <b><%= guest.getUsername() %> hasn&#39t earned any badges yet.</b>
                </h2>
            <% } else { %>
                <h2>
                    <b><%= guest.getUsername() %>&#39S BADGES:</b>
                </h2>

                <%
                    for(Badge badge : guestBadges){
                        String badgeIcon = badge.getBadgeIcon(); %>

                        <%-- !!! Images will probably have to be resized later and image folder will have to be moved !!! --%>
                        <img src = <%= "images/" + badgeIcon %> width = "50">
                <% } %>

            <% } %>

        <% } else if(VISIT == PERSONAL_VISIT){
             List<Badge> userBadges = user.getBadges(db);

             if(userBadges.isEmpty()){ %>
                <h2>
                    <b>You haven&#39t earned any badges yet.</b>
                </h2>
            <% } else { %>
                <%
                    for(Badge badge : userBadges){
                        String badgeIcon = badge.getBadgeIcon(); %>

                        <%-- !!! Images will probably have to be resized later and image folder will have to be moved !!! --%>
                        <img src = <%= "images/" + badgeIcon %> width = "50">
                <% } %>

            <% } %>

        <% } %>

        <%-- Followers/Following-ის გამოსახვა ვიზიტის შესაბამისად. სტუმრებთან მხოლოდ followers/following-ის რაოდენობები
                     გამოჩნდება. პირად პროფილზე კონკრეტული მომხმარებლების სია გამოჩდნება. --%>
        <%-- !!! Add read more property and a limit on the amount of things shown !!! ---%>

        <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){ %>

            <h2>
                <b><%= guest.getUsername() %>&#39S FOLLOWERS: </b><%= guest.getFollowers(db).size() %> <br>
                <b><%= guest.getUsername() %>&#39S FOLLOWING: </b><%= guest.getFollowing(db).size() %>
            </h2>

        <% } else if(VISIT == PERSONAL_VISIT){ %>

            <%-- აქ მოგვიანებით ერთი ცვლილება უნდა დაემატოს: მომხმარებელს უნდა შეეძლოს, რომ hyperlink-ით
                 თავისი რომელიმე follower/following მომხმარებლის პროფილის გვერდზე გადავიდეს. --%>

            <h2>
                YOUR FOLLOWERS (<%= user.getFollowers(db).size() %>):
            </h2>
            <%
                for(User follower : user.getFollowers(db)){
                    String followerUsername = follower.getUsername(); %>
                    <p><%= followerUsername %></p>
            <% } %>

            <h2>
                YOUR FOLLOWING (<%= user.getFollowing(db).size() %>):
            </h2>
            <%
                for(User following : user.getFollowing(db)){
                    String followingUsername = following.getUsername(); %>
                    <p><%= followingUsername %></p>
            <% } %>

        <% } %>

        <%-- ატვირთული ნივთების სიის გამოქვეყნება. --%>
        <%-- აქაც კარგი იქნება შემდეგი დამატება: hyperlink-ები, რომლებიც მომხმარებელს პროდუქტის გვერდზე გადაიყვანენ. --%>

        <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){
              List<Item> guestItems = guest.getItems(db);

              if(guestItems.isEmpty()){ %>
                  <h2>
                      <b><%= guest.getUsername() %> hasn&#39t uploaded any items yet.</b>
                  </h2>
              <% } else { %>
                    <h2>
                        <b><%= guest.getUsername() %>&#39S ITEMS:</b>
                    </h2>

                    <%
                        for(Item item : guestItems){
                            String title = item.getTitle();
                            String coverURL = item.getCoverURL();
                            double score = item.getScore();
                            int releaseDate = item.getReleaseDate(); %>

                        <%-- !!! Cover images will probably need resizing !!! --%>
                        <img src = <%= coverURL %> width = "100">
                        <h3>
                            <%= title %>, <%= releaseDate %> (<%= score %>/10)
                        </h3>
                    <% } %>
              <% } %>
        <% } else if(VISIT == PERSONAL_VISIT){
                List<Item> userItems = user.getItems(db);

                if(userItems.isEmpty()){ %>
                    <h2>
                      <b>You haven&#39t uploaded any items yet.</b>
                    </h2>
                <% } else { %>
                    <h2>
                        <b>YOUR ITEMS:</b>
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
                            <%= title %>, <%= releaseDate %> (<%= score %>/10)
                        </h3>
                    <% } %>
                <% } %>
        <% } %>
    </body>
</html>