/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectca1;

/**
 *
 * @author yagog
 */
    
import java.util.Optional;
import java.util.Scanner;

public class YAFood {

    private static int nowEpochSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose storage mode:");
        System.out.println("1. Use only front door (LIFO stack)");
        System.out.println("2. Use front + opposite door (FIFO queue)");
        int modeChoice = readInt(scanner, "Your choice (1-2): ");
        boolean differentSides = (modeChoice == 2);

        Execute storage = new Execute(differentSides);

        while (true) {
            System.out.println("\nStorage Menu:");
            System.out.println("1. Add Food");
            System.out.println("2. Remove Food");
            System.out.println("3. Show Foods");
            System.out.println("4. Exit");
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: {
                    System.out.print("Enter food name: ");
                    String name = scanner.nextLine().trim();

                    int weight = readInt(scanner, "Enter weight (grams): ");
                    int bbd    = readInt(scanner, "Enter best-before in days (1..14): ");

                    int timeplace = nowEpochSeconds();
                    Optional<Food> maybeFood = Food.safeCreate(name, weight, bbd, timeplace);
                    if (!maybeFood.isPresent()) {                   // Java 8 friendly
                        System.out.println("Invalid data (name/weight/bbd). Try again.");
                    } else {
                        storage.addFood(maybeFood.get());
                    }
                    break;
                }
                case 2: {
                    storage.removeFood();
                    break;
                }
                case 3: {
                    storage.showFoods();
                    break;
                }
                case 4: {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                }
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}


