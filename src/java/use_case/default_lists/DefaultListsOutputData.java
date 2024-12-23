package use_case.default_lists;

import java.util.ArrayList;

public class DefaultListsOutputData {
    private ArrayList<String> lists;

    public DefaultListsOutputData(ArrayList<String> lists) {
        this.lists = lists;
    }

    public ArrayList<String> getLists() {
        return this.lists;
    }
}
