package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ToDoList {
    private String title;
    private ArrayList<ToDo> priorityToDos;
    private ArrayList<ToDo> normalToDos;

    public ToDoList(String title) {
        this.title = title;
    }

    public ToDoList(String title, ArrayList<ToDo> todos) {
        this.title = title;
        for (ToDo todo : todos) {
            if (todo.checkPriority()) {
                priorityToDos.add(todo);
            } else {
                normalToDos.add(todo);
            }
        }
    }

    public void addTask(ToDo task) {
        if (task.checkPriority()) {
            priorityToDos.add(task);
        } else {
            normalToDos.add(task);
        }
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
