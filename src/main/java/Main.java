import controller.TodoController;
import model.TodoList;
import view.TodoView;

public class Main {
    public static void main(String[] args) {
        TodoList todolist = new TodoList();
        TodoView view = new TodoView();
        TodoController controller = new TodoController(todolist, view);
        controller.run();
    }
}
