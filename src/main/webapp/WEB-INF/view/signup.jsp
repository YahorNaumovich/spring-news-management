<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up - News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/signup.css'/>" />
  </head>
  <body>
    <jsp:include page="/WEB-INF/view/header.jsp" />
    <main class="signup-container">
      <form:form modelAttribute="signupForm" action="create" method="post" class="signup-form">
        <h2><fmt:message key="signup.title"/></h2>

                <c:if test="${not empty error}">
                  <div class="error">${error}</div>
                </c:if>

        <div class="form-group">
          <label for="username"><fmt:message key="signup.username"/></label>
          <form:input path="username" id="username" required="true" cssClass="form-control"/>
          <form:errors path="username" cssClass="error"/>
        </div>

        <div class="form-group">
          <label for="email"><fmt:message key="signup.email"/></label>
          <form:input path="email" id="email" type="email" required="true" cssClass="form-control"/>
          <form:errors path="email" cssClass="error"/>
        </div>

        <div class="form-group">
          <label for="password"><fmt:message key="signup.password"/></label>
          <form:password path="password" id="password" required="true" cssClass="form-control"/>
          <form:errors path="password" cssClass="error"/>
        </div>

        <div class="form-group">
          <label for="confirmPassword"><fmt:message key="signup.repeat"/></label>
          <form:password path="confirmPassword" id="confirmPassword" required="true" cssClass="form-control"/>
          <form:errors path="confirmPassword" cssClass="error"/>
        </div>

        <button type="submit" class="signup-button"><fmt:message key="signup.action"/></button>
      </form:form>
    </main>
  </body>
</html>
