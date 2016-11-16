<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Tables</h2>
<a href="createtable">Create table</a><br>
<br>
<table border="1">
    <c:forEach items="${tables}" var="table">
        <tr>
            <td><a href="table?name=${table}">${table}</a><br></td>
        </tr>
    </c:forEach>
</table>
<%@include file="footer.jsp" %>
</body>
</html>