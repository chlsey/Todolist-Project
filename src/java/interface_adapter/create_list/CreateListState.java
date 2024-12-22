package interface_adapter.create_list;

import java.util.ArrayList;

public class CreateListState {
    private ArrayList<String> todoLists = new ArrayList<>();

    public CreateListState() {
    }


    public ArrayList<String> getTodolists() {
        return todoLists;
    }

    public void addList(String title) {
        this.todoLists.add(title);
    }
}
