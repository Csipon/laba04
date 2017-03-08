<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <sec:authentication var="user" property="principal" />
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

        <sec:authorize access="hasRole('ROLE_Administrator')">
            <a href="/admin/profileAdmin">
                <button>Back</button>
            </a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ProjectManager')">
            <a href="/manager/profileMgr">
                <button>Back</button>
            </a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_Employee')">
            <a href="/employee/profileEmp">
                <button>Back</button>
            </a>
        </sec:authorize>
    </div>

    <script type="text/javascript">
        var password = "${employee.password}";
        function encodePassword(){
            document.getElementById('password').innerHTML = password;
        }

        function hidePassword(){
            document.getElementById('password').innerHTML = '<c:forEach begin="1" step="1" end="${10}">*</c:forEach>';
        }
    </script>

</body>
</html>
