<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
Delete record<br>
Table '${table}'. Delete record.<br>
<form action="deleterecord" method="post">
    <input type="hidden" name = "tableName" value="${table}">
    <input type="hidden" name = "id" value="${id}">
    <table>
        <c:forEach var="entry" items="${record}">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>