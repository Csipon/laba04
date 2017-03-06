<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../../resource/style.css" />" rel="stylesheet">
    <title>Registration</title>
</head>
<body>
    <c:if test="${role ne 'ADMIN' or empty role}">
        <c:redirect url="/login"/>
    </c:if>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <div>
        <h1>Registration</h1>
        <form name="register" action="/addWorker" method="post">
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
        <a href="/admin"><button>Back</button></a>
    </div>
    <script>
        var request;
        var v;
        function validLogin() {
            v = document.register.login.value;
            var url = "valid?login=" + v;

            if (window.XMLHttpRequest) {
                request = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            }

            try {
                request.onreadystatechange = getInfo;
                request.open("GET", url, true);
                request.send();
            }
            catch (e) {
                alert("Unable to connect to server");
            }
        }

        function getInfo() {
            if (request.readyState == 4) {
                var val = request.responseText;
                if(val == 'true' && v.length != 0) {
                    document.getElementById('result').innerHTML = 'This login is available';
                    document.getElementById('result').style.color = 'green';
                    document.getElementById('submit').disabled = false;
                }else if (v.length == 0){
                    document.getElementById('result').innerHTML = '';
                    document.getElementById('submit').disabled = true;
                }else {
                    document.getElementById('result').innerHTML = 'This login is not available';
                    document.getElementById('result').style.color = 'red';
                    document.getElementById('submit').disabled = true;
                }
            }
        }
    </script>
</body>
</html>
