<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
    <%@include file="header.jsp" %>
    <h2>Table '${table}'. New record.</h2><br>
    <form action="createrecord" method="post">
        <input type="hidden" name = "tableName" value="${table}">
        <table>
            <c:forEach items="${columns}" var="column">
                <tr>
                    <td>${column}</td>
                    <td><input type="text" name="${column}"></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td><input type="submit" value="Create"></td>
            </tr>
        </table>
    </form>
<%@include file="footer.jsp" %>
</body>
</html>