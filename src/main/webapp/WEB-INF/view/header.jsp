<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<%
    // Extract the query string and parameters
    String queryString = request.getQueryString();
    String queryParams = "";
    if (queryString != null && !queryString.isEmpty()) {
        queryParams = queryString.replaceAll("(^|&)?lang=[^&]*", "").replaceAll("^&", "");
        if (!queryParams.isEmpty()) {
            queryParams = "&" + queryParams;
        }
    }
%>

<header class="header">
  <div class="header-left">
    <a href="<c:url value='/'/>"><fmt:message key="header.home"/></a>
    <div class="dropdown">
      <a href="#" class="dropbtn"><fmt:message key="header.categories"/></a>
      <div class="dropdown-content">
        <a href="<c:url value='/news'/>">All</a>
        <c:forEach var="category" items="${categories}">
          <a href="<c:url value='/news?category=${category.id}'/>">${category.name}</a>
        </c:forEach>
      </div>
    </div>
  </div>
  <div class="header-right">
    <c:choose>
      <c:when test="${not empty sessionScope.user}">
        <a href="<c:url value='/profile'/>">${sessionScope.user.username}</a>
        <a href="<c:url value='/user/logout'/>"><fmt:message key="header.logout"/></a>
      </c:when>
      <c:otherwise>
        <a href="<c:url value='/user/login'/>"><fmt:message key="header.login"/></a>
      </c:otherwise>
    </c:choose>
    <a href="?lang=en<%= queryParams %>"><fmt:message key="loc.en"/></a> | <a href="?lang=ru<%= queryParams %>"><fmt:message key="loc.ru"/></a>
  </div>
</header>
