<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
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
