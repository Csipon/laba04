<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/13/2017
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Project</title>
</head>
<body>
    <div class="container">
        <c:if test="${empty role or (role ne 'CUSTOMER' and role ne 'ADMIN' and role ne 'MANAGER' and role ne 'EMPLOYEE')}">
            <c:redirect url="/login"/>
        </c:if>

        <div class="project">
            <p><b>Project :             </b> ${project.id}</p>
            <p><b>Name :                </b> ${project.name} </p>
            <p><b>Planed budget :       </b> ${project.planedBudget} $</p>
            <p><b>Paid :                </b> ${project.paid} $</p>
            <p><b>Date start :          </b> ${project.start}</p>
            <p><b>Date finish :         </b> ${project.finish}</p>
            <p><b>Additional payments : </b> ${project.additionalPayments} $</p>
            <p><b>Description :         </b> ${project.description}</p>
            <c:if test="${role eq 'ADMIN' or role eq 'MANAGER' or role eq 'EMPLOYEE'}">
                <p>
                    <b>Customer :</b>
                    <a href="/idCustomer?id=${project.customer.id}">${project.customer.name} ${project.customer.surname}</a>
                </p>

                <p>= = = = = = = = = = = = = = = = = = = = = = = = = = = =</p>
                <c:forEach items="${project.sprints}" var="sprint">
                    <h3>${sprint.name}</h3>
                    <p>${sprint.description}</p>
                    <a href="/idSprint?id=${sprint.id}">Go to sprint</a>
                    <p>= = = = = = = = = = = = = = = = = = = = = = = = = =</p>
                </c:forEach>
            </c:if>
        </div>
        <br/>
        <c:if test="${role eq 'MANAGER'}">

            <div class="addSprint">
                <a href="/toCreateSprint?idProject=${project.id}"><button>Add sprint</button></a>
            </div>
        </c:if>

        <c:if test="${role eq 'ADMIN'}">
            <p>Add this project to project manager for managed</p>
            <form action="/addManagerToProject" method="post">
                <input type="hidden" name="idProject" value="${project.id}">
                <select name="idManager">
                    <option>---</option>
                    <c:forEach items="${managers}" var="manager">
                        <option value="${manager.id}">${manager.firstName} ${manager.lastName}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="ADD">
            </form>

            <br/>

            <c:choose>
                <c:when test="${not empty employees}">
                    <c:forEach items="${employees}" var="employee">
                        <p> ${employee.id} : ${employee.firstName} ${employee.lastName}
                            , level qualification ${employee.levelQualification}, department ${employee.department.name}
                            <a href="/setEmpToProject?idEmployee=${employee.id}&idProject=${project.id}">
                                <img src="../../../images/add.png">
                            </a>
                        </p>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>Show employees who are not working on this project
                        <a href="/showEmpsFreeProject?idProject=${project.id}">
                            <button>Show</button>
                        </a>
                    </p>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:if test="${role eq 'CUSTOMER'}">
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
        </c:if>
        <div style="clear:both;"></div>
        <br/>
        <c:choose>
            <c:when test="${role eq 'ADMIN'}">
                <a href="/toMain">
                    <button>Back</button>
                </a>
            </c:when>
            <c:when test="${role eq 'MANAGER'}">
                <a href="/profileMgr">
                    <button>Back</button>
                </a>
            </c:when>
            <c:when test="${role eq 'EMPLOYEE'}">
                <a href="/profileEmp">
                    <button>Back</button>
                </a>
            </c:when><c:when test="${role eq 'CUSTOMER'}">
                <a href="/profileCustomer">
                    <button>Back</button>
                </a>
            </c:when>
        </c:choose>

    </div>
</body>
</html>
