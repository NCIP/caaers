<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}<a href=  "/caaers/Help/caaersSource_full.htm#section3eventandresponsedescription.htm" target="_blank"><img align="right" src="/caaers/images/book.gif" border="0" alt="HelpIndex" title="Help Index"></a></title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <style type="text/css">
        textarea {
            width: 30em;
            height: 12em;
        }
    </style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="instructions">
    </jsp:attribute>
    <jsp:attribute name="singleFields">
        <c:forEach items="${fieldGroups.desc.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>