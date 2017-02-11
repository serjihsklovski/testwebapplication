<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <meta charset="UTF-8">
  <title>Unauthorized</title>
  <link rel="stylesheet" href="../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<main>
  <h1>409 Unauthorized</h1>
  <p>You do not have access to this page! Ask the admin to give you access.</p>

  <ul>
    <li><a class="button" href="${pageContext.request.contextPath}/home">Home Page</a></li>
    <li><a class="button" href="${pageContext.request.contextPath}/logout">Logout</a></li>
  </ul>
</main>

</body>

</html>
