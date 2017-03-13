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
    <script src="<c:url value="../../../js/addjournal.js"/>" type="text/javascript"></script>
    <title>Profile</title>
</head>
<body>
    <sec:authentication var="user" property="principal" />
    <div class="container">
        <div align="right">
            <form action="/logout" method="post">
                <h3>${user.name} ${user.surname}</h3>
                <input type="submit" value="Exit"/>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
        <div align="center">
            <h2>Hi this is manager profile</h2>
        </div>
        <br/>
        <div class="toJournal" align="center">
            <p><a href="/manager/journal"><button>ADD employee</button></a> on task</p>
        </div>
        <br/>
        <br/>
        <br/>
        <br/>

        <c:if test="${user.projects ne null and not empty user.projects}">
            <p><b>Your projects : </b></p>
            <c:forEach items="${user.projects}" var="project">
                <p><b><a href="/maker/idProject?id=${project.id}">${project.name}</a></b></p>
            </c:forEach>
        </c:if>

        <c:if test="${user.subordinates ne null}">
            <p><b>Subordinates : </b></p>
            <c:forEach items="${user.subordinates}" var="employee">
                <p><b><a href="/maker/idEmp?id=${employee.id}">${employee.name} ${employee.surname}</a></b></p>
            </c:forEach>
        </c:if>


        <c:if test="${user.subordinates ne null}">
            <p><b>Managed projects : </b></p>
            <c:forEach items="${user.managedProjects}" var="project">
                <p><b><a href="/maker/idProject?id=${project.id}">${project.name}</a></b></p>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>
