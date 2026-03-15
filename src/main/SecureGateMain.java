package main;

import java.util.Scanner;
import java.util.List;

import datastructures.ZoneArray;
import models.User;
import services.AuthenticationService;
import services.LogService;
import utils.FileHandler;
import utils.InputValidator;

public class SecureGateMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FileHandler fileHandler = new FileHandler();

        // Load zones from CSV
        List<String> zoneList = fileHandler.readFile("zones.csv");

        ZoneArray zoneArray = new ZoneArray(20);
        zoneArray.loadZones(zoneList);

        AuthenticationService authService =
                new AuthenticationService(zoneArray);

        LogService logService = new LogService();

        while (true) {

            System.out.println("\n=== SecureGate System ===");
            System.out.println("1. Verify Access");
            System.out.println("2. Show Logs");
            System.out.println("3. Show Log Count");
            System.out.println("4. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Card ID: ");
                    String cardId = sc.nextLine();

                    System.out.print("Enter Zone ID: ");
                    String zoneId = sc.nextLine();

                    if (!InputValidator.isValidCardId(cardId)
                            || !InputValidator.isValidZone(zoneId)) {

                        System.out.println("Invalid input format.");
                        break;
                    }

                    boolean granted =
                            authService.verifyCard(cardId, zoneId);

                    User user =
                            authService.getUserByCard(cardId);

                    String userId =
                            (user != null) ? user.getUserId() : "UNKNOWN";

                    logService.recordLog(userId, zoneId, granted);

                    System.out.println(granted ?
                            "Access Granted" : "Access Denied");

                    break;

                case 2:
                    logService.showLogs();
                    break;

                case 3:
                    System.out.println("Total Logs: "
                            + logService.getTotalLogs());
                    break;

                case 4:
                    System.out.println("System shutting down...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}