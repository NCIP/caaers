<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Review and Submit</title>
    <script>
        function submitPage(s) {
            document.getElementById("nextView").value = s;
            document.getElementById("command").submit();
        }

    </script>
</head>
<body>


<tags:tabForm tab="${tab}" flow="${flow}"
              title="${participant.lastName}, ${participant.firstName}"
              willSave="false">

<jsp:attribute name="repeatingFields">
    	<c:if test="${(empty participant.id) or ( participant.id le 0) }">
            <input type="hidden" name="_finish" value="true"/>
        </c:if>
    <chrome:division title="Assigned to Studies">
        <table class="tablecontent">
            <tr>
                <th scope="col">Study Primary ID</th>
                <th scope="col">Study Short Title</th>
                <th scope="col">Site</th>
                <th scope="col">Study Subject Identifier</th>
            </tr>
            <c:forEach var="studySite" items="${studySites}" varStatus="status">
                <tr class="results">
                    <td>${studySite.study.primaryIdentifier ne null ? studySite.study.primaryIdentifier.value : '' }</td>
                    <td>${studySite.study.shortTitle}</td>
                    <td>${studySite.organization.name}</td>
                    <td>${studySubjectIdentifier}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </chrome:division>
    <chrome:division title="Subject Details">
        <br>
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
                                                    <div class="label">Id</div>
                                                    <div class="value">${participant.id}</div>
                                                </div>
                        <div class="row">
                                                    <div class="label">Grid id:</div>
                                                    <div class="value">${participant.gridId}</div>
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
                    <c:forEach items="${participant.identifiers}" var="identifier">
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
		<%----%>
        <td><a href="${flowExecutionUrl}&_eventId=updateDetails">Update Details</a></td>

    </jsp:attribute>
</tags:tabForm>
</body>
</html>