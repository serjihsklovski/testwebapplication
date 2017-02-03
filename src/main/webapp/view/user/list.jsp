<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <title>User List</title>
  <link rel="stylesheet" href="../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<main>

  <table>
    <thead>

    <tr>
      <th>Id</th>
      <th>Login</th>
      <th>Email</th>
      <th>Password</th>
    </tr>

    </thead>

    <tbody>

    <%--@elvariable id="users" type="java.util.List<database.dataset.user.User>"--%>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.email}</td>
        <td>${user.password}</td>
      </tr>
    </c:forEach>

    </tbody>

  </table>

</main>

</body>

</html>
