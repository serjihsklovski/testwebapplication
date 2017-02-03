<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <title>Add New User</title>
  <link rel="stylesheet" href="../../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<main>
  <h1>Add New User</h1>

  <form action="/user/add" method="post">
    <table>
      <caption>Fill in the application form:</caption>

      <tbody>

      <tr>
        <td><label for="login">Login:</label></td>
        <td><input type="text" name="login" id="login" required></td>
      </tr>

      <tr>
        <td><label for="email">Email:</label></td>
        <td><input type="email" name="email" id="email" required></td>
      </tr>

      <tr>
        <td><label for="password">Password:</label></td>
        <td><input type="password" name="password" id="password" required></td>
      </tr>

      </tbody>
    </table>

    <input type="submit" value="Add">
  </form>

  <a class="button" href="/user/list">Back</a>
</main>

</body>

</html>
