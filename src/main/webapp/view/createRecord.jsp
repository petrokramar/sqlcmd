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
        <h2>Table '${table}'. New record.</h2><br>
        <form action="createrecord" method="post" class="form-horizontal">
            <input type="hidden" name = "tableName" value="${table}">
           <table class="table">
                <c:forEach items="${columns}" var="column">
                    <div class="form-group">
                        <label for="${column}" class="col-sm-2 control-label">${column}</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="${column}" name="${column}" type="text">
                        </div>
                    </div>
                </c:forEach>
               <div class="form-group">
                   <div class="col-sm-offset-2 col-sm-10">
                       <button id="btn_create_record" class="btn btn-default">Create</button>
                   </div>
               </div>
            </table>
        </form>
        <a href="table?name=${table}">Back to ${table}</a>
        <%@include file="footer.jsp" %>
    </div>
</body>
</html>