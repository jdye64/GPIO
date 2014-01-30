package com.jeremydyer.gpio.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 4:00 PM
 */
public class EnvironmentPropertyPlaceholderConfigurer
        extends PropertyPlaceholderConfigurer {

    private static EnvironmentPropertyPlaceholderConfigurer instance;
    private Properties merged;
    private boolean silent = false;

    private EnvironmentPropertyPlaceholderConfigurer(Map<String, String> args) throws IOException {
        init(args);
    }

    public EnvironmentPropertyPlaceholderConfigurer() {
        Map<String, String> args = new HashMap<String, String>();
        args.put("defaultResourceLocation", "renovo.properties");
        args.put("propertyfileSystemPropertyName", "environmentFilename");
        args.put("environmentSystemPropertyname", "environmentName");
        init(args);
    }

    public static EnvironmentPropertyPlaceholderConfigurer createInstance(Map<String, String> args) throws IOException {
        if (instance == null) {
            instance = new EnvironmentPropertyPlaceholderConfigurer(args);
        }
        return instance;
    }

    public static EnvironmentPropertyPlaceholderConfigurer getInstance() {
        if (instance == null) {
            throw new RuntimeException("placeholder not properly configured");
        }
        return instance;
    }

    private void init(Map<String, String> constructorArgs) {
        ArrayList<Resource> locationsList = new ArrayList<Resource>();

        String silentValue = constructorArgs.get("silent");
        if (silentValue != null && silentValue.equals("true")) {
            silent = true;
        }

        String defaultResourceLocation = constructorArgs.get("defaultResourceLocation");
        locationsList.add(new ClassPathResource(defaultResourceLocation));

        String environmentSystemPropertyname = constructorArgs.get("environmentSystemPropertyname");
        if (environmentSystemPropertyname != null) {
            String environment = System.getProperty(environmentSystemPropertyname);
            if (environment != null && !"".equals(environment)) {
                print("environmentName is " + environment);
                String environmentPropertyResource = "/renovo-" + environment + ".properties";
                InputStream is = EnvironmentPropertyPlaceholderConfigurer.class.getResourceAsStream(environmentPropertyResource);
                if (is != null) {
                    locationsList.add(new ClassPathResource(environmentPropertyResource));
                }
            }
        }

        String propertyfileSystemPropertyName = constructorArgs.get("propertyfileSystemPropertyName");
        if (propertyfileSystemPropertyName != null) {
            String filename = System.getProperty(propertyfileSystemPropertyName);
            if (filename != null && !"".equals(filename)) {
                print("environmentFilename is " + filename);
                File file = new File(filename);
                if (file.exists()) {
                    locationsList.add(new FileSystemResource(filename));
                }
            }
        }
        Resource[] locations = locationsList.toArray(new Resource[locationsList.size()]);
        this.setLocations(locations);
        print("set locations: ");
        for (Resource resource : locations) {
            try {
                print("" + resource.getURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            merged = this.mergeProperties();
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal("problem loading properties", e);
            throw new RuntimeException("problem loading properties", e);
        }
    }

    public Properties getMerged() {
        return merged;
    }

    private void printOptional(String message) {
        logger.debug(message);
    }

    private void print(String message) {
        System.out.println(message);
        logger.debug(message);
    }

    public void printProperties() {

        printOptional("EnvironmentPropertyPlaceholderConfigurer properties:");
        for (Map.Entry<Object, Object> entry : merged.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            printOptional(key + "=" + value);
        }
    }

    public String getProperty(String name, String defaultValue) {
        String value =  this.merged.getProperty(name);
        return (value==null) ? defaultValue : value;
    }
    public String getProperty(String name) {
        return this.merged.getProperty(name);
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

}
