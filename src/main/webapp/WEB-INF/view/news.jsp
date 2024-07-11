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
    <title>News Portal</title>
    <link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/news-page.css"/>" />
  </head>
  <body>
    <header class="header">
      <div class="header-left">
        <a href="#">Home</a>
        <a href="#">Add article</a>
        <a href="#">Edit article</a>
        <a href="#">Delete article</a>
        <div class="dropdown">
          <a href="#" class="dropbtn">Categories</a>
          <div class="dropdown-content">
            <a href="#">Category 1</a>
            <a href="#">Category 2</a>
            <a href="#">Category 3</a>
            <!-- Add more categories as needed -->
          </div>
        </div>
      </div>
      <div class="header-right">
        <a href="<c:url value='/login/page'/>">Log in</a>
        <a href="<c:url value='/signup/page'/>">Sign up</a>
      </div>
    </header>

    <main class="news-container">
      <a href="#" class="news-article">
        <img src="<c:url value="/resources/images/image.jpg"/>" alt="News Image" />
        <div class="news-content">
          <h2>News Title 1</h2>
          <div class="article-footer">
            <p class="author">by Author Username</p>
            <p class="category">Category Name</p>
          </div>
        </div>
      </a>

      <a href="#" class="news-article">
        <img src="<c:url value="/resources/images/image.jpg"/>" alt="News Image" />
        <div class="news-content">
          <h2>News Title 1</h2>
          <div class="article-footer">
            <p class="author">by Author Username</p>
            <p class="category">Category Name</p>
          </div>
        </div>
      </a>
      <!-- News articles go here -->
    </main>
  </body>
</html>
