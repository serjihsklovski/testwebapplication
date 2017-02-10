<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="../css/node_modules/summarize.css/out/summarize.css">
</head>

<body class="summarize">

<main>
  <h1>Login Page</h1>

  <form action="${pageContext.request.contextPath}/login" method="post">
    <header>Enter your Login and Password:</header>

    <%--@elvariable id="errors" type="java.util.List<java.lang.String>"--%>
    <c:if test="${errors.size() != 0}">
      <ul>
        <c:forEach var="error" items="${errors}">
          <li>${error}</li>
        </c:forEach>
      </ul>
    </c:if>

    <table>
      <tbody>

      <tr>
        <%--@elvariable id="login" type="java.lang.String"--%>
        <td><label for="login">Login:</label></td>
        <td><input type="text" name="login" id="login" required value="${login}"></td>
      </tr>

      <tr>
        <td><label for="password">Password:</label></td>
        <td><input type="password" name="password" id="password" required></td>
      </tr>

      </tbody>
    </table>

    <input type="submit" value="Login">
  </form>
</main>

</body>

</html>
