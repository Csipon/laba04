<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<c:url value="../../../js/task.js"/>" type="text/javascript"></script>
    <script src="<c:url value="../../../js/message.js"/>" type="text/javascript"></script>
    <script src="<c:url value="../../../js/journal.js"/>" type="text/javascript"></script>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Task</title>
</head>
<body>
    <div class="containerTask">
        <sec:authentication var="user" property="principal" />
        <div class="infoTask">
            <p><b>Task :                </b> ${task.id}</p>
            <p><b>project :             </b> ${idProject}</p>
            <p><b>Name :                </b> ${task.name}</p>
            <p><b>Estimate :            </b> ${task.estimate} hours</p>
            <p><b>Level qualification : </b> ${task.levelQualification}</p>
            <p><b>Condition : </b>
                <c:choose>
                    <c:when test="${sprintStarted and start and !task.isStarted()}">
                        <p>This task may be a started
                            <a href="/employee/startTask?idTask=${task.id}&idSprint=${idSprint}">
                                <button>Start</button>
                            </a>
                        </p>
                    </c:when>
                    <c:when test="${start and !task.isCompleted() and task.isStarted()}">
                        <p>This task already opened</p>
                    </c:when>
                    <c:when test="${!start and !task.isCompleted() and !task.isStarted()}">
                        <p>This task not may be a open</p>
                    </c:when>
                    <c:when test="${start and task.isCompleted() and task.isStarted()}">
                        <p>This task is completed, go to next task :))</p>
                    </c:when>
                    <c:otherwise>
                        <p>With your task something strange</p>
                    </c:otherwise>
                </c:choose>
            </p>
            <p><b>Description :         </b> ${task.description}</p>
            <c:if test="${not empty task.tasks}">
                <p>Tasks which need make that to do this task</p>
                <c:forEach items="${task.tasks}" var="depend">
                    <h3>${depend.name}</h3>
                    <p>${depend.description}</p>
                    <a href="/maker/idTask?id=${depend.id}">Go to task</a>
                    <p>= = = = = = = = = = = = = = = = = = = = = = = = = </p>
                </c:forEach>
            </c:if>
            <c:if test="${task.isStarted() and !task.isCompleted()}">
                <a href="/employee/finishTask?idTask=${task.id}&idSprint=${idSprint}"><button>Finish</button></a>
            </c:if>

            <sec:authorize access="hasRole('ROLE_ProjectManager') or hasRole('ROLE_Employee')">
                <button id="sentMessage" onclick="makeMessage('${user.id}', '${task.id}', '${idSprint}', '${idProject}','simple message')">Send message</button>
                <br/>
                <span id="fieldMessage"></span>
                <br/>
                <button class="loadMessage" onclick="loadMessage('messageTask', '${task.id}')">Load message</button>
                <span id="taskMessages"></span>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ProjectManager')">
                <button onclick="deleteTask('${task.id}', '${idSprint}', '${idProject}')">Delete</button>
            </sec:authorize>
            <br/>
            <a href="/maker/idSprint?id=${idSprint}&idProject=${idProject}">
                <button>Back</button>
            </a>
        </div>


        <div class="containerJournal">
            <div class="journals">
                <sec:authorize access="hasRole('ROLE_ProjectManager')">
                    <button onclick="loadSprintJournals('task', '${task.id}')">load journals</button>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_Employee')">
                    <button onclick="loadJournals('task', '${task.id}', '${user.id}')">load journals</button>
                </sec:authorize>
                <span id="fieldJournal"></span>
                <div class="makers">
                    <span id="fieldMakers"></span>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
