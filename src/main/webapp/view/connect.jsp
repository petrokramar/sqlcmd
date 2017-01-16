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
        <h2><spring:message code="Connect.to.database"/></h2><br>
        <form action="connect" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="dbname" class="col-sm-2 control-label"><spring:message code="Database.name"/></label>
                <div class="col-sm-10">
                    <input class="form-control" id="dbname" name="dbname" type="text">
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label"><spring:message code="User.name"/></label>
                <div class="col-sm-10">
                    <input class="form-control" id="username" name="username" type="text">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label"><spring:message code="Password"/></label>
                <div class="col-sm-10">
                    <input class="form-control" id="password" name="password" type="text">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="btn_connect"></label>
                <div class="col-sm-10">
                    <button id="btn_connect" class="btn btn-default"><spring:message code="Connect"/></button>
                </div>
            </div>
            <%@include file="footer.jsp" %>
        </form>
    </div>
</body>
</html>