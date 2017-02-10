<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <meta charset="utf-8">
  <title>Home</title>
  <link rel="stylesheet" href="../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<main>
  <h1>Home Page</h1>

  <%--@elvariable id="user" type="database.model.user.User"--%>
  <h2>Hi, ${user.login}!</h2>

  <c:if test="${user.role.equals('admin')}">
    <a href="${pageContext.request.contextPath}/admin">Admin Panel</a>
  </c:if>
</main>

</body>

</html>
