<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
Databases<br>
<table border="1">
    <c:forEach items="${databases}" var="database">
        <tr>
            <td><a href="tables?name=${database}">${database}</a><br></td>
            <td><a href="dropdatabase?name=${database}">drop</a><br></td>
        </tr>

    </c:forEach>
</table>
<a href="createdatabase">Add database</a><br>
<%@include file="footer.jsp" %>
</body>
</html>