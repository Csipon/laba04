<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Update sprint</title>
</head>
<body>
    <c:if test="${role ne 'MANAGER' or empty role}">
        <c:redirect url="/login"/>
    </c:if>
    <div>
        <h1>Create sprint</h1>
        <form action="/updateSprint" method="post">
            <input type="hidden" name="id" value="${sprint.id}">
            <p>Name :                   <input type="text" name="name" value="${sprint.name}"/></p>
            <p>Description :            <input type="text" name="description" value="${sprint.description}"/></p>
            <p>Set previous sprint :
                <select name="idPreviousSprint">
                    <c:choose>
                        <c:when test="${sprint.previousSprint eq null}">
                            <option>---</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${sprint.previousSprint.id}">${sprint.previousSprint.name}</option>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach items="${sprints}" var="spt">
                        <c:if test="${spt.id ne sprint.previousSprint.id}">
                            <option value="${spt.id}">${spt.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </p>
            <br/>
            <br/>
            <input type="submit" value="SAVE">
        </form>

        <p>Sprint tasks :
            <c:forEach items="${sprint.tasks}" var="task">
                <p><a href="">${task.name} </a> Qualification : ${task.levelQualification}</p>
            </c:forEach>
        </p>
        <a href="/admin"><button>Back</button></a>
    </div>
</body>
</html>
