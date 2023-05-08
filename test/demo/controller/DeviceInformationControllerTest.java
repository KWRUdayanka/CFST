package demo.controller;

import demo.persistance.Device;
import demo.service.AddDeviceService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceInformationControllerTest {

    @Test
    void testAddNewDevice() {
        Device device = new Device();
        device.setDeviceType("Desktop + Screen");
        device.setOwnership("Owned");
        device.setUsageOfDevice("Work");
        device.setManufacturedDate("");
        device.setDeviceMode("Active");
        device.setLocation("Home");
        device.setUserId(3);

        new AddDeviceService().addDeviceToDatabase(device);
    }

}