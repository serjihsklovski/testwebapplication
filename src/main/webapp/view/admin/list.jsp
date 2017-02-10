<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <meta charset="utf-8">
  <title>User List</title>
  <link rel="stylesheet" href="../../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<main>
  <h1>User List</h1>

  <table>
    <thead>

    <tr>
      <th>Id</th>
      <th>Login</th>
      <th>Email</th>
      <th>Password</th>
      <th>Role</th>
      <th colspan="2">Action</th>
    </tr>

    </thead>

    <tbody>

    <%--@elvariable id="users" type="java.util.List<database.model.user.User>"--%>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.email}</td>
        <td>${user.password}</td>
        <td>${user.role}</td>
        <td><a class="button good" href="${pageContext.request.contextPath}/admin/edit?id=${user.id}">Edit</a></td>
        <td><a class="button bad" href="${pageContext.request.contextPath}/admin/delete?id=${user.id}">Delete</a></td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

  <a class="button" href="${pageContext.request.contextPath}/admin/add">Add new user</a>
</main>

</body>

</html>
