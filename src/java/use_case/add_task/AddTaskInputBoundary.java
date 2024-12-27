package use_case.add_task;

public interface AddTaskInputBoundary {
    void execute(AddTaskInputData addTaskInputData);

    void switchToListView();
}
