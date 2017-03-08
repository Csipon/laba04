<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/30/2017
  Time: 6:18 PM
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
        <sec:authentication var="user" property="principal" />
        <form action="/logout" method="post">
            <h3>${user.name} ${user.surname}</h3>
            <input type="submit" value="Exit"/>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
    <div>
        <h2 align="center">Hi, this is customer profile</h2>
        <div class="customer">
            <c:forEach items="${projects}" var="project">
                <p>project name : ${project.name}</p>
                <p>budget : ${project.planedBudget}</p>
                <p>paid : ${project.paid}</p>
                <p>additional payments : ${project.additionalPayments}</p>
                <p>date start : ${project.start}</p>
                <p>date finish : ${project.finish}</p>
                <p>condition project : <a href="/idProject?id=${project.id}"><button>show details</button></a></p>
                <p id="condition"></p>
            </c:forEach>
        </div>
    </div>
    <script type="text/javascript">

    </script>
</body>
</html>
