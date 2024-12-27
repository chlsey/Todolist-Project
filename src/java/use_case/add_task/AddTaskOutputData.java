package use_case.add_task;

public class AddTaskOutputData {
    private Boolean priority;
    private String desc;
    private String date;

    public AddTaskOutputData(String desc, String date, Boolean priority) {
        this.priority = priority;
        this.desc = desc;
        this.date = date;
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
}
