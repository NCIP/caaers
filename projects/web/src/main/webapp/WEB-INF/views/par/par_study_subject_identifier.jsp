<%--
    TODO: this entire flow's views need to be refactored.
--%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="par" tagdir="/WEB-INF/tags/par" %>

<html>
<head>
    <tags:includeScriptaculous/>
    <tags:stylesheetLink name="participant"/>

    <style type="text/css">
        .leftpane {
            width: 32em
        }
    </style>

</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}" formName="createParticipantForm" hideErrorDetails="false" willSave="false">

		 <jsp:attribute name="singleFields">
        <chrome:division>

            <c:forEach items="${fieldGroups.studySubjectIdentifier.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </chrome:division>



     </jsp:attribute>


</tags:tabForm>
</body>
</html>
