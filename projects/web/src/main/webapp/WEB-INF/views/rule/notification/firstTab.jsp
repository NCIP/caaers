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
    <p>Feature in progress.... Decide the Type of Notification here and name it...</p>
    
    <chrome:division title="Create Trigger">

        <form:form cssClass="standard">


            <tags:errors path="*"/>
    
            <tags:tabFields tab="${tab}"/>

            <div id="ruleset-fields">
                <c:forEach items="${fieldGroups.ruleset.fields}" var="field">
                    <ruletags:row field="${field}"/>
                </c:forEach>
            </div>

            <c:if test="${empty tab}">
						    <tags:tabControls tabNumber="${0}" isLast="${false}"/>
            </c:if>
        
        </form:form>

		</chrome:division>
    
</body>


</html>