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

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <%--<spring:bind path="username">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
            <div class="form-group">
                <form:input type="text" path="name" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <%--<form:errors path="username"></form:errors>--%>
            </div>
        <%--</spring:bind>--%>

        <%--<spring:bind path="password">--%>
        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
        <div class="form-group">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <%--<form:errors path="password"></form:errors>--%>
        </div>
        <%--</spring:bind>--%>

        <%--<spring:bind path="passwordConfirm">--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
            <div class="form-group">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <%--<form:errors path="passwordConfirm"></form:errors>--%>
            </div>
        <%--</spring:bind>--%>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

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
<%--&lt;%&ndash;TODO Registration &ndash;%&gt;--%>
<%--<body>--%>
    <%--&lt;%&ndash;<h2>New user</h2><br>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<form action="registration" method="post">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<table>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td>Login</td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td><input type="text" name="login"></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td>Password</td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td><input type="password" name="password"></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td>E-mail</td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td><input type="text" name="email"></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td></td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td><input type="submit" value="Registration"></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</table>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</form><br>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<%@include file="footer.jsp" %>&ndash;%&gt;--%>

    <%--<div class="container">--%>

        <%--<form:form method="POST" modelAttribute="userForm" class="form-signin">--%>
            <%--<h2 class="form-signin-heading">Create your account</h2>--%>
            <%--<spring:bind path="username">--%>
                <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                    <%--<form:input type="text" path="username" class="form-control" placeholder="Username"--%>
                                <%--autofocus="true"></form:input>--%>
                    <%--<form:errors path="username"></form:errors>--%>
                <%--</div>--%>
            <%--</spring:bind>--%>

            <%--<spring:bind path="password">--%>
                <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                    <%--<form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>--%>
                    <%--<form:errors path="password"></form:errors>--%>
                <%--</div>--%>
            <%--</spring:bind>--%>

            <%--<spring:bind path="passwordConfirm">--%>
                <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                    <%--<form:input type="password" path="passwordConfirm" class="form-control"--%>
                                <%--placeholder="Confirm your password"></form:input>--%>
                    <%--<form:errors path="passwordConfirm"></form:errors>--%>
                <%--</div>--%>
            <%--</spring:bind>--%>

            <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>--%>
        <%--</form:form>--%>

    <%--</div>--%>
    <%--<!-- /container -->--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>


<%--</body>--%>
<%--</html>--%>

