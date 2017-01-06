<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <h2>Login</h2><br>
    <c:if test="${not empty error}">
        <h3><div class="col-sm-offset-2 col-sm-10 label label-danger">${error}</div></h3>
    </c:if>
    <form action="login" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
                <input class="form-control" id="username" type="text" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input class="form-control" id="password" type="password" name="password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="btn_login"></label>
            <div class="col-sm-10">
                <button id="btn_login" class="btn btn-default">Login</button>
            </div>
        </div>
    </form>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>