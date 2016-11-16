<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
Create table<br>
<form action="createtable" method="post">
    <table>
        <tr>
            <td>Table name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Query</td>
            <td ><input type="text" name="query"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Create"/></td>
        </tr>
    </table>
    <br>
    Example query: id SERIAL PRIMARY KEY, name varchar(45) NOT NULL<br>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
