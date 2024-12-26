package data_access;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.ToDo;
import entity.ToDoList;
import use_case.add_task.AddTaskToDoDataAccessInterface;
import use_case.create_list.CreateListToDoDataAccessInterface;
import use_case.default_lists.DefaultListsToDoDataAccessObjectInterface;
import use_case.prioritize_task.PrioritizeTaskToDoDataAccessInterface;
import use_case.select_list.SelectListToDoDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileToDoDataAccessObject implements CreateListToDoDataAccessInterface, AddTaskToDoDataAccessInterface,
        PrioritizeTaskToDoDataAccessInterface, SelectListToDoDataAccessInterface, DefaultListsToDoDataAccessObjectInterface {
    private final File jsonFile;
    private final ArrayList<ToDoList> lists = new ArrayList<>();

    public FileToDoDataAccessObject(String jsonPath) {
        jsonFile = new File(jsonPath);
        if (jsonFile.length() != 0) {
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                final JsonNode rootNode = objectMapper.readTree(jsonFile);
                for (JsonNode jsonObject : rootNode) {
                    ToDoList toDoList = new ToDoList(jsonObject.get("title").asText());
                    JsonNode normals = jsonObject.get("normal");
                    JsonNode priorities = jsonObject.get("priority");
                    for (JsonNode priority : priorities) {
                        String title = priority.get("title").asText();
                        String date = priority.get("due").asText();
                        ToDo todo = new ToDo(title, date, 1);
                        toDoList.addTask(todo);
                    }
                    for (JsonNode normal : normals) {
                        String title = normal.get("title").asText();
                        String date = normal.get("due").asText();
                        ToDo todo = new ToDo(title, date, 0);
                        toDoList.addTask(todo);
                    }
                    lists.add(toDoList);
                }
            }
            catch (IOException event) {
                event.printStackTrace();
            }
        }
    }

    public void save(ToDo todo) {

    }

    public boolean containsList(String title) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final JsonNode rootNode = objectMapper.readTree(jsonFile);
            for (JsonNode list : rootNode) {
                if (list.get("title").asText().equals(title)) {
                    return true;
                }
            }
        }
        catch (IOException event) {
            event.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean createList(String listTitle) {
        if (!containsList(listTitle)) {
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                final JsonNode rootNode = objectMapper.readTree(jsonFile);

                ObjectNode newList = objectMapper.createObjectNode();
                newList.put("title", listTitle);
                newList.putArray("priority");
                newList.putArray("normal");

                ((ObjectNode) rootNode).set(listTitle, newList);

                objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, rootNode);
            }
            catch (IOException event) {
                event.printStackTrace();
            }

            return true;
        }
        return false;

    }

    @Override
    public ArrayList<String> getAllLists() {
        ArrayList<String> lists = new ArrayList<>();
        if (jsonFile.length() != 0) {
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                final JsonNode rootNode = objectMapper.readTree(jsonFile);
                for (JsonNode jsonObject : rootNode) {
                    lists.add(jsonObject.get("title").asText());
                }
            }
            catch (IOException event) {
                event.printStackTrace();
            }
        }
        return lists;
    }

    @Override
    public ArrayList<ToDo> getToDos(String list) {
        ArrayList<ToDo> todos = new ArrayList<>();
        if (jsonFile.length() != 0) {
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                final JsonNode rootNode = objectMapper.readTree(jsonFile);
                final JsonNode priorityNode = rootNode.get(list).get("priority");
                final JsonNode normalNode = rootNode.get(list).get("normal");
                for (JsonNode priority : priorityNode) {
                    todos.add(new ToDo(priority.get("title").asText(), priority.get("due").asText(), 1));
                }
                for (JsonNode normal : normalNode) {
                    todos.add(new ToDo(normal.get("title").asText(), normal.get("due").asText(), 0));
                }
            }
            catch (IOException event) {
                event.printStackTrace();
            }
        }

        return todos;
    }
}
