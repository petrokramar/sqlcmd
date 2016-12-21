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
    <h2>User actions<br></h2>
    <table class="table">
        <thead>
            <tr>
                <td>Id</td>
                <td>Date</td>
                <td>User</td>
                <td>Database</td>
                <td>Database user</td>
                <td>Action</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${actions}" var="action">
                <tr>
                    <td>${action.id}</td>
                    <td>${action.date}</td>
                    <td>${action.user.name}</td>
                    <td>${action.databaseConnection.databaseName}</td>
                    <td>${action.databaseConnection.userName}</td>
                    <td>${action.action}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="deleteactions" method="post">
        <div class="form-group">
            <div class="col-sm-10">
                <button id="btn_delete_actions" name="btn_delete_actions" class="btn btn-default">Delete actions</button>
            </div>
        </div>
    </form>
    <a href="menu">Back to main menu</a><br>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>