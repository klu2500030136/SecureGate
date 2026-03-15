package datastructures;

import models.Guard;

public class GuardCircularQueue {

    private Guard[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public GuardCircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new Guard[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addGuard(Guard guard) {

        if (isFull()) {
            System.out.println("Guard queue is full.");
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = guard;
        size++;
    }

    public Guard rotateShift() {

        if (isEmpty()) {
            System.out.println("No guards available.");
            return null;
        }

        Guard current = queue[front];

        front = (front + 1) % capacity;
        rear = (rear + 1) % capacity;

        queue[rear] = current;

        return getCurrentGuard();
    }

    public Guard getCurrentGuard() {

        if (isEmpty()) {
            return null;
        }

        return queue[front];
    }
}