<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Add Article - News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/article-form.css'/>"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<main class="article-container">
    <form:form class="article-form"
               modelAttribute="articleForm"
               action="${empty articleForm.articleId ? 'save' : 'update'}"
               method="post"
               enctype="multipart/form-data">

<h2><c:if test="${not isEditMode}">Add</c:if><c:if test="${isEditMode}">Edit</c:if> Article</h2>


        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <div class="form-group">
            <form:label path="title" for="title">Title</form:label>
            <form:input path="title" id="title" type="text" required="true"/>
        </div>

        <div class="form-group">
            <form:label path="articleText" for="articleText">Article Text</form:label>
            <form:textarea path="articleText" id="articleText" rows="10" required="true"/>
        </div>

        <div class="form-group">
            <form:label path="image" for="image">Image</form:label>
            <input type="file" id="image" name="image" accept="image/*"/>
        </div>

        <div class="form-group">
            <form:label path="categoryId" for="category">Category</form:label>
            <form:select path="categoryId" id="category" required="true">
                <form:options items="${categories}" itemValue="id" itemLabel="name"/>
            </form:select>
        </div>

        <c:if test="${not empty articleForm.articleId}">
            <form:hidden path="articleId"/>
        </c:if>

        <form:hidden path="userId" value="${sessionScope.user.id}"/>

        <button type="submit" class="article-button">Save Article</button>
    </form:form>
</main>
</body>
</html>
