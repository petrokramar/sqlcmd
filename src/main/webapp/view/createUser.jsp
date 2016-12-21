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
        <h2>New user.</h2><br>
        <form action="adduser" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <input class="form-control" id="name" type="text" name="name">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input class="form-control" id="password" type="text" name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">E-mail</label>
                <div class="col-sm-10">
                    <input class="form-control" id="email" type="text" name="email">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="enabled"></label>
                <div class="checkbox col-sm-10">
                    <label><input id="enabled" name="enabled" type="checkbox">Enabled</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="btn_add_user"></label>
                <div class="col-sm-10">
                    <button id="btn_add_user" name="btn_add_user" class="btn btn-default">Add</button>
                </div>
            </div>
            <a href="users">Back to users</a>
            <%@include file="footer.jsp" %>
        </form>
    </div>
</body>
</html>