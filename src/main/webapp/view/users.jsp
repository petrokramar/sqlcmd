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
            $('#tabledata').DataTable({
                "language": {
                    <%--
                        TODO spring message doesn't work here
                    --%>
                    <%--"paginate": {--%>
                        <%--"first":      <spring:message code="First"/>,--%>
                        <%--"last":       <spring:message code="Last"/>,--%>
                        <%--"next":       <spring:message code="Next"/>,--%>
                        <%--"previous":   <spring:message code="Previous"/>--%>
                    <%--}--%>
                }
            });
        } );
    </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <h2><spring:message code="Users"/></h2>
    <table id="tabledata" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <td>Name</td>
                <%--<td>Password</td>--%>
                <td>E-mail</td>
                <td>Enabled</td>
                <td></td>
                <td></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <%--<td>${user.password}</td>--%>
                    <td>${user.email}</td>
                    <td>${user.enabled}</td>
                    <td><a href="updateuser?name=${user.username}"><spring:message code="Update"/></a><br></td>
                    <td><a href="deleteuser?name=${user.username}"><spring:message code="Delete"/></a><br></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="adduser"><spring:message code="Create.user"/></a>
    <%@include file="footer.jsp" %>
</div>
<script type="text/javascript">
    $('#tabledata')
            .removeClass( 'display' )
            .addClass('table table-striped table-bordered');
</script>
</body>
</html>