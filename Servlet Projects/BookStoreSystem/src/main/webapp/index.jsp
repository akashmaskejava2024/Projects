<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome to Bookstore</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Light gray background */
            margin: 0;
            padding: 20px;
            text-align: center;
        }
        h1 {
            color: #28a745; /* Green color for heading */
            margin-bottom: 20px;
        }
        form {
            display: inline-block;
            margin-bottom: 10px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            background-color: #007bff; /* Blue button background */
            color: white;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
</head>
<body>
    <h1>Welcome to the Bookstore</h1>

    <form action="Login.jsp">
        <input type="submit" value="Login"> 
    </form>

    <form action="Register.jsp">
        <input type="submit" value="New User"> 
    </form>

    <form action="AdminLogin.jsp">
        <input type="submit" value="Admin Login"> 
    </form>

</body>
</html>
