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
<div class="container">
    <h2>Table: ${table}<br></h2>
    <table class="table">
        <thead>
            <tr>
                <c:forEach items="${columns}" var="column">
                    <td>${column}<br></td>
                </c:forEach>
                <td></td>
                <td></td>
                <c:set var="idindex" scope="page" value="${idIndex}"/>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tableData}" var="row">
                <tr>
                    <c:forEach items="${row}" var="column">
                        <td>${column}</td>
                    </c:forEach>
                    <td><a href="updaterecord?table=${table}&id=${row[idindex]}">update</a><br></td>
                    <td><a href="deleterecord?table=${table}&id=${row[idindex]}">delete</a><br></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table class="table">
        <tr>
            <td><a href="createrecord?table=${table}">Create record</a></td>
            <td><a href="cleartable?name=${table}">Clear table</a></td>
            <td><a href="droptable?name=${table}">Drop table</a></td>
            <td><a href="tables">Back to list of tables</a></td>
        </tr>
    </table>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>