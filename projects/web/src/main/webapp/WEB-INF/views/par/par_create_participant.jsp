<%--
    TODO: this entire flow's views need to be refactored.
--%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="par" tagdir="/WEB-INF/tags/par" %>

<html>
<head>
    <tags:includeScriptaculous/>
    <tags:stylesheetLink name="participant"/>
    <style type="text/css">
        .leftpane { width: 32em }
    </style>
    
<tags:dwrJavascriptLink objects="createParticipant"/>
<script language="JavaScript" type="text/JavaScript">
	var si = [];
	var addIdentifierEditor;
	var jsIdentifier = Class.create();;
	Object.extend(jsIdentifier.prototype, {	
            initialize: function(index) {
            	this.index = index;
            	si[index] = this;
            	
            	if($('identifiers['  + index + '].organization'))
            	{
        		
            	this.organizationName = "identifiers["  + index + "].organization";
            	AE.createStandardAutocompleter(this.organizationName, 
            		this.sitePopulator.bind(this),
            		this.siteSelector.bind(this)
            	);
            	
            	}
            	this.indicator = "identifiers["  + index + "].primaryIndicator1";
            	 Event.observe(this.indicator, "click", function() {
            	 	for(i = 0; i < si.length; i++){
            	 		if(i == this.index) continue;
            	 		$(si[i].indicator).checked = false;
            	 	}
            	 }.bind(this));
            
            
            },sitePopulator: function(autocompleter, text) {
         		createParticipant.matchOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},siteSelector: function(organization) { 
        		return organization.name 
        	}
        	}
        	
        	         
        	
    );
    function fireAction(action, selected){
		if(action == 'addIdentifier'){
			addIdentifierEditor.add.bind(addIdentifierEditor)();
		}else{
			document.getElementById('command')._target.name='_noname';
			document.createParticipantForm._action.value=action;
			document.createParticipantForm._selected.value=selected;		
			document.createParticipantForm.submit();
		}
	}
	
	function clearField(field){
		field.value="";
	}
	
	  
    Event.observe(window, "load", function() {
        	
        	
        	<c:forEach varStatus="status" items="${command.identifiers}" var="si">
        		<c:if test="${(si.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					new jsIdentifier(${status.index}, '${si.organization}');
				</c:if>
      		</c:forEach>
      		
      		
      		//This is added for Add Sysetem Identifiers button
		            new ListEditor("par-id-section", createParticipant, "Identifier", {
		            	addParameters: [1],
		            	addFirstAfter: "single-fields",
		                addCallback: function(nextIndex) {
             	new jsIdentifier(nextIndex);
             }
		            });
		            //This is added for Add Organization Identifiers buttion
		             new ListEditor("par-id-section", createParticipant, "Identifier", {
		                addButton: "add-par-id-section-org-button",
		                addIndicator: "add-par-id-section-org-indicator",
		            	addParameters: [2],
		            	addFirstAfter: "single-fields",
		                addCallback: function(nextIndex) { 
             	new jsIdentifier(nextIndex);
             }
            });      	
    });
	
</script>
    
    
</head>
<body>
		
    <p class="instructions">
        You are creating a new Participant
    </p>
    
    <tags:tabForm tab="${tab}" flow="${flow}"  formName="createParticipantForm">


		 <jsp:attribute name="singleFields">
            <div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>
		
            <div class="participant-fields">
                <c:forEach items="${fieldGroups.participant.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
            </div>
            
		 <c:forEach varStatus="status" items="${command.identifiers}">	
				  <par:parIdentifier title="Study Identifier ${status.index + 1}" enableDelete="${status.index > 0}" 
					sectionClass="par-id-section" removeButtonAction="removeIdentifier" index="${status.index}" identifier="${command.identifiers[status.index]}" />
		</c:forEach>	
		      
     </jsp:attribute>
     
     <jsp:attribute name="localButtons">
     
           <chrome:division title=""> 
           		<tags:listEditorAddButton divisionClass="par-id-section-org" label="Add Organization Identifier"/>
           		<tags:listEditorAddButton divisionClass="par-id-section" 	label="Add System Identifier" />
           </chrome:division>
     </jsp:attribute>
     
     </tags:tabForm>    
</body>
</html>
