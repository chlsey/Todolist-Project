package org.todo;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String DATABASE_URL = "jdbc:sqlite:todo.db";

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }

    public void insertTask(String taskName, int priority, boolean completed, int todoListId) {
        String sql = "INSERT INTO Task(taskName, priority, completed, todoListId) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, taskName);
            pstmt.setInt(2, priority);
            pstmt.setBoolean(3, completed);
            pstmt.setInt(4, todoListId);

            pstmt.executeUpdate(); // Execute the insert statement
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public void retrieveTasks() {
        String sql = "SELECT * FROM Task";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Task Name: " + rs.getString("taskName") +
                        ", Priority: " + rs.getInt("priority") +
                        ", Completed: " + rs.getBoolean("completed"));
            }
        } catch (SQLException e) {
            System.out.println("Retrieve failed: " + e.getMessage());
        }
    }






}
