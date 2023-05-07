package demo.persistance;

public class DeviceList {
    private final String deviceType;
    private final String ownership;
    private final String usageOfDevice;
    private final String location;

    public DeviceList(String deviceType, String ownership, String usageOfDevice, String location) {
        this.deviceType = deviceType;
        this.ownership = ownership;
        this.usageOfDevice = usageOfDevice;
        this.location = location;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getOwnership() {
        return ownership;
    }

    public String getUsageOfDevice() {
        return usageOfDevice;
    }

    public String getLocation() {
        return location;
    }
}
