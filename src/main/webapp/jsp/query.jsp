<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Query</h2><br>
<form action="query" method="post">
    <table>
        <tr>
            <td>Query</td>
            <td ><input type="text" name="query"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Execute"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>