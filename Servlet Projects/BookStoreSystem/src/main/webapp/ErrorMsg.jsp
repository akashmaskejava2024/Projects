<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        h3 {
            color: red;
            text-align: center;
            margin-top: 20px;
        }
        form {
            text-align: center;
            margin-top: 20px;
        }
        input[type="submit"] {
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h3>${ErrorMsg}</h3>
    
    <form action="DisplayAllBooksList">
        <input type="submit" value="Display All Books again">
    </form>
</body>
</html>
