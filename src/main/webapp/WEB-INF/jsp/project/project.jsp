<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/project.css" />" rel="stylesheet">
    <script src="<c:url value="../../../js/project.js"/>" type="text/javascript"></script>
    <title>Project</title>
</head>
<body>
    <div class="container">
        <div class="project">
            <p><b>Project :             </b> ${project.id}</p>
            <p><b>Name :                </b> ${project.name} </p>
            <p><b>Planed budget :       </b> ${project.planedBudget} $</p>
            <p><b>Paid :                </b> ${project.paid} $</p>
            <p><b>Date start :          </b> ${project.start}</p>
            <p><b>Date finish :         </b> ${project.finish}</p>
            <p><b>Additional payments : </b> ${project.additionalPayments} $</p>
            <p><b>Description :         </b> ${project.description}</p>
        </div>
        <sec:authorize access="hasRole('ROLE_Administrator') || hasRole('ROLE_ProjectManager') || hasRole('ROLE_Employee')">
            <p>
                <b>Customer :</b>
                <a href="/maker/idCustomer?id=${project.customer.id}">${project.customer.name} ${project.customer.surname}</a>
            </p>
            <sec:authorize access="hasRole('ROLE_ProjectManager')">
                <div class="addSprint">
                    <a href="/manager/toCreateSprint?idProject=${project.id}"><button>Add sprint</button></a>
                </div>
            </sec:authorize>
            <div class="sprints">
                <c:forEach items="${project.sprints}" var="sprint">
                    <div class="sprintItem">
                        <h3>${sprint.name}</h3>
                        <p>${sprint.description}</p>
                        <a href="/maker/idSprint?id=${sprint.id}">Go to sprint</a>
                    </div>
                </c:forEach>
            </div>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_Administrator')">
            <p>Add this project to project manager for managed</p>
            <form action="/admin/addManagerToProject" method="post">
                <input type="hidden" name="idProject" value="${project.id}">
                <select name="idManager">
                    <option>---</option>
                    <c:forEach items="${managers}" var="manager">
                        <option value="${manager.id}">${manager.name} ${manager.surname}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="ADD">
            </form>
            <br/>
            <p>Show employees who are not working on this project</p>
            <button id="showEmployees" onclick="showFreeEmployeesForSetOnProject('${project.id}')">Show</button>
            <span id="listEmployees"></span>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_Customer')">
            <c:choose>
                <c:when test="${not empty statistic}">
                    <p>loaded</p>
                    <p>project ready on : ${statistic.get(project.id)}%</p>
                    <c:forEach items="${project.sprints}" var="sprint">
                        <p>name sprint : ${sprint.name} , over ${statistic.get(sprint.id)}%</p>
                        <p>date created : ${sprint.created}</p>
                        <p>started : ${sprint.started}</p>
                    </c:forEach>
                    <a href="/loadStatistic?idProject=${project.id}"><button>Update</button></a>
                </c:when>
                <c:otherwise>
                    <a href="/loadStatistic?idProject=${project.id}"><button>Load statistic</button></a>
                </c:otherwise>
            </c:choose>
        </sec:authorize>
        <div style="clear:both;"></div>
        <br/>

        <sec:authorize access="hasRole('ROLE_Administrator')">
            <a href="/admin/profileAdmin">
                <button>Back</button>
            </a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ProjectManager')">
            <a href="/manager/profileMgr">
                <button>Back</button>
            </a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_Employee')">
            <a href="/employee/profileEmp">
                <button>Back</button>
            </a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_Customer')">
            <a href="customer/profileCustomer">
                <button>Back</button>
            </a>
        </sec:authorize>


    </div>
</body>
</html>
