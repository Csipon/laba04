<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a href="/tologin">Go to login page</a>
        </br>

        <c:if test="${not empty role}">
            <c:choose>
                <c:when test="${role eq 'ADMIN'}">
                    <a href="/admin">My cabinet</a>
                </c:when>
                <c:when test="${role eq 'MANAGER'}">
                    <a href="/profileMgr">My cabinet</a>
                </c:when>
                <c:when test="${role eq 'EMPLOYEE'}">
                    <a href="/profileEmp">My cabinet</a>
                </c:when>
            </c:choose>
        </c:if>
    </div>
</body>
</html>
