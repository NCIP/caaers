<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>

<%@attribute name="index" required="true" type="java.lang.Integer"%>
<%@attribute name="ta" required="true" type="gov.nih.nci.cabig.caaers.domain.TreatmentAssignment"%>
<%@attribute name="title"%>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
<%@attribute name="collapsable" type="java.lang.Boolean" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>

<c:set var="mainGroup">main${index}</c:set>
<c:set var="editable" value="${ta.study.loadStatus == 0 || empty ta.id}" />

<chrome:division title="&nbsp;${title}" id="OneTA-${index}" collapsable="${collapsable}" enableDelete="true" deleteParams="'delete', ${index}">
    <jsp:attribute name="additionalInfo"><div id="BTN${index}UPDATE" style="display:${!editable ? '' : 'none'};"><tags:button icon="edit" size="small" value="Update" color="blue" onclick="enableEdit('${index}');" type="button" /></div><div id="BTN${index}CANCEL" style="display: ${editable && !empty ta.id ? '' : 'none'}"><tags:button icon="x" size="small" value="Cancel" color="blue" onclick="disableEdit('${index}');" type="button"/></div></jsp:attribute>
    <jsp:body>
<%-- BODY --%>

    <ui:row path="${fieldGroups[mainGroup].fields[0].propertyName}">
		<jsp:attribute name="label">
			<ui:label path="${fieldGroups[mainGroup].fields[0].propertyName}" text="${fieldGroups[mainGroup].fields[0].displayName}" required="${fieldGroups[mainGroup].fields[0].required}"/>
		</jsp:attribute>
		<jsp:attribute name="value">
			<ui:text path="${fieldGroups[mainGroup].fields[0].propertyName}" size="${fieldGroups[mainGroup].fields[0].attributes.size}" readonly="${ta.id > 0 || !editable}" required="${fieldGroups[mainGroup].fields[0].required}" title="${fieldGroups[mainGroup].fields[0].displayName}">
				<jsp:attribute name="embededJS">
					Event.observe('${fieldGroups[mainGroup].fields[0].propertyName}', 'keyup', function(){
						$('titleOf_OneTA-${index}').innerHTML = $('${fieldGroups[mainGroup].fields[0].propertyName}').value;
					})
				</jsp:attribute>
            </ui:text>
		</jsp:attribute>
    </ui:row>

    <div class="row">
        <div class="label">
            <ui:label path="${fieldGroups[mainGroup].fields[1].propertyName}" text="abc" required="${fieldGroups[mainGroup].fields[1].required}"/>
        </div>
        <div class="value">
            <ui:text path="${fieldGroups[mainGroup].fields[1].propertyName}" disabled="${!editable}"  required="${fieldGroups[mainGroup].fields[1].required}" title="${fieldGroups[mainGroup].fields[1].displayName}"/>
        </div>
    </div>
    <div class="row">
        <div class="label">
            <ui:label path="${fieldGroups[mainGroup].fields[2].propertyName}" text="abc" required="${fieldGroups[mainGroup].fields[2].required}"/>
        </div>
        <div class="value">
            <ui:textarea path="${fieldGroups[mainGroup].fields[2].propertyName}" disabled="${!editable}" cols="80" required="${fieldGroups[mainGroup].fields[2].required}" title="${fieldGroups[mainGroup].fields[2].displayName}"/>
        </div>
    </div>
    <div class="row">
        <div class="label">
            <ui:label path="${fieldGroups[mainGroup].fields[3].propertyName}" text="abc" required="${fieldGroups[mainGroup].fields[3].required}"/>
        </div>
        <div class="value">
            <ui:textarea path="${fieldGroups[mainGroup].fields[3].propertyName}" disabled="${!editable}" cols="80" required="${fieldGroups[mainGroup].fields[3].required}" title="${fieldGroups[mainGroup].fields[3].displayName}"/>
        </div>
    </div>

    <div style="padding-left:130px;">
        <chrome:division title="Please choose interventions for ${title}" collapsable="true" collapsed="false" id="${ta}">
            <study:treatmentAssignmentStudyInterventions ta="${ta}" studyInterventionsHelper="${command.treatmentAssignmentAgentsHelpers}" sectionTitle="Agents" />
            <study:treatmentAssignmentStudyInterventions ta="${ta}" studyInterventionsHelper="${command.treatmentAssignmentDevicesHelpers}" sectionTitle="Devices" />
            <study:treatmentAssignmentStudyInterventions ta="${ta}" studyInterventionsHelper="${command.treatmentAssignmentOthersHelpers}" sectionTitle="Other interventions" />
        </chrome:division>
    </div>
<%-- BODY --%>
    </jsp:body>
</chrome:division>
