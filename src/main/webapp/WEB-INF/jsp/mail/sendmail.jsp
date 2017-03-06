<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOUSE
  Date: 27.07.2016
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../../resource/style.css" />" rel="stylesheet">
    <title>JavaMail</title>
</head>
<body>
    <form action="/send" method="POST">
        <table>
            <tr>
                <td>Send to:</td>
                <td><input type="text" name="to"/></td>
            </tr>
            <tr>
                <td>Subject:</td>
                <td><input type="text" name="subject"/></td>
            </tr>
        </table>
            <hr/>
            <textarea type="text" name="body" rows="5" cols="45">Message text</textarea>
            <br/>
            <br/>
            <input type="submit" value="Send message!"/>
    </form>
</body>
</html>
