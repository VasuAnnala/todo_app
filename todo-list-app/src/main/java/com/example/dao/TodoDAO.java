package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.model.TodoItem;

public class TodoDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/todo_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public List<TodoItem> getAllTodos() throws SQLException {
        List<TodoItem> todos = new ArrayList<>();
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        String sql = "SELECT * FROM todo_items";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            TodoItem todo = new TodoItem();
            todo.setId(rs.getInt("id"));
            todo.setTitle(rs.getString("title"));
            todo.setDescription(rs.getString("description"));
            todo.setDueDate(rs.getString("due_date"));
            todo.setStatus(rs.getString("status"));
            todos.add(todo);
        }

        rs.close();
        stmt.close();
        conn.close();

        return todos;
    }

    public void addTodoItem(TodoItem todo) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        String sql = "INSERT INTO todo_items (title, description, due_date, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, todo.getTitle());
        stmt.setString(2, todo.getDescription());
        stmt.setString(3, todo.getDueDate());
        stmt.setString(4, todo.getStatus());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void updateTodoItem(TodoItem todo) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        String sql = "UPDATE todo_items SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, todo.getTitle());
        stmt.setString(2, todo.getDescription());
        stmt.setString(3, todo.getDueDate());
        stmt.setString(4, todo.getStatus());
        stmt.setInt(5, todo.getId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void deleteTodoItem(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        String sql = "DELETE FROM todo_items WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
}
