package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class ToDo {
    private String description;
    private String dueDate;
    private int priority;

    public ToDo(String description, String date, int priority) {
        this.description = description;
        this.dueDate = date;
        this.priority = priority;
    }

    public void editDesc(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDue() {
        return LocalDateTime.of(Integer.parseInt(dueDate.substring(0,4)),
                Integer.parseInt(dueDate.substring(5,7)),
                Integer.parseInt(dueDate.substring(8,10)),
                Integer.parseInt(dueDate.substring(11,13)),
                Integer.parseInt(dueDate.substring(14,16)));
    }

    public boolean checkPriority() {
        return priority == 1;
    }
}
