<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Assign Course to Student</h2>

<form action="${pageContext.request.contextPath}/professor/assign-course" method="post">
    <label>Select Student:</label>
    <select name="studentId">
        <c:forEach var="student" items="${students}">
            <option value="${student.id}">${student.name}</option>
        </c:forEach>
    </select>

    <label>Select Course:</label>
    <select name="courseId">
        <c:forEach var="course" items="${courses}">
            <option value="${course.id}">${course.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Assign Course</button>
</form>

<c:if test="${param.success == 'courseAssigned'}">
    <p style="color: green;">Course assigned successfully!</p>
</c:if>

<a href="${pageContext.request.contextPath}/professor/dashboard">Back to Dashboard</a>