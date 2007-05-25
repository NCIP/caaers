<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ruletags" tagdir="/WEB-INF/tags/rule"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <title>Not implemented</title>
    <style>
    #name {width:300px}
    </style>
</head>
<body>
    <chrome:division title="Configure Notification">
    <tags:tabForm tab="${tab}" flow="${flow}" willSave="false">
		<jsp:attribute name="singleFields">
           
            <div id="ruleset-fields">
                <c:forEach items="${fieldGroups.ruleset.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
               <input type="hidden" name="lastPointOnScale" value=""/>
               <input type="hidden" name="notificationType" value="EMAIL_NOTIFICATION" />
            </div>
            
		</jsp:attribute>
	</tags:tabForm> 
	</chrome:division>
    
</body>


</html>