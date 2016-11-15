<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Table '${table}'. Update record.</h1><br>
<form action="updaterecord" method="post">
    <input type="hidden" name = "tableName" value="${table}">
    <input type="hidden" name = "id" value="${id}">
    <table>
        <tr>
            <td>id</td>
            <td>${id}</td>
        </tr>
        <c:forEach var="entry" items="${record}">
            <tr>
                <td>${entry.key}</td>
                <td><input type="text" name="${entry.key}" value="${entry.value}"></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td><input type="submit" value="Update"></td>
        </tr>
    </table>
</form>
</body>
<%@include file="footer.jsp" %>
</html>