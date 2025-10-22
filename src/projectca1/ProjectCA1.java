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
}
}
