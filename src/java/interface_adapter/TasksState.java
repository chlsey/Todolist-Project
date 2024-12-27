package interface_adapter;

import entity.ToDo;

import java.text.ParseException;
import java.time.LocalDateTime;
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

    public void addTask(ToDo toDo) {
        int i = 0;
        LocalDateTime date = toDo.getDue();

        for (ToDo task : todos) {
            if (date.isBefore(task.getDue())) {
                todos.add(i, toDo);
                break;
            }
            i += 1;
        }
    }

}
