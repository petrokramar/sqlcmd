<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
Tables<br>
<table border="1">
    <c:forEach items="${tables}" var="table">
        <tr>
            <td><a href="table?name=${table}">${table}</a><br></td>
            <td><a href="droptable?name=${table}">drop</a><br></td>
        </tr>
    </c:forEach>
</table>
<a href="databases">Database list</a><br>
<%@include file="footer.jsp" %>
</body>
</html>