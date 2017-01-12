<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="form-inline">
            <a href="<c:url value="/locale?language=ru"/>">RU</a>
            <a href="<c:url value="/locale?language=en"/>">EN</a>
        </div>
        <div class="navbar-header">
            <a href="<spring:url value="/"/>" class="navbar-brand">SQL Commander</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="databases"> Databases </a></li>
            <li><a href="query"> Query </a></li>
            <li><a href="users"> Users </a></li>
            <li><a href="actions"> User actions </a></li>
            <li><a href="help"> Help </a></li>
            <sec:authorize access="authenticated" var="authenticated"/>
            <c:choose>
                <c:when test="${authenticated}">
                    <li>
                        <p class="navbar-text">
                            Welcome
                            <sec:authentication property="name"/>
                            <a id="logout" href="<c:url value="j_spring_security_logout" />">Logout</a>
                        </p>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<spring:url value="/login"/>">Sign In</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <div class="container">
    </div>
</nav>