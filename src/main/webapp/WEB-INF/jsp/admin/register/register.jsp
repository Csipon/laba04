<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/style.css" />" rel="stylesheet">
    <script src="<c:url value="../../../../js/register.js"/>" type="text/javascript"></script>
    <title>Registration</title>
</head>
<body>
    <div>
        <h1>Registration</h1>
        <form name="register" action="/admin/addWorker" method="post">
            <p>Project manager :  <input type="checkbox" name="manager"/></p>
            <p>First name :  <input type="text" name="firstName"/></p>
            <p>Last name :   <input type="text" name="lastName"/></p>
            <p>Login :       <input type="text" name="login" onkeyup="validLogin()"/><span id="result"></span></p>
            <p>Password :    <input type="text" name="password"/></p>
            Department :
            <select name="idDept">
                <option>---</option>
                <c:forEach items="${departments}" var="department">
                    <option value="${department.id}">${department.name} ${department.number}</option>
                </c:forEach>
            </select>
            <br/>
            <br/>
            Level qualification :
            <select name="qualification">
                <c:forEach items="${quality}" var="qualification">
                    <option value="${qualification}">${qualification}</option>
                </c:forEach>
            </select>
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
