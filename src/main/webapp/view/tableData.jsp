<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <h2><spring:message code="Table"/>: ${table}<br></h2>
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
                    <td><a href="updaterecord?table=${table}&id=${row[idindex]}">
                        <spring:message code="Update"/></a></td>
                    <td><a href="deleterecord?table=${table}&id=${row[idindex]}">
                        <spring:message code="Delete"/></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table class="table">
        <tr>
            <td><a href="createrecord?table=${table}"><spring:message code="Create.record"/></a></td>
            <td><a href="cleartable?name=${table}"><spring:message code="Clear.table"/></a></td>
            <td><a href="droptable?name=${table}"><spring:message code="Drop.table"/></a></td>
            <td><a href="tables"><spring:message code="Back.to.tables"/></a></td>
        </tr>
    </table>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>