package app;

import data_access.FileToDoDataAccessObject;
import interface_adapter.DefaultListsViewModel;
import interface_adapter.TasksViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.AddTaskPresenter;
import interface_adapter.create_list.CreateListController;
import interface_adapter.create_list.CreateListPresenter;
import interface_adapter.default_lists.DefaultListsController;
import interface_adapter.default_lists.DefaultListsPresenter;
import use_case.add_task.AddTaskInputBoundary;
import use_case.add_task.AddTaskInputData;
import use_case.add_task.AddTaskInteractor;
import use_case.add_task.AddTaskOutputBoundary;
import use_case.create_list.CreateListInputBoundary;
import use_case.create_list.CreateListInteractor;
import use_case.create_list.CreateListOutputBoundary;
import use_case.create_list.CreateListToDoDataAccessInterface;
import use_case.default_lists.DefaultListsInputBoundary;
import use_case.default_lists.DefaultListsInteractor;
import use_case.default_lists.DefaultListsOutputBoundary;
import use_case.default_lists.DefaultListsToDoDataAccessObjectInterface;

public final class UseCaseFactory {

    private UseCaseFactory() {}

    public static DefaultListsController createDefaultListsUseCase(
            ViewManagerModel viewManagerModel,
            DefaultListsViewModel defaultListsViewModel,
            DefaultListsToDoDataAccessObjectInterface defaultListsToDoDataAccessObjectInterface, TasksViewModel tasksViewModel) {

        final DefaultListsOutputBoundary defaultListsOutputBoundary = new DefaultListsPresenter(viewManagerModel,
                defaultListsViewModel, tasksViewModel);
        final DefaultListsInputBoundary defaultListsInputBoundary = new DefaultListsInteractor(
                defaultListsToDoDataAccessObjectInterface, defaultListsOutputBoundary);

        return new DefaultListsController(defaultListsInputBoundary);
    }

    public static CreateListController createCreateListUseCase(
            ViewManagerModel viewManagerModel,
            DefaultListsViewModel defaultListsViewModel,
            CreateListToDoDataAccessInterface createListToDoDataAccessInterface) {

        final CreateListOutputBoundary createListOutputBoundary = new CreateListPresenter(viewManagerModel,
                defaultListsViewModel);
        final CreateListInputBoundary createListInputBoundary = new CreateListInteractor(
                createListToDoDataAccessInterface, createListOutputBoundary);

        return new CreateListController(createListInputBoundary);
    }

    public static AddTaskController createAddTaskUseCase(ViewManagerModel viewManagerModel,
                                                         DefaultListsViewModel defaultListsViewModel,
                                                         TasksViewModel tasksViewModel,
                                                         FileToDoDataAccessObject fileToDoDataAccessObject) {
        final AddTaskOutputBoundary addTaskOutputBoundary = new AddTaskPresenter(viewManagerModel, defaultListsViewModel,tasksViewModel);
        final AddTaskInputBoundary addTaskInputBoundary = new AddTaskInteractor(fileToDoDataAccessObject, addTaskOutputBoundary);

        return new AddTaskController(addTaskInputBoundary);
    }
}

