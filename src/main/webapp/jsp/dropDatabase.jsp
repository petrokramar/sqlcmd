<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Drop database</h2><br>
<form action="dropdatabase" method="post">
    <input type="hidden" name = "database" value="${database}">
    <table>
        <tr>
            <td>Database name</td>
            <td>${database}</td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Drop database"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>