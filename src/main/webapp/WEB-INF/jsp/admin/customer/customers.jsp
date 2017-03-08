<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/13/2017
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Customers</title>
</head>
<body>
    <div align="center">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Company name</th>
                <th>Description</th>
            </tr>
            <c:forEach items="${customerList}" var="customer">
                <tr>
                    <th>${customer.id}</th>
                    <th><a href="/idCustomer?id=${customer.id}">${customer.name}</a></th>
                    <th>${customer.surname}</th>
                    <th>${customer.companyName}</th>
                    <th>${customer.description}</th>
                    <th><button><a href="/admin/getCustomerUpdate?id=${customer.id}">Update</a></button></th>
                    <th>
                        <a href="/admin/deleteCustomer?id=${customer.id}&password=${customer.password}">
                            <img src="../../../../images/delete.png"></a>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="/admin">
            <button>Back</button>
        </a>
    </div>
</body>
</html>
