package interface_adapter;

public class DefaultListsViewModel extends ViewModel<DefaultListsState> {
    public DefaultListsViewModel() {
        super("lists view");
        setState(new DefaultListsState());
    }
}
