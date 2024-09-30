package org.todo.model;

import org.todo.DatabaseHelper;

import javax.xml.crypto.Data;

public class Task {
    private String taskName;
    private int priority;
    private boolean completed;
    private DatabaseHelper dbHelp;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
        this.priority = 0;
        this.dbHelp = new DatabaseHelper();
    }

    public Task(String taskName, int prior) {
        this.taskName = taskName;
        this.completed = false;
        this.priority = prior;
        this.dbHelp = new DatabaseHelper();
        dbHelp.insertTask(taskName, priority, completed, 0);
    }
    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted () {
        return completed;
    }

    public int getPriority () {
        return priority;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void changePriority(int num) {
        priority = num;
    }
}

