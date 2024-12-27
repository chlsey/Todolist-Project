package use_case.add_task;

public interface AddTaskToDoDataAccessInterface {
    void addTask(String title, String desc, String date, Boolean priority);
}
