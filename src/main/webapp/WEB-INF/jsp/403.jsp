<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>400</title>
</head>
<body class="error">
<div class="container">
    <div class="col-lg-8 col-lg-offset-2 text-center" align="center">
        <div class="logo">
            <h1>403</h1>
        </div>
        <p class="lead text-muted">Sorry, You entered incorrect data</p>
        <br>
        <div class="col-lg-6 col-lg-offset-3">
            <div class="btn-group btn-group-justified">
                <c:if test="${role eq 'ADMIN'}">
                    <a href="/admin" class="btn btn-warning">Return to register</a>
                </c:if>

                <c:if test="${role != 'ADMIN'}">
                    <a href="/index" class="btn btn-warning">Return to register</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
