<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit To-Do</title>
</head>
<body>
    <h1>Edit To-Do</h1>
    <form action="todos?action=update" method="post">
        <input type="hidden" name="id" value="${todo.id}">
        <label for="title">Title:</label>
        <input type="text" name="title" id="title" value="${todo.title}" required><br><br>
        <label for="description">Description:</label>
        <textarea name="description" id="description" required>${todo.description}</textarea><br><br>
        <label for="dueDate">Due Date:</label>
        <input type="date" name="dueDate" id="dueDate" value="${todo.dueDate}" required><br><br>
        <label for="status">Status:</label>
        <select name="status" id="status" required>
            <option value="Pending" <c:if test="${todo.status == 'Pending'}">selected</c:if>>Pending</option>
            <option value="In Progress" <c:if test="${todo.status == 'In Progress'}">selected</c:if>>In Progress</option>
