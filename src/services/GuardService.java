package services;

import datastructures.GuardCircularQueue;
import models.Guard;

public class GuardService {

    private GuardCircularQueue guardQueue;

    public GuardService() {

        guardQueue = new GuardCircularQueue(5);

        // Initial guards
        guardQueue.addGuard(new Guard("G101", "Raj"));
        guardQueue.addGuard(new Guard("G102", "Amit"));
        guardQueue.addGuard(new Guard("G103", "Suresh"));
    }

    public void showCurrentGuard() {

        Guard guard = guardQueue.getCurrentGuard();

        if (guard != null) {
            System.out.println("Current Guard on Duty → " + guard);
        } else {
            System.out.println("No guard on duty.");
        }
    }

    public void startShiftRotation() {

        Guard guard = guardQueue.rotateShift();

        if (guard != null) {
            System.out.println("Shift rotated. New Guard → " + guard);
        }
    }
}