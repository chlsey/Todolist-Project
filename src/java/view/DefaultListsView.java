package view;

import interface_adapter.DefaultListsViewModel;
import interface_adapter.create_list.CreateListController;
import interface_adapter.default_lists.DefaultListsController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class DefaultListsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "default list";
    private final DefaultListsViewModel defaultListsViewModel;
    private final CreateListController createListController;
    private final DefaultListsController defaultListsController;

    private final JLabel lists;

    public DefaultListsView(DefaultListsViewModel defaultListsViewModel, CreateListController createListController,
                            DefaultListsController defaultListsController) {
        this.defaultListsViewModel = defaultListsViewModel;
        this.createListController = createListController;
        this.defaultListsController = defaultListsController;

        this.lists = new JLabel("Your ToDo Lists :)");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }
}
