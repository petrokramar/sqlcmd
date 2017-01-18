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
            $('#result').DataTable();
        } );
    </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <h2><spring:message code="Query"/> : ${query}<br></h2>
<%--
    TODO where is header?
--%>
    <table id="result" class="display" cellspacing="0" width="100%">
        <c:set var="tbodystart" scope="page" value="true"/>
        <c:forEach items="${querydata}" var="row" varStatus="status">
            <c:choose>
                <c:when test ="${status.index==0}">
                    <thead>
                        <tr>
                            <c:forEach items="${row}" var="column">
                                <td>${column}</td>
                            </c:forEach>
                        </tr>
                    </thead>
                </c:when>
                <c:otherwise>
                    <c:if test="${tbodystart==true}">
                        <tbody>
                    </c:if>
                    <tr>
                        <c:forEach items="${row}" var="column">
                            <td>${column}</td>
                        </c:forEach>
                    </tr>
                    <c:if test="${tbodystart==true}">
                        </tbody>
                        <c:set var="tbodystart" scope="page" value="false"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
    <%--<a href="menu">Back to menu</a>--%>
    <%@include file="footer.jsp" %>
</div>
<script type="text/javascript">
    $('#result')
            .removeClass( 'display' )
            .addClass('table table-striped table-bordered');
</script>
</body>
</html>