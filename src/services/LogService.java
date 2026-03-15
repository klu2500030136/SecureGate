package services;

import datastructures.LogLinkedList;
import models.LogEntry;
import utils.DateTimeUtil;

public class LogService {

    private LogLinkedList logList;

    public LogService() {
        logList = new LogLinkedList();
    }

    public LogLinkedList getLogList() {
    return logList;
}

    public void recordLog(String userId, String zoneId, boolean granted) {

        String timestamp = DateTimeUtil.getCurrentTimestamp();

        LogEntry log = new LogEntry(
                userId,
                zoneId,
                timestamp,
                granted
        );

        logList.addLog(log);
    }

    public void showLogs() {
        logList.displayLogs();
    }

    public int getTotalLogs() {
        return logList.getSize();
    }
}