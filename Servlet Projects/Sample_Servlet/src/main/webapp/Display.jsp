
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Table</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #ddd;
        }
        .center {
            text-align: center;
        }
    </style>
</head>
<body>

<h2>Employee Table</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Salary</th>
    </tr>
    <c:forEach items="${emplist}" var="e">
    
    <tr>
    <td>${e.id}</td>
    <td>${e.name}</td>
    <td>${e.sal}</td>
    </tr>
    
    
    </c:forEach>
    
    
    <!-- Add more rows as needed -->
</table>

</body>
</html>
