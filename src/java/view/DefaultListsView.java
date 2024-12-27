package view;

import interface_adapter.DefaultListsState;
import interface_adapter.DefaultListsViewModel;
import interface_adapter.create_list.CreateListController;
import interface_adapter.default_lists.DefaultListsController;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.*;

public class DefaultListsView extends JPanel implements PropertyChangeListener {
    private final String viewName = "lists view";
    private final DefaultListsViewModel defaultListsViewModel;
    private final CreateListController createListController;
    private final DefaultListsController defaultListsController;

    private final JLabel lists;
    private final JPanel buttons;
    private final JPanel contentPanel = new JPanel();
    private final JPanel newListPanel = new JPanel();
    private final JScrollPane scrollPane;

    private static final int SIZE = 16;
    private static final int SMALL = 12;
    private static final String ARIAL = "Arial";

    public DefaultListsView(DefaultListsViewModel defaultListsViewModel, CreateListController createListController,
                            DefaultListsController defaultListsController) {
        this.defaultListsViewModel = defaultListsViewModel;
        this.createListController = createListController;
        this.defaultListsController = defaultListsController;
        this.defaultListsViewModel.addPropertyChangeListener(this);

        defaultListsController.execute();

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(SIZE, SIZE, SIZE, SIZE));

        this.lists = new JLabel("Your ToDo Lists :)");
        this.lists.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.lists.setFont(new Font(ARIAL, Font.BOLD, SIZE));

        buttons = getButtons(defaultListsViewModel);

        // add new list option
        newListPanel.setLayout(new BoxLayout(newListPanel, BoxLayout.X_AXIS));
        final JLabel commentLabel = new JLabel("Add new todo list:");
        commentLabel.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
        commentLabel.setAlignmentX(CENTER_ALIGNMENT);
        newListPanel.add(commentLabel);

        JTextField commentInput = new JTextField(SMALL);
        commentInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, commentInput.getPreferredSize().height));
        newListPanel.add(commentInput);

        JButton check = new JButton("✔");
        check.addActionListener(
                evt -> {
                    JButton sourceButton = (JButton) evt.getSource();
                    if (sourceButton.getText().equals("✔")) {
                        this.createListController.execute(commentInput.getText());
                    }
                }
        );

        newListPanel.add(check);

        // adding everything to main content panel
        contentPanel.add(lists);
        contentPanel.add(Box.createRigidArea(new Dimension(0, SIZE)));
        contentPanel.add(buttons);
        contentPanel.add(Box.createRigidArea(new Dimension(0, SMALL)));
        contentPanel.add(newListPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, SMALL)));

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel getButtons(DefaultListsViewModel defaultListsViewModel) {
        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        DefaultListsState defaultListsState = defaultListsViewModel.getState();
        ArrayList<String> lists = defaultListsState.getLists();

        int num = 1;

        for (String list : lists) {
            final JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));

            JLabel listNum = new JLabel(String.valueOf(num));

            JButton listBut = new JButton(list);
            listBut.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
            listBut.addActionListener(
                    evt -> {
                        JButton sourceButton = (JButton) evt.getSource();
                        if (sourceButton.getText().equals(list)) {
                            this.defaultListsController.switchToTasksView(list);
                        }
                    }
            );

            JButton delBut = new JButton("✖");
            delBut.addActionListener(
                    evt -> {
                        deleteList();
                    }
            );

//            delBut.setPreferredSize(new Dimension(1, 1));

            listPanel.add(listNum);
            listPanel.add(listBut);
            listPanel.add(delBut);
            num += 1;

            buttons.add(listPanel);
        }
        return buttons;
    }

    private void deleteList() {
        JDialog popUp = new JDialog((Frame) null, "Confirm Deletion", true);
        popUp.setLayout(new BoxLayout(popUp.getContentPane(), BoxLayout.Y_AXIS));

        JButton yesBut = new JButton("yes");
        JButton noBut = new JButton("no");
        noBut.addActionListener(evt -> popUp.dispose());
        JPanel buttons = new JPanel(new FlowLayout());

        buttons.add(yesBut);
        buttons.add(noBut);

        JLabel warn = new JLabel("are you sure? there's no turning back.");
        warn.setAlignmentX(Component.CENTER_ALIGNMENT);
        warn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        popUp.add(warn);
        popUp.add(buttons);

        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setVisible(true);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("new list")) {
            DefaultListsState defaultListsState = defaultListsViewModel.getState();
            JPanel newBut = new JPanel();
            JLabel num = new JLabel(String.valueOf(defaultListsState.getLists().size()));
            num.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
            newBut.add(num);
            JButton listBut = new JButton(defaultListsState.getNewest());
            listBut.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
            listBut.addActionListener(
                    event -> {
                            this.defaultListsController.switchToTasksView(defaultListsState.getNewest());
                    });

            JButton delBut = new JButton("✖");
            delBut.addActionListener(
                    event -> {
                        deleteList();
                    }
            );

            newBut.add(listBut);
            newBut.add(delBut);
            buttons.add(newBut);

            this.revalidate();
            this.repaint();
        }
    }


    public String getViewName() {
        return viewName;
    }
}
