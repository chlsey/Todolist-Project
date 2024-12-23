package app;

import data_access.FileToDoDataAccessObject;
import interface_adapter.DefaultListsViewModel;
import interface_adapter.TasksState;
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
        // Create the main JFrame
        final JFrame frame = new JFrame("ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        final JPanel views = new JPanel(cardLayout);
        frame.add(views);

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        final DefaultListsViewModel defaultListsViewModel = new DefaultListsViewModel();
        final TasksState tasksState = new TasksState();

        final FileToDoDataAccessObject fileToDoDataAccessObject = new FileToDoDataAccessObject("src/todos.json");

        // Views
        final DefaultListsView defaultListsView = new DefaultListsView(defaultListsViewModel,
                UseCaseFactory.createCreateListUseCase(viewManagerModel, defaultListsViewModel, fileToDoDataAccessObject),
                UseCaseFactory.createDefaultListsUseCase(viewManagerModel, defaultListsViewModel, fileToDoDataAccessObject));

        final TasksView tasksView = new TasksView();

        views.add(defaultListsView, defaultListsView.getViewName());

        views.add(tasksView, tasksView.getViewName());

    }
}
