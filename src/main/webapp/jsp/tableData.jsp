<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<h1>Table: ${table}<br></h1>
<table border="1">
    <tr>
        <c:forEach items="${columns}" var="column">
            <td>${column}<br></td>
        </c:forEach>
        <td></td>
        <td></td>
        <c:forEach items="${tableData}" var="row">
            <tr>
                <c:forEach items="${row}" var="column">
                    <td>${column}</td>
                </c:forEach>
                <td><a href="updateecord?name=${column}">update</a><br></td>
                <td><a href="deleterecord?name=${column}">delete</a><br></td>
            </tr>
        </c:forEach>
    </tr>
</table>
<td><a href="createrecord?table=${table}">Create record</a><br></td>
<%@include file="footer.jsp" %>
</body>
</html>