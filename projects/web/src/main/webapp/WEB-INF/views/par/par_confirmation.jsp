<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Participant Review</title>
    <tags:stylesheetLink name="participant"/>
    <script>
        function submitPage(s) {
            document.getElementById("nextView").value = s;
            document.getElementById("command").submit();
        }

    </script>
<style>
    div.row div.label {
        width:15em;        
    }
</style>
</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}"
              title="${command.participant.lastName}, ${command.participant.firstName}"
              willSave="false">

<jsp:attribute name="repeatingFields">
    	<c:if test="${(empty command.participant.id) or ( command.participant.id le 0) }">
            <input type="hidden" name="_finish" value="true"/>
        </c:if>

    <chrome:division title="Demographic Information">
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
            <table class="tablecontent" width="100%">
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
		
        <chrome:division title="Study Subject Assignments">
            <table class="tablecontent" width="100%">
                <tr>
                    <th scope="col" width="150px">Study Primary ID</th>
                    <th scope="col">Study Short Title</th>
                    <th scope="col">Site</th>
                    <th scope="col" width="150px">Study Subject Identifier</th>
                </tr>
                <c:forEach items="${command.assignments}" var="assignment" varStatus="i">
                        <tr class="results">
                            <td>${assignment.studySite.study.primaryIdentifier}</td>
                            <td>${assignment.studySite.study.shortTitle}</td>
                            <td>${assignment.studySite.organization.name}</td>
                            <td>${assignment.studySubjectIdentifier}</td>
                        </tr>
                </c:forEach>

            </table>
            <br>
        </chrome:division>

        <chrome:division title="Medical History">
            <c:forEach items="${command.assignments}" var="assignment" varStatus="i">
                <chrome:division title="${assignment.studySite.study.shortTitle} - ${assignment.studySite.study.primaryIdentifier}" collapsable="true" id="a_id_${assignment.id}" collapsed="true">
                    <table width=100% border="0">
                        <tr><td colspan="4"><h2><tags:message key="section.general" /></h2></td></tr>
                        <tr><td></td><td colspan="3"><tags:message key="baseline.performance" />: ${assignment.baselinePerformance}</td></tr>

                        <tr><td colspan="4"><h2><tags:message key="section.disease.information" /></h2></td></tr>
                        <tr>
                            <td></td>
                            <td colspan="3">
                                <div class="row">
                                    <div class="label"><tags:message key="disease.name"/>:</div>
                                    <div class="value">${assignment.diseaseHistory.abstractStudyDisease.termName}</div>
                                </div>
                                <div class="row">
                                    <div class="label"><tags:message key="primary.site.of.disease"/>:</div>
                                    <div class="value">${assignment.diseaseHistory.codedPrimaryDiseaseSite.name}</div>
                                </div>
                                <div class="row">
                                    <div class="label"><tags:message key="date.of.initial.diagnosis"/>:</div>
                                    <div class="value">${assignment.diseaseHistory.diagnosisDate}</div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2" width="50%"><h2><tags:message key="section.metastatic.disease.site" /></h2></td>
                            <td colspan="2" width="50%"><h2><tags:message key="section.preexisting.conditions" /></h2></td>
                        </tr>
                        <tr>
                            <td width="5%"></td>
                            <td valign="top">
                                <ul>
                                <c:forEach items="${assignment.diseaseHistory.metastaticDiseaseSites}" var="mds" varStatus="status">
                                    <li>
                                            <c:if test="${mds.codedSite.id != 110}">${mds.codedSite.name}</c:if>
                                            <c:if test="${mds.codedSite.id == 110}">${mds.otherSite}</c:if>
                                </c:forEach>
                                </ul>
                            </td>
                            <td width="5%"></td>
                            <td valign="top">
                                <ul>
                                    <c:forEach items="${assignment.preExistingConditions}" var="pc" varStatus="status">
                                        <li>
                                                <c:if test="${not empty pc.preExistingCondition}">${pc.preExistingCondition.text}</c:if>
                                                <c:if test="${empty pc.preExistingCondition}">${pc.other}</c:if>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2"><h2><tags:message key="section.conmeds" /></h2></td>
                            <td colspan="2"><h2><tags:message key="section.prior.therapies" /></h2></td>                            
                        </tr>
                        <tr>
                            <td></td>
                            <td valign="top">
                                <ul>
                                <c:forEach items="${assignment.concomitantMedications}" var="cm" varStatus="status">
                                    <li>${cm.agentName}
                                        <ul>
                                            <li><tags:message key="still.taking" />: <c:if test="${cm.stillTakingMedications}"><tags:message key="yes" /></c:if><c:if test="${!cm.stillTakingMedications}"><tags:message key="no" /></c:if>
                                            <li><tags:message key="start.date" />: ${cm.startDate}
                                            <li><tags:message key="end.date" />: ${cm.endDate}
                                        </ul><br />
                                </c:forEach>
                                </ul>
                            </td>
                            <td></td>
                            <td valign="top">
                                <ul>
                                <c:forEach items="${assignment.priorTherapies}" var="pt" varStatus="status">
                                    <li>${pt.priorTherapy.text}
                                        <ul>
                                            <li><tags:message key="comments" />: ${pt.other}
                                            <li><tags:message key="start.date" />: ${pt.startDate}
                                            <li><tags:message key="end.date" />: ${pt.endDate}
                                            <c:if test="${fn:length(pt.priorTherapyAgents) > 0}">
                                                <li><tags:message key="therapy.agents" />:
                                            <ul>
                                                <c:forEach items="${pt.priorTherapyAgents}" var="pta" varStatus="status">
                                                    <li>${pta.name}
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        </ul><br />
                                </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </table>
                </chrome:division>
            </c:forEach>
        </chrome:division>

    </jsp:attribute>
</tags:tabForm>
</body>
</html>

