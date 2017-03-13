<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 1/22/2017
  Time: 12:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/menu.css" />" rel="stylesheet">
    <title>Create new project</title>
</head>
<body>
<div>
    <h1>Creating</h1>
    <form action="/admin/addProject" method="post">
        <p>Project name :           <input type="text" name="name"/></p>
        <p>Planed budget :          <input type="number" name="planedBudget"/></p>
        <p>Paid :                   <input type="number" name="paid"/></p>
        <p>Additional payments :    <input type="number" name="additionalPayments"/></p>
        <p>Finish project :         <input id="date" type="date" /></p>
        Customer :
        <select name="idCustomer">
            <c:forEach items="${customers}" var="customer">
                <option value="${customer.id}">${customer.name}</option>
            </c:forEach>
        </select>
        <td><p>Description : </p></td>
        <td><textarea type="text" name="description" placeholder="Set here description..." rows="5" cols="45"></textarea></td>
        <input id="time" type="hidden" name="finishP" />
        <br/>
        <br/>

        <input type="submit" onclick="displayDate()" value="SUBMIT">
        <input type="reset" value="REST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <a href="/admin/profileAdmin"><button>Back</button></a>
</div>
    <script>
        function setDate(){
            var d = document.getElementById("date").value;
            return new Date(d).getTime();
        }

        function displayDate() {
            document.getElementById("time").value = setDate();
        }
    </script>
</body>
</html>
