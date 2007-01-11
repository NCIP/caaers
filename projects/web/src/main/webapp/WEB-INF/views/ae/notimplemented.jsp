<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
</head>
<body>
    Not implemented
    <form:form>
        <tags:tabFields tab="${tab}"/>
    </form:form>
</body>
</html>