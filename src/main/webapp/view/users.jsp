<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <h2><spring:message code="Users"/></h2>
    <table class="table">
        <thead>
            <tr>
                <td>Name</td>
                <td>Password</td>
                <td>E-mail</td>
                <td>Enabled</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.enabled}</td>
                    <td><a href="updateuser?name=${user.username}"><spring:message code="Update"/></a><br></td>
                    <td><a href="deleteuser?name=${user.username}"><spring:message code="Delete"/></a><br></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="adduser"><spring:message code="Create.user"/></a>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>