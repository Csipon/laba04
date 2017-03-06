<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/13/2017
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Department</title>
</head>
<body>
    <div class="department">
        <p><b>Department : </b> ${department.id}</p>
        <p><b>Name : </b> ${department.name}</p>
        <p><b>Number : </b> ${department.number}</p>
        <p><b>Description : </b> ${department.description}</p>
    </div>
    <a href="/getAllDepartment">Show all departments</a>
</body>
</html>
