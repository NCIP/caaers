<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    
<tags:dwrJavascriptLink objects="createParticipant"/>

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
			<ui:label path="${fieldGroups.site.fields[0].propertyName}" text="Site" />
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
    <table id="test" class="tablecontent">
        <tr id="organization-section">
            <th class="tableHeader"><tags:requiredIndicator/>Identifier</th>
            <th class="tableHeader"><tags:requiredIndicator/>Identifier type</th>
            <th class="tableHeader"><tags:requiredIndicator/>Organization</th>
            <th class="tableHeader"><tags:requiredIndicator/>Primary indicator</th>
        </tr>

        <c:forEach items="${command.participant.organizationIdentifiers}" varStatus="status" var="idt">
            <par:parIdentifier
                    title="Subject Identifier ${status.index + 1}"
                    disableDelete="${fn:length(command.participant.organizationIdentifiers) lt 2}"
                    sectionClass="organization-section-row"
                    removeButtonAction="removeIdentifier"
                    index="${status.index}"
                    identifier="${command.participant.organizationIdentifiers[status.index]}"
                    mainGroupName="mainOrg"/>
        </c:forEach>

    </table>
</chrome:division>


<chrome:division title="Subject ID Assigned by a System">
    <table id="test1" class="tablecontent">
        <tr id="system-section">
            <th class="tableHeader"><tags:requiredIndicator/>Identifier</th>
            <th class="tableHeader"><tags:requiredIndicator/>Identifier type</th>
            <th class="tableHeader"><tags:requiredIndicator/>System name</th>
            <th class="tableHeader"><tags:requiredIndicator/>Primary indicator</th>
        </tr>

        <c:forEach items="${command.participant.systemAssignedIdentifiers}" varStatus="status">
            <par:parIdentifier
                    title="Subject Identifier ${status.index + 1}"
                    disableDelete="${fn:length(command.participant.systemAssignedIdentifiers) lt 2}"
                    sectionClass="system-section-row"
                    removeButtonAction="removeIdentifier"
                    index="${status.index}"
                    identifier="${command.participant.systemAssignedIdentifiers[status.index]}"
							initialValue=""
                    mainGroupName="mainSys" />
        </c:forEach>
    </table>
</chrome:division>
         
     </jsp:attribute>
     
     <jsp:attribute name="localButtons"> 
	      	<chrome:division title="">          	
	      		<tags:listEditorAddButton divisionClass="system-section-row" label="Add System Identifier" />   
                <tags:listEditorAddButton divisionClass="organization-section-row" label="Add Organization Identifier" /> 
            </chrome:division>                                                                                                                                                                                                                                                             
	</jsp:attribute>
     
     </tags:tabForm>    
</body>
</html>
