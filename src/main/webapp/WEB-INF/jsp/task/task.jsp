<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:if test="${empty role or (role ne 'ADMIN' and role ne 'MANAGER' and role ne 'EMPLOYEE')}">
        <c:redirect url="/login"/>
    </c:if>
    <c:if test="${role eq 'EMPLOYEE' and not empty accept}">
        <p>${accept}</p>
    </c:if>
    <div class="task">
        <div class="infoTask">
            <p><b>Task :                </b> ${task.id}</p>
            <p><b>project :             </b> ${idProject}</p>
            <p><b>Name :                </b> ${task.name}</p>
            <p><b>Estimate :            </b> ${task.estimate} hours</p>
            <p><b>Level qualification : </b> ${task.levelQualification}</p>
            <p><b>Condition : </b>
                <c:choose>
                    <c:when test="${start and !task.isCompleted() and !task.isStarted()}">
                        <p>This task may be a started
                            <a href="/startTask?idTask=${task.id}&idSprint=${idSprint}">
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
                <p><b>Tasks which need make that to do this task</b></p>
                <c:forEach items="${task.tasks}" var="depend">
                    <h5>${depend.name}</h5>
                    <p>${depend.description}</p>
                    <a href="/idTask?id=${depend.id}">Go to task</a>
                    <p>= = = = = = = = = = = = = = = = = = = = = = = = = </p>
                </c:forEach>
            </c:if>
        </div>


        <c:if test="${task.isStarted() and !task.isCompleted()}">
            <a href="/finishTask?idTask=${task.id}&idSprint=${idSprint}"><button>Finish</button></a>
        </c:if>
        <div class="journals">
            <button onclick="loadJournals('task', '${task.id}', '${user.id}')">load journals</button>
            <span id="fieldJournal"></span>
            <span id="fieldMakers"></span>
        </div>
        <div class="clearfix"></div>

        <c:if test="${role eq 'EMPLOYEE'}">
            <button id="sentMessage" onclick="makeMessage('${user.id}', '${task.id}', '${idSprint}', '${idProject}','simple message')">Send message</button>
            <br/>
            <span id="fieldMessage"></span>
        </c:if>

        <c:if test="${role eq 'MANAGER'}">
            <button onclick="deleteTask('${task.id}', '${idSprint}', '${idProject}')">Delete</button>
        </c:if>


        <c:if test="${role eq 'MANAGER' or role eq 'EMPLOYEE'}">
            <button class="loadMessage" onclick="loadMessage('messageTask', '${task.id}')">Load message</button>
            <span id="taskMessages"></span>
        </c:if>
        <br/>
        <a href="/idSprint?id=${idSprint}&idProject=${idProject}">
            <button>Back</button>
        </a>
    </div>
</body>
</html>
