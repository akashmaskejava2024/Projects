<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Book Stock</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 20px;
    }
    form {
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

<form action="RemoveBooks">

<label for="id">Book ID:</label>
<input type="text" name="id" id="id" required="required">

<label for="stock">New Stock:</label>
<input type="text" name="stock" id="stock">

<input type="submit" value="Update Books"> 

</form>

</body>
</html>
