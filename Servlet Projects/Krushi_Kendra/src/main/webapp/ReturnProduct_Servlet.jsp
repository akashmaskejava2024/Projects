<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Return Product</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }
    .container {
        width: 80%;
        max-width: 400px;
        background: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    h2 {
        text-align: center;
        color: #333;
    }
    form {
        display: flex;
        flex-direction: column;
    }
    label {
        margin: 10px 0 5px;
        font-weight: bold;
    }
    input[type="text"] {
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        width: 100%;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
        border: none;
        border-radius: 5px;
        padding: 10px;
        width: auto;
        align-self: center;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Product Information Form</h2>
        <form action="ReturnProduct_Servlet">
            <!-- Product ID -->
            <label for="product-id">Product ID:</label>
            <input type="text" id="product-id" name="id" required>
           
            <!-- Submit Button -->
            <input type="submit" value="Return Product">
        </form>
    </div>
</body>
</html>
