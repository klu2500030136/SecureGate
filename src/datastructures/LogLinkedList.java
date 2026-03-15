package datastructures;

import models.LogEntry;

public class LogLinkedList {

    private class Node {
        LogEntry data;
        Node next;

        Node(LogEntry data) {
            this.data = data;
            this.next = null;
        }
    }

    public java.util.List<LogEntry> getAllLogs() {

    java.util.List<LogEntry> logs = new java.util.ArrayList<>();

    Node temp = head;

    while (temp != null) {
        logs.add(temp.data);
        temp = temp.next;
    }

    return logs;
}

    private Node head;
    private int size;

    public void addLog(LogEntry log) {

        Node newNode = new Node(log);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }

        size++;
    }

    public void displayLogs() {

        if (head == null) {
            System.out.println("No logs available.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public int getSize() {
        return size;
    }
}