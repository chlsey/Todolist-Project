package view;

import entity.ToDo;
import interface_adapter.TasksState;
import interface_adapter.TasksViewModel;
import interface_adapter.add_task.AddTaskController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class TasksView extends JPanel implements PropertyChangeListener {
    final private String viewName = "tasks view";
    final private TasksViewModel tasksViewModel;
    final private AddTaskController addTaskController;

    private JLabel title;
    private JPanel buttons = new JPanel();
    private final JPanel contentPanel = new JPanel();
    private final JScrollPane scrollPane;
    private final JButton backBut;

    private static final int SIZE = 16;
    private static final int SMALL = 12;
    private static final String ARIAL = "Arial";

    public TasksView(TasksViewModel tasksViewModel, AddTaskController addTaskController) {
        this.tasksViewModel = tasksViewModel;
        this.addTaskController = addTaskController;
        tasksViewModel.addPropertyChangeListener(this);

        backBut = new JButton("back");
        backBut.addActionListener(evt -> this.addTaskController.switchToListView());

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(SMALL, SMALL, SMALL, SMALL));

        JPanel backPan = new JPanel(new BorderLayout());
        backPan.add(backBut, BorderLayout.WEST);

        this.scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());
        this.add(backPan, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel getTasks(TasksViewModel tasksViewModel) {
        TasksState tasksState = tasksViewModel.getState();
        ArrayList<ToDo> toDos = tasksState.getTodos();
        JPanel buttons = new JPanel();

        for (ToDo toDo : toDos) {
            JCheckBox task = new JCheckBox(toDo.getDescription());
            task.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
            task.setMnemonic(KeyEvent.VK_C);

//            JButton listBut = new JButton(list);
//            listBut.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
//            listBut.addActionListener(
//                    evt -> {
//                        JButton sourceButton = (JButton) evt.getSource();
//                        if (sourceButton.getText().equals(list)) {
//                            this.defaultListsController.switchToTasksView(list);
//                        }
//                    }
//            );

            buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
            buttons.add(task);
        }
        return buttons;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.buttons.removeAll();
        this.title = new JLabel(tasksViewModel.getState().getTitle(), JLabel.CENTER); // Set alignment to CENTER
        this.title.setFont(new Font(ARIAL, Font.BOLD, SIZE));

        // Create a wrapper panel for the title and use BoxLayout for centering
        JPanel titleWrapper = new JPanel();
        titleWrapper.setLayout(new BoxLayout(titleWrapper, BoxLayout.X_AXIS));
        titleWrapper.add(Box.createHorizontalGlue()); // Push content to the center
        titleWrapper.add(this.title);
        titleWrapper.add(Box.createHorizontalGlue()); // Push content to the center

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(titleWrapper); // Add the centered title
        buttons.add(getTasks(tasksViewModel)); // Add tasks
        contentPanel.add(buttons);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public String getViewName() {
        return viewName;
    }
}
