package interface_adapter.default_lists;

import use_case.create_list.CreateListInputData;
import use_case.default_lists.DefaultListsInputBoundary;
import use_case.default_lists.DefaultListsInputData;

public class DefaultListsController {
    private final DefaultListsInputBoundary defaultListsInteractor;

    public DefaultListsController(DefaultListsInputBoundary defaultListsInteractor) {
        this.defaultListsInteractor = defaultListsInteractor;
    }

    public void execute(String listTitle) {
        DefaultListsInputData defaultListsInputData = new DefaultListsInputData();
        this.defaultListsInteractor.execute(defaultListsInputData);
    }
}
