<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/25/2017
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<c:url value="../../../js/addjournal.js"/>" type="text/javascript"></script>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Journal</title>
</head>
<body>
    <sec:authentication var="user" property="principal"/>
    <div class="createJournal">
        <p>Select project :</p>
        <form>
            <select id="project">
                <c:forEach items="${user.managedProjects}" var="project">
                    <option value="${project.id}">${project.name}</option>
                </c:forEach>
            </select>
            <input type="button" onclick="selectProject()" value="Select"/>
        </form>
        <span id="sprints"></span>
        <span id="tasks"></span>
        <span id="employees"></span>

        <form>
            <p>Description :</p>
            <textarea type="text" id="description" placeholder="Set here description..." rows="5" cols="45"></textarea>
            <input type="button" onclick="saveAdded()" value="SAVE"/>
        </form>

        <a href="/manager/profileMgr"><button>Cancel</button></a>
    </div>
</body>
</html>
