<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 10.08.2016
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="resource/menu.css" />" rel="stylesheet">
    <title>Welcome</title>
</head>
<body>
    <div align="center">
        <h1>Hi, welcome to my page, if you is here, so your way is successful</h1>
        <a href="/login">Go to login page</a>
        </br>

        <sec:authorize access="hasRole('ROLE_Administrator')">
            <a href="/admin/profileAdmin">My cabinet</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ProjectManager')">
            <a href="/manager/profileMgr">My cabinet</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_Employee')">
            <a href="/employee/profileEmp">My cabinet</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_Customer')">
            <a href="customer/profileCustomer">My cabinet</a>
        </sec:authorize>
    </div>
</body>
</html>
