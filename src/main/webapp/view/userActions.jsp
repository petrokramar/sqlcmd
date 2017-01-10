<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            $('#actions').DataTable();
        } );
    </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <h2>User actions<br></h2>
    <table id="actions" class="display" cellspacing="0" width="100%">
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
                <button id="btn_delete_actions" class="btn btn-default">Delete actions</button>
            </div>
        </div>
    </form>
    <a href="menu">Back to main menu</a>
    <%@include file="footer.jsp" %>
</div>
<script type="text/javascript">
    $('#actions')
            .removeClass( 'display' )
            .addClass('table table-striped table-bordered');
</script>
</body>
</html>