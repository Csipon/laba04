<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Chalienko
  Date: 11.05.2016
  Time: 01:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html lang="en">
<head>
    <link href="<c:url value="../../resource/menu.css" />" rel="stylesheet">
    <meta charset="UTF-8">
    <title>404</title>
</head>
<body class="error">
<div class="container">
    <div class="col-lg-8 col-lg-offset-2 text-center" align="center">
        <div class="logo">
            <h1>404</h1>
        </div>
        <p class="lead text-muted">Sorry, please enter correct data</p>
        <br>
        <div class="col-lg-6 col-lg-offset-3">
            <div class="btn-group btn-group-justified">
                <sec:authorize access="hasRole('ROLE_Administrator')">
                    <a href="/admin/profileAdmin">Return to my cabinet</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ProjectManager')">
                    <a href="/manager/profileMgr">Return to my cabinet</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_Employee')">
                    <a href="/employee/profileEmp">Return to my cabinet</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_Customer')">
                    <a href="customer/profileCustomer">Return to my cabinet</a>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>
</body>
</html>
