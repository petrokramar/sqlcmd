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
    <h2>Table '${table}'. Update record.</h2><br>
    <form action="updaterecord" method="post" class="form-horizontal">
        <input type="hidden" name = "tableName" value="${table}">
        <input type="hidden" name = "id" value="${id}">
        <table class="table">
            <div class="form-group">
                <label for="id" class="col-sm-2 control-label">id</label>
                <div class="col-sm-10">
                    <input class="form-control" id="id" name="id" type="text" value="${id}">
                </div>
            </div>
            <c:forEach var="entry" items="${record}">
                <div class="form-group">
                    <label for="${entry.key}" class="col-sm-2 control-label">${entry.key}</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="${entry.key}" name="${entry.key}" type="text" value="${entry.value}">
                    </div>
                </div>
            </c:forEach>
        </table>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button id="btn_save_record" class="btn btn-default">Save</button>
            </div>
        </div>
        <a href="table?name=${table}">Back to ${table}</a>
        <%@include file="footer.jsp" %>
    </form>
</div>
</body>
</html>