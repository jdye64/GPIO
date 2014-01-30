package com.jeremydyer.gpio.core;

/**
 * Describes a single outlet on the remote device.
 *
 * User: jeremydyer
 * Date: 1/30/14
 * Time: 4:43 PM
 */
public class Outlet {

    private Long outletId;
    private Long remoteDeviceId;
    private boolean on;
    private String pluggedInDevice;

    public Outlet() {
    }

    public Long getOutletId() {
        return outletId;
    }

    public void setOutletId(Long outletId) {
        this.outletId = outletId;
    }

    public Long getRemoteDeviceId() {
        return remoteDeviceId;
    }

    public void setRemoteDeviceId(Long remoteDeviceId) {
        this.remoteDeviceId = remoteDeviceId;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getPluggedInDevice() {
        return pluggedInDevice;
    }

    public void setPluggedInDevice(String pluggedInDevice) {
        this.pluggedInDevice = pluggedInDevice;
    }
}
