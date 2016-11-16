<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Table: ${table}<br></h2>
<table border="1">
    <tr>
        <c:forEach items="${columns}" var="column">
            <td>${column}<br></td>
        </c:forEach>
        <td></td>
        <td></td>
        <c:set var="idindex" scope="page" value="${idIndex}"/>
        <c:forEach items="${tableData}" var="row">
            <tr>
                <c:forEach items="${row}" var="column">
                    <td>${column}</td>
                </c:forEach>
                <td><a href="updaterecord?table=${table}&id=${row[idindex]}">update</a><br></td>
                <td><a href="deleterecord?table=${table}&id=${row[idindex]}">delete</a><br></td>
            </tr>
        </c:forEach>
    </tr>
</table>
<br>
<table>
    <tr>
        <td><a href="createrecord?table=${table}">Create record</a><br></td>
        <td><a href="cleartable?name=${table}">Clear table</a><br></td>
        <td><a href="droptable?name=${table}">Drop table</a><br></td>
        <td><a href="tables">Back to list of tables</a><br></td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>