package org.todo.model;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks;
//    public static ArrayList ids = new ArrayList<>;

    public TodoList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task tasky) {
        if (tasky.getPriority() == 0) {
            tasks.add(tasky);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasky.getPriority() > tasks.get(i).getPriority()) {
                    tasks.add(i, tasky);
                    break;
                }
            }
        }

    }



    public boolean removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public boolean completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted();
            return true;
        } else {
            return false;
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
