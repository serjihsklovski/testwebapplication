<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--@elvariable id="loggedIn" type="boolean"--%>
<%--@elvariable id="user" type="database.model.user.User"--%>

<html>

<head>
  <meta charset="utf-8">
  <title>Home</title>
  <link rel="stylesheet" href="../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<header>
  <ul>
    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/profile/list">Members</a></li>

    <c:choose>
      <c:when test="${loggedIn}">
        <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
      </c:when>

      <c:otherwise>
        <li><a href="${pageContext.request.contextPath}/sign-up">Sign Up</a></li>
        <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
      </c:otherwise>
    </c:choose>
  </ul>
</header>

<main>
  <h1>Home Page</h1>
  <p>Hello, ${loggedIn ? user.login : "Guest"}!</p>
</main>

</body>

</html>
