<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        background: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    h1 {
        text-align: center;
        color: #333;
    }
    form {
        display: flex;
        flex-direction: column;
        margin: 10px 0;
    }
    input[type="submit"] {
        padding: 10px 20px;
        margin: 5px 0;
        border: none;
        border-radius: 5px;
        background-color: #4CAF50;
        color: white;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>

<div class="container">
    <h1>Product Management</h1>
    <form action="Insert_Sevlet.jsp">
        <input type="submit" value="Register Product">
    </form>

    <form action="AddSale_Servlet.jsp">
        <input type="submit" value="Add Sale">
    </form>

    <form action="ReturnProduct_Servlet.jsp">
        <input type="submit" value="Return Product">
    </form>

    <form action="Check_Stock.jsp">
        <input type="submit" value="Check Stock">
    </form>
</div>

</body>
</html>
