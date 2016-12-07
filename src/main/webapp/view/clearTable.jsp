<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Clear table</h2><br>
<form action="cleartable" method="post">
    <input type="hidden" name = "table" value="${table}">
    <table>
        <tr>
            <td>Table name</td>
            <td>${table}</td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Clear table"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>