<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Profile - News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/profile.css'/>" />
  </head>
  <body>
    <jsp:include page="/WEB-INF/view/header.jsp" />
    <main class="profile-container">
      <h2 class="profile-header"><fmt:message key="profile.title" /></h2>
      <div class="profile-info">
        <div class="info-group">
          <span><fmt:message key="profile.username" />:</span> ${user.username}
        </div>
        <div class="info-group">
          <span><fmt:message key="profile.email" />:</span> ${user.email}
        </div>
        <div class="info-group">
          <span><fmt:message key="profile.role" />:</span> ${user.userRole.name}
        </div>
      </div>
    </main>
  </body>
</html>
