<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .login-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h3 {
        color: red;
        margin-bottom: 15px;
    }
    form {
        display: flex;
        flex-direction: column;
    }
    label {
        margin-bottom: 5px;
        font-weight: bold;
    }
    input[type="text"], input[type="password"] {
        margin-bottom: 15px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    input[type="submit"] {
        padding: 10px;
        border: none;
        border-radius: 4px;
        background-color: #007bff;
        color: white;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>

<div class="login-container">
    <h3>${ErrorMsg}</h3>
    <form action="AdminLogin">
        <label for="user">UserName :</label>
        <input type="text" name="user" id="user" required="required">
        <label for="pass">Password :</label>
        <input type="password" name="pass" id="pass" required="required">
        <input type="submit" value="Login"> 
    </form>
</div>

</body>
</html>
