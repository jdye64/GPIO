package com.jeremydyer.gpio.core;

/**
 * Defines a physical location that a remote power strip is located at. For now this is all considered
 * a common pool. For example Rental House, Home, Office, etc. There may be more than a single remote power
 * strip at each location.
 *
 * User: jeremydyer
 * Date: 1/30/14
 * Time: 3:06 PM
 */
public class Location {

    private Long locationId;
    private String locationName;
    private String url;
}
