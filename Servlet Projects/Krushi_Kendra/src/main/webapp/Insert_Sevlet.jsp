<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Form</title>
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
            max-width: 500px;
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
        input[type="text"], input[type="number"], input[type="submit"], input[type="radio"] {
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
            width: auto;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        input[type="radio"] {
            width: auto;
            margin-right: 10px;
        }
        .radio-label {
            display: inline-block;
            margin-right: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Product Information Form</h2>
        <form action="Insert_Sevlet">
            
            <!-- Product Name -->
            <label for="product-name">Product Name:</label>
            <input type="text" id="product-name" name="name" required>
            
            <!-- Batch -->
            <label for="batch">Batch:</label>
            <input type="text" id="batch" name="batch" required>
            
            <!-- Price -->
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" step="0.01" required>
            
            <!-- Stock -->
            <label for="stock">Stock:</label>
            <input type="text" id="stock" name="stock" required>
            
            <!-- Type -->
            <label for="type">Type:</label><br>
            <div class="radio-label">
                <input type="radio" id="liquid" name="type" value="liquid" required>
                <label for="liquid">Liquid</label>
            </div>
            <div class="radio-label">
                <input type="radio" id="powder" name="type" value="powder" required>
                <label for="powder">Powder</label>
            </div>
            
            <!-- Submit Button -->
            <input type="submit" value="Register Product">
        </form>
    </div>
</body>
</html>
