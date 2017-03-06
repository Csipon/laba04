<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 23.07.2016
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../resource/menu.css" />" rel="stylesheet">
    <title>Result time</title>
</head>
<body>
    <p>Время :${calendar.time}</p>
    <a href="/checkLogin"><button>Update</button></a>
</body>
</html>
