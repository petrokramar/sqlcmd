<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Delete user</h2><br>
<form action="deleteuser" method="post">
    <input type="hidden" name = "name" value="${user.name}">
    <table>
        <tr>
            <td>Name</td>
            <td>${user.name}</td>
        </tr>
        <tr>
            <td>E-mail</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Delete"></td>
        </tr>
    </table>
</form>
<td><a href="users">Back to users</a><br></td>
<%@include file="footer.jsp" %>
</body>
</html>