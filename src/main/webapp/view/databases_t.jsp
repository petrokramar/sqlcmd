<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Spring Core Online Tutorial - List Products</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
          th:href="@{/webjars/bootstrap/3.3.5/css/bootstrap.min.css}"
          rel="stylesheet" media="screen"/>

    <script src="http://cdn.jsdelivr.net/webjars/jquery/2.1.4/jquery.min.js"
            th:src="@{/webjars/jquery/2.1.4/jquery.min.js}"></script>

    <link href="../static/css/spring-core.css"
          th:href="@{css/spring-core.css}" rel="stylesheet" media="screen"/>
</head>
<body>
<div class="container">
    <%--<div th:if="${not #lists.isEmpty(databases)}">--%>
    <div class="row">
        <h2>Product List</h2>
        <table class="table table-striped">
            <tr>
                <th>Description</th>
            </tr>
            <tr th:each="database : ${databases}">
                <td th:text="${database}"></td>
                <%--<td><a th:href="${'dropdatabase?name=' + database}">Drop</a> </td>--%>
            </tr>
        </table>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <a href="/product/new">New Product</a>
        </div>
    </div>
</div>

</body>