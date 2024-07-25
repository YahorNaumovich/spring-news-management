<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User Management - News Portal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/header.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/user-management.css'/>" />
    <script>
      function submitFormOnRoleChange(selectElement) {
        selectElement.closest('form').submit();
      }
    </script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/view/header.jsp" />
    <div class="container">
      <h2 class="table-header"><fmt:message key="userManagement.title" /></h2>
      <table class="user-table">
        <thead>
          <tr>
            <th><fmt:message key="userManagement.id" /></th>
            <th><fmt:message key="userManagement.email" /></th>
            <th><fmt:message key="userManagement.username" /></th>
            <th><fmt:message key="userManagement.role" /></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="user" items="${users}">
            <tr>
              <td>${user.id}</td>
              <td>${user.email}</td>
              <td>${user.username}</td>
              <td>
                <form action=<c:url value='/user/update-role'/> method="post">
                  <input type="hidden" name="id" value="${user.id}" />
                  <select name="roleId" onchange="submitFormOnRoleChange(this)">
                    <c:forEach var="role" items="${roles}">
                      <option value="${role.id}" ${role eq user.userRole ? 'selected="selected"' : ''}>
                        ${role.name}
                      </option>
                    </c:forEach>
                  </select>
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
