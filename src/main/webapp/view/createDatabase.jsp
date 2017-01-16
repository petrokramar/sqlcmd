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
    <h2><spring:message code="Create.database"/></h2><br>
    <form action="createdatabase" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="database" class="col-sm-2 control-label"><spring:message code="Database.name"/></label>
            <div class="col-sm-10">
                <input class="form-control" id="database" name="database" type="text">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button id="btn_create_database" class="btn btn-default">
                    <spring:message code="Create.database"/></button>
            </div>
        </div>
        <a href="databases"><spring:message code="Back.to.databases"/></a>
    </form>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
