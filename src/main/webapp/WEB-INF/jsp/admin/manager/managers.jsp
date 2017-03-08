<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/17/2017
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Managers</title>
</head>
<body>
    <div align="center">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Login</th>
                <th>Description</th>
                <th>Hiredate</th>
                <th>Password</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${managerList}" var="manager">
                <tr>
                    <th>${manager.id}</th>
                    <th><a href="/idManager?id=${manager.id}">${manager.firstName}</a></th>
                    <th>${manager.lastName}</th>
                    <th>${manager.login}</th>
                    <th>${manager.description}</th>
                    <th>${manager.hiredate}</th>
                    <th><c:forEach begin="1" step="1" end="${manager.password.length()}">*</c:forEach></th>
                    <th><button><a href="/admin/getManagerUpdate?id=${manager.id}">Update</a></button></th>
                    <th>
                        <a href="/admin/deleteManager?id=${manager.id}&password=${manager.password}">
                            <img src="../../../../images/delete.png"></a>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="/admin/profileAdmin">
            <button>Back</button>
        </a>
    </div>
</body>
</html>
