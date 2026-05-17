package UTS_db_kel8;

public class Infrastructure {
    private String serverConfig;
    private String storageConfig;
    private float currentAvailability;

    public Infrastructure(String serverConfig, String storageConfig, float currentAvailability) {
        this.serverConfig = serverConfig;
        this.storageConfig = storageConfig;
        this.currentAvailability = currentAvailability;
    }

    public float calculateUptime() {
        return currentAvailability;
    }

    public void applyUpgrade() {
        this.currentAvailability = 0.9999f;
        this.serverConfig = "Upgraded HA Server";
        this.storageConfig = "Expanded 2TB SSD";
    }

    public String getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(String serverConfig) {
        this.serverConfig = serverConfig;
    }

    public String getStorageConfig() {
        return storageConfig;
    }

    public void setStorageConfig(String storageConfig) {
        this.storageConfig = storageConfig;
    }

    public float getCurrentAvailability() {
        return currentAvailability;
    }

    public void setCurrentAvailability(float currentAvailability) {
        this.currentAvailability = currentAvailability;
    }
}