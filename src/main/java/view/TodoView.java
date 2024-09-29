package view;

import model.Task;

import java.util.List;
import java.util.Scanner;

public class TodoView {
    private Scanner scanner = new Scanner(System.in);

    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You're done for the day :)");
        } else {
            System.out.println("Your To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\n" + tasks.get(i));
            }
        }
    }

    public String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int getTaskIndex() {
        System.out.print("Enter task number: ");
        return scanner.nextInt() - 1;
    }

    public void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add task");
        System.out.println("2. Remove task");
        System.out.println("3. Complete task");
        System.out.println("4. View tasks");
        System.out.println("5. Exit");
    }


}
