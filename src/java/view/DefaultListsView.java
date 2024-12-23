package view;

import interface_adapter.DefaultListsState;
import interface_adapter.DefaultListsViewModel;
import interface_adapter.create_list.CreateListController;
import interface_adapter.default_lists.DefaultListsController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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

        defaultListsController.execute();

        this.lists = new JLabel("Your ToDo Lists :)");
        this.lists.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        DefaultListsState defaultListsState = defaultListsViewModel.getState();
        ArrayList<String> lists = defaultListsState.getLists();
        for (String list : lists) {
            JButton but = new JButton(list);
//            but.addActionListener(
//                    evt -> {
//                        if (evt.getSource().equals(list)) {
//                            final LoggedInState currentState = loggedInViewModel.getState();
//
//                            this.changePasswordController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword()
//                            );
//                        }
//                    }
//            );
            buttons.add(but);
        }

        this.add(this.lists);
        this.add(buttons);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
