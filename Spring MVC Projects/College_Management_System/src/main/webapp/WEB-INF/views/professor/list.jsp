<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Professor List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
</head>
<body>
    <h2>Professor List</h2>
    <a href="<c:url value='/professor/add' />">Add New Professor</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="professor" items="${professors}">
            <tr>
                <td>${professor.id}</td>
                <td>${professor.name}</td>
                <td>${professor.email}</td>
                <td>${professor.department}</td>
                <td>
                    <a href="<c:url value='/professor/edit/${professor.id}' />">Edit</a> |
                    <a href="<c:url value='/professor/delete/${professor.id}' />" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>