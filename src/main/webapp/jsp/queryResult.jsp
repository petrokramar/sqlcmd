<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Query : ${query}<br></h1>
<table border="1">
    <tr>
        <c:forEach items="${querydata}" var="row">
            <tr>
                <c:forEach items="${row}" var="column">
                    <td>${column}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tr>
</table>
<td><a href="menu">Back to menu</a><br></td>
<%@include file="footer.jsp" %>
</body>
</html>