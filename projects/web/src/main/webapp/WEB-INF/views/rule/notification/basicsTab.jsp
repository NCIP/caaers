<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>Not implemented</title>
    <tags:dwrJavascriptLink objects="reportDef"/>
    <script>
     
      Event.observe(window, "load", function() {
    	//Calls ReportDefinitionAjaxFacade:matchOrganization(..)
    	AE.createStandardAutocompleter(
    		'reportDefinition.organization', function(autocompleter, text) {
         		reportDef.restrictOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	}, function(organization) { 
  		                var image;            	
				    	if(organization.externalId != null){
				                  image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
				        } else {
				                  image = '';
				        }
				                	
        		 var nciInstituteCode = organization.nciInstituteCode == null ? "" : 
            							 " ( " + organization.nciInstituteCode + " ) ";
        		return image + organization.name + nciInstituteCode;
        	});
        
        //populate the name of the associated organization in 'organization-input' field	
      	$('reportDefinition.organization-input').value = '${command.reportDefinition.organization.fullName}';
      });
      
    </script>
<link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>   
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}">
		<jsp:attribute name="singleFields">
        <tags:instructions code="createreportdefinition" />
			<div>
			<tags:renderRow field="${fieldGroups.reportDefinitionOrganization.fields[0]}" />
			</div>
            <div id="ruleset-fields">
                <c:forEach items="${fieldGroups.reportDefinitionFieldGroup.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
               <input type="hidden" name="lastPointOnScale" value=""/>
               <input type="hidden" name="notificationType" value="EMAIL_NOTIFICATION" />
            </div>
		</jsp:attribute>
	</tags:tabForm> 
    
</body>


</html>