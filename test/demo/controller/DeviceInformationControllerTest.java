/**
 *  This test class is used to test the functionality of adding a new device to the database.
 *  It creates a new Device object and sets its attributes with sample data.
 *  It then calls the addDeviceToDatabase() method of the AddDeviceService class and passes the Device object as a parameter.
 *  The test is successful if no exceptions are thrown during the execution of the method.
 * */
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