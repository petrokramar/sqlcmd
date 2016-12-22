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
    <h2>Create table</h2><br>
    <form action="createtable" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Table name</label>
            <div class="col-sm-10">
                <input class="form-control" id="name" type="text" name="name">
            </div>
        </div>
        <div class="form-group">
            <label for="query" class="col-sm-2 control-label">Query</label>
            <div class="col-sm-10">
                <input class="form-control" id="query" type="text" name="query">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-offset-2 col-sm-10 control-label">
                Example query: id SERIAL PRIMARY KEY, name varchar(45) NOT NULL
            </label>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="btn_create_table"></label>
            <div class="col-sm-10">
                <button id="btn_create_table" name="btn_create_table" class="btn btn-default">Create</button>
            </div>
        </div>
        <a href="tables">Back to tables</a>
    </form>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
