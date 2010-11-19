<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Simple jsp page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/index.css" />">
</head>
<body>

    <h1>WebSSO Suite Authentication page</h1>
    <h2>Found URLs</h2>
    <ul>
        <c:forEach items="${urls}" var="url">
            <li><b>${url.key}</b>&nbsp;<a href="${url.value}">${url.value}</a>
        </c:forEach>
    </ul>

<hr>
<address>(&copy;) SB</address>
</body>
</html>