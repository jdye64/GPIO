package com.jeremydyer.gpio.core;

import java.util.List;

/**
 * Represents a remote device at a particular location.
 *
 * User: jeremydyer
 * Date: 1/30/14
 * Time: 3:17 PM
 */
public class RemoteDevice {

    private Long remoteDeviceId;
    private Long locationId;
    private String remoteDeviceDescription;
    private List<Outlet> remoteDeviceOutlets;

    public Long getRemoteDeviceId() {
        return remoteDeviceId;
    }

    public void setRemoteDeviceId(Long remoteDeviceId) {
        this.remoteDeviceId = remoteDeviceId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getRemoteDeviceDescription() {
        return remoteDeviceDescription;
    }

    public void setRemoteDeviceDescription(String remoteDeviceDescription) {
        this.remoteDeviceDescription = remoteDeviceDescription;
    }

    public List<Outlet> getRemoteDeviceOutlets() {
        return remoteDeviceOutlets;
    }

    public void setRemoteDeviceOutlets(List<Outlet> remoteDeviceOutlets) {
        this.remoteDeviceOutlets = remoteDeviceOutlets;
    }
}
