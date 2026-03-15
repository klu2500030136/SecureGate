package main;

import datastructures.ZoneArray;
import java.util.List;
import java.util.Scanner;
import models.User;
import services.AlertService;
import services.AnalysisService;
import services.AuthenticationService;
import services.GuardService;
import services.LogService;
import services.RuleService;
import services.VisitorService;
import utils.FileHandler;
import utils.InputValidator;

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
        RuleService ruleService = new RuleService();

       AnalysisService analysisService =
        new AnalysisService(logService.getLogList());
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
System.out.println("11. Change Access Rule");
System.out.println("12. Undo Last Rule Change");
System.out.println("13. Show Last Rule Change");

System.out.println("14. Analyze Zone Access");
System.out.println("15. Detect Suspicious Activity");
System.out.println("16. Sort Logs by Time");
System.out.println("17. Exit");

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

    System.out.print("Enter rule change description: ");
    String rule = sc.nextLine();

    ruleService.changeRule(rule);
    break;


case 12:
    ruleService.undoLastRule();
    break;


case 13:
    ruleService.showLastRule();
    break;


case 14:
    analysisService.analyzeZoneAccess();
    break;

case 15:
    analysisService.detectSuspiciousActivity();
    break;

case 16:
    analysisService.sortLogsByTime();
    break;

case 17:
    System.out.println("System shutting down...");
    sc.close();
    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}