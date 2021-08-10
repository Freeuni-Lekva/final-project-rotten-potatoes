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

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        // httpServletRequest.getServletContext().setAttribute("guest", null);
        httpServletRequest.getRequestDispatcher("/WEB-INF/index.jsp").forward(httpServletRequest, httpServletResponse);
    }
}