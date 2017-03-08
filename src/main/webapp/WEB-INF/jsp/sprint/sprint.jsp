<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/23/2017
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<c:url value="../../../js/message.js"/>" type="text/javascript"></script>
    <script src="<c:url value="../../../js/sprint.js"/>" type="text/javascript"></script>
    <script src="<c:url value="../../../js/journal.js"/>" type="text/javascript"></script>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Sprint</title>
</head>
<body>
    <div class="sprintC" align="center">
        <div class="sprint" align="left">
            <div class="sprintDetails">
                <p><b>Sprint :                </b> ${sprint.id}</p>
                <p><b>Name :                  </b> ${sprint.name}</p>
                <p><b>Date created :          </b> ${sprint.created}</p>
                <p>
                    <b>Previous sprint :</b>
                    <c:choose>
                        <c:when test="${sprint.previousSprint ne null}">
                            <a href="/idSprint?id=${sprint.previousSprint.id}">${sprint.previousSprint.name}</a>
                        </c:when>
                        <c:otherwise>
                            <p>This sprint without previous sprint</p>
                        </c:otherwise>
                    </c:choose>
                </p>
                <p><b>Description :           </b> ${sprint.description}</p>
                <p>
                    <b>Condition sprint :</b>
                    <c:choose>
                        <c:when test="${(sprint.previousSprint.finished or sprint.previousSprint eq null) and !sprint.started and !sprint.finished and role eq 'MANAGER'}">
                            <p>This sprint may be started</p>
                            <a href="/startSprint?idSprint=${sprint.id}"><button>Start sprint</button></a>
                        </c:when>
                        <c:when test="${(sprint.previousSprint.finished or sprint.previousSprint eq null) and !sprint.started and !sprint.finished and role ne 'MANAGER'}">
                            <p>This sprint may be started</p>
                        </c:when>
                        <c:when test="${sprint.previousSprint.finished}">
                            <p>This sprint not may be started, because previous sprint
                                is not finished <a href="/idSprint?id=${sprint.previousSprint.id}">${sprint.previousSprint.name}</a></p>
                        </c:when>
                        <c:when test="${(sprint.previousSprint.finished or sprint.previousSprint eq null) and sprint.started}">
                            <p>This sprint is started, make task that start next sprint :))</p>
                        </c:when>
                        <c:when test="${sprint.previousSprint.finished and sprint.finished}">
                            <p>This sprint is finished, go to next sprint :))</p>
                        </c:when>
                        <c:otherwise>
                            <p>With your sprint something problem</p>
                        </c:otherwise>

                    </c:choose>
                </p>
            </div>
            <br/>
            <sec:authorize access="hasRole('ROLE_ProjectManager')">
                <a href="/toCreateTask?idSprint=${sprint.id}&idProject=${idProject}"><button>Add task</button></a>
                <button onclick="deleteSprint('${sprint.id}', '${idProject}')">Delete</button>
                <div class="inform">
                    <div class="tasks" align="center">
                    <h1>Tasks</h1>
                    <c:forEach items="${sprint.tasks}" var="task">
                        <div class="task">
                            <h3>${task.name}</h3>
                            <p>${task.description}</p>
                            <a href="/idTask?id=${task.id}&idSprint=${sprint.id}">Go to task</a>
                        </div>
                    </c:forEach>
                </div>
                    <div class="messages">
                        <button class="loadMessage" onclick="loadMessage('messageSprint', '${sprint.id}')">Load message</button>
                        <span id="taskMessages"></span>
                    </div>
                </div>
                <br/>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_Employee')">
                <button onclick="loadTask('${sprint.id}', '${user.id}')">Load my tasks</button>
                <span id="myTask"></span>
            </sec:authorize>
        </div>


        <sec:authorize access="hasRole('ROLE_ProjectManager')">
            <div class="journals">
                <button onclick="loadSprintJournals('idSprint', '${sprint.id}')">load journals</button>
                <span id="fieldJournal"></span>
                <span id="fieldMakers"></span>
            </div>
        </sec:authorize>
        <a href="/idProject?id=${idProject}">
            <button>Back</button>
        </a>
    </div>
</body>
</html>
