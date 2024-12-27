package interface_adapter.add_task;

import use_case.add_task.AddTaskInputBoundary;
import use_case.add_task.AddTaskInputData;

public class AddTaskController {
    private AddTaskInputBoundary addTaskInteractor;

    public AddTaskController(AddTaskInputBoundary addTaskInteractor) {
        this.addTaskInteractor = addTaskInteractor;
    }

    public void execute(Boolean priority, String desc, String date, String title) {
        this.addTaskInteractor.execute(new AddTaskInputData(priority, desc, date, title));
    }

    public void switchToListView() {
        this.addTaskInteractor.switchToListView();
    }
}
