<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/21/2017
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <script src="<c:url value="../../../../js/register.js"/>" type="text/javascript"></script>
    <title>Register customer</title>
</head>
<body>
    <div>
        <h1>Registration</h1>
        <form name="register" action="/admin/addCustomer" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <p>Name :  <input type="text" name="name"/></p>
            <p>Surname :   <input type="text" name="surname"/></p>
            <p>Login :       <input type="text" name="login" onkeyup="validLogin()"/><span id="result"></span></p>
            <p>Password :    <input type="text" name="password"/></p>
            <p>Company name :    <input type="text" name="companyName"/></p>
            <td><p>Description : </p></td>
            <td><textarea type="text" name="description" placeholder="Set here description..." rows="5" cols="45"></textarea></td>
            <br/>
            <br/>

            <input type="submit" id="submit" value="SUBMIT">
            <input type="reset" value="REST">
        </form>
        <a href="/admin/profileAdmin"><button>Back</button></a>
    </div>
</body>
</html>
