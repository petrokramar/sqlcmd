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
    <h2>Query</h2><br>
    <form action="query" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="query" class="col-sm-2 control-label">Query</label>
            <div class="col-sm-10">
                <input class="form-control" id="query" name="query" type="text">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="btn_query"></label>
            <div class="col-sm-10">
                <button id="btn_query" name="btn_query" class="btn btn-default">Query</button>
            </div>
        </div>
    </form>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>