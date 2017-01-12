<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <%--<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/resources/css/common.css" rel="stylesheet">--%>

    <style>
        <%@include file='../resources/css/bootstrap.min.css' %>
        <%@include file='../resources/css/common.css' %>
    </style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<%@include file="header.jsp" %>
<div class="container">
    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>

    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<c:set var="contextPath" value="${pageContext.request.contextPath}"/>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>SQLCmd</title>--%>
    <%--<link rel="stylesheet"--%>
          <%--href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet"--%>
          <%--href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="header.jsp" %>--%>
<%--<div class="container">--%>
    <%--<h2>Login</h2><br>--%>
    <%--<c:if test="${not empty error}">--%>
        <%--<h3><div class="col-sm-offset-2 col-sm-10 label label-danger">${error}</div></h3>--%>
    <%--</c:if>--%>
    <%--<form action="login" method="post" class="form-horizontal">--%>
        <%--<div class="form-group">--%>
            <%--<label for="username" class="col-sm-2 control-label">Username</label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<input class="form-control" id="username" type="text" name="username">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<label for="password" class="col-sm-2 control-label">Password</label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<input class="form-control" id="password" type="password" name="password">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<label class="col-sm-2 control-label" for="btn_login"></label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<button id="btn_login" class="btn btn-default">Login</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</form>--%>
    <%--<%@include file="footer.jsp" %>--%>
<%--</div>--%>
<%--<div class="container">--%>
    <%--<div class="row">--%>
        <%--<div class="col-md-6 col-md-offset-3">--%>
            <%--<div class="panel panel-login">--%>
                <%--<div class="panel-heading">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-xs-6">--%>
                            <%--<a href="#" class="active" id="login-form-link">Login</a>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-6">--%>
                            <%--<a href="#" id="register-form-link">Register</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr>--%>
                <%--</div>--%>
                <%--<div class="panel-body">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<form id="login-form" action="http://phpoll.com/login/process" method="post" role="form" style="display: block;">--%>
                                <%--<div class="form-group">--%>
                                    <%--<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">--%>
                                <%--</div>--%>
                                <%--<div class="form-group text-center">--%>
                                    <%--<input type="checkbox" tabindex="3" class="" name="remember" id="remember">--%>
                                    <%--<label for="remember"> Remember Me</label>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-sm-6 col-sm-offset-3">--%>
                                            <%--<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-lg-12">--%>
                                            <%--<div class="text-center">--%>
                                                <%--<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                            <%--<form id="register-form" action="http://phpoll.com/register/process" method="post" role="form" style="display: none;">--%>
                                <%--<div class="form-group">--%>
                                    <%--<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-sm-6 col-sm-offset-3">--%>
                                            <%--<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<script type="text/javascript">--%>
    <%--$(function() {--%>
        <%--$('#login-form-link').click(function(e) {--%>
            <%--$("#login-form").delay(100).fadeIn(100);--%>
            <%--$("#register-form").fadeOut(100);--%>
            <%--$('#register-form-link').removeClass('active');--%>
            <%--$(this).addClass('active');--%>
            <%--e.preventDefault();--%>
        <%--});--%>
        <%--$('#register-form-link').click(function(e) {--%>
            <%--$("#register-form").delay(100).fadeIn(100);--%>
            <%--$("#login-form").fadeOut(100);--%>
            <%--$('#login-form-link').removeClass('active');--%>
            <%--$(this).addClass('active');--%>
            <%--e.preventDefault();--%>
        <%--});--%>

    <%--});</script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>