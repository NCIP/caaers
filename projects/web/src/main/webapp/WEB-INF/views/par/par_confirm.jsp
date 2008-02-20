<!-- TODO: This view is virtually identical to the last screen of the create
participant flow. Factor out their commonalities. -->

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>View Subject</title>
    <tags:stylesheetLink name="participant"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">
    <style type="text/css">
        table.tablecontent {
            width: 90%;
        }

        table.single-fields {
            width: 90%;
        }
    </style>
    <script>
        function submitPage(s) {
            document.getElementById("nextView").value = s;
            document.getElementById("participant").submit();
        }

    </script>
</head>
<body>
<p id="instructions">
    You have successfully created a new Subject
</p>
<chrome:box title="${participant.lastName}, ${participant.firstName}">

    <chrome:division title="Assigned to Studies">
        <table class="tablecontent">
            <tr>
                <th scope="col">Study Short Title</th>
                <th scope="col">Site</th>
                <th scope="col">Study Subject Identifier</th>
            </tr>
            <c:forEach var="assignment" items="${participant.assignments}" varStatus="status">
                <tr class="results">
                    <td>${assignment.studySite.study.shortTitle}</td>
                    <td>${assignment.studySite.organization.name}</td>
                    <td>${assignment.studySubjectIdentifier}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </chrome:division>
    <chrome:division title="Subject Details">
        <table id="test2" class="single-fields">
            <tr>
                <td>
                    <div class="leftpane">
                        <div class="row">
                            <div class="label">First name:</div>
                            <div class="value">${participant.firstName}</div>
                        </div>
                        <div class="row">
                            <div class="label">Last name:</div>
                            <div class="value">${participant.lastName}</div>
                        </div>

                        <div class="row">
                            <div class="label">Maiden name:</div>
                            <div class="value">${participant.maidenName}</div>
                        </div>

                        <div class="row">
                            <div class="label">Middle name:</div>
                            <div class="value">${participant.middleName}</div>
                        </div>
                </td>

                <td>
                    <div class="row">
                        <div class="label">Date of birth:</div>
                        <div class="value">${participant.dateOfBirth}</div>
                    </div>

                    <div class="row">
                        <div class="label">Ethnicity:</div>
                        <div class="value">${participant.ethnicity}</div>
                    </div>
                    <div class="row">
                        <div class="label">Race:</div>
                        <div class="value">${participant.race}</div>
                    </div>
                    <div class="row">
                        <div class="label">Gender:</div>
                        <div class="value">${participant.gender}</div>
                    </div>
                </td>
            </tr>
        </table>
    </chrome:division>
    <c:if test="${not empty participant.identifiers}">
        <chrome:division title="Identifiers">
            <table class="tablecontent">
                <tr>
                    <th scope="col">Assigning Authority</th>
                    <th scope="col">Identifier Type</th>
                    <th scope="col">Identifier</th>
                </tr>
                <c:forEach items="${participant.identifiers}"
                           var="identifier">
                    <tr class="results">
                        <c:if
                                test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
                            <td>${identifier.organization}</td>
                        </c:if>
                        <c:if
                                test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
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
</chrome:box>
</body>