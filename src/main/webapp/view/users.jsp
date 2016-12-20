<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"
            type="text/javascript"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<c:url value="/js/global.js"/>"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <h2>Users<br></h2>
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
                    <td>${user.name}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.enabled}</td>
                    <td><a href="updateuser?name=${user.name}">update</a><br></td>
                    <td><a href="deleteuser?name=${user.name}">delete</a><br></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="adduser">add</a>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>