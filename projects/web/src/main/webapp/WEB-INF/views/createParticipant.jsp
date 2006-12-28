<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>${action} Participant</title>
    <style type="text/css">
        div.label {
            width: 35%;
        }
        div.submit {
            text-align: right;
        }
        form {
            width: 20em;
        }
    </style>
</head>
<body>
<h1>${action} Participant</h1>
<%--<c:url value="/pages/createParticipant?id=${studyId}" var="formAction"/>--%>
<c:url value="/pages/createParticipant?id=1" var="formAction"/>
<form:form action="${formAction}" method="post">

	<div class="row">
        <div class="label">
            <form:label path="instituitionalPatientNumber">Institutional Patient Number</form:label>
        </div>
        <div class="value">
            <form:input path="instituitionalPatientNumber"/>
        </div>
    </div>
    <div class="row">
        <div class="label">
            <form:label path="institution">Institution</form:label>
        </div>
        <div class="value">
            <form:input path="institution"/>
        </div>
    </div>
    <div class="row">
        <div class="label">
            <form:label path="studyParticipantName">Study Participant Name</form:label>
        </div>
        <div class="value">
            <form:input path="studyParticipantName"/>
        </div>
    </div>
    <div class="row">
        <div class="label">
            <form:label path="firstName">First Name</form:label>
        </div>
        <div class="value">
            <form:input path="firstName"/>
        </div>
    </div>
    <div class="row">
        <div class="label">
            <form:label path="lastName">Last Name</form:label>
        </div>
        <div class="value">
            <form:input path="lastName"/>
        </div>
    </div> 
    <div class="row">
        <div class="label">
            <form:label path="dateOfBirth">Date of Birth (mm/dd/yyyy)</form:label>
        </div>
        <div class="value">
            <form:input path="dateOfBirth"/>
        </div>
	</div>
	
	<div class="row">
        <div class="label">
            <form:label path="gender">Gender</form:label>
        </div>
        <div class="value">
            <form:select path="gender">
            	<form:options items="${genders}"/>
            </form:select>
        </div>
	</div> 
	
	<div class="row">
        <div class="label">
            <form:label path="race">Race</form:label>
        </div>
        <div class="value">
            <form:input path="race"/>
        </div>
    </div>
    
    <div class="row">
        <div class="label">
            <form:label path="lastName">Ethnicity</form:label>
        </div>
        <div class="value">
            <form:input path="ethnicity"/>
        </div>
    </div>
	
	<div class="row">
        <div class="label">
            <form:label path="gender">Study Sites</form:label>
        </div>
        <div class="value">
            <form:select path="studySiteArray">
                <c:forEach var="studySite" items="${studySites}" varStatus="status"> 
            	<form:option value="${status.count - 1 }" label="${studySite.siteStudyNames}"/>
            	</c:forEach>
            </form:select>
        </div>
	</div> 
	
    <div class="row">
        <div class="submit">
            <input type="submit" value="Create"/>
        </div>
    </div>
</form:form>
</body>
</html>