<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
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
        h3 {
            color: red;
            text-align: center;
            margin-top: 20px;
        }
        hr {
            margin-bottom: 20px;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
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
            margin-bottom: 10px;
        }
        input[type="submit"], button {
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }
        input[type="submit"]:hover, button:hover {
            background-color: #0056b3;
        }
        .removed {
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>
    <h3>${ErrorMsg}</h3>
    <hr>
    <h1>Book List</h1>
    <table border="1">
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
                           <c:when test="${book.isAddedToCart()}">
                              <form action="RemoveFromCart" method="post">
                                   <input type="hidden" name="id" value="${book.id}">
                                   <button type="submit">Remove from cart</button>
                               </form>
                           </c:when>
                           <c:otherwise>
                               <span class="removed">Removed</span>
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
    
    <form action="ConfirmOrders">
        <input type="submit" value="Confirm Orders">
    </form>
</body>
</html>
