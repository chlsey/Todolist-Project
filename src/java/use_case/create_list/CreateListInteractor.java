package use_case.create_list;

public class CreateListInteractor implements CreateListInputBoundary {
    private CreateListToDoDataAccessInterface createListToDoDataAccessInterface;
    private CreateListOutputBoundary createListPresenter;

    public CreateListInteractor(CreateListToDoDataAccessInterface createListToDoDataAccessInterface,
                                CreateListOutputBoundary createListOutputBoundary) {
        this.createListToDoDataAccessInterface = createListToDoDataAccessInterface;
        this.createListPresenter = createListOutputBoundary;
    }

    @Override
    public void execute(CreateListInputData createListInputData) {
        if (createListToDoDataAccessInterface.createList(createListInputData.getListTitle())) {
            final CreateListOutputData createListOutputData = new CreateListOutputData(createListInputData.getListTitle());
            createListPresenter.prepareSuccessView(createListOutputData);
        } else {
            createListPresenter.prepareFailureView("This list name is already taken :( Please choose another.");
        }


    }
}
