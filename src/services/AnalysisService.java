package services;

import models.LogEntry;
import datastructures.LogLinkedList;

import java.util.*;

public class AnalysisService {

    private LogLinkedList logList;

    public AnalysisService(LogLinkedList logList) {
        this.logList = logList;
    }

    // Count zone access frequency
    public void analyzeZoneAccess() {

        List<LogEntry> logs = logList.getAllLogs();

        HashMap<String, Integer> zoneCount = new HashMap<>();

        for (LogEntry log : logs) {

            String zone = log.getZoneId();

            zoneCount.put(zone,
                    zoneCount.getOrDefault(zone, 0) + 1);
        }

        System.out.println("Zone Access Frequency:");

        for (String zone : zoneCount.keySet()) {
            System.out.println(zone + " → " + zoneCount.get(zone));
        }
    }


    // Detect suspicious activity
    public void detectSuspiciousActivity() {

        List<LogEntry> logs = logList.getAllLogs();

        HashMap<String, Integer> deniedCount = new HashMap<>();

        for (LogEntry log : logs) {

            if (!log.isAccessGranted()) {

                String user = log.getUserId();

                deniedCount.put(user,
                        deniedCount.getOrDefault(user, 0) + 1);
            }
        }

        System.out.println("Suspicious Users:");

        for (String user : deniedCount.keySet()) {

            if (deniedCount.get(user) >= 3) {
                System.out.println(user + " → "
                        + deniedCount.get(user)
                        + " denied attempts");
            }
        }
    }


    // Sort logs by timestamp
    public void sortLogsByTime() {

        List<LogEntry> logs = logList.getAllLogs();

        logs.sort(Comparator.comparing(LogEntry::getTimestamp));

        System.out.println("Logs Sorted by Time:");

        for (LogEntry log : logs) {
            System.out.println(log);
        }
    }
}