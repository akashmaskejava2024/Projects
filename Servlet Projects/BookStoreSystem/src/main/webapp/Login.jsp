<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 20px;
    }
    h3 {
        color: red;
    }
    form {
        margin-top: 20px;
        width: 300px;
        background-color: #fff;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    label {
        display: block;
        margin-bottom: 10px;
    }
    input[type="text"], input[type="password"] {
        width: calc(100% - 20px);
        padding: 8px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
        border-radius: 3px;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
    input[type="submit"]:focus {
        outline: none;
    }
</style>
</head>
<body>

<h3>${ErrorMsg}</h3>

<form action="Login">

<label for="user">UserName :</label>
<input type="text" name="user" id="user" required="required">

<label for="pass">Password :</label>
<input type="password" name="pass" id="pass" required="required">

<input type="submit" value="Login"> 
</form>

<form action="index.jsp">

<input type="submit" value="Back to previous view"> 
</form>
</body>
</html>
