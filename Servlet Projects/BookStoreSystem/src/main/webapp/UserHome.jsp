<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success Message</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 20px;
    }
    h3 {
        color: green;
    }
    form {
        margin-top: 20px;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
        border-radius: 3px;
        margin-right: 10px;
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

<h3>${SuccessMsg}</h3>

<form action="DisplayAllBooksList">
    <input type="submit" value="Display All Books"> 
</form>

<form action="ConfirmOrders">
    <input type="submit" value="My Orders"> 
</form>

<form action="Logout">
    <input type="submit" value="Logout"> 
</form>

</body>
</html>
