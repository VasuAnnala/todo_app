package com.example.controller;

import com.example.dao.TodoDAO;
import com.example.model.TodoItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/todos")
public class TodoController extends HttpServlet {
    private TodoDAO todoDAO;

    @Override
    public void init() {
        todoDAO = new TodoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteTodoItem(request, response);
                    break;
                default:
                    listTodoItems(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertTodoItem(request, response);
                    break;
                case "update":
                    updateTodoItem(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTodoItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<TodoItem> todoItems = todoDAO.getAllTodos();
        request.setAttribute("todoItems", todoItems);
        request.getRequestDispatcher("/views/listTodos.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/addTodo.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TodoItem existingTodo = todoDAO.getAllTodos().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        request.setAttribute("todo", existingTodo);
        request.getRequestDispatcher("/views/editTodo.jsp").forward(request, response);
    }

    private void insertTodoItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDate = request.getParameter("dueDate");
        String status = request.getParameter("status");

        TodoItem newTodo = new TodoItem();
        newTodo.setTitle(title);
        newTodo.setDescription(description);
        newTodo.setDueDate(dueDate);
        newTodo.setStatus(status);

        todoDAO.addTodoItem(newTodo);
        response.sendRedirect("todos?action=list");
    }

    private void updateTodoItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDate = request.getParameter("dueDate");
        String status = request.getParameter("status");

        TodoItem todo = new TodoItem();
        todo.setId(id);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDueDate(dueDate);
        todo.setStatus(status);

        todoDAO.updateTodoItem(todo);
        response.sendRedirect("todos?action=list");
    }

    private void deleteTodoItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoDAO.deleteTodoItem(id);
        response.sendRedirect("todos?action=list");
    }
}
