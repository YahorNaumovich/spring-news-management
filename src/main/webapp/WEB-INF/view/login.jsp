<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login - News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/login.css'/>" />
  </head>
  <body>
    <jsp:include page="/WEB-INF/view/header.jsp" />
    <main class="login-container">
      <form:form modelAttribute="loginForm" class="login-form" action="authenticate" method="post">
        <h2>Login</h2>
        <c:if test="${not empty error}">
          <div class="error">${error}</div>
        </c:if>
        <div class="form-group">
          <form:label path="username">Username</form:label>
          <form:input path="username" id="username" required="true" />
          <form:errors path="username" cssClass="error" />
        </div>
        <div class="form-group">
          <form:label path="password">Password</form:label>
          <form:password path="password" id="password" required="true" />
          <form:errors path="password" cssClass="error" />
        </div>
        <button type="submit" class="login-button">Log In</button>
      </form:form>
    </main>
  </body>
</html>
