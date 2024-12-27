package view;

import entity.ToDo;
import interface_adapter.TasksState;
import interface_adapter.TasksViewModel;
import interface_adapter.add_task.AddTaskController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TasksView extends JPanel implements PropertyChangeListener {
    final private String viewName = "tasks view";
    final private TasksViewModel tasksViewModel;
    final private AddTaskController addTaskController;

    private JLabel title;
    private JPanel buttons = new JPanel();
    private final JPanel contentPanel = new JPanel();
    private final JScrollPane scrollPane;
    private final JButton backBut;
    private JPanel newTaskPanel = new JPanel();


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

        contentPanel.add(buttons);
        contentPanel.add(newTaskPanel);

        this.scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());
        this.add(backPan, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel getTasks(TasksViewModel tasksViewModel) {
        TasksState tasksState = tasksViewModel.getState();
        ArrayList<ToDo> priority = tasksState.getPriorityTodos();
        ArrayList<ToDo> normal = tasksState.getNormalTodos();
        JPanel buttons = new JPanel();

        for (ToDo toDo : priority) {
            JCheckBox task = new JCheckBox(toDo.getDescription());
            task.setFont(new Font(ARIAL, Font.BOLD, SMALL));
            task.setMnemonic(KeyEvent.VK_C);
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
            buttons.add(task);
        }

        for (ToDo toDo : normal) {
            JCheckBox task = new JCheckBox(toDo.getDescription());
            task.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
            task.setMnemonic(KeyEvent.VK_C);
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
            buttons.add(task);
        }

        return buttons;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.buttons.removeAll();
        if (evt.getPropertyName().equals("state")) {
            this.newTaskPanel.removeAll();
            this.title = new JLabel(tasksViewModel.getState().getTitle(), JLabel.CENTER); // Set alignment to CENTER
            this.title.setFont(new Font(ARIAL, Font.BOLD, SIZE));

            // centering title and tasks
            getTitleWrapper();
            buttons.add(getTasks(tasksViewModel));

            // new task section:
            newTaskPanel.setLayout(new BoxLayout(newTaskPanel, BoxLayout.Y_AXIS));

            // task name
            JPanel namePanel = new JPanel();
            final JLabel nameLabel = new JLabel("Task Name:");
            nameLabel.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
            JTextField nameInput = new JTextField(SMALL);
            nameInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameInput.getPreferredSize().height));
            namePanel.add(nameLabel);
            namePanel.add(nameInput);

            // date selection
            JPanel datePanel = new JPanel();
            final JLabel dateLabel = new JLabel("due date:");
            dateLabel.setFont(new Font(ARIAL, Font.PLAIN, SMALL));

            SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
            JSpinner dateSpinner = new JSpinner(dateModel);
            JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy/MM/dd/HH:mm");
            dateSpinner.setEditor(dateEditor);
            dateSpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, dateSpinner.getPreferredSize().height));

            datePanel.add(dateLabel);
            datePanel.add(dateSpinner);

            // check priority
            final JCheckBox priorityCheckBox = new JCheckBox("is it a priority?");
            priorityCheckBox.setFont(new Font(ARIAL, Font.PLAIN, SMALL));
            priorityCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

            // submit but
            JButton submitButton = new JButton("✔");
            submitButton.addActionListener(
                    event -> {
                        String taskName = nameInput.getText();
                        Date dueDate = (Date) dateSpinner.getValue();
                        boolean isPriority = priorityCheckBox.isSelected();

                        if (!taskName.isEmpty()) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                            String formattedDate = dateFormat.format(dueDate);
                            this.addTaskController.execute(isPriority, taskName, formattedDate, tasksViewModel.getState().getTitle());
                        } else {
                            JOptionPane.showMessageDialog(newTaskPanel, "need task name", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
            );
            submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel smallPanel = new JPanel();
            smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
            smallPanel.add(priorityCheckBox);
            smallPanel.add(submitButton);

            newTaskPanel.add(namePanel);
            newTaskPanel.add(datePanel);
            newTaskPanel.add(smallPanel);

            contentPanel.revalidate();
            contentPanel.repaint();
        } else {
            getTitleWrapper();
            buttons.add(getTasks(tasksViewModel));
        }
    }

    private void getTitleWrapper() {
        JPanel titleWrapper = new JPanel();
        titleWrapper.setLayout(new BoxLayout(titleWrapper, BoxLayout.X_AXIS));
        titleWrapper.add(Box.createHorizontalGlue());
        titleWrapper.add(this.title);
        titleWrapper.add(Box.createHorizontalGlue());
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(titleWrapper);
    }

    public void updateSelectedTasks() {

    }

    public String getViewName() {
        return viewName;
    }
}
