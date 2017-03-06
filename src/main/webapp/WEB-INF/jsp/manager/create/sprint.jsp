<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/23/2017
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Create sprint</title>
</head>
<body>
    <c:if test="${role ne 'MANAGER' or empty role}">
        <c:redirect url="/login"/>
    </c:if>
    <div>
        <h1>Create sprint</h1>
        <form action="/addSprint" method="post">
            <input type="hidden" name="idProject" value="${idProject}"/>
            <p>Name :            <input type="text" name="name"/></p>
            <td><p>Description : </p></td>
            <td><textarea type="text" name="description" placeholder="Set here description..." rows="5" cols="45"></textarea></td>
            <p>Set previous sprint :
                <select name="idPreviousSprint">
                    <option value="${null}">---</option>
                    <c:forEach items="${sprints}" var="sprint">
                        <option value="${sprint.id}">${sprint.name}</option>
                    </c:forEach>
                </select>
            </p>
            <input type="submit" value="SUBMIT">
            <input type="reset" value="REST">
        </form>
        <br/>
        <a href="/admin"><button>Back</button></a>
    </div>
</body>
</html>
