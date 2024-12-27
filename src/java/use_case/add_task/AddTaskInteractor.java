package use_case.add_task;

import interface_adapter.add_task.AddTaskPresenter;

public class AddTaskInteractor implements AddTaskInputBoundary {
    private AddTaskToDoDataAccessInterface addTaskToDoDataAccessInterface;
    private AddTaskOutputBoundary addTaskPresenter;

    public AddTaskInteractor(AddTaskToDoDataAccessInterface addTaskToDoDataAccessInterface,
                             AddTaskOutputBoundary addTaskPresenter) {
        this.addTaskPresenter = addTaskPresenter;
        this.addTaskToDoDataAccessInterface = addTaskToDoDataAccessInterface;
    }

    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        addTaskToDoDataAccessInterface.addTask(addTaskInputData.getTitle(),
                addTaskInputData.getDesc(),
                addTaskInputData.getDate(),
                addTaskInputData.getPriority());
        AddTaskOutputData addTaskOutputData = new AddTaskOutputData(addTaskInputData.getDesc(),
                addTaskInputData.getDate(),
                addTaskInputData.getPriority());
        addTaskPresenter.prepareSuccessView(addTaskOutputData);

    }

    @Override
    public void switchToListView() {
        addTaskPresenter.switchToListView();
    }
}
