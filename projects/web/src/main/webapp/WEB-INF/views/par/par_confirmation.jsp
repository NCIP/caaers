<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<link rel="stylesheet" type="text/css"
      href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Review and Submit</title>
    <tags:stylesheetLink name="participant"/>
    <script>
        function submitPage(s) {
            document.getElementById("nextView").value = s;
            document.getElementById("command").submit();
        }

    </script>
</head>
<body>


<tags:tabForm tab="${tab}" flow="${flow}"
              title="${command.participant.lastName}, ${command.participant.firstName}"
              willSave="false">

<jsp:attribute name="repeatingFields">
    	<c:if test="${(empty command.participant.id) or ( command.participant.id le 0) }">
            <input type="hidden" name="_finish" value="true"/>
        </c:if>
    <chrome:division title="Assigned to Studies">
        <table class="tablecontent" width="100%">
            <tr>
                <th scope="col">Study Primary ID</th>
                <th scope="col">Study Short Title</th>
                <th scope="col">Site</th>
                <th scope="col">Study Subject Identifier</th>
            </tr>
            <tr class="results">
                <td>&nbsp;</td>
                <td>${command.assignment.studySite.study.shortTitle}</td>
                <td>${command.assignment.studySite.organization.name}</td>
                <td>${command.assignment.studySubjectIdentifier}</td>
            </tr>
        </table>
        <br>
    </chrome:division>
    <chrome:division title="Subject Details">
        <br>
        <table id="test2" class="single-fields" width="100%">
            <tr>
                <td>
                    <div class="leftpane">
                        <div class="row">
                            <div class="label">First name:</div>
                            <div class="value">${command.participant.firstName}</div>
                        </div>
                        <div class="row">
                            <div class="label">Last name:</div>
                            <div class="value">${command.participant.lastName}</div>
                        </div>

                        <div class="row">
                            <div class="label">Maiden name:</div>
                            <div class="value">${command.participant.maidenName}</div>
                        </div>

                        <div class="row">
                            <div class="label">Middle name:</div>
                            <div class="value">${command.participant.middleName}</div>
                        </div>
                </td>
                <td>
                    <div class="row">
                        <div class="label">Date of birth:</div>
                        <div class="value">${command.participant.dateOfBirth}</div>
                    </div>

                    <div class="row">
                        <div class="label">Ethnicity:</div>
                        <div class="value">${command.participant.ethnicity}</div>
                    </div>

                    <div class="row">
                        <div class="label">Race:</div>
                        <div class="value">${command.participant.race}</div>
                    </div>


                    <div class="row">
                        <div class="label">Gender:</div>
                        <div class="value">${command.participant.gender}</div>
                    </div>

                </td>
            </tr>
        </table>
    </chrome:division>
	
		<c:if test="${not empty command.participant.identifiers}">
            <chrome:division title="Identifiers">
                <table class="tablecontent">
                    <tr>
                        <th scope="col">Assigning Authority</th>
                        <th scope="col">Identifier Type</th>
                        <th scope="col">Identifier</th>
                    </tr>
                    <c:forEach items="${command.participant.identifiers}" var="identifier">
                        <tr class="results">
                            <c:if test="${(identifier.class.name eq 'gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
                                <td>${identifier.organization}</td>
                            </c:if>
                            <c:if test="${(identifier.class.name eq 'gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
                                <td>${identifier.systemName}</td>
                            </c:if>
                            <td>${identifier.type}</td>
                            <td>${identifier.value}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </chrome:division>
        </c:if>
		
	        
    </jsp:attribute>
</tags:tabForm>
</body>
