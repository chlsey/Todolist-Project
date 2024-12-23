package use_case.default_lists;

import java.util.ArrayList;

public class DefaultListsInteractor implements DefaultListsInputBoundary {

    private DefaultListsToDoDataAccessObjectInterface defaultListsToDoDataAccessObjectInterface;
    private DefaultListsOutputBoundary defaultListsPresenter;

    public DefaultListsInteractor(DefaultListsToDoDataAccessObjectInterface defaultListsToDoDataAccessObjectInterface,
                                DefaultListsOutputBoundary defaultListsOutputBoundary) {
        this.defaultListsToDoDataAccessObjectInterface = defaultListsToDoDataAccessObjectInterface;
        this.defaultListsPresenter = defaultListsOutputBoundary;
    }


    @Override
    public void execute(DefaultListsInputData defaultListsInputData) {
        ArrayList<String> lists = defaultListsToDoDataAccessObjectInterface.getAllLists();
        DefaultListsOutputData defaultListsOutputData = new DefaultListsOutputData(lists);
        defaultListsPresenter.prepareSuccessView(defaultListsOutputData);
    }
}
