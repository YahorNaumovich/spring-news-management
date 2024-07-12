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
    <title>Article - News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/article.css'/>" />
  </head>
  <body>
      <jsp:include page="/WEB-INF/view/header.jsp" />
    <main class="article-container">
      <article class="article">
        <h1 class="article-title">Article Title</h1>
        <div class="article-meta">
          <span class="author">by Author Username</span>
          <span class="category">Category Name</span>
          <span class="date"><fmt:formatDate value="${articleDate}" pattern="dd MMM yyyy" /></span>
        </div>
        <img src="<c:url value='/resources/images/image.jpg'/>" alt="Article Image" class="article-image" />
        <div class="article-content">
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
          <!-- More paragraphs as needed -->
        </div>
      </article>
    </main>
  </body>
</html>
