<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
Databases<br>
<table border="1">
    <c:forEach items="${databases}" var="database">
        <tr>
            <c:choose>
                <c:when test ="${database == current}">
                    <td><a href="tables?name=${database}">${database}</a><br></td>
                    <td>Connected</td>
                    <td></td>
                </c:when>
                <c:otherwise>
                    <td>${database}<br></td>
                    <td></td>
                    <td><a href="dropdatabase?name=${database}">drop</a><br></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
<a href="connect">Connect database</a><br>
<a href="createdatabase">Add database</a><br>
<%@include file="footer.jsp" %>
</body>
</html>