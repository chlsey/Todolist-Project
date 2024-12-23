package interface_adapter;

public class TasksViewModel extends ViewModel<TasksState> {
    public TasksViewModel() {
        super("tasks view");
        setState(new TasksState());
    }
}
