package Servlets;

import Database.DB;
import Database.SQL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    // Constant variable declarations.
    private static final String DB_ATTRIBUTE = "db";

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