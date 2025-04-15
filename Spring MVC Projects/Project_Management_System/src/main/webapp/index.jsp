<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Management System</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        /* Container */
        .container {
            max-width: 450px;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #007bff;
            margin-bottom: 10px;
        }

        p {
            font-size: 16px;
            color: #555;
            margin-bottom: 20px;
        }

        /* Login Links */
        .login-links {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .login-links a {
            display: block;
            padding: 12px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }

        .login-links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to Project Management System</h2>
        <p>Select your role to log in:</p>

        <div class="login-links">
            <a href="<%= request.getContextPath() %>/student/login">Student Login</a>
            <a href="<%= request.getContextPath() %>/professor/login">Professor Login</a>
        </div>
    </div>
</body>
</html>