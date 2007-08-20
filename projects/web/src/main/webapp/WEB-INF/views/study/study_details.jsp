<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<title>${tab.longTitle}</title>
  
	<tags:javascriptLink name="hover-display" />
  
	<tags:includeScriptaculous/>
  
	<tags:dwrJavascriptLink objects="createStudy"/>
  
	
	<script>
  	
	
	Event.observe(window, "load", function() {
    	
		Event.observe("terminology.term", "change", function() { showCtcTerms(); showMeddraTerms(); })	
		Event.observe("multiInstitutionIndicator", "change", function() { showOrganizationIdentifier(); dontShowOrganizationIdentifier();})
		
		
		
		function showOrganizationIdentifier(){
				if ($('multiInstitutionIndicator').options[0].selected ){
					Effect.toggle($('organizationAssignedIdentifier.organization-row'), 'slide');
					Effect.toggle($('organizationAssignedIdentifier.value-row'), 'slide');
				}
		}
		function dontShowOrganizationIdentifier(){
				if ($('multiInstitutionIndicator').options[1].selected ){
					Effect.toggle($('organizationAssignedIdentifier.organization-row'), 'slide');
					Effect.toggle($('organizationAssignedIdentifier.value-row'), 'slide');
				}
		}
		
		
		showMeddraTerms()
		function showCtcTerms(){
				if ($('terminology.term').options[0].selected ){
					Effect.toggle($('terminology.ctcVersion-row'), 'slide');
					Effect.toggle($('terminology.meddraVersion-row'), 'slide');
				}
		}
		function showMeddraTerms(){		
				if ($('terminology.term').options[1].selected){
					$('terminology.ctcVersion').options[0].selected=true
					Effect.toggle($('terminology.ctcVersion-row'), 'slide');
					Effect.toggle($('terminology.meddraVersion-row'), 'slide');
				}else{
					$('terminology.meddraVersion-row').style.display="none"
				}
		}
	
		//Calls CreateStudyAjaxFacade:matchOrganization(..)
    	
		AE.createStandardAutocompleter('primaryFundingSponsorOrganization', 
			function(autocompleter, text) {
					createStudy.matchOrganization(text, function(values) {
													autocompleter.setChoices(values)
												})
				}, 
				function(organization) { return organization.name });
        
        
				//populate the name of the associated organization in 'primaryFundingSponsorOrganization-input' field	
      	
				$('primaryFundingSponsorOrganization-input').value = '${command.primaryFundingSponsorOrganization.name}';
				
				if($('organizationAssignedIdentifier.organization')){
            	
            	$('organizationAssignedIdentifier.organization-input').value = '${command.organizationAssignedIdentifier.organization.name}';
        		this.organizationName = "organizationAssignedIdentifier.organization";
                this.organizationInputId = this.organizationName + "-input";
               	AE.createStandardAutocompleter(this.organizationName, 
			
			function(autocompleter, text)
			{		
				createStudy.matchOrganization(text,	function(values) 
					{autocompleter.setChoices(values)})
			},function(organization) { return organization.name }
		);
		
            	
            	}      	
      
	});
  
	</script> 

</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="true">
    
	<jsp:attribute name="singleFields">
		<div id="study-details-fields">
          
       		<c:forEach items="${fieldGroups.studyDetails.fields}" var="field" varStatus="status">
				<tags:renderRow field="${field}"/>
       		</c:forEach>
        
       	</div>
       	
       	<chrome:division title="Multi-Institutional Details" >
			<c:forEach items="${fieldGroups.organizationFieldGroup.fields}" var="field" varStatus="status">
			
				<tags:renderRow field="${field}"  style="${command.multiInstitutionIndicator or status.index eq 0? '':'display:none'}" />
				
			</c:forEach>
        </chrome:division>
       	
	</jsp:attribute>

</tags:tabForm>
</body>
</html>
