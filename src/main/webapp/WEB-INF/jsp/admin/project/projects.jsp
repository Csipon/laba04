<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/13/2017
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <script src="<c:url value="../../../../js/project.js"/>" type="text/javascript"></script>
    <title>Projects</title>
</head>
<body>
    <div align="center">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Planed budget</th>
                <th>Paid</th>
                <th>Additional payments</th>
                <th>Customer</th>
                <th>Description</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${projectList}" var="project">
                <tr>
                    <th>${project.id}</th>
                    <th><a href="/maker/idProject?id=${project.id}">${project.name}</a></th>
                    <th>${project.planedBudget}</th>
                    <th>${project.paid}</th>
                    <th>${project.additionalPayments}</th>
                    <th><a href="/maker/idCustomer?id=${project.customer.id}">${project.customer.name} ${project.customer.surname}</a></th>
                    <th>${project.description}</th>
                    <th>
                        <button onclick="confirmDelete('${project.id}')">Delete</button>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <br>
        <span id="confirm"></span>
        <a href="/admin/profileAdmin">
            <button>Back</button>
        </a>
    </div>
    <script type="text/javascript">
        function confirmDelete(id){
            if(window.confirm("Are you confirm delete project?")){
                document.getElementById("confirm").innerHTML =
                    '<br/>'+
                    '<p>Are you really want delete this project? With id ='+ id +'</p>' +
                    '<a href="#" onclick="deleteProject(' + id + ', ' + true + ')"><img src="../../../../images/delete.png"></a>' +
                    '<br/>';
            }
        }
    </script>
</body>
</html>
