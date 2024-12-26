package interface_adapter;

import entity.ToDo;

import java.util.ArrayList;

public class TasksState {
    private String title;
    private ArrayList<ToDo> todos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTasks(ArrayList<ToDo> todos) {
        this.todos = todos;
    }

    public ArrayList<ToDo> getTodos() {
        return todos;
    }

}
