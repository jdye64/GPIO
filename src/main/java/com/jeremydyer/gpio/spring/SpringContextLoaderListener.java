package com.jeremydyer.gpio.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * A replacement for the default Spring ContextLoaderListener that first better with how we initialize
 * Spring in an embedded Jetty environment.
 * It's primary goal is to set the appropriate attribute in the Servlet Context so all other pieces of Spring (Spring Security in particular)
 * can find the main context if they need to
 *
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 2:20 PM
 */
@RequiredArgsConstructor
public class SpringContextLoaderListener
        implements ServletContextListener {

    private AnnotationConfigWebApplicationContext context;

    public SpringContextLoaderListener(AnnotationConfigWebApplicationContext context) {
        this.context = context;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
        context.setServletContext(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //do nothing
    }
}
