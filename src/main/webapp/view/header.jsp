<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<h1>JuJa SQLcmd</h1>--%>
<%--<table>--%>
    <%--<tr>--%>
        <%--<td><a href="menu"> Menu </a></td>--%>
        <%--<td><a href="databases"> Databases </a></td>--%>
        <%--<td><a href="query"> Query </a></td>--%>
        <%--<td><a href="help"> Help </a></td>--%>
        <%--<td><a href="login"> Login </a></td>--%>
        <%--<td><a href="users"> Users </a></td>--%>
        <%--<td><a href="actions"> User actions </a></td>--%>
    <%--</tr>--%>
<%--</table>--%>

<%--<br>--%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a href="<spring:url value="/"/>" class="navbar-brand">SQL Commander</a>
        </div>
        <ul class="nav navbar-nav">
//            <li><a href="<spring:url value="/services/"/>">Services</a></li>
            <li><a href="menu"> Menu </a></li>
            <li><a href="databases"> Databases </a></li>
            <li><a href="query"> Query </a></li>
            <li><a href="help"> Help </a></li>
            <li><a href="login"> Login </a></li>
            <li><a href="users"> Users </a></li>
            <li><a href="actions"> User actions </a></li>
            <sec:authorize access="authenticated" var="authenticated"/>
            <c:choose>
                <c:when test="${authenticated}">
                    <li>
                        <p class="navbar-text">
                            Welcome
                            <sec:authentication property="name"/>
                            <a id="logout" href="#">Logout</a>
                        </p>
                        <form id="logout-form" action="<c:url value="/logout"/>" method="post">
                            <sec:csrfInput/>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<spring:url value="/login/"/>">Sign In</a></li>
                </c:otherwise>
            </c:choose>


        </ul>
    </div>
</nav>