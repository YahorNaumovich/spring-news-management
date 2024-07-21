<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Article - News Portal</title>
    <!-- Include your CSS files -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/article.css'/>" />
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>

    <main class="article-container">
        <c:if test="${not empty article}">
            <article class="article">
                <h1 class="article-title">${article.title}</h1>
                <div class="article-meta">
                    <span class="author">by ${article.user.username}</span>
                    <span class="category">${article.category.name}</span>
                </div>
        <c:choose>
            <c:when test="${sessionScope.user != null && (sessionScope.user.userRole.name == 'Admin' || sessionScope.user.userRole.name == 'Editor')}">
                <div class="article-actions">
                    <a href="<c:url value='/article/edit?id=${article.id}'/>"><fmt:message key="article.edit"/></a>
                    <a href="<c:url value='/article/delete?id=${article.id}'/>" class="delete"><fmt:message key="article.delete"/></a>
                </div>
            </c:when>
        </c:choose>

                <img src="<c:url value='${article.imagePath}'/>" alt="Article Image" class="article-image" />
                <div class="article-content">
                    <p>${article.articleText.text}</p>
                    <!-- Additional content rendering -->
                </div>
            </article>
        </c:if>
    </main>
</body>
</html>

