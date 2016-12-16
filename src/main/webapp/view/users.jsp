<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Users<br></h2>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Password</td>
        <td>E-mail</td>
        <td>Enabled</td>
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
    </tr>
</table>
<td><a href="adduser">add</a><br></td>
<br>
<%@include file="footer.jsp" %>
</body>
</html>