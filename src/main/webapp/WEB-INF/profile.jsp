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
        <style>
        .alert {
          padding: 20px;
          background-color: #abc2ff;
        }

        .closebtn {
          float: right;
          font-size: 22px;
          line-height: 20px;
          cursor: pointer;
        }
        </style>
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

    <body style="background-color:#fffdc2;">
        <p></p>
        <p></p>

        <div class="container">
            <div class="row">
                <%-- ეს ბათონები სხვანაირად უნდა იყოს შექმნილი. ჯერ-ჯერობით, კომენტარად მივუწერ, მათზე დაჭერისას რაები უნდა მოხდეს. --%>
                <% if(VISIT == USER_VISIT){ %>
                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
                    <div class="col">
						<form action="/guest" method="get">
							<input type="submit" class="btn btn-primary" value="HOMEPAGE"/>
						</form>
					</div>

                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს საკუთარი პროფილის გვერდზე. --%>
                    <div class="col" style="text-align: right;">
                        <form action="/profile.jsp" method="post">
                            <input type="submit" class="btn btn-warning" value="MY PROFILE"/>
                        </form>
                    </div>

                    <%-- guestUsername და username ატრიბუტები უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
                    <div class="col" style="text-align: right;">
                        <form action="/logout" method="post">
                            <input type="submit" class="btn btn-danger" value="LOGOUT"/>
                        </form>
                    </div>
                <% } else if(VISIT == PERSONAL_VISIT){ %>
                    <%-- მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
                    <div class="col">
                        <form action="/guest" method="get">
                            <input type="submit" class="btn btn-primary" value="HOMEPAGE"/>
                        </form>
                    </div>

                    <%-- ახალი ნივთის შექმნა მხოლოდ მაშინ შეიძლება, როდესაც გვყავს რეგისტრირებული მომხმარებელი, რომელიც ამჟამად
                         საკუთარი პროფილის გვერდზე იმყოფება. --%>
                    <%-- !!! Temporarily solution for new item creation feature (not sure if works) !!! --%>
                    <% if(VISIT == PERSONAL_VISIT){ %>
                        <div class="col" style="text-align: center;">
                            <form action="/addNewItem.jsp" method="post"> <%-- !!! .jsp file name might change !!! --%>
                                <select name="NEW_ITEM_CATEGORY">
                                    <option value= <%= Movie.ATTRIBUTE %> >Movies</option>
                                    <option value= <%= TV_Show.ATTRIBUTE %> >TV Shows</option>
                                    <option value= <%= Music.ATTRIBUTE %> >Music</option>
                                    <option value= <%= Video_Game.ATTRIBUTE %> >Video Games</option>
                                    <option value= <%= Book.ATTRIBUTE %> >Books</option>
                                </select>
                                <input type="submit" class="btn btn-warning" value="Add New Item"/>
                            </form>
                        </div>
                    <% } %>

                    <%-- username ატრიბუტები უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
                    <div class="col" style="text-align: right;">
                        <form action="/logout" method="post">
                            <input type="submit" class="btn btn-danger" value="LOGOUT"/>
                        </form>
                    </div>
                <% } else if(VISIT == GUEST_VISIT){ %>
                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს კატეგორიების გვერდზე. --%>
                    <div class="col">
                        <form action="/guest" method="get">
                            <input type="submit" class="btn btn-primary" value="HOMEPAGE"/>
                        </form>
                    </div>

                    <%-- guestUsername ატრიბუტი უნდა გახდეს null, მომხმარებელი უნდა გადავიდეს აუთენტიფიკაციის გვერდზე. --%>
                    <div class="col" style="text-align: right;">
                        <form action="/index.jsp" method="get">
                            <input type="submit" class="btn btn-warning" value="LOGIN"/>
                        </form>
                    </div>

                    <div class="col-1">
                        <form action="/createNewAcc" method="get">
                            <input type="submit" class="btn btn-danger" value="REGISTER"/>
                        </form>
                    </div>
                <% } %>
            </div>

            <br><br>

            <div class="row">
                <div class="col">
                    <%
                    if(VISIT == PERSONAL_VISIT){
                        List<Notification> notifications = Notification.getNotifications(db, user.getUsername());

                        for(Notification notification : notifications){
                            String notificationID = String.valueOf(notification.getNotificationID());
                            String senderUsername = notification.getSenderUsername();
                            String notificationType = notification.getNotificationType();

                            String notificationItemID = null;
                            Item notificationItem = null;

                            if(notificationType != null && !notificationType.equals("FOLLOW")){
                                notificationItemID = notification.getItemId();
                                notificationItem = Item.getItemByID(db, notificationItemID);
                            }
                            String message = "";

                            if(notificationType != null){
                                if(notificationType.equals("REVIEW")){
                                    message = senderUsername + " wrote a new review about " + notificationItem.getTitle()
                                              + ", " + String.valueOf(notificationItem.getReleaseDate()) + ".";
                                } else if(notificationType.equals("REQUEST")){
                                    message = senderUsername + " requested you to write a review about " +
                                              notificationItem.getTitle() + ", " +
                                              String.valueOf(notificationItem.getReleaseDate()) + ".";
                                } else if(notificationType.equals("UPLOAD")){
                                     message = senderUsername + " uploaded a new item - " + notificationItem.getTitle()
                                               + ", " + String.valueOf(notificationItem.getReleaseDate()) + ".";
                                } else if(notificationType.equals("FOLLOW")){
                                    message = senderUsername + " started following you!";
                                }
                            }
                            %>

                            <form action="/notification" method="post">
                                <div class="alert">
                                    <strong><%= message %></strong>
                                    <input type="submit" value="x" style="float: right;"/>
                                    <input id="notification_id" name="notification_id" type="hidden" value=<%= "\"" + notificationID +"\"" %>>
                                </div>
                            </form>
                    <% }
                    } %>
                </div>
            </div>

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
                                <div class="p-2 mb-3 bg-danger text-white rounded">
                                    <h2><center> ADMINISTRATOR&#39S PAGE </center></h2>
                                </div>
                            <% } else { %>
                                <div class="p-2 mb-3 bg-danger text-white rounded">
                                    <h2><center>WELCOME TO YOUR PROFILE, <b><%= user.getUsername() %></b>!</center></h2>
                                </div>
                                <h2>

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

                            <div class="p-1 mb-1 bg-primary text-white rounded">
                            <form action="/report.jsp" method="post">
                                REPORT #<%= reportID %>: <%= reporter %> is reporting about - <%= item.getTitle() %>, <%= item.getReleaseDate() %>.
                                <input type="submit" value="RESOLVE"/>
                                <input id="report_id" name="report_id" type="hidden" value=<%= "\"" + reportID +"\"" %>>
                            </form></div>
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
                                <div class="p-2 mb-3 bg-danger text-white rounded">
                                    <h2><center><%= guest.getUsername() %>&#39S ITEMS:</center></h2>
                                </div>

                                <%
                                    for(Item item : guestItems){
                                        String title = item.getTitle();
                                        String coverURL = item.getCoverURL();
                                        double score = item.getScore();
                                        int releaseDate = item.getReleaseDate();
                                        String individualLink = "product?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                    <div class="figure" style="width:150px;">
                                        <img src=<%= coverURL %> class="img-thumbnail" alt="logo" />
                                        <div class="caption"><a href= <%= individualLink %> class="link-danger"
                                        style="text-decoration: none;">
                                        <%= title %>, <%= releaseDate %><br>
                                        <strong>(<%= score %>/10)</strong></a></p></div>
                                    </div>
                                <% } %>
                          <% } %>
                    <% } else if(VISIT == PERSONAL_VISIT){
                            List<Item> userItems = user.getItems(db);

                            if(userItems.isEmpty()){ %>
                                <h2>
                                  <b>You haven&#39t uploaded any items yet.</b>
                                </h2>
                            <% } else { %>
                                <div class="p-2 mb-3 bg-danger text-white rounded">
                                    <h2><center>YOUR ITEMS:</center></h2>
                                </div>
                                <%
                                    for(Item item : userItems){
                                        String title = item.getTitle();
                                        String coverURL = item.getCoverURL();
                                        double score = item.getScore();
                                        int releaseDate = item.getReleaseDate();
                                        String individualLink = "product?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                    <div class="figure" style="width:150px;">
                                        <img src=<%= coverURL %> class="img-thumbnail" alt="logo" />
                                        <div class="caption"><a href= <%= individualLink %> class="link-danger"
                                        style="text-decoration: none;">
                                        <%= title %>, <%= releaseDate %><br>
                                        <strong>(<%= score %>/10)</strong></a></p></div>
                                    </div>
                                <% } %>
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
                            <div class="p-2 mb-3 bg-warning text-white rounded">
                                <h2><center><%= guest.getUsername() %>&#39S BADGES:</center></h2>
                            </div>
                            <%
                                for(Badge badge : guestBadges){
                                    String badgeIcon = badge.getBadgeIcon();
                                    String badgeDescription = badge.getBadgeDescription(); %>

                                    <img src = <%= "images/" + badgeIcon %>
                                    alt="alternative text" title= <%= "\"" + badgeDescription + "\"" %> width = "100">
                            <% } %>
                            <br><br>
                        <% } %>
                    <% } else if(VISIT == PERSONAL_VISIT){
                         List<Badge> userBadges = user.getBadges(db);

                         if(userBadges.isEmpty()){ %>
                            <h2>
                                <b>You haven&#39t earned any badges yet.</b>
                            </h2>
                        <% } else { %>
                            <div class="p-2 mb-3 bg-warning text-white rounded">
                                <h2><center>YOUR BADGES:</center></h2>
                            </div>
                            <%
                                for(Badge badge : userBadges){
                                    String badgeIcon = badge.getBadgeIcon();
                                    String badgeDescription = badge.getBadgeDescription(); %>

                                    <img src = <%= "images/" + badgeIcon %>
                                    alt="alternative text" title= <%= "\"" + badgeDescription + "\"" %> width = "100">
                            <% } %>
                            <br><br>
                        <% } %>
                    <% } %>

                    <%-- Followers/Following-ის გამოსახვა ვიზიტის შესაბამისად. სტუმრებთან მხოლოდ followers/following-ის რაოდენობები
                    გამოჩნდება. პირად პროფილზე კონკრეტული მომხმარებლების სია გამოჩდნება. --%>
                    <%-- !!! Add read more property and a limit on the amount of things shown !!! ---%>

                    <% if(VISIT == USER_VISIT || VISIT == GUEST_VISIT){ %>
                        <div class="p-2 mb-3 bg-warning text-white rounded">
                            <h2><center><%= guest.getUsername() %>&#39S FOLLOWERS: </b><%= guest.getFollowers(db).size() %></center>
                        </div>
                        <div class="p-2 mb-3 bg-warning text-white rounded">
                            <h2><center><%= guest.getUsername() %>&#39S FOLLOWING: </b><%= guest.getFollowing(db).size() %></center></h2>
                        </div>
                    <% } else if(VISIT == PERSONAL_VISIT){ %>

                        <%-- აქ მოგვიანებით ერთი ცვლილება უნდა დაემატოს: მომხმარებელს უნდა შეეძლოს, რომ hyperlink-ით
                             თავისი რომელიმე follower/following მომხმარებლის პროფილის გვერდზე გადავიდეს. --%>

                        <div class="p-2 mb-3 bg-warning text-white rounded">
                            <h2><center>YOUR FOLLOWERS (<%= user.getFollowers(db).size() %>):</center></h2>
                        </div>
                        <%
                            for(User follower : user.getFollowers(db)){
                                String followerUsername = follower.getUsername();
                                String link = "/profile.jsp?guest_visitor_id=" + followerUsername; %>
                                <a href=<%= link %> style="font-size: 40px; text-decoration: none;">
                                <%= followerUsername %>&nbsp;&nbsp;</a>
                        <% } %>
                        <br><br>

                        <div class="p-2 mb-3 bg-warning text-white rounded">
                            <h2><center>YOUR FOLLOWING (<%= user.getFollowing(db).size() %>):</center></h2>
                        </div>

                        <%
                            for(User following : user.getFollowing(db)){
                                String followingUsername = following.getUsername();
                                String link = "/profile.jsp?guest_visitor_id=" + followingUsername; %>
                                <a href=<%= link %> style="font-size: 40px; text-decoration: none;">
                                <%= followingUsername %>&nbsp;&nbsp;</a>
                        <% } %>
                        <br><br>

                    <% } %>


                </div>

                <div class="row">
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
                            <div class="p-2 mb-3 bg-danger text-white rounded">
                                    <h2><center><%= guest.getUsername() %>&#39S REVIEWS:</center></h2>
                            </div>
                            <%
                                for(Review review : guestReviews){
                                    String itemID = review.getItemID();
                                    Item item = Item.getItemByID(db, itemID);
                                    String userReview = review.getReview();
                                    double userScore = review.getScore();
                                    String itemLink = "product?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                    <div class="row" style="margin: 5px;">
                                        <div class="col" style="text-align: right;">
                                            <img src=<%= item.getCoverURL() %> width="250" class="img-thumbnail" alt="logo" />
                                        </div>
                                        <div class="col-8">
                                            <a href= <%= itemLink %> class="link-danger"
                                            style = "text-decoration: none; font-size: 40px;">
                                            <%= item.getTitle() %>, <%= item.getReleaseDate() %>
                                            (<%= item.getScore() %>/10)</a>

                                            <h4>
                                                <strong><%= guest.getUsername() %>&#39s personal score: </strong><%= userScore %> <br>
                                                <strong><%= guest.getUsername() %>&#39s review: </strong><%= userReview %>
                                            </h4>
                                        </div>
                                    </div>
                            <% } %>
                        <% } %>
                    <% } else if(VISIT == PERSONAL_VISIT){
                            List<Review> userReviews = user.getReviews(db);

                            if(userReviews.isEmpty()){ %>
                                <h2>
                                    <b>You haven&#39t written any reviews yet.</b>
                                </h2>
                            <% } else { %>
                                <div class="p-2 mb-3 bg-danger text-white rounded">
                                    <h2><center>YOUR REVIEWS:</center></h2>
                                </div>

                                <%
                                    for(Review review : userReviews){
                                        String itemID = review.getItemID();
                                        Item item = Item.getItemByID(db, itemID);
                                        String userReview = review.getReview();
                                        double userScore = review.getScore();
                                        String itemLink = "product?id=" + Item.getItemIdWithoutSpaces(item.getItemID()); %>

                                        <%-- !!! Cover images will probably need resizing !!! --%>

                                        <div class="row" style="margin: 5px;">
                                            <div class="col" style="text-align: right;">
                                                <img src=<%= item.getCoverURL() %> width="250" class="img-thumbnail" alt="logo" />
                                            </div>
                                            <div class="col-8">
                                                <a href= <%= itemLink %> class="link-danger"
                                                style = "text-decoration: none; font-size: 40px;">
                                                <%= item.getTitle() %>, <%= item.getReleaseDate() %>
                                                (<%= item.getScore() %>/10)</a>

                                                <h4>
                                                    <strong>Your personal score: </strong><%= userScore %> <br>
                                                    <strong>Your review: </strong><%= userReview %>
                                                </h4>
                                            </div>
                                        </div>
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