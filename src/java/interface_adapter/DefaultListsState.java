package interface_adapter;

import java.util.ArrayList;

public class DefaultListsState {
    private ArrayList<String> lists;

    public void setLists(ArrayList<String> lists) {
        this.lists = lists;
    }

    public void addList(String list) {
        this.lists.add(list);
    }

    public ArrayList<String> getLists() {
        return this.lists;
    }
}
