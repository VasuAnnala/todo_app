<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
</head>
<body>
    <h1>To-Do List</h1>
    <a href="todos?action=new">Add New To-Do</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="todo" items="${todoItems}">
            <tr>
                <td>${todo.id}</td>
                <td>${todo.title}</td>
                <td>${todo.description}</td>
                <td>${todo.dueDate}</td>
                <td>${todo.status}</td>
                <td>
                    <a href="todos?action=edit&id=${todo.id}">Edit</a>
                    <a href="todos?action=delete&id=${todo.id}">Delete</a>
                </td>
            </tr>
        </