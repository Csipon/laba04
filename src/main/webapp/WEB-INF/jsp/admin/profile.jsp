<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
    <title>Profile</title>
</head>
<body>
    <div>
        <p><b>Admin :             </b> ${admin.id}</p>
        <p><b>Name :                </b> ${admin.name} </p>
        <p><b>Surname :       </b> ${admin.surname}</p>
        <p><b>Login :       </b> ${admin.login}</p>
        <p><b>Password :       </b> ${admin.password}</p>
        <br>
        <a href="/admin/profileAdmin"><button>Back</button></a>
    </div>
</body>
</html>
