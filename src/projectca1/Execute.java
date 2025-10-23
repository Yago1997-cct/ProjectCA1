/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectca1;

/**
 *
 * @author yagog
 */
public class Execute {
    private final Food[] aliment;
    private static final int CAPACITY = 8;

    private int front;
    private int rear;
    private int top;

    private final boolean differentSides; // true = FIFO (queue), false = LIFO (stack)

    Execute(boolean differentSides) {
        this.aliment = new Food[CAPACITY];
        this.differentSides = differentSides;
        this.front = -1;
        this.rear  = -1;
        this.top   = -1;
    }

    boolean isEmpty() {
        return differentSides ? (front == -1 && rear == -1) : (top == -1);
    }

    boolean isFull() {
        return differentSides ? (rear == CAPACITY - 1) : (top == CAPACITY - 1);
    }

    // ADD food
    void addFood(Food item) {
        if (item == null) {
            System.out.println("Invalid item.");
            return;
        }
        if (differentSides) {
            // QUEUE (FIFO) linear (no wrap)
            if (isFull()) {
                System.out.println("Storage is full. Try again later.");
                return;
            }
            if (front == -1 && rear == -1) { // empty
                front = 0; rear = 0;
            } else {
                rear++;
            }
            aliment[rear] = item;
            System.out.println("Added (FIFO): " + item);
        } else {
            // STACK (LIFO)
            if (isFull()) {
                System.out.println("Storage is full. Try again later.");
                return;
            }
            aliment[++top] = item;
            System.out.println("Added (LIFO): " + item);
        }
    }

    // REMOVE food
    Food removeFood() {
        if (differentSides) {
            // QUEUE (FIFO)
            if (front == -1 && rear == -1) {
                System.out.println("Storage is empty.");
                return null;
            }
            Food out = aliment[front];
            if (front == rear) { // last element
                front = -1; rear = -1;
            } else {
                front++;
            }
            System.out.println("Removed (FIFO): " + out);
            return out;
        } else {
            // STACK (LIFO)
            if (top == -1) {
                System.out.println("Storage is empty.");
                return null;
            }
            Food out = aliment[top--];
            System.out.println("Removed (LIFO): " + out);
            return out;
        }
    }

    void showFoods() {
        if (differentSides) {
            // QUEUE
            if (front == -1 && rear == -1) {
                System.out.println("Storage is empty.");
                return;
            }
            System.out.println("Contents (FIFO) [front -> rear]:");
            for (int i = front; i <= rear; i++) {
                System.out.println("  " + aliment[i]);
            }
        } else {
            // STACK
            if (top == -1) {
                System.out.println("Storage is empty.");
                return;
            }
            System.out.println("Contents (LIFO) [base -> top]:");
            for (int i = 0; i <= top; i++) {
                System.out.println("  " + aliment[i]);
            }
        }
    }
}


