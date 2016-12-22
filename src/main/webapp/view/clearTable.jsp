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
    <h2>Clear table</h2><br>
    <form action="cleartable" method="post" class="form-horizontal">
        <input type="hidden" name = "table" value="${table}">
        <div class="form-group">
            <label for="table" class="col-sm-2 control-label">Table name</label>
            <div class="col-sm-10">
                <input class="form-control" id="table" name="table" type="text" value="${table}" disabled>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button id="btn_clear_table" name="btn_clear_table" class="btn btn-default">Clear table</button>
            </div>
        </div>
        <a href="table?name=${table}">Back to table ${table}</a>
        <%@include file="footer.jsp" %>
    </form>
</div>
</body>
</html>