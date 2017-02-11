<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.AccountService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--@elvariable id="users" type="java.util.List<database.model.user.User>"--%>
<%--@elvariable id="loggedIn" type="boolean"--%>

<html>

<head>
  <meta charset="UTF-8">
  <title>Members</title>
  <link rel="stylesheet" href="../../css/node_modules/summarize.css/out/summarize.css">

  <style>
    main {
      max-width: 800px;
      margin: auto;
    }

    .card {
      background-color: #e7e7e7;
      max-width: 400px;
      padding: 12px;
      margin-bottom: 24px;
      box-shadow: 0 0 16px rgba(0, 0, 0, 0.7);
    }

    .avatar, .information {
      display: inline-block;
    }

    .information {
      position: absolute;
      margin-left: 12px;
    }

    .login {
      font-size: 1.5em;
    }
  </style>
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
  <h1>Our Members</h1>

  <div class="cards">
    <c:forEach var="user" items="${users}">
      <div class="card">
        <div class="avatar">
          <img width="128" src="../../img/person-boy-flat.png" alt="person-boy-flat.png">
        </div>

        <div class="information">
          <span class="login"><a href="${pageContext.request.contextPath}/profile?id=${user.id}">${user.login}</a></span><br>
          <span class="email">${user.email}</span><br>
          <span class="role">${user.role}</span><br>
        </div>
      </div>
    </c:forEach>
  </div>
</main>

</body>

</html>
