<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>

<tags:dwrJavascriptLink objects="createParticipant"/>
    <script language="JavaScript">

    //---------------------------------------------------------------------------------------------------------------------

    Event.observe(window, "load", function() {
/*
        Event.observe($('organization'), "change", function() {
            populateAutocompleter('participant.organizationIdentifiers[0].organization', $('organization').options[$('organization').options.selectedIndex].text, $('organization').options[$('organization').options.selectedIndex].value);
        });
*/
    })

    //---------------------------------------------------------------------------------------------------------------------

    function populateAutocompleter(autocompleterID, textValue, hiddenValue) {
        $(autocompleterID + '-input').value = textValue;
        $(autocompleterID).value = hiddenValue;
    }

    //---------------------------------------------------------------------------------------------------------------------

    function populateParameters(methodName, viewName) {

        var paramHash = new Hash();

        paramHash.set('_asynchronous', true);
        paramHash.set('_asyncMethodName', methodName);
        paramHash.set('_asyncViewName', viewName);

        return paramHash;
    }

    //---------------------------------------------------------------------------------------------------------------------

    function onAddOrganizationIdentifier() {
    //    alert("onAjaxStudySearch");
    }

    //---------------------------------------------------------------------------------------------------------------------

    function addOrganizationIdentifier(container) {
        var paramHash = populateParameters("addOrganizationIdentifier", "par/ajax/par_OrganizationIdentifiersSection");
        var url = $('command').action + "?dummy=0&subview"

        new Ajax.Updater(container, url, {
            parameters: paramHash.toQueryString(), onSuccess: onAddOrganizationIdentifier, insertion: Insertion.Bottom, evalScripts : true
        });
        $('DIV_addOrganizationIdentifierDiv').show();
    }

    function onRemoveOrganizationIdentifier() {
    //    alert("onAjaxStudySearch");
    }

    //---------------------------------------------------------------------------------------------------------------------

    function formElements(aContainer) {
        return aContainer.select('input', 'select', 'textarea');	
    }

    //---------------------------------------------------------------------------------------------------------------------

    function removeOrganizationIdentifier(container, index) {
        var paramHash = populateParameters("removeOrganizationIdentifier", "par/ajax/par_OrganizationIdentifiersSection");
        paramHash.set("index", index);
        var url = $('command').action + "?subview"

        var identifiersParams = Form.serializeElements(formElements($('organizationIdentifierTable')), true);
        paramHash = paramHash.merge(identifiersParams);

        new Ajax.Updater(container, url, {
            parameters: paramHash.toQueryString(), onSuccess: onRemoveOrganizationIdentifier, evalScripts : true
        });
    }

    //---------------------------------------------------------------------------------------------------------------------

    function onAddSystemIdentifier() {
    //    alert("onAjaxStudySearch");
    }

    //---------------------------------------------------------------------------------------------------------------------

    function addSystemIdentifier(container) {
        var paramHash = populateParameters("addSystemIdentifier", "par/ajax/par_SystemIdentifiersSection");
        var url = $('command').action + "?subview"

        new Ajax.Updater(container, url, {
            parameters: paramHash.toQueryString(), onSuccess: onAddSystemIdentifier, insertion: Insertion.Bottom, evalScripts : true
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
        var url = $('command').action + "?subview"

        var identifiersParams = Form.serializeElements(formElements($('systemIdentifierTable')), true);
        paramHash = paramHash.merge(identifiersParams);
        
        new Ajax.Updater(container, url, {
            parameters: paramHash.toQueryString(), onSuccess: onRemoveSystemIdentifier, evalScripts : true
        });
    }

    //---------------------------------------------------------------------------------------------------------------------

    </script>

</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}"  formName="createParticipantForm" hideErrorDetails="false" willSave="false">

    <jsp:attribute name="singleFields">
      <p><tags:instructions code="instruction_subject_enter.details"/></p>
        <div>
            <input type="hidden" name="_action" value="">
            <input type="hidden" name="_selected" value="">
        </div>
    </jsp:attribute>

    <jsp:attribute name="repeatingFields">

<chrome:division  title="Site"  >

<c:if test="${(empty command.participant.id) or (command.participant.id le 0)}">
	<ui:row path="${fieldGroups.site.fields[0].propertyName}">
		<jsp:attribute name="label">
			<ui:label path="${fieldGroups.site.fields[0].propertyName}" text="Site" required="true"/>
		</jsp:attribute>
		<jsp:attribute name="value">
			<ui:select options="${fieldGroups.site.fields[0].attributes.options}" path="${fieldGroups.site.fields[0].propertyName}" title="Site" required="true"/>
		</jsp:attribute>
	</ui:row>

</c:if>

<c:if test="${!(empty command.participant.id) and (command.participant.id gt 0)}">
${command.organization}
</c:if>

</chrome:division>


<chrome:division  title="Demographic Information">
<table id="test2" class="single-fields" width="100%">
    <tr >
        <td>
            <c:if test="${!unidentifiedMode}">

                    <ui:row path="participant.firstName">
                        <jsp:attribute name="label"><ui:label path="participant.firstName" text="First Name" required="true" /></jsp:attribute>
                        <jsp:attribute name="value"><ui:text path="${fieldGroups.participant.fields[0].propertyName}" title="${fieldGroups.participant.fields[0].displayName}" validationJSClass="${fieldGroups.participant.fields[0].validatorClassName}" required="true"/></jsp:attribute>
                    </ui:row>

                    <ui:row path="participant.lastName">
                        <jsp:attribute name="label"><ui:label path="participant.lastName" text="Last Name" required="true" /></jsp:attribute>
                        <jsp:attribute name="value"><ui:text path="${fieldGroups.participant.fields[1].propertyName}" title="${fieldGroups.participant.fields[1].displayName}" validationJSClass="${fieldGroups.participant.fields[1].validatorClassName}" required="true"/></jsp:attribute>
                    </ui:row>

                    <ui:row path="participant.maidenName">
                        <jsp:attribute name="label"><ui:label path="participant.maidenName" text="Maiden Name" /></jsp:attribute>
                        <jsp:attribute name="value"><ui:text path="${fieldGroups.participant.fields[2].propertyName}" title="${fieldGroups.participant.fields[2].displayName}" validationJSClass="${fieldGroups.participant.fields[2].validatorClassName}"/></jsp:attribute>
                    </ui:row>

                    <ui:row path="participant.middleName">
                        <jsp:attribute name="label"><ui:label path="participant.middleName" text="Middle Name" /></jsp:attribute>
                        <jsp:attribute name="value"><ui:text path="${fieldGroups.participant.fields[3].propertyName}" title="${fieldGroups.participant.fields[3].displayName}" validationJSClass="${fieldGroups.participant.fields[3].validatorClassName}"/></jsp:attribute>
                    </ui:row>

                    <ui:row path="participant.organizationIdentifiers[0]">
                        <jsp:attribute name="label"><ui:label path="participant.organizationIdentifiers[0]" text="Subject Identifier" required="true"/></jsp:attribute>
                        <jsp:attribute name="value"><ui:text path="participant.organizationIdentifiers[0].value" required="true"/></jsp:attribute>
                    </ui:row>

            </c:if>

        </td>
        <td valign="top">
            <div class="row" id="participant.dateOfBirth-row">
                <div class="label"><tags:renderLabel field="${fieldGroups.participant.fields[!unidentifiedMode ? 4 : 0]}"/></div>
                <div class="value"><tags:renderInputs field="${fieldGroups.participant.fields[!unidentifiedMode ? 4 : 0]}"/></div>
            </div>
            <c:set var="_start" value="${!unidentifiedMode ? 5 : 1}" />
            <c:set var="_end" value="${!unidentifiedMode ? 7 : 3}" />
            <c:forEach begin="${_start}" end="${_end}" items="${fieldGroups.participant.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </td>
    </tr>
</table>
</chrome:division>


<c:if test="${!unidentifiedMode}">

<div id="DIV_addOrganizationIdentifierDiv" style="display: ${fn:length(command.participant.organizationIdentifiers) > 1 ? 'inline' : 'none'}">
<chrome:division title="Subject ID Assigned by an Organization">
    <table id="organizationIdentifierTable" class="tablecontent">
        <tr id="organization-section">
            <th class="tableHeader" width="20%"><tags:requiredIndicator/>Identifier</th>
            <th class="tableHeader" width="20%"><tags:requiredIndicator/>Identifier type</th>
            <th class="tableHeader" width="40%"><tags:requiredIndicator/>Organization</th>
            <th class="tableHeader"></th>
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
                    removeAction="removeOrganizationIdentifier" />
        </c:forEach>
        </tbody>

    </table>
</chrome:division>
</div>

<br>

<div id="DIV_addSystemIdentifier" style="display: ${fn:length(command.participant.systemAssignedIdentifiers) > 0? 'inline' : 'none'}">
<chrome:division title="Subject ID Assigned by a System" id="DIVISION_addSystemIdentifier">
    <table id="systemIdentifierTable" class="tablecontent">
        <tr id="system-section">
            <th class="tableHeader" width="20%"><tags:requiredIndicator/>Identifier</th>
            <th class="tableHeader" width="20%"><tags:requiredIndicator/>Identifier type</th>
            <th class="tableHeader" width="40%"><tags:requiredIndicator/>System name</th>
        </tr>

        <tbody id="addSystemIdentifierDiv">
        <c:forEach items="${command.participant.systemAssignedIdentifiers}" varStatus="status">
            <par:parIdentifier
                    title="Subject Identifier ${status.index + 1}"
                    disableDelete="false"
                    sectionClass="system-section-row"
                    removeButtonAction="removeIdentifier"
                    index="${status.index}"
                    identifier="${command.participant.systemAssignedIdentifiers[status.index]}"
                    mainGroupName="mainSys"
                    containerName="addSystemIdentifierDiv"
                    removeAction="removeSystemIdentifier" />
        </c:forEach>
        </tbody>
    </table>
<br>
</chrome:division>
</div>

<tags:button id="system-button" color="blue" type="button" value="Add System Identifier" size="small" icon="add" onclick="addSystemIdentifier('addSystemIdentifierDiv')"/>
<tags:button id="organization-button" color="blue" type="button" value="Add Organization Identifier" size="small" icon="add" onclick="addOrganizationIdentifier('addOrganizationIdentifierDiv')"/>

</c:if>


     </jsp:attribute>

     <jsp:attribute name="localButtons">
    </jsp:attribute>

     </tags:tabForm>
</body>
</html>
