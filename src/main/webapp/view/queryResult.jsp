<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <h2>Query : ${query}<br></h2>
    <table class="table">
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
    <a href="menu">Back to menu</a>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>