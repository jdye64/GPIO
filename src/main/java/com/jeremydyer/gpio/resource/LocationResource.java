package com.jeremydyer.gpio.resource;

import com.jeremydyer.gpio.core.Location;
import com.jeremydyer.gpio.dao.LocationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 2:35 PM
 */
@Path("/gpio")
@Service
public class LocationResource {

    private static final Logger logger = LoggerFactory.getLogger(LocationResource.class);

    @Inject private LocationDao locationDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Location getAllBeers() {
        return new Location();
    }

}
