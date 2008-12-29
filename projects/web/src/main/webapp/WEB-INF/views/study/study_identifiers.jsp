<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
            	
            	
            	if($('study.identifiersLazy['  + index + '].organization'))
            	{
            	this.organizationName = "study.identifiersLazy["  + index + "].organization";
                this.organizationInputId = this.organizationName + "-input";
            	this.orgName = orgName;
            	if(orgName) $(this.organizationInputId).value = orgName;
                
         
                     	
            	AE.createStandardAutocompleter(this.organizationName, 
            		this.sitePopulator.bind(this),
            		this.siteSelector.bind(this)
            	);
            	
            	}        	
            	
            	 //only one primary indicator is possible, by default Coordinating center identifier is primary
            	 this.indicator = "study.identifiersLazy["  + index + "].primaryIndicator";
            	 Event.observe(this.indicator, "click", function() {
            	 	for(i = 0; i < si.length; i++){
            	 		if(i == this.index){
            	 			$(si[i].indicator).checked = true; 
            	 			continue;
            	 		}
            	 		$(si[i].indicator).checked = false;
            	 	}
            	 }.bind(this));
               
            
            },sitePopulator: function(autocompleter, text) {
         		createStudy.matchOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	siteSelector: function(organization) {
        		var nciInstituteCode = organization.nciInstituteCode == null ? "" : 
            							 " ( " + organization.nciInstituteCode + " ) ";
        		return organization.name + nciInstituteCode
        	}
        	}
        	       
        	
    );
    function fireDelete(selected, trClass){
    	ValidationManager.validate = false; //do not validate on delete
		var ssfrm = $('command');
		ssfrm._target.name='_noname';
		ssfrm._action.value='removeIdentifier';
		ssfrm._selected.value=selected;		
		ssfrm.submit();
	}
   
	  
    Event.observe(window, "load", function() {
        	
      		<c:forEach varStatus="status" items="${command.study.identifiersLazy}" var="si">
        		<c:if test="${(si.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					new jsIdentifier(${status.index}, '${si.organization.fullName}');
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
						 if($('si-empty-row')){
                				Effect.Fade('si-empty-row');
               			 }  		            
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
<!--[if IE]>
<style>
#thirdlevelnav {
font-size: 10pt;
margin:5px;
}
</style>
<![endif]-->
</head>
<body>
<study:summary />
<p><tags:instructions code="study.study_identifers.top" /></p>
<tags:tabForm tab="${tab}" flow="${flow}" formName="studyIdentifiersForm" hideErrorDetails="true">   
   <jsp:attribute name="repeatingFields">
        	<div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
			</div>
			<chrome:division  title="Study ID Assigned by Organization"  >
        	<table id="test" class="tablecontent">
    			<tr id="organization-section">
    				<th class="tableHeader" ><tags:requiredIndicator />Identifier </th>
    				<th class="tableHeader" ><tags:requiredIndicator />Identifier type </th>
    				<th class="tableHeader" ><tags:requiredIndicator />Organization name </th>
    				 <th class="tableHeader" ><tags:requiredIndicator />Primary indicator</th>
    				<th class="tableHeader" >&nbsp;</th>
    			</tr>
    			<c:set var="cntOrg">0</c:set>
            	<c:forEach items="${command.study.identifiersLazy}" varStatus="status">
				  <c:if test="${(command.study.identifiersLazy[status.index].class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					<study:oneStudyChildRow cssClass="organization-section-row" index="${status.index}" idSuffix="${cntOrg}" exclusions="System Name" 
					identifiers="true" disableDelete="${status.index < 2}" />
					<c:set var="cntOrg">${cntOrg + 1}</c:set>
				  </c:if>
				</c:forEach>
            </table>
            </chrome:division>
 
           <chrome:division title="Study ID Assigned by a System">
        	<table id="test1" class="tablecontent" >
    			<tr id="system-section">
    				<th class="tableHeader" ><tags:requiredIndicator />Identifier </th>
    				<th class="tableHeader" ><tags:requiredIndicator />Identifier type </th>
    				<th class="tableHeader" ><tags:requiredIndicator />System name </th>
    				<th class="tableHeader" ><tags:requiredIndicator />Primary indicator</th>
    				<th class="tableHeader" >&nbsp;</th>
    			</tr>
				<c:if  test="${fn:length(command.study.systemAssignedIdentifiers) lt 1}">
	   			 <tr id="si-empty-row" class="si-empty-row"><td colspan="4">No system assigned an ID available to this study</td></tr>
	  			</c:if>    			
    			<c:set var="cntSys">0</c:set>
            	<c:forEach items="${command.study.identifiersLazy}" varStatus="status" >
            	 <c:if test="${(command.study.identifiersLazy[status.index].class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
				  <study:oneStudyChildRow cssClass="system-section-row" index="${status.index}" idSuffix="${cntSys}"
				  identifiers="true" exclusions="Organization" />
				  <c:set var="cntSys">${cntSys + 1}</c:set>
			     </c:if>
			    </c:forEach>
             </table>
           	</chrome:division>
    	
   </jsp:attribute>
   <jsp:attribute name="localButtons"> 
	 <tags:listEditorAddButton divisionClass="system-section-row" label="Add System Identifier" />   
     <tags:listEditorAddButton divisionClass="organization-section-row" label="Add Organization Identifier" />
     <br /> 
   </jsp:attribute>
</tags:tabForm>
</body>
</html>
