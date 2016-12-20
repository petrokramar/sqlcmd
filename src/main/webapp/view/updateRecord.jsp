<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"
            type="text/javascript"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<c:url value="/js/global.js"/>"></script>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Table '${table}'. Update record.</h2><br>
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