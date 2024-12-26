package DataAccessObjects;

import data_access.FileToDoDataAccessObject;
import entity.ToDo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataAccessObjectTests {
    private static FileToDoDataAccessObject fileToDoDataAccessObject = new FileToDoDataAccessObject("src/todos.json");;

    @Test
    public void getTasks() {
        ArrayList<ToDo> todos = fileToDoDataAccessObject.getToDos("important");
        assertEquals(todos.get(0).getDescription(), "cook");
        System.out.println(todos);
    }
}
