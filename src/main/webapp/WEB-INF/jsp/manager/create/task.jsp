<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/24/2017
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Create task</title>
</head>
<body>
    <c:if test="${role ne 'MANAGER' or empty role}">
        <c:redirect url="/login"/>
    </c:if>
    <div>
        <h1>Create task</h1>
        <form action="/addTask" method="post">
            <input type="hidden" name="idProject" value="${project}"/>
            <input type="hidden" name="idSprint" value="${sprint}">
            <p>Name :            <input type="text" name="name"/></p>
            <p>Estimate task :   <input type="number" name="estimate"/></p>
            <td><p>Description : </p></td>
            <td><textarea type="text" name="description" placeholder="Set here description..." rows="5" cols="45"></textarea></td>
            <p>Set level qualification :
                <select name="qualification">
                    <c:forEach items="${qualifications}" var="quality">
                        <option value="${quality}">${quality}</option>
                    </c:forEach>
                </select>
            </p>
            <input type="submit" value="SUBMIT">
            <input type="reset" value="REST">
        </form>
        <br/>
        <a href="/idSprint?id=${sprint}"><button>Cancel</button></a>
    </div>
</body>
</html>
