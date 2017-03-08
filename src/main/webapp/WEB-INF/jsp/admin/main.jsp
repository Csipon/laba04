<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/19/2017
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
    <title>Admin</title>
</head>
<body>
    <sec:authentication var="user" property="principal" />
    <div class="main" align="center">
        <div align="right">
            <form action="/logout" method="post">
                <h3>${user.name} ${user.surname}</h3>
                <input type="submit" value="Exit"/>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>

        <h1 align="center">Admin main page</h1>

        <div class="container">
            <div class="register" align="left">
                <p><a href="<c:url value="/admin/registry"/>">Register employee</a></p>
                <p><a href="<c:url value="/admin/registerCustomer"/>">Register customer</a></p>
                <p><a href="<c:url value="/admin/createProject"/>">Create new project</a></p>
                <p><a href="<c:url value="/admin/createDepartment"/>">Create new departemnt</a></p>
            </div>

            <div class="information" align="right">
                <p><a href="<c:url value="/admin/getAllEmployee"/>">Employees</a></p>
                <p><a href="<c:url value="/admin/getAllCustomer"/>">Customers</a></p>
                <p><a href="<c:url value="/admin/getAllProject"/>">Projects</a></p>
                <p><a href="<c:url value="/admin/getAllDepartment"/>">Departments</a></p>
                <p><a href="<c:url value="/admin/getAllManager"/>">Managers</a></p>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>
</body>
</html>
