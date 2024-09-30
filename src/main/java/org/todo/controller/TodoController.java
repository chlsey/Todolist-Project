package org.todo.controller;

import org.todo.view.TodoView;
import org.todo.model.Task;
import org.todo.model.TodoList;

public class TodoController {
    private TodoList todolist;
    private TodoView view;

    public TodoController(TodoList todolist, TodoView view) {
        this.todolist = todolist;
        this.view = view;
    }

    public void run() {
        boolean isRunning = true;
        view.showMenu();

        while (isRunning) {
            String choice = view.getInput("Choose an option: ");

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    removeTask();
                    break;
                case "3":
                    completeTask();
                    break;

                case "4":
                    view.displayTasks(todolist.getTasks());
                    break;
                case "5":
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addTask() {
        String description = view.getInput("Enter task description: ");
        int prior = view.getPriority();
        todolist.addTask(new Task(description, prior));
        System.out.println("Task added.");
    }

    private void removeTask() {
        view.displayTasks(todolist.getTasks());
        int ind = view.getTaskIndex();
        if (!todolist.removeTask(ind)) {
            view.printIndexError();
        } else {
            System.out.println("Task removed.");
        }
    }

    private void completeTask() {
        view.displayTasks(todolist.getTasks());
        int index = view.getTaskIndex();
        if (todolist.completeTask(index)) {
            view.printIndexError();
        } else {
            System.out.println("Task marked as complete.");
        }
    }
}

