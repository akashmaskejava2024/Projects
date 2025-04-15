<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Project Status</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Container */
        .container {
            max-width: 450px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #007bff;
            margin-bottom: 20px;
        }

        /* Form Styles */
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        select, input, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* Button */
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* UI Image */
        .ui-image {
            margin-top: 10px;
            max-width: 100%;
            border-radius: 5px;
            box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Project Status</h2>

        <form action="${pageContext.request.contextPath}/student/updateProjectStatus" method="post" enctype="multipart/form-data">
            <input type="hidden" name="projectId" value="${projectId}" />

            <div class="form-group">
                <label>Phase:</label>
                <select name="phase">
                    <option value="Requirement Gathering" ${projectStatus.phase == 'Requirement Gathering' ? 'selected' : ''}>Requirement Gathering</option>
                    <option value="Design" ${projectStatus.phase == 'Design' ? 'selected' : ''}>Design</option>
                    <option value="Development" ${projectStatus.phase == 'Development' ? 'selected' : ''}>Development</option>
                    <option value="Testing" ${projectStatus.phase == 'Testing' ? 'selected' : ''}>Testing</option>
                    <option value="Deployment" ${projectStatus.phase == 'Deployment' ? 'selected' : ''}>Deployment</option>
                </select>
            </div>

            <div class="form-group">
                <label>Upload UI Image:</label>
                <input type="file" name="uiImage" accept="image/*" />
                <%-- Display existing UI image if available --%>
                <c:if test="${not empty projectStatus.uiImage}">
                    <img src="${projectStatus.uiImage}" alt="Project UI" class="ui-image" />
                </c:if>
            </div>

            <div class="form-group">
                <label>Update Description:</label>
                <textarea name="updateDescription" required>${projectStatus.updateDescription}</textarea>
            </div>

            <div class="form-group">
                <label>Professor Feedback:</label>
                <input type="text" value="${projectStatus.feedback}" readonly />
            </div>

            <button type="submit">Update</button>
        </form>

        <p>${message}</p>
    </div>
</body>
</html>