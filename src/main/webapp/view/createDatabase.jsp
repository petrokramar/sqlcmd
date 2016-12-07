<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Create database</h2><br>
<form action="createdatabase" method="post">
    <table>
        <tr>
            <td>
                Database name
            </td>
            <td><input type="text" name="database"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Create database"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
