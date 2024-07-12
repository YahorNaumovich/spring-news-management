<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up - News Portal</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />

    <link rel="stylesheet" type="text/css"
    	href="<c:url value="/resources/css/signup.css"/>" />
  </head>
  <body>
      <jsp:include page="/WEB-INF/view/header.jsp" />
    <main class="signup-container">
      <form class="signup-form">
        <h2>Sign Up</h2>
        <div class="form-group">
          <label for="username">Username</label>
          <input type="text" id="username" name="username" required />
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" name="email" required />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" id="password" name="password" required />
        </div>
        <div class="form-group">
          <label for="repeat-password">Repeat Password</label>
          <input
            type="password"
            id="repeat-password"
            name="repeat-password"
            required
          />
        </div>
        <button type="submit" class="signup-button">Sign Up</button>
      </form>
    </main>
  </body>
</html>
