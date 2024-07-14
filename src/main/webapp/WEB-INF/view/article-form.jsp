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
    <title>Add Article - News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/article-form.css'/>" />
  </head>
  <body>
    <jsp:include page="/WEB-INF/view/header.jsp" />
    <main class="article-container">
      <form class="article-form" action="<c:url value='/article/save'/>" method="post" enctype="multipart/form-data">
        <h2>Add Article</h2>
        <c:if test="${not empty error}">
          <div class="error">${error}</div>
        </c:if>
        <div class="form-group">
          <label for="title">Title</label>
          <input type="text" id="title" name="title" required />
        </div>
        <div class="form-group">
          <label for="articleText">Article Text</label>
          <textarea id="articleText" name="articleText" rows="10" required></textarea>
        </div>
        <div class="form-group">
          <label for="image">Image</label>
          <input type="file" id="image" name="image" accept="image/*" />
        </div>
        <div class="form-group">
          <label for="category">Category</label>
          <select id="category" name="category" required>
            <c:forEach var="category" items="${categories}">
              <option value="${category.id}">${category.name}</option>
            </c:forEach>
          </select>
        </div>
        <input type="hidden" name="userId" value="${sessionScope.user.id}" />
        <button type="submit" class="article-button">Save Article</button>
      </form>
    </main>
  </body>
</html>
