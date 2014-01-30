package com.jeremydyer.gpio.resource;

import com.jeremydyer.gpio.core.Outlet;
import com.jeremydyer.gpio.core.RemoteDevice;
import com.jeremydyer.gpio.service.RemoteDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jeremydyer
 * Date: 1/30/14
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/remote")
@Service
public class RemoteDeviceResource {

    private static final Logger logger = LoggerFactory.getLogger(LocationResource.class);

    @Inject
    private RemoteDeviceService remoteDeviceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RemoteDevice getRemoteDevice() {
        RemoteDevice remoteDevice = new RemoteDevice();

        //Executes the Raspberry pi code to get the GPIO pin information

        logger.info("Something is happing here!");
        System.out.println("Something is happening here ...");

        remoteDevice.setLocationId(1L);
        remoteDevice.setRemoteDeviceDescription("Jeremy and Carla bedroom power strip");
        remoteDevice.setRemoteDeviceId(1L);

        List<Outlet> deviceOutlets = new ArrayList<Outlet>();

        //Creates the Outlets.
        Outlet out1 = new Outlet();
        out1.setRemoteDeviceId(1L);
        out1.setOn(true);
        out1.setOutletId(1L);
        out1.setPluggedInDevice("Bedroom Lamp");

        //Invokes needed setters.
        deviceOutlets.add(out1);
        remoteDevice.setRemoteDeviceOutlets(deviceOutlets);

        return remoteDevice;
    }

}
