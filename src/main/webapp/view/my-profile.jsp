<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--@elvariable id="loggedIn" type="boolean"--%>
<%--@elvariable id="user" type="database.model.user.User"--%>

<html>

<head>
  <meta charset="utf-8">
  <title>${user.login}</title>
  <link rel="stylesheet" href="../css/node_modules/summarize.css/out/summarize.css">

  <style>
    main {
      max-width: 800px;
      margin: auto;
    }

    .profile {
      background-color: #e7e7e7;
      max-width: 800px;
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

    <c:if test="${user.role.equals('admin')}">
      <li><a href="${pageContext.request.contextPath}/admin">Admin Panel</a></li>
    </c:if>
  </ul>
</header>

<main>
  <h1>My Profile Page</h1>

  <div class="profile">
    <div class="avatar">
      <img width="256" src="../img/person-boy-flat.png" alt="person-boy-flat.png">
    </div>

    <div class="information">
      <span class="login">I'm ${user.login}.</span>
      <p>My email is ${user.email}.</p>
      <p>My password is ${user.password}.</p>
    </div>
  </div>
</main>

</body>

</html>
