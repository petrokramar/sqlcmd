<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>SQLCmd</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
</head>
<%--TODO Registration --%>
<body>
    <%--<h2>New user</h2><br>--%>
    <%--<form action="registration" method="post">--%>
        <%--<table>--%>
            <%--<tr>--%>
                <%--<td>Login</td>--%>
                <%--<td><input type="text" name="login"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Password</td>--%>
                <%--<td><input type="password" name="password"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>E-mail</td>--%>
                <%--<td><input type="text" name="email"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input type="submit" value="Registration"></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form><br>--%>
    <%--<%@include file="footer.jsp" %>--%>

    <div class="container">

        <form:form method="POST" modelAttribute="userForm" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>
    <!-- /container -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>


</body>
</html>