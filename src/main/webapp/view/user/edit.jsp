<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <title>Edit User</title>
  <link rel="stylesheet" href="../../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<main>
  <h1>Edit User</h1>

  <%--@elvariable id="user" type="database.model.user.User"--%>

  <form action="/user/edit" method="post">
    <table>
      <caption>Editing of ${user.login}:</caption>

      <tbody>

      <tr>
        <td><label for="login">Login:</label></td>
        <td><input type="text" name="login" id="login" required value="${user.login}"></td>
      </tr>

      <tr>
        <td><label for="email">Email:</label></td>
        <td><input type="email" name="email" id="email" required value="${user.email}"></td>
      </tr>

      <tr>
        <td><label for="password">Password:</label></td>
        <td><input type="password" name="password" id="password" required></td>
      </tr>

      <tr>
        <td><label for="is-admin">Is Admin:</label></td>
        <td><input type="checkbox" name="is-admin" id="is-admin" ${user.role.equals("admin") ? "cheched=\"on\"" : ""}></td>
      </tr>

      </tbody>
    </table>

    <input type="hidden" name="id" value="${user.id}">
    <input type="submit" value="Ok">
  </form>

  <a class="button" href="/user/list">Back</a>
</main>

</body>

</html>
