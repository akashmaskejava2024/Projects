<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Professor</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
</head>
<body>
    <h2>Add New Professor</h2>
    <form action="<c:url value='/professor/save' />" method="post">
        <label>Name:</label>
        <input type="text" name="name" required /><br>
        
        <label>Email:</label>
        <input type="email" name="email" required /><br>
        
        <label>Password:</label>
        <input type="password" name="password" required /><br>

        <label>Department:</label>
        <input type="text" name="department" required /><br>

        <input type="submit" value="Save">
        <a href="<c:url value='/professor/list' />">Cancel</a>
    </form>
</body>
</html>