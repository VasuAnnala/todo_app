<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New To-Do</title>
</head>
<body>
    <h1>Add New To-Do</h1>
    <form action="todos?action=insert" method="post">
        <label for="title">Title:</label>
        <input type="text" name="title" id="title" required><br><br>
        <label for="description">Description:</label>
        <textarea name="description" id="description" required></textarea><br><br>
        <label for="dueDate">Due Date:</label>
        <input type="date" name="dueDate" id="dueDate" required><br><br>
        <label for="status">Status:</label>
        <select name="status" id="status" required>
            <option value="Pending">Pending</option>
            <option value="In Progress">In Progress</option>
            <option value="Completed">Completed</option>
        </select><br><br>
        <input type="submit" value="Add To-Do">
    </form>
    <br>
    <a href="todos?action=list">Back to To-Do List</a>
</body>
</html>
