<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>User actions<br></h2>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Date</td>
        <td>User</td>
        <td>Database</td>
        <td>Database user</td>
        <td>Action</td>
        <c:forEach items="${actions}" var="action">
            <tr>
                <td>${action.id}</td>
                <td>${action.date}</td>
                <td>${action.user.name}</td>
                <td>${action.databaseConnection.databaseName}</td>
                <td>${action.databaseConnection.userName}</td>
                <td>${action.action}</td>
            </tr>
        </c:forEach>
    </tr>
</table>
<br>
<%@include file="footer.jsp" %>
</body>
</html>