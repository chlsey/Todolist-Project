package app;

import data_access.FileToDoDataAccessObject;
import interface_adapter.DefaultListsViewModel;
import interface_adapter.TasksState;
import interface_adapter.TasksViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_list.CreateListController;
import use_case.create_list.CreateListInteractor;
import view.DefaultListsView;
import view.TasksView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        final int width = 1024;
        final int height = 768;

        // main frame
        final JFrame frame = new JFrame("ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        final JPanel views = new JPanel(cardLayout);
        frame.add(views);

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // viewmodels
        final DefaultListsViewModel defaultListsViewModel = new DefaultListsViewModel();
        final TasksViewModel tasksViewModel = new TasksViewModel();

        final FileToDoDataAccessObject fileToDoDataAccessObject = new FileToDoDataAccessObject("src/todos.json");

        // views
        final DefaultListsView defaultListsView = new DefaultListsView(defaultListsViewModel,
                UseCaseFactory.createCreateListUseCase(viewManagerModel, defaultListsViewModel, fileToDoDataAccessObject),
                UseCaseFactory.createDefaultListsUseCase(viewManagerModel, defaultListsViewModel, fileToDoDataAccessObject, tasksViewModel));

        final TasksView tasksView = new TasksView(tasksViewModel, UseCaseFactory.createAddTaskUseCase(viewManagerModel, defaultListsViewModel, tasksViewModel, fileToDoDataAccessObject));

        views.add(defaultListsView, defaultListsViewModel.getViewName());

        views.add(tasksView, tasksViewModel.getViewName());

        // final steps
        viewManagerModel.setState(defaultListsView.getViewName());
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
