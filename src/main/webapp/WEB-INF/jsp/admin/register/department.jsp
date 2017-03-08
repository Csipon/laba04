<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/23/2017
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Department</title>
</head>
<body>
    <div>
        <h1>Create department</h1>
        <form action="/admin/addDepartment" method="post">
            <p>Name :           <input type="text" name="name"/></p>
            <p>Number :         <input type="number" name="number"/></p>
            <p>Description :    <input type="text" name="description"/></p>
            <br/>
            <br/>

            <input type="submit" value="SUBMIT">
            <input type="reset" value="REST">
        </form>
        <a href="/admin"><button>Back</button></a>
    </div>
</body>
</html>
