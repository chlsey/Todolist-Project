package org.todo.view;

import org.todo.model.Task;

import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class TodoView {
    private Scanner scanner = new Scanner(System.in);

    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks at the moment :)");
        } else {
            System.out.println("Your To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\n" + (i + 1) + "." + tasks.get(i).getTaskName());
            }
        }
    }

    public String getInput(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        return scan.nextLine();
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

    public void printIndexError() {
        System.out.println("Invalid Task Number :(");
    }

    public String getTaskName() {
        System.out.print("Enter task name: ");
        return scanner.next();
    }

    public int getPriority() {
        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("Normal", 0);
        priorityMap.put("Important", 1);
        priorityMap.put("Urgent", 2);
//
//        while (true) {
//            System.out.println("Please indicate priority level: Normal, Important, or Urgent.");
//            String input = scanner.next();
//
//            if (priorityMap.containsKey(input)) {
//                return priorityMap.get(input);
//            }
//        }
        System.out.println("Please indicate priority level: Normal, Important, or Urgent. Enter none or any value " +
                "for Normal level.");
        String input = scanner.next();
        if (priorityMap.containsKey(input)) {
            return priorityMap.get(input);
        }
        return 0;
    }
}
