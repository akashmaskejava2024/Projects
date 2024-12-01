<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Actions</title>
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
    .form-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 15px;
    }
    form {
        display: flex;
        flex-direction: column;
        width: 100%;
    }
    input[type="submit"] {
        padding: 10px;
        border: none;
        border-radius: 4px;
        background-color: #007bff;
        color: white;
        cursor: pointer;
        font-size: 16px;
        margin: 5px 0;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>

<div class="form-container">
    <form action="RegisterBooks.jsp">
        <input type="submit" value="New Book"> 
    </form>

    <form action="RemoveBook.jsp">
        <input type="submit" value="Update Book Stock"> 
    </form>

    <form action="AdminLogout">
        <input type="submit" value="Logout"> 
    </form>
</div>

</body>
</html>
