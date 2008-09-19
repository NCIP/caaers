<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    
<tags:dwrJavascriptLink objects="createParticipant"/>
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
    }

    function onRemoveOrganizationIdentifier() {
    //    alert("onAjaxStudySearch");
    }

    //---------------------------------------------------------------------------------------------------------------------

    function removeOrganizationIdentifier(container, index) {
        var paramHash = populateParameters("removeOrganizationIdentifier", "par/ajax/par_OrganizationIdentifiersSection");
        paramHash.set("index", index);
        var url = $('command').action + "?subview"

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
		    <ui:row path="participant.firstName">
				<jsp:attribute name="label">
					<ui:label path="participant.firstName" text="First Name" required="true" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="participant.firstName" required="true" title="First name" />
				</jsp:attribute>
			</ui:row>
			<ui:row path="participant.lastName">
				<jsp:attribute name="label">
					<ui:label path="participant.lastName" text="Last Name" required="true" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="participant.lastName" required="true" title="Last name"/>
				</jsp:attribute>
			</ui:row>
			<ui:row path="participant.maidenName">
				<jsp:attribute name="label">
					<ui:label path="participant.maidenName" text="Maiden Name" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="participant.maidenName" title="Maiden name"/>
				</jsp:attribute>
			</ui:row>
			<ui:row path="participant.middleName">
				<jsp:attribute name="label">
					<ui:label path="participant.middleName" text="Middle Name" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="participant.middleName" title="Middle name" />
				</jsp:attribute>
			</ui:row>
        </td>
        <td>
            <div class="row" id="participant.dateOfBirth-row">
                <div class="label"><tags:renderLabel field="${fieldGroups.participant.fields[4]}"/></div>
                <div class="value"><tags:renderInputs field="${fieldGroups.participant.fields[4]}"/></div>
            </div>
            <c:forEach begin="5" end="7" items="${fieldGroups.participant.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </td>
    </tr>
</table>
</chrome:division>

<chrome:division title="Subject ID Assigned by Organization">
    <table id="organizationIdentifierTable" class="tablecontent">
        <tr id="organization-section">
            <th class="tableHeader"><tags:requiredIndicator/>Identifier</th>
            <th class="tableHeader"><tags:requiredIndicator/>Identifier type</th>
            <th class="tableHeader"><tags:requiredIndicator/>Organization</th>
            <th class="tableHeader"><tags:requiredIndicator/>Primary indicator</th>
        </tr>

        <tbody id="addOrganizationIdentifierDiv">
        <c:forEach items="${command.participant.organizationIdentifiers}" varStatus="status" var="idt">
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


<chrome:division title="Subject ID Assigned by a System">
    <table id="systemIdentifierTable" class="tablecontent">
        <tr id="system-section">
            <th class="tableHeader"><tags:requiredIndicator/>Identifier</th>
            <th class="tableHeader"><tags:requiredIndicator/>Identifier type</th>
            <th class="tableHeader"><tags:requiredIndicator/>System name</th>
            <th class="tableHeader"><tags:requiredIndicator/>Primary indicator</th>
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
</chrome:division>
         
     </jsp:attribute>
     
     <jsp:attribute name="localButtons"> 
         <input type=button value="Add System Identifier" id="system-button" onclick="addSystemIdentifier('addSystemIdentifierDiv')">
         <input type=button value="Add Organization Identifier" id="organization-button" onclick="addOrganizationIdentifier('addOrganizationIdentifierDiv')">
	</jsp:attribute>
     
     </tags:tabForm>    
</body>
</html>
