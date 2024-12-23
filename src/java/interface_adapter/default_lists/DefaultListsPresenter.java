package interface_adapter.default_lists;

import interface_adapter.DefaultListsState;
import interface_adapter.DefaultListsViewModel;
import interface_adapter.ViewManagerModel;
import use_case.default_lists.DefaultListsOutputBoundary;
import use_case.default_lists.DefaultListsOutputData;

public class DefaultListsPresenter implements DefaultListsOutputBoundary {
    private final DefaultListsViewModel defaultListsViewModel;
    private final ViewManagerModel viewManagerModel;

    public DefaultListsPresenter(ViewManagerModel viewManagerModel, DefaultListsViewModel defaultListsViewModel) {
        this.defaultListsViewModel = defaultListsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DefaultListsOutputData defaultListsOutputData) {
        final DefaultListsState defaultListsState = defaultListsViewModel.getState();
        defaultListsState.setLists(defaultListsOutputData.getLists());
        this.defaultListsViewModel.setState(defaultListsState);
        this.defaultListsViewModel.firePropertyChanged();
    }
}
