package interface_adapter.add_task;

import entity.ToDo;
import interface_adapter.*;
import use_case.add_task.AddTaskOutputBoundary;
import use_case.add_task.AddTaskOutputData;

public class AddTaskPresenter implements AddTaskOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private DefaultListsViewModel defaultListsViewModel;
    private TasksViewModel tasksViewModel;

    public AddTaskPresenter(ViewManagerModel viewManagerModel, DefaultListsViewModel defaultListsViewModel,
                            TasksViewModel tasksViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.defaultListsViewModel = defaultListsViewModel;
        this.tasksViewModel = tasksViewModel;
    }

    @Override
    public void prepareSuccessView(AddTaskOutputData addTaskOutputData) {
        TasksState tasksState = tasksViewModel.getState();
        ToDo newToDo = new ToDo(addTaskOutputData.getDesc(),
                addTaskOutputData.getDate(),
                ((addTaskOutputData.getPriority()) ? 1 : 0));
        tasksState.addTask(newToDo);

        tasksViewModel.setState(tasksState);
        tasksViewModel.firePropertyChanged();
    }

    @Override
    public void switchToListView() {
        this.viewManagerModel.setState(defaultListsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
