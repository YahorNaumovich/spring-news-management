<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<header class="header">
  <div class="header-left">
    <a href="<c:url value='/'/>">Home</a>
    <a href="<c:url value='/add-article'/>">Add article</a>
    <div class="dropdown">
      <a href="#" class="dropbtn">Categories</a>
      <div class="dropdown-content">
      <a href="<c:url value='/news'/>">All</a>
      <c:forEach var="category" items="${categories}">
      <a href="<c:url value='/news?category=${category.id}'/>">${category.name}</a>
      </c:forEach>
      </div>
    </div>
  </div>
  <div class="header-right">
    <a href="<c:url value='/login/page'/>">Log in</a>
    <a href="<c:url value='/signup/page'/>">Sign up</a>
  </div>
</header>
