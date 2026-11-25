<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>CV Builder - Validation Error</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="form-page">
<div class="form-container error-box">
  <h1>Oops! Something is missing</h1>
  <p><c:out value="${errorMessage}" default="Invalid input."/></p>
  <ul>
    <c:forEach var="e" items="${cvData.errors}">
      <li><c:out value="${e}"/></li>
    </c:forEach>
  </ul>
  <a class="btn" href="cvForm.jsp">&larr; Back to Form</a>
</div>
</body>
</html>