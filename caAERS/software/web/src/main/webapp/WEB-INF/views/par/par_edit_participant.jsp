<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <tags:dwrJavascriptLink objects="createParticipant"/>
</head>
<body>

<script language="JavaScript">

//---------------------------------------------------------------------------------------------------------------------

function populateParameters(methodName, viewName) {

    var paramHash = new Hash();

    paramHash.set('_asynchronous', true);
    paramHash.set('_asyncMethodName', methodName);
    paramHash.set('_asyncViewName', viewName);

    return paramHash;
}

//---------------------------------------------------------------------------------------------------------------------

function formElements(aContainer) {
    return aContainer.select('input', 'select', 'textarea');
}

//---------------------------------------------------------------------------------------------------------------------

function onAddOrganizationIdentifier() {
//    alert("onAjaxStudySearch");
}

//---------------------------------------------------------------------------------------------------------------------

function addOrganizationIdentifier(container) {
    var paramHash = populateParameters("addOrganizationIdentifier", "par/ajax/par_OrganizationIdentifiersSection");
    var url = $('command').action + "&subview"

    new Ajax.Updater(container, url, {
        parameters: paramHash.toQueryString(), onComplete: onAddOrganizationIdentifier, insertion: Insertion.Bottom, evalScripts : true
    });

    $('DIV_addOrganizationIdentifierDiv').show();
}

//---------------------------------------------------------------------------------------------------------------------

function onRemoveOrganizationIdentifier() {
//    alert("onAjaxStudySearch");
}

//---------------------------------------------------------------------------------------------------------------------

function removeOrganizationIdentifier(container, index) {
    var paramHash = populateParameters("removeOrganizationIdentifier", "par/ajax/par_OrganizationIdentifiersSection");
    paramHash.set("index", index);
    var url = $('command').action + "&subview"

    var identifiersParams = Form.serializeElements(formElements($('DIV_addOrganizationIdentifierDiv')), true);
    paramHash = paramHash.merge(identifiersParams);

    new Ajax.Updater(container, url, {
        parameters: paramHash.toQueryString(), onComplete: onRemoveOrganizationIdentifier, evalScripts : true
    });
}

//---------------------------------------------------------------------------------------------------------------------

function onAddSystemIdentifier() {
//    alert("onAjaxStudySearch");
}

//---------------------------------------------------------------------------------------------------------------------

function addSystemIdentifier(container) {
    var paramHash = populateParameters("addSystemIdentifier", "par/ajax/par_SystemIdentifiersSection");
    var url = $('command').action + "&subview"

    new Ajax.Updater(container, url, {
        parameters: paramHash.toQueryString(), onComplete: onAddSystemIdentifier, insertion: Insertion.Bottom, evalScripts : true
    });
    $('DIV_addSystemIdentifier').show();
}

//---------------------------------------------------------------------------------------------------------------------

function onRemoveSystemIdentifier() {
//    alert("onAjaxStudySearch");
}

//---------------------------------------------------------------------------------------------------------------------

function removeSystemIdentifier(container, index) {
    var paramHash = populateParameters("removeSystemIdentifier", "par/ajax/par_SystemIdentifiersSection");
    paramHash.set("index", index);
    var url = $('command').action + "&subview"

    var identifiersParams = Form.serializeElements(formElements($('DIV_addSystemIdentifier')), true);
    paramHash = paramHash.merge(identifiersParams);

    new Ajax.Updater(container, url, {
        parameters: paramHash.toQueryString(), onComplete: onRemoveSystemIdentifier, evalScripts : true
    });
}

//---------------------------------------------------------------------------------------------------------------------

</script>

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false" willSave="true">

<jsp:attribute name="singleFields">
<p><tags:instructions code="instruction_subject_edit.details.top"/></p>
<div>
    <input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
</div>
</jsp:attribute>

<jsp:attribute name="repeatingFields">


<chrome:division  title="Study Subject Assignments"  >
<table class="tablecontent" width="100%">
    <tr>
       <th scope="col" width="150px">Study Primary ID</th>
       <th scope="col">Study Short Title</th>
       <th scope="col">Site</th>
       <th scope="col" width="150px">Study Subject Identifier</th>
    </tr>
    <%--<b style="color: red;">[<c:out value="${command.participant.assignments == null}" />]</b>--%>

  <c:forEach items="${command.assignments}" var="assignment" varStatus="i">
    <c:set var="assign" value="assignments[${i.index}].studySubjectIdentifier" />
    <tr class="results">

        <c:if test="${fn:length(command.assignments) == 1}">
            <td><input type="radio" value="${command.assignments[0].id}" name="assignment" id="assignment" checked="true" />${command.assignments[0].studySite.study.primaryIdentifier}
        </c:if>

        <c:if test="${fn:length(command.assignments) != 1}">
            <td><ui:radio path="assignment" value="${assignment.id}" />${assignment.studySite.study.primaryIdentifier}</td>
        </c:if>

      <td>${assignment.studySite.study.shortTitle}</td>
      <td>
   	<c:if test ="${assignment.studySite.organization.externalId != null}">
			<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
	</c:if>        
      ${assignment.studySite.organization.name}
      </td>
      <td><ui:text path="${assign}" /></td>
    </tr>
  </c:forEach>
</table>

</chrome:division>

<chrome:division title="Demographic Information" collapsed="false" collapsable="false" id="DemoInfo">
<div id="test2" class="single-fields row">

        <div class="leftpanel">
            <c:if test="${!unidentifiedMode}">

		    <ui:row path="participant.firstName">
				<jsp:attribute name="label">
					<ui:label path="participant.firstName" text="First Name" required="true" />
				</jsp:attribute>
				<jsp:attribute name="value">
                    <ui:text path="${fieldGroups.participant.fields[0].propertyName}" title="${fieldGroups.participant.fields[0].displayName}" validationJSClass="${fieldGroups.participant.fields[0].validatorClassName}" required="true"/>
				</jsp:attribute>
			</ui:row>
			<ui:row path="participant.lastName">
				<jsp:attribute name="label">
					<ui:label path="participant.lastName" text="Last Name" required="true" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="${fieldGroups.participant.fields[1].propertyName}" title="${fieldGroups.participant.fields[1].displayName}" validationJSClass="${fieldGroups.participant.fields[1].validatorClassName}" required="true"/>
				</jsp:attribute>
			</ui:row>
			<ui:row path="participant.maidenName">
				<jsp:attribute name="label">
					<ui:label path="participant.maidenName" text="Maiden Name" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="${fieldGroups.participant.fields[2].propertyName}" title="${fieldGroups.participant.fields[2].displayName}" validationJSClass="${fieldGroups.participant.fields[2].validatorClassName}"/>
				</jsp:attribute>
			</ui:row>
			<ui:row path="participant.middleName">
				<jsp:attribute name="label">
					<ui:label path="participant.middleName" text="Middle Name" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="${fieldGroups.participant.fields[3].propertyName}" title="${fieldGroups.participant.fields[3].displayName}" validationJSClass="${fieldGroups.participant.fields[3].validatorClassName}"/>
				</jsp:attribute>
			</ui:row>
            <ui:row path="participant.organizationIdentifiers[0]">
                <jsp:attribute name="label"><ui:label path="participant.organizationIdentifiers[0]" text="Subject Identifier" required="true"/></jsp:attribute>
                <jsp:attribute name="value"><ui:text path="participant.organizationIdentifiers[0].value" required="true"/></jsp:attribute>
            </ui:row>
            </c:if>
        </div>
        <div class="rightpanel">
            <div class="row" id="participant.dateOfBirth-row">
                <div class="label"><tags:renderLabel field="${fieldGroups.participant.fields[!unidentifiedMode ? 4 : 0]}"/></div>
                <div class="value"><tags:renderInputs field="${fieldGroups.participant.fields[!unidentifiedMode ? 4 : 0]}"/></div>
            </div>

            <c:set var="_start" value="${!unidentifiedMode ? 5 : 1}" />
            <c:set var="_end" value="${!unidentifiedMode ? 7 : 3}" />
            <c:forEach begin="${_start}" end="${_end}" items="${fieldGroups.participant.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </div>

</div>
</chrome:division>

<c:if test="${!unidentifiedMode}">

<div id="DIV_addOrganizationIdentifierDiv" style="display: ${fn:length(command.participant.organizationIdentifiers) > 1 ? 'inline' : 'none'}">
<chrome:division title="Subject ID Assigned by an Organization">
<table id="test" class="tablecontent">
    <tr id="organization-section">
        <th class="tableHeader" width="20%"><tags:requiredIndicator />Identifier</th>
        <th class="tableHeader" width="20%"><tags:requiredIndicator />Identifier type</th>
        <th class="tableHeader" width="40%"><tags:requiredIndicator />Organization</th>
		<th width="1%"> </th>
    </tr>

    <tbody id="addOrganizationIdentifierDiv">
    <c:forEach items="${command.participant.organizationIdentifiers}" varStatus="status" var="idt" begin="1">
            <par:parIdentifier
                    title="Subject Identifier ${status.index + 1}"
                    disableDelete="false"
                    sectionClass="organization-section-row"
                    removeButtonAction="removeIdentifier"
                    index="${status.index}"
                    identifier="${command.participant.organizationIdentifiers[status.index]}"
                    mainGroupName="mainOrg"
                    containerName="addOrganizationIdentifierDiv"
                    removeAction="removeOrganizationIdentifier"/>
    </c:forEach>
    </tbody>

</table>
</chrome:division>
</div>

<br>

<div id="DIV_addSystemIdentifier" style="display: ${fn:length(command.participant.systemAssignedIdentifiers) > 0? 'inline' : 'none'}">
<chrome:division title="Subject ID Assigned by a System">
<table id="test1" class="tablecontent" >
    <tr id="system-section">
        <th  class="tableHeader" width="20%"><tags:requiredIndicator />Identifier</th>
        <th  class="tableHeader" width="20%"><tags:requiredIndicator />Identifier type</th>
        <th  class="tableHeader" width="40%"><tags:requiredIndicator />System name</th>
		<th width="1%"> </th>
    </tr>

    <tbody id="addSystemIdentifierDiv">
    <c:forEach items="${command.participant.systemAssignedIdentifiers}" varStatus="status" >
        <par:parIdentifier
                title="Subject Identifier ${status.index + 1}"
                disableDelete="false"
                sectionClass="system-section-row"
                removeButtonAction="removeIdentifier"
                index="${status.index}"
                identifier="${command.participant.systemAssignedIdentifiers[status.index]}"
                mainGroupName="mainSys"
                containerName="addSystemIdentifierDiv"
                removeAction="removeSystemIdentifier"/>
    </c:forEach>
    <tbody>
</table>
</chrome:division>
</div>

    <tags:button id="system-button" color="blue" type="button" value="Add System Identifier" size="small" icon="add" onclick="addSystemIdentifier('addSystemIdentifierDiv')"/>
    <tags:button id="organization-button" color="blue" type="button" value="Add Organization Identifier" size="small" icon="add" onclick="addOrganizationIdentifier('addOrganizationIdentifierDiv')"/>

</c:if>    

<script language="JavaScript">
    var sizeSys = 0<c:out value="${fn:length(command.participant.systemAssignedIdentifiers)}" />;
    var sizeOrg = 0<c:out value="${fn:length(command.participant.organizationIdentifiers)}" />;

    function idSelect(id, i) {
        if ($(id).checked) {

            for (i=0; i<sizeSys; i++) {
                _id = $("participant.systemAssignedIdentifiers[" + i + "].primaryIndicator1");
                if ($(id) != _id) _id.checked = false;
            }

            for (i=0; i<sizeOrg; i++) {
                _id = $("participant.organizationIdentifiers[" + i + "].primaryIndicator1");
                if ($(id) != _id) _id.checked = false;
            }
            
        }
    }

<c:forEach items="${command.participant.systemAssignedIdentifiers}" varStatus="status" var="i">
    <c:set var="_id" value="participant.systemAssignedIdentifiers[${status.index}].primaryIndicator1" />
    Event.observe("${_id}", "click", function() {idSelect('${_id}', ${status.index})});
</c:forEach>
</script>

<script language="JavaScript">
<c:forEach items="${command.participant.organizationIdentifiers}" varStatus="status" var="i">
    <c:set var="_id" value="participant.organizationIdentifiers[${status.index}].primaryIndicator1" />
    Event.observe("participant.organizationIdentifiers[${status.index}].primaryIndicator1", "click", function() {idSelect('${_id}', ${status.index})});
</c:forEach>
</script>

</jsp:attribute>

<jsp:attribute name="localButtons">
   
    
</jsp:attribute>
     
</tags:tabForm>

<form name="dummyForm"></form>

</body>
</html>
