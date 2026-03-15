package models;

public class LogEntry {

    private String userId;
    private String zoneId;
    private String timestamp;
    private boolean accessGranted;

    public LogEntry(String userId, String zoneId, String timestamp, boolean accessGranted) {
        this.userId = userId;
        this.zoneId = zoneId;
        this.timestamp = timestamp;
        this.accessGranted = accessGranted;
    }

    public String getUserId() {
        return userId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isAccessGranted() {
        return accessGranted;
    }

    @Override
    public String toString() {
        return timestamp +
                " | User: " + userId +
                " | Zone: " + zoneId +
                " | Access: " + (accessGranted ? "GRANTED" : "DENIED");
    }
}