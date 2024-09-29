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

        while (isRunning) {
            view.showMenu();
            String choice = view.getInput("Choose an option: ");

            switch (choice) {
                case "1":
                    todolist.addTask(new Task("lol"));
                    break;
                case "2":
                    todolist.removeTask(0);
                    break;
                case "3":
                    todolist.completeTask(0);
                    break;
                case "4":
                    // org.todo.view.displayTasks(todolist.getTasks());
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
        todolist.addTask(new Task(description));
        System.out.println("Task added.");
    }

    private void removeTask() {
        view.displayTasks(todolist.getTasks());
        int index = view.getTaskIndex();
        todolist.removeTask(index);
        System.out.println("Task removed.");
    }

    private void completeTask() {
        view.displayTasks(todolist.getTasks());
        int index = view.getTaskIndex();
        todolist.completeTask(index);
        System.out.println("Task marked as complete.");
    }

}

