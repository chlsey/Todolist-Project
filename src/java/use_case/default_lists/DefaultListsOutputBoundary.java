package use_case.default_lists;

import entity.ToDo;

import java.util.ArrayList;

public interface DefaultListsOutputBoundary {
    void prepareSuccessView(DefaultListsOutputData defaultListsOutputData);

    void switchToTasksView(ArrayList<ToDo> todos, String list);
}
