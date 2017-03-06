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
    <link href="<c:url value="../../../resource/menu.css" />" rel="stylesheet">
    <title>Customer</title>
</head>
<body>

    <div class="customer">
        <p><b>Customer : </b> ${customer.id}</p>
        <p><b>Name : </b> ${customer.name}</p>
        <p><b>Surname : </b> ${customer.surname}</p>
        <p><b>Login : </b> ${customer.login}</p>
        <p><b>Password :</b>
            <span id="password">
                <c:forEach begin="1" step="1" end="${customer.password.length()}">*</c:forEach>
            </span>
            <c:if test="${customer.id eq user.id}">
                <button onclick="encodePassword()">Show</button>
                <button onclick="hidePassword()">Hide</button>
            </c:if>
        </p>

        <p><b>Company name : </b> ${customer.companyName}</p>
        <p><b>Description : </b> ${customer.description}</p>
    </div>
    <c:choose>
        <c:when test="${role eq 'ADMIN'}">
            <a href="/getAllCustomer">Back</a>
        </c:when>
        <c:when test="${role eq 'EMPLOYEE'}">
            <a href="/profileEmp">Back</a>
        </c:when>
        <c:when test="${role eq 'MANAGER'}">
            <a href="/profileMgr">Back</a>
        </c:when>
        <c:when test="${role eq 'CUSTOMER'}">
            <a href="/profileCustomer">Back</a>
        </c:when>
    </c:choose>


    <script type="text/javascript">
        var password = "${customer.password}";
        function encodePassword(){
            var result = "";
            for (var i = 0; i < password.length; i++) {
                var temp = password.charCodeAt(i);
                if (i % 2 == 0) {
                    temp -= 25;
                } else {
                    temp -= 32;
                }
                result += String.fromCharCode(temp);
            }
            document.getElementById('password').innerHTML = result;
        }

        function hidePassword(){
            document.getElementById('password').innerHTML = '<c:forEach begin="1" step="1" end="${customer.password.length()}">*</c:forEach>';
        }
    </script>
</body>
</html>
