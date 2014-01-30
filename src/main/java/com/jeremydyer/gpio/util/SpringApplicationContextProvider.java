package com.jeremydyer.gpio.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 4:06 PM
 */
public class SpringApplicationContextProvider
        implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringApplicationContextProvider.applicationContext = applicationContext;
    }
}