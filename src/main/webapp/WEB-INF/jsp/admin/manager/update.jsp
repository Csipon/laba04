<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/14/2017
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Update Employee</title>
</head>
<body>
    <c:if test="${role ne 'ADMIN' or empty role}">
        <c:redirect url="/login"/>
    </c:if>
    <div>
        <form action="/updateManager" method="post">
            <input type="hidden" name="id" value="${manager.id}" /></p>

            First name : <input type="text" value="${manager.firstName}" name="firstName"/></p>
            Last name : <input type="text" value="${manager.lastName}" name="lastName"/></p>
            Login : <input type="text" value="${manager.login}" name="login"/></p>
            Password : <input type="text" value="${manager.password}" name="password"/></p>
            Description : <input type="text" value="${manager.description}" name="description"/></p>
            Department :
            <select name="idDept">
                <c:forEach items="${departments}" var="department">
                    <option value="${department.id}">${department.name} ${department.number}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="SAVE">
        </form>
        <a href="/admin"><button>Back</button></a>
    </div>
</body>
</html>
