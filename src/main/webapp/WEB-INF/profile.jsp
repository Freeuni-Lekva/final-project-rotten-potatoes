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
        boolean isAdministrator = false;

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

        String username = (String) session.getAttribute("username");
        String guestUsername = (String) request.getAttribute("guest_visitor_id");

        if(guestUsername != null && username != null){
            if(guestUsername.equals(username)){
                guestUsername = null;
            }
        }

        /* სანამ სერვლეტ/ჯეესპეებზე არ მაქვს წვდომა, ტესტი:
        String username = "admin";
        String guestUsername = "sjanj19"; */

        // გავარკვიოთ, ვიზიტის რომელ ტიპაჟში ვიმყოფებით.
        if(username != null && guestUsername != null){
            VISIT = USER_VISIT;
        } else if(username != null && guestUsername == null){
            VISIT = PERSONAL_VISIT;
            if(username.equals("admin")){
                isAdministrator = true;
            }
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
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
        <p></p>
        <p></p>

        <div class="container">
            <div class="row">

            </div>
        </div>

        <div class="container">
            <div class="row">
                <%-- ეს ბათონები სხვანაირად უნდა იყოს შექმნილი. ჯერ-ჯერობით, კომენტარად მივუწერ, მათზე დაჭერისას რაები უნდა მოხდეს. --%>
                <% if(VISIT == USER_VISIT){ %>
                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
                    <div class="col">
						<form action="/guest" method="get">
							<input type="submit" value="HOMEPAGE"/>
						</form>
					</div>

                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს საკუთარი პროფილის გვერდზე. --%>
                    <div class="col">
                        <form action="/profile.jsp" method="post">
                            <input type="submit" value="MY PROFILE"/>
                        </form>
                    </div>

                    <%-- guestUsername და username ატრიბუტები უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
                    <div class="col">
                        <button name="button" type="button">LOG OUT</button>
                    </div>
                <% } else if(VISIT == PERSONAL_VISIT){ %>
                    <%-- მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
                    <div class="col">
                        <form action="/guest" method="get">
                            <input type="submit" value="HOMEPAGE"/>
                        </form>
                    </div>

                    <%-- username ატრიბუტები უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
                    <div class="col">
                        <button name="button" type="button">LOG OUT</button>
                    </div>

                    <%-- ახალი ნივთის შექმნა მხოლოდ მაშინ შეიძლება, როდესაც გვყავს რეგისტრირებული მომხმარებელი, რომელიც ამჟამად
                         საკუთარი პროფილის გვერდზე იმყოფება. --%>
                    <%-- !!! Temporarily solution for new item creation feature (not sure if works) !!! --%>

                    <% if(VISIT == PERSONAL_VISIT){ %>
                        <div class="col">
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
                        </div>
                    <% } %>
                <% } else if(VISIT == GUEST_VISIT){ %>
                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
                    <div class="col">
                        <form action="/guest" method="get">
                            <input type="submit" value="HOMEPAGE"/>
                        </form>
                    </div>

                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
                    <div class="col">
                        <button name="button" type="button">LOG IN/REGISTER</button>
                    </div>
                <% } %>
            </div>

            <br><br>

            <div class="row">
                <div class="col">
                    <%-- პირადი ინფორმაციის გამოსახვა ვიზიტების შესაბამისად. --%>
                    <%-- Display usernames at top with big chunky letters. --%>
                    <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){ %>
                        <h2>
                            YOU ARE VIEWING <b><%= guest.getUsername() %></b>&#39S PROFILE.
                        </h2>
                    <% } else if(VISIT == PERSONAL_VISIT){
                            if(isAdministrator){ %>
                                <h2>
                                    ADMINISTRATOR&#39S PAGE
                                </h2>
                            <% } else { %>
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
                    <% } %>
                </div>
                <div class="col">
                    <%-- Follow/Unfollow ბათონი. --%>
                    <% if(VISIT == USER_VISIT){
                        // Check if I (user) am following them (guest).
                        if(User.isFollowing(db, guest, user)){ %>
                            <form action= <%= "/unfollowed?guest_visitor_id=" + guestUsername %> method="post">
                                <input type="submit" value="UNFOLLOW"/>
                            </form>
                        <% } else { %>
                            <form action= <%= "/followed?guest_visitor_id=" + guestUsername %> method="post">
                                <input type="submit" value="FOLLOW"/>
                            </form>
                        <% } %>
                    <% } %>
                </div>
                <div class="col">
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
                                    String badgeIcon = badge.getBadgeIcon();
                                    String badgeDescription = badge.getBadgeDescription(); %>

                                    <%-- !!! Images will probably have to be resized later and image folder will have to be moved !!! --%>
                                    <img src = <%= "images/" + badgeIcon %>
                                    alt="alternative text" title= <%= "\"" + badgeDescription + "\"" %> width = "50">
                            <% }

                           } %>

                    <% } else if(VISIT == PERSONAL_VISIT){
                         List<Badge> userBadges = user.getBadges(db);

                         if(userBadges.isEmpty()){ %>
                            <h2>
                                <b>You haven&#39t earned any badges yet.</b>
                            </h2>
                        <% } else { %>
                            <h2>
                                <b>YOUR BADGES:</b>
                            </h2>
                            <%
                                for(Badge badge : userBadges){
                                    String badgeIcon = badge.getBadgeIcon();
                                    String badgeDescription = badge.getBadgeDescription(); %>

                                    <%-- !!! Images will probably have to be resized later and image folder will have to be moved !!! --%>
                                    <img src = <%= "images/" + badgeIcon %>
                                    alt="alternative text" title= <%= "\"" + badgeDescription + "\"" %> width = "50">
                            <% } %>
                        <% } %>
                    <% } %>
                </div>
            </div>

            <div class="row">
                <%-- თუ ამჟამად PERSONAL_VISIT-ის ტიპის ვიზიტია და ასევე, ის ხორციელდება ადმინისტრატორის მიერ,
                     უნდა გამოვსახოთ რეპორტების სია მისთვის. ეს ნაწილი გასატესტია (რადგან ჯერ შესაბამისი მეთოდები
                     და ცხრილები არ არსებობს). --%>
                <% if(VISIT == PERSONAL_VISIT && isAdministrator){
                    List<Report> reports = Report.getReports(db);

                    if(reports.isEmpty()){ %>
                        <h2>
                          <b>No user reports present.</b>
                        </h2>
                    <% } else { %>
                        <h2>
                            <b>USER REPORTS:</b>
                        </h2>
                        <%
                        for(Report report : reports){
                            int reportID = report.getReportId();
                            String reporter = report.getReporterUsername();
                            String itemID = report.getItemId();
                            Item item = Item.getItemByID(db, itemID); %>
                            <h3>
                            <%-- REPORT #5: sjanj19 is reporting about - Dangerous, 1991. --%>
                            <mark>REPORT #<%= reportID %>:</mark> <%= reporter %> is reporting about - <%= item.getTitle() %>, <%= item.getReleaseDate() %>.
                            <p></p>
                            <form action="/report.jsp" method="post">
                                <input type="submit" value="RESOLVE"/>
                                <input id="report_id" name="report_id" type="hidden" value=<%= "\"" + reportID +"\"" %>>
                            </form>
                            <br>
                            </h3>
                        <% } %>
                    <% } %>
                <% } %>
                <p></p>
            </div>

            <p></p>

            <div class="row">
                <div class="col">
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
                                        int releaseDate = item.getReleaseDate();
                                        String individualLink = "product.jsp?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                    <%-- !!! Cover images will probably need resizing !!! --%>
                                    <img src = <%= coverURL %> width = "100">
                                    <a href= <%= individualLink %>><%= title %>, <%= releaseDate %> (<%= score %>/10)</a></p>
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
                                    for(Item item : userItems){
                                        String title = item.getTitle();
                                        String coverURL = item.getCoverURL();
                                        double score = item.getScore();
                                        int releaseDate = item.getReleaseDate();
                                        String individualLink = "product.jsp?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                    <%-- !!! Cover images will probably need resizing !!! --%>
                                    <img src = <%= coverURL %> width = "100">
                                    <a href= <%= individualLink %>><%= title %>, <%= releaseDate %> (<%= score %>/10)</a></p>
                                <% } %>
                            <% } %>
                    <% } %>
                </div>
                <div class="col">
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
                                String followerUsername = follower.getUsername();
                                String link = "/profile.jsp?guest_visitor_id=" + followerUsername; %>
                                <p><a href=<%= link %>><%= followerUsername %></a></p>
                        <% } %>
                        <h2>
                            YOUR FOLLOWING (<%= user.getFollowing(db).size() %>):
                        </h2>
                        <%
                            for(User following : user.getFollowing(db)){
                                String followingUsername = following.getUsername();
                                String link = "/profile.jsp?guest_visitor_id=" + followingUsername; %>
                                <p><a href=<%= link %>><%= followingUsername %></a></p>
                        <% } %>
                    <% } %>
                    <%-- დაწერილი კრიტიკის ნახვა. --%>
                    <%-- აქაც კარგი იქნება შემდეგი დამატება: hyperlink-ები, რომლებიც მომხმარებელს იმ პროდუქტის გვერდზე გადაიყვანენ,
                         რომელზეც დაიწერა შეფასება. --%>

                    <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){
                        List<Review> guestReviews = guest.getReviews(db);

                        if(guestReviews.isEmpty()){ %>
                            <h2>
                                <b><%= guest.getUsername() %> hasn&#39t written any reviews yet.</b>
                            </h2>
                        <% } else { %>
                            <h2>
                                <b><%= guest.getUsername() %>&#39S REVIEWS:</b>
                            </h2>
                            <%
                                for(Review review : guestReviews){
                                    String itemID = review.getItemID();
                                    Item item = Item.getItemByID(db, itemID);
                                    String userReview = review.getReview();
                                    double userScore = review.getScore();
                                    String itemLink = "product.jsp?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                    <%-- !!! Cover images will probably need resizing !!! --%>
                                    <img src = <%= item.getCoverURL() %> width = "160">
                                    <a href= <%= itemLink %>><%= item.getTitle() %>, <%= item.getReleaseDate() %> (<%= item.getScore() %>/10)</a></p>
                                    <h3>
                                        <%= guest.getUsername() %>&#39s personal score: <%= userScore %> <br>
                                        <%= guest.getUsername() %>&#39s review: <%= userReview %>
                                    </h3>
                            <% } %>
                        <% } %>
                    <% } else if(VISIT == PERSONAL_VISIT){
                            List<Review> userReviews = user.getReviews(db);

                            if(userReviews.isEmpty()){ %>
                                <h2>
                                    <b>You haven&#39t written any reviews yet.</b>
                                </h2>
                            <% } else { %>
                                <h2>
                                    <b>YOUR REVIEWS:</b>
                                </h2>

                                <%
                                    for(Review review : userReviews){
                                        String itemID = review.getItemID();
                                        Item item = Item.getItemByID(db, itemID);
                                        String userReview = review.getReview();
                                        double userScore = review.getScore();
                                        String itemLink = "product.jsp?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                        <%-- !!! Cover images will probably need resizing !!! --%>
                                        <img src = <%= item.getCoverURL() %> width = "160">
                                        <a href= <%= itemLink %>><%= item.getTitle() %>, <%= item.getReleaseDate() %>
                                        (<%= item.getScore() %>/10)</a></p>
                                        <h3>
                                            Your personal score: <%= userScore %> <br>
                                            Your review: <%= userReview %>
                                        </h3>
                                <% } %>

                            <% } %>

                    <% } %>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
        integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>