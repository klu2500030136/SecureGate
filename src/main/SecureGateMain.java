package main;

import java.util.Scanner;
import java.util.List;

import datastructures.ZoneArray;
import models.User;
import services.AuthenticationService;
import services.LogService;
import utils.FileHandler;
import utils.InputValidator;
import services.AlertService;

import services.VisitorService;
import services.GuardService;

public class SecureGateMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AlertService alertService = new AlertService();

        FileHandler fileHandler = new FileHandler();

        // Load zones from CSV
        List<String> zoneList = fileHandler.readFile("zones.csv");

        ZoneArray zoneArray = new ZoneArray(20);
        zoneArray.loadZones(zoneList);

        AuthenticationService authService = new AuthenticationService(zoneArray);

        LogService logService = new LogService();

        VisitorService visitorService = new VisitorService();
        GuardService guardService = new GuardService();

        while (true) {

            System.out.println("1. Verify Access");
            System.out.println("2. Show Logs");
            System.out.println("3. Show Log Count");
            System.out.println("4. Show Alerts");
            System.out.println("5. Handle Next Alert");
            System.out.println("6. Register Visitor");
            System.out.println("7. Process Next Visitor");
            System.out.println("8. Show Visitor Queue");
            System.out.println("9. Show Current Guard");
            System.out.println("10. Rotate Guard Shift");
            System.out.println("11. Exit");

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

                    boolean granted = authService.verifyCard(cardId, zoneId);

                    User user = authService.getUserByCard(cardId);

                    String userId = (user != null) ? user.getUserId() : "UNKNOWN";

                    logService.recordLog(userId, zoneId, granted);

                    if (!granted) {
                        alertService.generateAlert(
                                "Unauthorized access attempt by card: " + cardId + " to zone " + zoneId,
                                5);
                    }

                    System.out.println(granted ? "Access Granted" : "Access Denied");

                case 2:
                    logService.showLogs();
                    break;

                case 3:
                    System.out.println("Total Logs: "
                            + logService.getTotalLogs());
                    break;

                case 4:
                    alertService.showAllAlerts();
                    break;

                case 5:
                    alertService.handleNextAlert();
                    break;

                case 6:

                    System.out.print("Visitor Name: ");
                    String name = sc.nextLine();

                    System.out.print("Purpose: ");
                    String purpose = sc.nextLine();

                    visitorService.registerVisitor(name, purpose);
                    break;

                case 7:
                    visitorService.processNextVisitor();
                    break;

                case 8:
                    visitorService.displayVisitors();
                    break;

                case 9:
                    guardService.showCurrentGuard();
                    break;

                case 10:
                    guardService.startShiftRotation();
                    break;

                case 11:
                    System.out.println("System shutting down...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}