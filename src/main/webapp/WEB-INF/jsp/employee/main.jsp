<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/19/2017
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Profile</title>
</head>
<body>
    <div align="right">
        <h3>${user.name} ${user.surname}</h3>
        <a href="/exit">Exit</a>
    </div>
    <div align="center">
        <p>Hi this is employee page</p>
    </div>
    <br/>
    <a href="/idEmp?id=${user.id}">Profile</a>
    <br/>
    <br/>
    <br/>
    <br/>

    <h2>Your projects</h2>
    <c:forEach items="${user.projects}" var="project">
        <p><b><a href="/idProject?id=${project.id}">${project.name}</a></b></p>
    </c:forEach>
</body>
</html>
