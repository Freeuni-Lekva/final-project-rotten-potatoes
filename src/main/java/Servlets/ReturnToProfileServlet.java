package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// სერვლეტის გამოძახება უნდა მოხდეს მაშინ, როდესაც მომხმარებელს საკუთარი პროფილის გვერდზე უნდა მოხვედრა და შესაბამის
// ბათონს აჭერს.
public class ReturnToProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String guestUsername = httpServletRequest.getParameter("guest_visitor_id");
        httpServletRequest.setAttribute("guest_visitor_id", guestUsername);
        httpServletRequest.getRequestDispatcher("/WEB-INF/profile.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");
        String guestUsername = httpServletRequest.getParameter("guest_visitor_id");
        httpServletRequest.setAttribute("guest_visitor_id", guestUsername);
        httpServletRequest.setAttribute("username", username);
        httpServletRequest.getRequestDispatcher("/WEB-INF/profile.jsp").forward(httpServletRequest, httpServletResponse);
    }
}