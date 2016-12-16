<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Edit user</h2><br>
<form action="updateuser" method="post">
    <input type="hidden" name = "name" value="${user.name}">
    <table>
        <tr>
            <td>Name</td>
            <td>${user.name}</td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${user.password}"></td>
        </tr>
        <tr>
            <td>E-mail</td>
            <td><input type="text" name="email" value="${user.email}"/></td>
        </tr>
        <tr>
            <td>Enabled</td>
            <td><input type="checkbox" name="enabled" value="${user.enabled}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>
<td><a href="users">Back to users</a><br></td>
<%@include file="footer.jsp" %>
</body>
</html>