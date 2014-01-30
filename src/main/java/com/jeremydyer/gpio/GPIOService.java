package com.jeremydyer.gpio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jeremydyer.gpio.spring.SpringContextLoaderListener;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.tasks.Task;
import com.yammer.metrics.core.HealthCheck;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.Map;

/**
 * User: Jeremy Dyer
 * Date: 1/13/14
 * Time: 10:54 AM
 */
public class GPIOService
        extends Service<GPIOConfiguration> {

    public static void main(String[] args) throws Exception {
        new GPIOService().run(args);
    }

    @Override
    public void initialize(Bootstrap<GPIOConfiguration> bootstrap) {
        bootstrap.setName("Dyer");
        bootstrap.getObjectMapperFactory().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //serve some HTML resources
        bootstrap.addBundle(new AssetsBundle("/assets/", "/dyer/"));
    }

    @Override
    public void run(GPIOConfiguration configuration, Environment environment) {
        // nothing to do yet
        AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        parent.refresh();
        parent.getBeanFactory().registerSingleton("configuration", configuration);
        parent.registerShutdownHook();
        parent.start();

        //the real main app context has a link to the parent context
        ctx.setParent(parent);
        ctx.register(GPIOSpringConfiguration.class);
        ctx.refresh();
        ctx.registerShutdownHook();
        ctx.start();

        //now that Spring is started, let's get all the beans that matter into DropWizard

        //health checks
        Map<String, HealthCheck> healthChecks = ctx.getBeansOfType(HealthCheck.class);
        for(Map.Entry<String,HealthCheck> entry : healthChecks.entrySet()) {
            environment.addHealthCheck(entry.getValue());
        }

        //resources
        Map<String, Object> resources = ctx.getBeansWithAnnotation(Path.class);
        for(Map.Entry<String,Object> entry : resources.entrySet()) {
            environment.addResource(entry.getValue());
        }

        //tasks
        Map<String, Task> tasks = ctx.getBeansOfType(Task.class);
        for(Map.Entry<String,Task> entry : tasks.entrySet()) {
            environment.addTask(entry.getValue());
        }

        //JAX-RS providers
        Map<String, Object> providers = ctx.getBeansWithAnnotation(Provider.class);
        for(Map.Entry<String,Object> entry : providers.entrySet()) {
            environment.addProvider(entry.getValue());
        }

        //last, but not least, let's link Spring to the embedded Jetty in Dropwizard
        environment.addServletListeners(new SpringContextLoaderListener(ctx));

        //activate Spring Security filter
        environment.addFilter(DelegatingFilterProxy.class,"/*").setName("springSecurityFilterChain");
    }

}
