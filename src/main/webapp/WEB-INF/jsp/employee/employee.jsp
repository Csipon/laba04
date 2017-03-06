<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/13/2017
  Time: 12:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Employee</title>
</head>
<body>
    <div class="employee">
        <p><b>Employee :</b> ${employee.id}</p>
        <p><b>First Name :</b> ${employee.name}</p>
        <p><b>Last Name :</b> ${employee.surname}</p>
        <p><b>Login :</b> ${employee.login}</p>
        <p><b>Password :</b>
            <span id="password">
                <c:forEach begin="1" step="1" end="${10}">*</c:forEach>
            </span>
            <c:if test="${employee.id eq user.id}">
                <button onclick="encodePassword()">Show</button>
                <button onclick="hidePassword()">Hide</button>
            </c:if>
        </p>
        <p><b>Department :</b> <a href="/idDepartment?id=${employee.department.id}">${employee.department.name}</a></p>
        <p><b>Description :</b> ${employee.description}</p>
        <p><b>Hiredate :</b> ${employee.hiredate}</p>
        <p><b>Projects : </b></p>

        <c:forEach items="${employee.projects}" var="project">
            <p><b><a href="/idProject?id=${project.id}">    ${project.name}</a></b></p>
        </c:forEach>

        <c:choose>
            <c:when test="${role eq 'EMPLOYEE'}">
                <button><a href="/profileEmp">Back</a></button>
            </c:when>
            <c:when test="${role eq 'MANAGER'}">
                <button><a href="/profileMgr">Back</a></button>
            </c:when>
            <c:when test="${role eq 'ADMIN'}">
                <button><a href="/getAllEmployee">Back</a></button>
            </c:when>
        </c:choose>
    </div>

    <script type="text/javascript">
        var password = "${employee.password}";
        function encodePassword(){
            var result = "";
            for (var i = 0; i < password.length; i++) {
                var temp = password.charCodeAt(i);
                if (i % 2 == 0) {
                    temp -= 25;
                } else {
                    temp -= 32;
                }
                result += String.fromCharCode(temp);
            }
            document.getElementById('password').innerHTML = result;
        }

        function hidePassword(){
            document.getElementById('password').innerHTML = '<c:forEach begin="1" step="1" end="${10}">*</c:forEach>';
        }
    </script>

</body>
</html>
