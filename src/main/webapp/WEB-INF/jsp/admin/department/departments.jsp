<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/13/2017
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <script src="<c:url value="../../../../js/department.js"/>" type="text/javascript"></script>
    <title>Departments</title>
</head>
<body>
    <div align="center">
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Number</th>
            <th>Description</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${deptList}" var="department">
            <tr>
                <th>${department.id}</th>
                <th><a href="/maker/idDepartment?id=${department.id}">${department.name}</a></th>
                <th>${department.number}</th>
                <th>${department.description}</th>
                <th>
                    <button onclick="confirmDelete('${department.id}')">Delete</button>
                </th>
            </tr>
        </c:forEach>
    </table>
    <span id="confirm"></span>
    <br/>
        <a href="/admin/profileAdmin">
            <button>Back</button>
        </a>
</div>

    <script type="text/javascript">
        function confirmDelete(id){
            if(window.confirm("Are you confirm delete department?")){
                document.getElementById("confirm").innerHTML =
                    '<br/>'+
                    '<p>Are you really want delete this department? With id ='+ id +'</p>' +
                    '<a href="" onclick="deleteDepartment(' + id + ', ' + true + ')"><img src="../../../../images/delete.png"></a>' +
                    '<br/>';
            }
        }
    </script>
</body>
</html>
