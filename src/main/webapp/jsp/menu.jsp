<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SQLCmd</title>
</head>
<body>
<a href="jsp/clearTable.jsp">Clear table</a><br>
<a href="jsp/createDatabase.jsp">Create database</a><br>
<a href="jsp/createRecord.jsp">Create record</a><br>
<a href="jsp/createTable.jsp">Create table</a><br>
<a href="jsp/databaseNames.jsp">Database names</a><br>
<a href="jsp/deleteRecord.jsp">Delete record</a><br>
<a href="jsp/disconnect.jsp">Disconnect</a><br>
<a href="jsp/dropDatabase.jsp">Drop database</a><br>
<a href="jsp/dropTable.jsp">Drop table</a><br>
<a href="jsp/help.jsp">Help</a><br>
<a href="jsp/query.jsp">Query</a><br>
<a href="jsp/tableData.jsp">Table data</a><br>
<a href="jsp/tableNames.jsp">Table names</a><br>
<a href="jsp/updateRecord.jsp">Update record</a><br>

<%--<c:forEach items="${commands}" var="command">--%>
    <%--<a href="${command}">${command}</a><br>--%>
<%--</c:forEach>--%>
</body>
</html>