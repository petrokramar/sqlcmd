<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <h2>Edit user</h2><br>
    <form action="updateuser" method="post" class="form-horizontal">
        <input type="hidden" name = "name" value="${user.name}">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Name</label>
            <div class="col-sm-10">
                <input class="form-control" id="name" type="text" value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input class="form-control" id="password" type="text" value="${user.password}">
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">E-mail</label>
            <div class="col-sm-10">
                <input class="form-control" id="email" type="text" value="${user.email}">
            </div>
        </div>
        <div class="checkbox col-sm-2">
            <label><input type="checkbox" value="${user.enabled}">Enabled</label>
        </div>
        <input type="submit" class="btn btn-primary" value="Save"/>
    </form>
    <a href="users">Back to users</a>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>