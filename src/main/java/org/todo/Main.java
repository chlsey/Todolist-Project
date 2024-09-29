package org.todo;

import org.todo.controller.TodoController;
import org.todo.model.TodoList;
import org.todo.view.TodoView;

public class Main {
    public static void main(String[] args) {
        TodoList todolist = new TodoList();
        TodoView view = new TodoView();
        TodoController controller = new TodoController(todolist, view);
        controller.run();
    }
}
