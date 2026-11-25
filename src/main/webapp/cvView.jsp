<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Safety: if opened directly without data, go to the form --%>
<c:if test="${empty cvData}">
  <c:redirect url="cvForm.jsp"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><c:out value="${cvData.fullName}"/> - CV</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="view-page">

<div class="toolbar no-print">
  <a class="btn btn-secondary" href="cvForm.jsp">&larr; Back to Form</a>
  <button class="btn" onclick="window.print()">Print / Save as PDF</button>
  <c:if test="${isSample}"><span class="badge">Sample Data</span></c:if>
</div>

<div class="cv">
  
  <header class="cv-header">
    <div class="header-text">
      <h1><c:out value="${cvData.fullName}"/></h1>
      <c:if test="${not empty cvData.professionalTitle}">
        <h2><c:out value="${cvData.professionalTitle}"/></h2>
      </c:if>
      <p class="contact">
        <c:out value="${cvData.email}"/>
        <c:if test="${not empty cvData.phone}"> | <c:out value="${cvData.phone}"/></c:if>
        <c:if test="${not empty cvData.profileUrl}"> | <c:out value="${cvData.profileUrl}"/></c:if>
      </p>
    </div>
    <c:if test="${not empty cvData.photo}">
      <img class="cv-photo" src="<c:out value='${cvData.photo}'/>" alt="Profile photo">
    </c:if>
  </header>

 
  <c:if test="${not empty cvData.profileSummary}">
    <section>
      <h3>Profile</h3>
      <p><c:out value="${cvData.profileSummary}"/></p>
    </section>
  </c:if>

 
  <section>
    <h3>Education</h3>
    <div class="item">
      <div class="row">
        <strong><c:out value="${cvData.degree}"/></strong>
        <span><c:out value="${cvData.graduationYear}"/></span>
      </div>
      <c:if test="${not empty cvData.institution}"><div><c:out value="${cvData.institution}"/></div></c:if>
      <c:if test="${not empty cvData.major}"><div class="muted">Major: <c:out value="${cvData.major}"/></div></c:if>
    </div>
  </section>

  
  <c:if test="${not empty cvData.jobTitle or not empty cvData.company}">
    <section>
      <h3>Experience</h3>
      <div class="item">
        <div class="row">
          <strong><c:out value="${cvData.jobTitle}"/></strong>
          <span><c:out value="${cvData.duration}"/></span>
        </div>
        <div class="muted"><c:out value="${cvData.company}"/></div>
        <ul>
          <c:forEach var="r" items="${cvData.responsibilitiesList}">
            <li><c:out value="${r}"/></li>
          </c:forEach>
        </ul>
      </div>
    </section>
  </c:if>

 
  <section>
    <h3>Skills</h3>
    <c:if test="${not empty cvData.technicalSkillsList}">
      <p><strong>Technical:</strong></p>
      <div class="tags">
        <c:forEach var="s" items="${cvData.technicalSkillsList}"><span class="tag"><c:out value="${s}"/></span></c:forEach>
      </div>
    </c:if>
    <c:if test="${not empty cvData.softSkillsList}">
      <p><strong>Soft:</strong></p>
      <div class="tags">
        <c:forEach var="s" items="${cvData.softSkillsList}"><span class="tag"><c:out value="${s}"/></span></c:forEach>
      </div>
    </c:if>
  </section>
</div>
</body>
</html>