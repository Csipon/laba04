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
<sec:authentication var="user" property="principal" />
    <div class="sprintC">
        <div class="sprint" align="left">
            <div class="sprintDetails">
                <p><b>Sprint :                </b> ${sprint.id}</p>
                <p><b>Name :                  </b> ${sprint.name}</p>
                <p><b>Date created :          </b> ${sprint.created}</p>
                <p><b>Previous sprint :</b></p>
                <c:choose>
                    <c:when test="${sprint.previousSprint ne null}">
                        <a href="/idSprint?id=${sprint.previousSprint.id}">${sprint.previousSprint.name}</a>
                    </c:when>
                    <c:otherwise>
                        <p>This sprint without previous sprint</p>
                    </c:otherwise>
                </c:choose>
                <p><b>Description :           </b> ${sprint.description}</p>
                <p><b>Condition sprint :</b></p>
                <c:choose>
                    <c:when test="${finish and sprint.isStarted() and sprint.previousSprint.isFinished()}">
                        <p>Finish this sprint <a href="/manager/finishSprint?idSprint=${sprint.id}"><button>Finish</button></a></p>
                    </c:when>
                    <c:when test="${(sprint.previousSprint eq null or sprint.previousSprint.isFinished()) and !sprint.isStarted()}">
                        <p>This sprint may be started</p>
                        <sec:authorize access="hasRole('ROLE_ProjectManager')">
                            <a href="/manager/startSprint?idSprint=${sprint.id}"><button>Start sprint</button></a>
                        </sec:authorize>
                    </c:when>
                    <c:when test="${sprint.previousSprint ne null and !sprint.previousSprint.isFinished()}">
                        <p>This sprint not may be started, because previous sprint
                            is not finished <a href="/maker/idSprint?id=${sprint.previousSprint.id}">${sprint.previousSprint.name}</a></p>
                    </c:when>
                    <c:when test="${(sprint.previousSprint eq null or sprint.previousSprint.isFinished()) and sprint.isStarted()}">
                        <p>This sprint is started, make tasks that start next sprint :))</p>
                    </c:when>
                    <c:when test="${sprint.previousSprint.isFinished() and sprint.isFinished()}">
                        <p>This sprint is finished, go to next sprint :))</p>
                    </c:when>
                    <c:otherwise>
                        <p>With your sprint something problem</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <br/>
            <a href="/maker/idProject?id=${idProject}">
                <button>Back</button>
            </a>
            <sec:authorize access="hasRole('ROLE_ProjectManager')">
                <a href="/manager/toCreateTask?idSprint=${sprint.id}"><button>Add task</button></a>
                <button onclick="deleteSprint('${sprint.id}', '${idProject}')">Delete</button>
                <div class="inform">
                    <div class="tasks">
                        <c:forEach items="${sprint.tasks}" var="task">
                            <div class="task">
                                <h3>${task.name}</h3>
                                <p>${task.description}</p>
                                <a href="/maker/idTask?id=${task.id}&idSprint=${sprint.id}">Go to task</a>
                            </div>
                        </c:forEach>
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
            <div class="journals" align="left">
                <button onclick="loadSprintJournals('idSprint', '${sprint.id}')">load journals</button>
                <span id="fieldJournal"></span>
                <div class="makers">
                    <span id="fieldMakers"></span>
                </div>
                <div class="messages" align="left">
                    <button class="loadMessage" onclick="loadMessage('messageSprint', '${sprint.id}')">Load message</button>
                    <span id="taskMessages"></span>
                </div>
            </div>
        </sec:authorize>
    </div>
</body>
</html>
