package Servlets;

import Database.DB;
import Database.SQL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    // Constant variable declarations.
    public static final String DB_ATTRIBUTE = "db";
    public static final String USERNAME_ATTRIBUTE = "username";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DB db = new SQL();
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute(DB_ATTRIBUTE, db);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}