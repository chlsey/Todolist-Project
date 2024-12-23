package interface_adapter.create_list;

import interface_adapter.DefaultListsState;
import interface_adapter.DefaultListsViewModel;
import interface_adapter.ViewManagerModel;
import use_case.create_list.CreateListOutputBoundary;
import use_case.create_list.CreateListOutputData;

public class CreateListPresenter implements CreateListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DefaultListsViewModel defaultListsViewModel;

    public CreateListPresenter(ViewManagerModel viewManagerModel,
                               DefaultListsViewModel defaultListsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.defaultListsViewModel = defaultListsViewModel;
    }

    @Override
    public void prepareSuccessView(CreateListOutputData createListOutputData) {
        final DefaultListsState defaultListsState = defaultListsViewModel.getState();


    }

    @Override
    public void prepareFailureView(String errorMessage) {

    }
}
