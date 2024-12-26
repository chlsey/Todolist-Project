package view;

import entity.ToDo;
import interface_adapter.TasksState;
import interface_adapter.TasksViewModel;

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

    private final JLabel title;
    private JPanel buttons;
    private final JPanel contentPanel = new JPanel();
    private final JPanel newListPanel = new JPanel();
    private final JScrollPane scrollPane;

    private static final int SIZE = 16;
    private static final int SMALL = 12;
    private static final String ARIAL = "Arial";

    public TasksView(TasksViewModel tasksViewModel) {
        this.tasksViewModel = tasksViewModel;
        tasksViewModel.addPropertyChangeListener(this);

        TasksState tasksState = this.tasksViewModel.getState();
        this.title = new JLabel(tasksState.getTitle());

        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.title.setFont(new Font(ARIAL, Font.BOLD, SIZE));

        contentPanel.add(title);

        this.scrollPane = new JScrollPane(contentPanel);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());
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
        buttons = getTasks(tasksViewModel);
        contentPanel.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }
}
