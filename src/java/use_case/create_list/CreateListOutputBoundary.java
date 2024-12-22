package use_case.create_list;

public interface CreateListOutputBoundary {
    void prepareSuccessView(CreateListOutputData createListOutputData);
    void prepareFailureView(String errorMessage);
}
