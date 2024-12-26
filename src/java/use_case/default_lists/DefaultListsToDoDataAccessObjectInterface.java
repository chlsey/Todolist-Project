package use_case.default_lists;

import entity.ToDo;

import java.util.ArrayList;

public interface DefaultListsToDoDataAccessObjectInterface {
    ArrayList<String> getAllLists();

    ArrayList<ToDo> getToDos(String list);
}
