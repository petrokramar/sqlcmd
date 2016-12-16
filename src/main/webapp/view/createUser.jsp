<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
    <%@include file="header.jsp" %>
    <h2>New user.</h2><br>
    <form action="adduser" method="post">
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>E-mail</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>Enabled</td>
                <td><input type="checkbox" name="enabled"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create"></td>
            </tr>
        </table>
    </form>
    <td><a href="users">Back to users</a><br></td>
    <%@include file="footer.jsp" %>
</body>
</html>