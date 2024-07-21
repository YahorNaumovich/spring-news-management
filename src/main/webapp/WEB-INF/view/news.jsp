<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/news-page.css'/>" />
</head>
<body>
    <jsp:include page="/WEB-INF/view/header.jsp" />

    <main class="main-content">
        <c:choose>
            <c:when test="${sessionScope.user != null && (sessionScope.user.userRole.name == 'Admin' || sessionScope.user.userRole.name == 'Editor')}">
                <div class="add-article-link">
                    <a href="<c:url value='/article/add'/>" class="button">Add article</a>
                </div>
            </c:when>
        </c:choose>
        <div class="news-container">
            <c:forEach var="article" items="${articles}">
                <a href="<c:url value='/article?id=${article.id}'/>" class="news-article">
                    <img src="<c:url value='${article.imagePath}'/>" alt="News Image" />
                    <div class="news-content">
                        <h2>${article.title}</h2>
                        <div class="article-footer">
                            <p class="author">by ${article.user.username}</p>
                            <p class="category">${article.category.name}</p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </main>
</body>
</html>
