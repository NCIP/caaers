<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<tags:includeScriptaculous/>
  <tags:dwrJavascriptLink objects="createStudy"/>
<script language="JavaScript" type="text/JavaScript">
	var si = [];
	var addIdentifierEditor;
	var jsIdentifier = Class.create();
	Object.extend(jsIdentifier.prototype, {	
            initialize: function(index,orgName) {
            	this.index = index;
            	si[index] = this;            	
            	
            	
            	if($('identifiersLazy['  + index + '].organization'))
            	{
        		
            	this.organizationName = "identifiersLazy["  + index + "].organization";
                this.organizationInputId = this.organizationName + "-input";
            	this.orgName = orgName;
            	if(orgName) $(this.organizationInputId).value = orgName;
                
         
                     	
            	AE.createStandardAutocompleter(this.organizationName, 
            		this.sitePopulator.bind(this),
            		this.siteSelector.bind(this)
            	);
            	
            	}        	
            	this.indicator = "identifiersLazy["  + index + "].primaryIndicator1";
            	 Event.observe(this.indicator, "click", function() {
            	 	for(i = 0; i < si.length; i++){
            	 		if(i == this.index) continue;
            	 		$(si[i].indicator).checked = false;
            	 	}
            	 }.bind(this));
            
            
            },sitePopulator: function(autocompleter, text) {
         		createStudy.matchOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	siteSelector: function(organization) { 
        		return organization.name 
        	}
        	}
        	       
        	
    );
    
    function fireAction(action, selected){
		if(action == 'addIdentifier'){
			addIdentifierEditor.add.bind(addIdentifierEditor)();
		}else{
			document.getElementById('command')._target.name='_noname';
			document.studyIdentifiersForm._action.value=action;
			document.studyIdentifiersForm._selected.value=selected;		
			document.studyIdentifiersForm.submit();
		}
	}
	
	function clearField(field){
		field.value="";
	}
	
	  
    Event.observe(window, "load", function() {
        	
      		<c:forEach varStatus="status" items="${command.identifiersLazy}" var="si">
        		<c:if test="${(si.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					new jsIdentifier(${status.index}, '${si.organization.name}');
				</c:if>
					<c:if test="${(si.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
					new jsIdentifier(${status.index});
				</c:if>
				
      		</c:forEach>
      		
      		//This is added for Add Sysetem Identifiers button
		            new ListEditor("system-section-row", createStudy, "Identifier", {
		            	addParameters: [1],
		            	addFirstAfter: "system-section",
		                addCallback: function(newIndex) {
						var newIndex = 0;
						var sysSectionLength = $$('.system-section-row').length;
						var orgSectionLength = $$('.organization-section-row').length;
						if(sysSectionLength > 0) newIndex = newIndex + sysSectionLength;
						if(orgSectionLength > 0) newIndex = newIndex + orgSectionLength;  		            
		                newIndex=newIndex-1;
             	new jsIdentifier(newIndex);
             }
		            });
		            //This is added for Add Organization Identifiers buttion
		             new ListEditor("organization-section-row", createStudy, "Identifier", {
		            	addParameters: [2],
		            	addFirstAfter: "organization-section",
		                addCallback: function(newIndex) { 
		                var newIndex = 0;
						var sysSectionLength = $$('.system-section-row').length;
						var orgSectionLength = $$('.organization-section-row').length;
						
						if(sysSectionLength > 0) newIndex = newIndex + sysSectionLength;
						if(orgSectionLength > 0) newIndex = newIndex + orgSectionLength;
						newIndex=newIndex-1;  		            
		                
             	new jsIdentifier(newIndex);
             }
            });      	
    });
	
</script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" formName="studyIdentifiersForm" hideErrorDetails="true">
    
    <jsp:attribute name="repeatingFields">
        	<div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>
		
		
        	<chrome:division title="System Identifiers">
        	<table id="test1" class="tablecontent" >
    			<tr id="system-section">
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Identifier:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Identifier type:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>System name:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Primary indicator:</b> </th>
    			</tr>
    			
            	<c:forEach items="${command.identifiersLazy}" varStatus="status" >
            	<c:if test="${(command.identifiersLazy[status.index].class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
		
					<study:studyIdentifier title="Study Identifier ${status.index + 1}" enableDelete="${status.index > 0}" 
					sectionClass="system-section-row" removeButtonAction="removeIdentifier" index="${status.index}" identifier="${command.identifiersLazy[status.index]}" />            
					</c:if>
						</c:forEach>
            	</table>
            	</chrome:division>
    	
		    
		
		
        	<chrome:division  title="Organization Identifiers"  >
        	<table id="test" class="tablecontent">
    			<tr id="organization-section">
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Identifier:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Identifier type:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Organization:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Primary indicator:</b> </th>
    			</tr>
    			
            	<c:forEach items="${command.identifiersLazy}" varStatus="status">
					<c:if test="${(command.identifiersLazy[status.index].class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					<study:studyIdentifier  title="Study Identifier ${status.index + 1}" enableDelete="${status.index > 0}" 
					sectionClass="organization-section-row" removeButtonAction="removeIdentifier" index="${status.index}" identifier="${command.identifiersLazy[status.index]}" />
					</c:if>
					            	</c:forEach>
            	
            	</table>
            	</chrome:division>
        </jsp:attribute>
    
    
	<jsp:attribute name="localButtons"> 
	      	<chrome:division title="">          	
	      		<tags:listEditorAddButton divisionClass="system-section-row" 	label="Add System Identifier" />   
                <tags:listEditorAddButton divisionClass="organization-section-row" label="Add Organization Identifier" /> 
            </chrome:division>                                                                                                                                                                                                                                                             
	</jsp:attribute>
	
</tags:tabForm>
</body>
</html>
