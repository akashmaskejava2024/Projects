<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stock Checker</title>
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
        max-width: 600px;
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
        margin-bottom: 20px;
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
    hr {
        border: 1px solid #ddd;
        margin: 20px 0;
        width: 100%;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Stock Checker</h2>
        
        <form action="check_lessthan_stock">
            <input type="text" name="checker" placeholder="Enter max stock" required>
            <input type="submit" value="Check lessthan Stock">
        </form>

        <form action="check_stock_with_lessthan_price">
            <input type="text" name="checker" placeholder="Enter max Price" required>
            <input type="submit" value="Check stock with lessthan price">
        </form>

        <form action="check_stock_with_greaterthan_price">
            <input type="text" name="checker" placeholder="Enter min Price" required>
            <input type="submit" value="Check stock with greaterthan price">
        </form>

        <form action="check_stock_with_type">
            <input type="text" name="checker" placeholder="Enter type (powder or liquid)" required>
            <input type="submit" value="Check stock with type">
        </form>

        <form action="check_prod_with_letter">
            <input type="text" name="checker" placeholder="Enter First few Letters" required>
            <input type="submit" value="Check product with letter">
        </form>
        
        <hr>

        <form action="index.jsp">
            <input type="submit" value="Back to main menu">
        </form>
    </div>
</body>
</html>
