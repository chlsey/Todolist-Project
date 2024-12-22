package interface_adapter.create_list;

import use_case.create_list.CreateListInputBoundary;
import use_case.create_list.CreateListInputData;
import use_case.create_list.CreateListInteractor;

public class CreateListController {
    private final CreateListInputBoundary createListInteractor;

    public CreateListController(CreateListInteractor createListInteractor) {
        this.createListInteractor = createListInteractor;
    }

    public void execute(String listTitle) {
        CreateListInputData createListInputData = new CreateListInputData(listTitle);
        this.createListInteractor.execute(createListInputData);
    }
}
