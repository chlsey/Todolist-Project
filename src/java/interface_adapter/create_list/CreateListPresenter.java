package interface_adapter.create_list;

import interface_adapter.ViewManagerModel;
import use_case.create_list.CreateListOutputBoundary;
import use_case.create_list.CreateListOutputData;

public class CreateListPresenter implements CreateListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CreateListViewModel createListViewModel;


    public CreateListPresenter(ViewManagerModel viewManagerModel,
                               CreateListViewModel createListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createListViewModel = createListViewModel;
    }

    @Override
    public void prepareSuccessView(CreateListOutputData createListOutputData) {
        final CreateListState createListState = createListViewModel.getState();


    }

    @Override
    public void prepareFailureView(String errorMessage) {

    }
}
