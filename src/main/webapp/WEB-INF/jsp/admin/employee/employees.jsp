<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/13/2017
  Time: 12:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Employees</title>
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
        </tr>
        <c:forEach items="${empList}" var="employee">
            <tr>
                <th>${employee.id}</th>
                <th><a href="/idEmp?id=${employee.id}">${employee.name}</a></th>
                <th>${employee.surname}</th>
                <th>${employee.login}</th>
                <th>${employee.description}</th>
                <th>${employee.hiredate}</th>
                <th><c:forEach begin="1" step="1" end="10">*</c:forEach></th>

                <th><button><a href="/admin/getEmpUpdate?id=${employee.id}">Update</a></button></th>
                <th>
                    <a href="/deleteEmployee?id=${employee.id}&password=${employee.password}">
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
