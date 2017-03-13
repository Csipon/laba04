<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/14/2017
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Update customer</title>
</head>
<body>
    <div>
        <form action="/admin/updateCustomer" method="post">
            <input type="hidden" name="id" value="${customer.id}" /></p>

            Name : <input type="text" value="${customer.name}" name="name"/></p>
            Surname : <input type="text" value="${customer.surname}" name="surname"/></p>
            Password : <input type="text" value="${customer.password}" name="password"/></p>
            Description : <input type="text" value="${customer.description}" name="description"/></p>
            Company name : <input type="text" value="${customer.companyName}" name="companyName"/></p>
            <br/>
            <input type="submit" value="SAVE">
        </form>
        <a href="/admin/profileAdmin"><button>Back</button></a>
    </div>
</body>
</html>
