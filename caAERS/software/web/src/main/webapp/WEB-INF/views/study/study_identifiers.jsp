<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<csmauthz:accesscontrol var="_isSSIMonCC" scope="request" domainObject="${command.study.studyCoordinatingCenter.organization}" authorizationCheckName="siteAuthorizationCheck" hasPrivileges="supplemental_study_information_manager" />
<csmauthz:accesscontrol var="_isSSIMonFS" scope="request" domainObject="${command.study.primaryFundingSponsor.organization}" authorizationCheckName="siteAuthorizationCheck" hasPrivileges="supplemental_study_information_manager" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
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
            	
/*
            	 //only one primary indicator is possible, by default Coordinating center identifier is primary
            	 this.indicator = "study.identifiersLazy["  + index + "].primaryIndicator";
            	 Event.observe(this.indicator, "click", function() {
            	 	for(i = 0; i < si.length; i++) {
            	 		if(i == this.index){
            	 			$(si[i].indicator).checked = true; 
            	 			$(si[i].indicator).value = "true";
            	 			continue;
            	 		}
            	 		$(si[i].indicator).checked = false;
            	 		$(si[i].indicator).value = "false";
            	 	}
            	 }.bind(this));
*/

            
            },

            sitePopulator: function(autocompleter, text) {
                <c:if test="${_isSSIMonCC || _isSSIMonFS}">
                    createStudy.matchStudyOrganizations(text, ${command.study.id}, function(values) {
                        autocompleter.setChoices(values)
                    })
                </c:if>
                <c:if test="${!_isSSIMonCC && !_isSSIMonFS}">
                    createStudy.restrictOrganizations(text, false, function(values) {
                        autocompleter.setChoices(values)
                    })
                </c:if>
        	},
        	
        	siteSelector: function(organization) {
        	    var image;            	
            	if (organization.externalId != null) {
                    image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
                } else {
                    image = '';
                }
                
        		var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
        		return image + "" + organization.name + nciInstituteCode
        	}
        	}
        	       
        	
    );
    function fireDelete(selected, trClass){
    	ValidationManager.validate = false; //do not validate on delete
		var ssfrm = $('command');
		ssfrm._target.name='_noname';
		ssfrm._action.value='removeIdentifier';
		ssfrm._selected.value=selected;
        if (AE.checkForModification) AE.checkForModification = false; 
		ssfrm.submit();
	}
   
	  
    Event.observe(window, "load", function() {
        	
      		<c:forEach varStatus="status" items="${command.study.identifiersLazy}" var="si">
        		<c:if test="${(si.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					new jsIdentifier(${status.index}, "${si.organization.fullName}");
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

    /* Will finish the data entry status*/
    function dataEntryComplete(){
       if(exist('study.dataEntryStatus')){
           $('study.dataEntryStatus').value = 'true';
       }
    }
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
<c:set var="isLast" value="${not (tab.number < flow.tabCount - 1)}"/>
<tags:tabForm tab="${tab}" flow="${flow}" formName="studyIdentifiersForm" hideErrorDetails="false">   
   <jsp:attribute name="repeatingFields">
        	<div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
			</div>
			<chrome:division  title="Study ID Assigned by Organization"  >

            <tags:table bgColor="#cccccc" contentID="ID_si">
        	<table id="test" cellpadding="3" cellspacing="1" border="0" width="100%">
    			<tr id="organization-section" bgcolor="#eeeeee">
    				<th class="tableHeader" width="140px"><tags:requiredIndicator />Identifier </th>
    				<th class="tableHeader" width="200px"><tags:requiredIndicator />Identifier type </th>
    				<th class="tableHeader"><tags:requiredIndicator />Organization name </th>
    				<th class="tableHeader" width="120px"><tags:requiredIndicator />Primary indicator</th>
    				<th class="tableHeader" width="60px"><caaers:message code="LBL_captureAdverseEvents.tableHeader.action" /></th>
    			</tr>
    			<c:set var="cntOrg">0</c:set>
            	<c:forEach items="${command.study.identifiersLazy}" varStatus="status">
				  <c:if test="${(command.study.identifiersLazy[status.index].class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					    <study:oneStudyChildIdentifierRow cssClass="organization-section-row" index="${status.index}" idSuffix="${cntOrg}" exclusions="System Name" disableDelete="${status.index < 2}" readOnly="true"/>
					    <c:set var="cntOrg">${cntOrg + 1}</c:set>
				  </c:if>
				</c:forEach>
            </table>
            </tags:table>
            </chrome:division>
 
           <chrome:division title="Study ID Assigned by a System">
            <tags:table bgColor="#cccccc" contentID="test1">
        	<table id="test1"  width="100%" cellpadding="3" cellspacing="1">
    			<tr bgcolor="#eeeeee" id="system-section">
    				<th class="tableHeader" width="140px"><tags:requiredIndicator />Identifier </th>
    				<th class="tableHeader" width="200px"><tags:requiredIndicator />Identifier type </th>
    				<th class="tableHeader"><tags:requiredIndicator />System name </th>
    				<th class="tableHeader" width="120px"><tags:requiredIndicator />Primary indicator</th>
    				<th class="tableHeader" width="60px"><caaers:message code="LBL_captureAdverseEvents.tableHeader.action" /></th>
    			</tr>
				<c:if  test="${fn:length(command.study.systemAssignedIdentifiers) lt 1}">
	   			 <tr id="si-empty-row" class="si-empty-row" bgcolor="#ffffff"><td colspan="5">No system assigned an ID available to this study</td></tr>
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
             </tags:table>
           	</chrome:division>

<br/>
<tags:listEditorAddButton divisionClass="system-section-row" label="Add System Identifier"/>
<tags:listEditorAddButton divisionClass="organization-section-row" label="Add Organization Identifier"/>
<br/>
   <form:hidden path="study.dataEntryStatus" id="study.dataEntryStatus" />  	
   </jsp:attribute>
   <jsp:attribute name="tabControls">
        <tags:tabControls flow="${flow}" tab="${tab}" willSave="true">
            <jsp:attribute name="customNextButton">
                <c:if test="${not command.dataEntryComplete}">
                    <tags:button value="Data Entry Complete" color="orange" icon="check" id="btnDataEntryComplete" onclick="dataEntryComplete();" type="submit"/>
                </c:if>
            </jsp:attribute>
        </tags:tabControls>
   </jsp:attribute>
</tags:tabForm>
</body>
</html>
