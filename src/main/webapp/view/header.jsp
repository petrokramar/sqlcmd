<%--<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="form-inline">
            <a href="<c:url value="?language=ru"/>">RU</a>
            <a href="<c:url value="?language=en"/>">EN</a>
        </div>
        <div class="navbar-header">
            <a href="<spring:url value="/"/>" class="navbar-brand">SQL Commander</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="databases"> <spring:message code="Databases"/> </a></li>
            <li><a href="query"> <spring:message code="Query"/> </a></li>
            <li><a href="users"> <spring:message code="Users"/> </a></li>
            <li><a href="actions"> <spring:message code="User.actions"/> </a></li>
            <li><a href="help"> <spring:message code="Help"/> </a></li>
            <sec:authorize access="authenticated" var="authenticated"/>
            <c:choose>
                <c:when test="${authenticated}">
                    <li>
                        <p class="navbar-text">
                            <spring:message code="Welcome"/>
                            <sec:authentication property="name"/>
                            <a id="logout" href="<c:url value="j_spring_security_logout" />"><spring:message code="Logout"/></a>
                        </p>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<spring:url value="/login"/>"><spring:message code="Sign.in"/></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <div class="container">
    </div>
</nav>