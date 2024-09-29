package org.todo.model;

public class Task {
    private String taskName;
    private int priority;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
        this.priority = 0;
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

