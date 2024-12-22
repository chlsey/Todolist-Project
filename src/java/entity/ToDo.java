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

    public Date getDue() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.parse(this.dueDate);
    }

    public boolean checkPriority() {
        return priority == 1;
    }
}
