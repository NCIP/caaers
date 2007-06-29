<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
     <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
    
    </script>
    <style type="text/css">
    	div.row div.label { width: 16em; }
    	div.row div.value { margin-left: 17em;}

        textarea {
            width: 20em;
            height: 5em;
        }
    </style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="instructions">
            If applicable, enter Radiation Intervention Information for ${command.assignment.participant.fullName}
            on ${command.assignment.studySite.study.shortTitle}. YES
        </jsp:attribute>
    <jsp:attribute name="singleFields">
        <c:forEach items="${fieldGroups.desc.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>