<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<html>
<head>
    
<tags:dwrJavascriptLink objects="createParticipant"/>

<script language="JavaScript" type="text/JavaScript">
	var si = [];
	var addIdentifierEditor;
	var jsIdentifier = Class.create();

    Object.extend(jsIdentifier.prototype, {

        initialize: function(index, orgName) {
            this.index = index;
            si[index] = this;
            this.orgName = orgName;

            if ($('participant.identifiers[' + index + '].organization'))
            {
                this.organizationName = "participant.identifiers[" + index + "].organization";
                this.organizationInputId = this.organizationName + "-input";
                if (orgName) $(this.organizationInputId).value = orgName;

                AE.createStandardAutocompleter(this.organizationName, this.sitePopulator.bind(this), this.siteSelector.bind(this));
            }

            this.indicator = "participant.identifiers[" + index + "].primaryIndicator1";
            Event.observe(this.indicator, "click", function() {
                for (i = 0; i < si.length; i++) {
                    if (i == this.index) continue;
                    $(si[i].indicator).checked = false;
                }
            }.bind(this));
            
               //fix for #9917 - Organization name reset
            Event.observe($('command'), "reset", function(event) {
                event.target.reset(); //explicitly call reset.
                Event.stop(event); //stop the event propagation
                if (this.orgName) $(this.organizationInputId).value = this.orgName;
            }.bind(this));

        },

        sitePopulator: function(autocompleter, text) {
            createParticipant.matchOrganization(text, function(values) {
                autocompleter.setChoices(values)
            })
        },

        siteSelector: function(organization) {
            var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
            return organization.name + nciInstituteCode
        }
    }
    );

    function fireAction(action, selected) {
        if (action == 'addIdentifier') {
            addIdentifierEditor.add.bind(addIdentifierEditor)();
        } else {
            document.getElementById('command')._target.name = '_noname';
            document.createParticipantForm._action.value = action;
            document.createParticipantForm._selected.value = selected;
            document.createParticipantForm.submit();
        }
    }

    function clearField(field) {
        field.value = "";
    }

    Event.observe(window, "load", function() {
        	
      		<c:forEach varStatus="status" items="${command.participant.identifiers}" var="si">
        		<c:if test="${(si.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					new jsIdentifier(${status.index}, '${si.organization.fullName}');
				</c:if>
					<c:if test="${(si.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
					new jsIdentifier(${status.index});
				</c:if>
				
      		</c:forEach>
      		
      		//This is added for Add Sysetem Identifiers button
		            new ListEditor("system-section-row", createParticipant, "Identifier", {
		            	addParameters: [1],
		            	addFirstAfter: "system-section",
		                addCallback: function(newIndex) {
                            var newIndex = 0;
                            var sysSectionLength = $$('.system-section-row').length;
                            var orgSectionLength = $$('.organization-section-row').length;
                            if (sysSectionLength > 0) newIndex = newIndex + sysSectionLength;
                            if (orgSectionLength > 0) newIndex = newIndex + orgSectionLength;
                            newIndex = newIndex - 1;
                            new jsIdentifier(newIndex);
                        }
                    });
		            //This is added for Add Organization Identifiers buttion
		             new ListEditor("organization-section-row", createParticipant, "Identifier", {
		            	addParameters: [2],
		            	addFirstAfter: "organization-section",
		                addCallback: function(newIndex) {
                            var newIndex = 0;
                            var sysSectionLength = $$('.system-section-row').length;
                            var orgSectionLength = $$('.organization-section-row').length;

                            if (sysSectionLength > 0) newIndex = newIndex + sysSectionLength;
                            if (orgSectionLength > 0) newIndex = newIndex + orgSectionLength;
                            newIndex = newIndex - 1;

                            new jsIdentifier(newIndex);
                        }
                     });
    });
	
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



<chrome:division  title="Subject Details">
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
    
            <chrome:division  title="Subject ID Assigned by Organization"  >
        	<table id="test" class="tablecontent">
    			<tr id="organization-section">
    				<th  class="tableHeader"><tags:requiredIndicator />Identifier</th>
    				<th  class="tableHeader"><tags:requiredIndicator />Identifier type</th>
    				<th  class="tableHeader"><tags:requiredIndicator />Organization</th>
    				<th  class="tableHeader"><tags:requiredIndicator />Primary indicator</th>
    			</tr>
    			
            	<c:forEach items="${command.participant.identifiers}" varStatus="status">
					<c:if test="${(command.participant.identifiers[status.index].class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
                        <par:parIdentifier
                                title="Subject Identifier ${status.index + 1}"
                                disableDelete="${fn:length(command.participant.identifiers) lt 2}"
                                sectionClass="organization-section-row"
                                removeButtonAction="removeIdentifier"
                                index="${status.index}"
                                identifier="${command.participant.identifiers[status.index]}"
                                mainGroupName="mainOrg"/>
					</c:if>
				</c:forEach>
            	
            	</table>
            	</chrome:division>
    
    
            <chrome:division title="Subject ID Assigned by a System">
        	<table id="test1" class="tablecontent" >
    			<tr id="system-section">
    				<th  class="tableHeader"><tags:requiredIndicator />Identifier</th>
    				<th  class="tableHeader"><tags:requiredIndicator />Identifier type</th>
    				<th  class="tableHeader"><tags:requiredIndicator />System name</th>
    				<th  class="tableHeader"><tags:requiredIndicator />Primary indicator</th>
    			</tr>
    			
            	<c:forEach items="${command.participant.identifiers}" varStatus="status" >
            	<c:if test="${(command.participant.identifiers[status.index].class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
		
					<par:parIdentifier
                            title="Subject Identifier ${status.index + 1}"
                            disableDelete="${fn:length(command.participant.identifiers) lt 2}"
					        sectionClass="system-section-row"
                            removeButtonAction="removeIdentifier"
					        index="${status.index}"
                            identifier="${command.participant.identifiers[status.index]}"
                            mainGroupName="mainSys" />
					
					</c:if>
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
