<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/17/2017
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<c:url value="../../../js/manager.js"/>" type="text/javascript"></script>
    <script src="<c:url value="../../../js/password.js"/>" type="text/javascript"></script>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Project manager</title>
</head>
<body>
    <div class="container">
        <c:if test="${empty role or (role ne 'ADMIN' and role ne 'MANAGER')}">
            <c:redirect url="/login"/>
        </c:if>
        <div class="manager">
            <p><b>Project manager :</b> ${manager.id}</p>
            <p><b>First Name :</b> ${manager.name}</p>
            <p><b>Last Name :</b> ${manager.surname}</p>
            <p><b>Login :</b> ${manager.login}</p>
            <p><b>Password :</b>
                <span id="password">
                <c:forEach begin="1" step="1" end="${manager.password.length()}">*</c:forEach>
            </span>
                <c:if test="${manager.id eq user.id}">
                    <button onclick="encodePassword()">Show</button>
                    <button onclick="hidePassword()">Hide</button>
                </c:if>
            </p>

            <p><b>Department :</b> <a href="/idDepartment?id=${manager.department.id}">${manager.department.name}</a></p>
            <p><b>Description :</b> ${manager.description}</p>
            <p><b>Hiredate :</b> ${manager.hiredate}</p>

            <p><b>Projects : </b></p>
            <c:forEach items="${manager.projects}" var="project">
                <p><b><a href="/idProject?id=${project.id}">    ${project.name}</a></b></p>
            </c:forEach>

            <p><b>Subordinates : </b></p>
            <c:forEach items="${manager.subordinates}" var="employee">
                <p><b><a href="/idEmp?id=${employee.id}">${employee.name} ${employee.surname}</a></b></p>
            </c:forEach>

            <p><b>Managed projects : </b></p>
            <c:forEach items="${manager.managedProjects}" var="project">
                <p><b><a href="/idProject?id=${project.id}">${project.name}</a></b></p>
            </c:forEach>

            <a id="back" href="/getAllManager">Back</a>
        </div>

        <c:if test="${role eq 'ADMIN'}">
            <div class="addSubordinate">
                <button onclick="loadFreeEmployees('${manager.id}')">Load employees</button>
                <span id="successfulAdd"></span>
                <span id="employees"></span>
            </div>
        </c:if>
    </div>
    <script type="text/javascript">
        function hidePassword(){
            document.getElementById('password').innerHTML = '<c:forEach begin="1" step="1" end="${manager.password.length()}">*</c:forEach>';
        }
    </script>
</body>
</html>
