<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Book List</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 20px;
    }
    h1 {
        text-align: center;
        color: #333;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px 0;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f4f4f4;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    form {
        display: inline-block;
    }
    input[type="submit"], button {
        padding: 10px 20px;
        margin: 10px;
        border: none;
        border-radius: 4px;
        background-color: #007bff;
        color: white;
        cursor: pointer;
        font-size: 16px;
    }
    input[type="submit"]:hover, button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <h1>Book List</h1>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Cart</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${booklist}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td>${book.stock}</td>
                    <td>
                        <c:choose>
                            <c:when test="${book != null and book.isAddedToCart()}">
                                Added to Cart
                            </c:when>
                            <c:otherwise>
                                <form action="AddToCart" method="post">
                                    <input type="hidden" name="id" value="${book.id}">
                                    <button type="submit">Add to Cart</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <form action="Logout">
        <input type="submit" value="Logout">
    </form>
    
    <form action="Orders">
        <input type="submit" value="My Orders">
    </form>
</body>
</html>
