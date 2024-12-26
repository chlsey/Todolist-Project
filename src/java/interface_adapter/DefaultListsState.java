package interface_adapter;

import java.awt.*;
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

    public String getNewest() {
        return this.lists.get(this.lists.size() - 1);
    }

    public int getIndByTitle(String newest) {
        int i = 0;
        for (String title : this.lists) {
            if (title.equals(newest)) {
                return i;
            }
            i += 1;
        }
        return -1;
    }
}
