package app;

import interface_adapter.DefaultListsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_list.CreateListController;
import interface_adapter.create_list.CreateListPresenter;
import interface_adapter.default_lists.DefaultListsController;
import interface_adapter.default_lists.DefaultListsPresenter;
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
            DefaultListsToDoDataAccessObjectInterface defaultListsToDoDataAccessObjectInterface) {

        // Notice how we pass this method's parameters to the Presenter.
        final DefaultListsOutputBoundary defaultListsOutputBoundary = new DefaultListsPresenter(viewManagerModel,
                defaultListsViewModel);
        final DefaultListsInputBoundary defaultListsInputBoundary = new DefaultListsInteractor(
                defaultListsToDoDataAccessObjectInterface, defaultListsOutputBoundary);

        return new DefaultListsController(defaultListsInputBoundary);
    }

    public static CreateListController createCreateListUseCase(
            ViewManagerModel viewManagerModel,
            DefaultListsViewModel defaultListsViewModel,
            CreateListToDoDataAccessInterface createListToDoDataAccessInterface) {

        // Notice how we pass this method's parameters to the Presenter.
        final CreateListOutputBoundary createListOutputBoundary = new CreateListPresenter(viewManagerModel,
                defaultListsViewModel);
        final CreateListInputBoundary createListInputBoundary = new CreateListInteractor(
                createListToDoDataAccessInterface, createListOutputBoundary);

        return new CreateListController(createListInputBoundary);
    }
}

