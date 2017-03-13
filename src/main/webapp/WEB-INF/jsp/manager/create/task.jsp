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
    <script src="<c:url value="../../../../js/task.js"/>" type="text/javascript"></script>
    <title>Create task</title>
</head>
<body>
    <div>
        <h1>Create task</h1>
        <button onclick="showTasks('${sprint}')">Show tasks</button>
        <form name="createTask">
            <input id="idSprint" type="hidden" name="idSprint" value="${sprint}">
            <p>Name :            <input id="name" type="text" name="name"/></p>
            <p>Estimate task :   <input id="estimate" type="number" name="estimate"/></p>

            <div id="dependentTasks">
                 <span id="listTasks"></span>
            </div>

            <td><p>Description : </p></td>
            <td><textarea type="text" id="description" name="description" placeholder="Set here description..." rows="5" cols="45"></textarea></td>
            <p>Set level qualification :
                <select id="level" name="qualification">
                    <c:forEach items="${qualifications}" var="quality">
                        <option value="${quality}">${quality}</option>
                    </c:forEach>
                </select>
            </p>
            <button onclick="addTask()">SUBMIT</button>
            <input type="reset" value="REST">
        </form>

        <br/>
        <a href="/idSprint?id=${sprint}"><button>Cancel</button></a>
    </div>
<script>
</script>
</body>
</html>
