/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectca1;

import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author yagog
 */
public class ProjectCA1 {
    
public class Food {
    public String name;   
    private int weight;
    private int bbd;          
    private int timeplace;    

    public Food(String name, int weight, int bbd, int timeplace) {
        this.name = name;
        this.weight = weight;
        this.bbd = bbd;
        this.timeplace = timeplace;
    }

    
    public static Optional<Food> safeCreate(String name, int weight, int bbd, int timeplace) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }
        if (weight <= 0) {
            return Optional.empty();
        }
        if (bbd < 1 || bbd > 14) {
            return Optional.empty();
        }
        return Optional.of(new Food(name.trim(), weight, bbd, timeplace));
    }
    public String getName() { 
        return name; }           
    public int getWeight() { 
        return weight; }          
    public int getBbd() { 
        return bbd; }                
    public int getTimeplace() { 
        return timeplace; }    

    @Override
    public String toString() {
        return name + " (" + weight + "g, BB " + bbd + "d, t=" + timeplace + ")";
    }
}
    class Execute {
    private Food[] aliment;
    private final int CAPACITY = 8;

    private int front;
    private int rear;
    private int top;

    private boolean differentSides; // true = FIFO (queue), false = LIFO (stack)

    public Execute(boolean differentSides) {
        this.aliment = new Food[CAPACITY];
        this.differentSides = differentSides;

        this.front = -1;
        this.rear = -1;
        this.top = -1;
    }
    public boolean isEmpty() {
        if (differentSides) {
            return front == -1 && rear == -1; // queue
        }
        return top == -1; // stack
    }

    public boolean isFull() {
        if (differentSides) {
            return rear == CAPACITY - 1; 
        }
        return top == CAPACITY - 1; 
    }
    
    public void addFood(Food item) {
        if (item == null) {
            System.out.println("Invalid item.");
            return;
        }
        if (differentSides) {
            // QUEUE (FIFO) linear with front/rear
            if (isFull()) {
                System.out.println("Storage is full. Try again later.");
                return;
            }
            if (front == -1 && rear == -1) { // empty
                front = 0;
                rear  = 0;
            } else {
                rear++;
            }
            aliment[rear] = item;
            System.out.println("Added (FIFO): " + item);
        } else {
            // STACK (LIFO) using 'top'
            if (isFull()) {
                System.out.println("Storage is full. Try again later.");
                return;
            }
            aliment[++top] = item;
            System.out.println("Added (LIFO): " + item);
        }
    }

    public Food removeFood() {
        if (differentSides) {
            // QUEUE (FIFO)
            if (front == -1 && rear == -1) {
                System.out.println("Storage is empty.");
                return null;
            }
            Food out = aliment[front];
            if (front == rear) {
                // last element
                front = -1;
                rear  = -1;
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
}
}
