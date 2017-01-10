<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <h1>HTTP Status 403 - Access is denied</h1>
        <c:choose>
            <c:when test="${empty username}">
                <h2>You do not have permission to access this page!</h2>
            </c:when>
            <c:otherwise>
                <h2>Username : ${username} <br/>
                    You do not have permission to access this page!</h2>
            </c:otherwise>
        </c:choose>
</div>
</body>
</html>
