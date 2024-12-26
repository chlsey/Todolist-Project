package interface_adapter.default_lists;

import entity.ToDo;
import interface_adapter.*;
import use_case.default_lists.DefaultListsOutputBoundary;
import use_case.default_lists.DefaultListsOutputData;

import java.util.ArrayList;

public class DefaultListsPresenter implements DefaultListsOutputBoundary {
    private final DefaultListsViewModel defaultListsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final TasksViewModel tasksViewModel;

    public DefaultListsPresenter(ViewManagerModel viewManagerModel, DefaultListsViewModel defaultListsViewModel, TasksViewModel tasksViewModel) {
        this.defaultListsViewModel = defaultListsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.tasksViewModel = tasksViewModel;
    }

    @Override
    public void prepareSuccessView(DefaultListsOutputData defaultListsOutputData) {
        final DefaultListsState defaultListsState = defaultListsViewModel.getState();
        defaultListsState.setLists(defaultListsOutputData.getLists());
        this.defaultListsViewModel.setState(defaultListsState);
        this.defaultListsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToTasksView(ArrayList<ToDo> todos, String title) {
        final TasksState tasksState = tasksViewModel.getState();
        tasksState.setTasks(todos);
        tasksState.setTitle(title);
        this.tasksViewModel.setState(tasksState);
        this.tasksViewModel.firePropertyChanged();

        this.viewManagerModel.setState(this.tasksViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
