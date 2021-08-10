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

    </body>
</html>