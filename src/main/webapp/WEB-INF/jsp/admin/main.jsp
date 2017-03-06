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
    <c:if test="${role ne 'ADMIN' or empty role}">
        <c:redirect url="/login"/>
    </c:if>
    <c:if test="${role eq 'ADMIN'}">
        <div class="main" align="center">
            <div align="right">
                <h3>${user.name} ${user.surname}</h3>
                <a href="/exit">Exit</a>
            </div>

            <h1 align="center">Admin main page</h1>

            <div class="container">
                <div class="register" align="left">
                    <p><a href="<c:url value="/registry"/>">Register employee</a></p>
                    <p><a href="<c:url value="/registerCustomer"/>">Register customer</a></p>
                    <p><a href="<c:url value="/createProject"/>">Create new project</a></p>
                    <p><a href="<c:url value="/createDepartment"/>">Create new departemnt</a></p>
                </div>

                <div class="information" align="right">
                    <p><a href="<c:url value="/getAllEmployee"/>">Employees</a></p>
                    <p><a href="<c:url value="/getAllCustomer"/>">Customers</a></p>
                    <p><a href="<c:url value="/getAllProject"/>">Projects</a></p>
                    <p><a href="<c:url value="/getAllDepartment"/>">Departments</a></p>
                    <p><a href="<c:url value="/getAllManager"/>">Managers</a></p>
                </div>
                <div style="clear:both;"></div>
            </div>

        </div>
    </c:if>


</body>
</html>
