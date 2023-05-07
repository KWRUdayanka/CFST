package demo.persistance;

public class Device {
    private String deviceType;
    private String ownership;
    private String usageOfDevice;
    private String manufacturedDate;
    private String deviceMode;
    private String location;
    private int userId;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getUsageOfDevice() {
        return usageOfDevice;
    }

    public void setUsageOfDevice(String usageOfDevice) {
        this.usageOfDevice = usageOfDevice;
    }

    public String getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(String manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public String getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(String deviceMode) {
        this.deviceMode = deviceMode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
