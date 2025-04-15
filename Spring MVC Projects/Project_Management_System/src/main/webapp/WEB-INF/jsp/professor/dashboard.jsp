<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Professor Dashboard</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        /* Navigation Bar */
        .navbar {
            background-color: #007bff;
            padding: 15px;
            text-align: right;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 16px;
        }

        .navbar a:hover {
            text-decoration: underline;
        }

        /* Container */
        .container {
            max-width: 90%;
            margin: 30px auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        /* Table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
        }

        table, th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        /* Buttons */
        button {
            padding: 8px 12px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #0056b3;
        }

        .logout-btn {
            background-color: #dc3545;
            padding: 8px 12px;
            text-decoration: none;
            border-radius: 5px;
        }

        .logout-btn:hover {
            background-color: #c82333;
        }

        /* Feedback Form */
        .feedback-form {
            display: flex;
            align-items: center;
        }

        input[type="text"] {
            padding: 5px;
            width: 80%;
        }
    </style>
</head>
<body>

    <!-- Navigation Bar -->
    <div class="navbar">
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/professor/logout" class="logout-btn">Logout</a>
    </div>

    <!-- Dashboard Content -->
    <div class="container">
        <h2>Professor Dashboard</h2>

        <table>
            <thead>
                <tr>
                    <th>Project ID</th>
                    <th>Student ID</th>
                    <th>Phase</th>
                    <th>Update Description</th>
                    <th>UI Image</th>
                    <th>Current Feedback</th>
                    <th>Provide Feedback</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="projectStatus" items="${projectStatuses}">
                    <tr>
                        <td>${projectStatus.projectId}</td>
                        <td>${projectStatus.studentId}</td>
                        <td>${projectStatus.phase}</td>
                        <td>${projectStatus.updateDescription}</td>
                        <td>
                            <c:if test="${not empty projectStatus.uiImage}">
                                <img src="${pageContext.request.contextPath}${projectStatus.uiImage}" alt="Project Image" width="100">
                            </c:if>
                        </td>
                        <td>${projectStatus.feedback}</td>
                        <td>
                            <form class="feedback-form" action="${pageContext.request.contextPath}/professor/addFeedback" method="post">
                                <input type="hidden" name="projectStatusId" value="${projectStatus.id}">
                                <input type="text" name="feedback" placeholder="Enter feedback" required>
                                <button type="submit">Submit</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>