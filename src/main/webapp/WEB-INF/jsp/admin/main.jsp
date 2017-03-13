<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
    <title>Admin</title>
</head>
<body>
    <div class="main" align="center">
        <div align="right">
            <sec:authentication var="user" property="principal" />
            <h3>${user.name} ${user.surname}</h3>
            <form action="/logout" method="post">
                <input type="submit" value="Exit"/>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>

        <h1 align="center">Admin main page</h1>

        <div class="container">
            <div class="register" align="left">
                <p><a href="<c:url value="/admin/profile"/>">My profile</a></p>
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
