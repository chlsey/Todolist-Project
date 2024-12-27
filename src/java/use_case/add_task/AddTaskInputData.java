package use_case.add_task;

public class AddTaskInputData {
    private Boolean priority;
    private String desc;

    private String date;
    private String title;

    public AddTaskInputData(Boolean priority, String desc, String date, String title) {
        this.priority = priority;
        this.desc = desc;
        this.date = date;
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public Boolean getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

}
