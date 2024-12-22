package interface_adapter.create_list;

import interface_adapter.ViewModel;

public class CreateListViewModel extends ViewModel<CreateListState> {
    public CreateListViewModel(String viewName) {
        super(viewName);
        setState(new CreateListState());
    }
}
