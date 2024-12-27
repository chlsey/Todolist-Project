package interface_adapter;

import entity.ToDo;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TasksState {
    private String title;
    private ArrayList<ToDo> priority = new ArrayList<>();
    private ArrayList<ToDo> normal = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTasks(ArrayList<ToDo> todos) {
        priority.clear();
        normal.clear();
        for (ToDo todo : todos) {
            if (todo.checkPriority()) {
                priority.add(todo);
            } else {
                normal.add(todo);
            }
        }
    }

    public ArrayList<ToDo> getPriorityTodos() {
        return priority;
    }

    public ArrayList<ToDo> getNormalTodos() {
        return normal;
    }

    public void addTask(ToDo toDo) {
        int i = 0;
        LocalDateTime date = toDo.getDue();
        ArrayList<ToDo> todos = ((toDo.checkPriority()) ? priority : normal);
        boolean added = false;

        for (ToDo task : todos) {
            if (date.isBefore(task.getDue())) {
                if (toDo.checkPriority()) {
                    priority.add(i, toDo);
                } else {
                    normal.add(i, toDo);
                }
                added = true;
                break;
            }
            i += 1;
        }
        if (!added) {
            if (toDo.checkPriority()) {
                priority.add(i, toDo);
            } else {
                normal.add(i, toDo);
            }
        }
    }

}
