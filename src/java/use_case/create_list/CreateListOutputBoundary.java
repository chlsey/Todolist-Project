package use_case.create_list;

import interface_adapter.DefaultListsViewModel;

public interface CreateListOutputBoundary {
    void prepareSuccessView(CreateListOutputData createListOutputData);

    void prepareFailureView(String errorMessage);
}
