<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>Not implemented</title>
    <tags:dwrJavascriptLink objects="reportDef"/>
    <script>
 var win;
 
 //===================================================================================================
 // This will observe the window load, and will initilze the autocompleters. 
 Event.observe(window, "load", function() {
  //Calls ReportDefinitionAjaxFacade:matchOrganization(..)
   AE.createStandardAutocompleter('reportDefinition.organization',
       function(autocompleter, text) {
           reportDef.restrictOrganization(text, function(values) {
               autocompleter.setChoices(values)
           })
       },
       function(organization) {
           var image;
           if (organization.externalId != null) {
               image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
           } else {
               image = '';
           }

           var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
           return image + organization.name + nciInstituteCode;
       }, 
       {
       	afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
       		//set the value, call the validation, and then update the parent options
           	$('reportDefinition.organization').value = selectedChoice.id;
           	ValidationManager.setValidState($('reportDefinition.organization-input'));
           	fetchParentReportDefinitions();
       	}
   });

   //populate the name of the associated organization in 'organization-input' field
   $('reportDefinition.organization-input').value = '${command.reportDefinition.organization.fullName}';


	//observe change event on config type, so that the parent can be fliped. 
	$('reportDefinition.group').observe("change", function(evt){
		var elGroup = Event.element(evt);
		var len = elGroup.options.length;
		var selectedIndex = elGroup.selectedIndex;
		if(selectedIndex == (len-1)){
		 newReportTypePopup();
		}
	});

   
        
 });

//===================================================================================================
//this function will refresh the group options.     
function fetchReportGroups(){
	var elGroup = $('reportDefinition.group');
	reportDef.fetchReportGroups(function(ajaxOutput){
		elGroup.options.length = 0;
		for(var key in ajaxOutput.objectContent){
			elGroup.options.add(new Option(ajaxOutput.objectContent[key], key));
		}
		//select the last but one
		var len = elGroup.options.length;
		elGroup.options.selectedIndex = len - 2;
	});
}

 //===================================================================================================
 //this function will show the new group creation window.
function newReportTypePopup() {
    url = "createConfigProperty?subview=dummy&configPropertyType=REPORT_GROUP",
    win = new Window({className:"alphacube",
        destroyOnClose:true,
        url: url,
        title:"",
        width: 500,
        height: 300,
        recenterAuto: true,
        closable: true,
        resizable: true,
        maximizable: false,
        minimizable: false
    });

    win.showCenter(true);
}
 //===================================================================================================
//this function will update the parent options, based on the report definition selected.       
function fetchParentReportDefinitions(organizationID) {

	var elParent = $('reportDefinition.parent');
    var elOrg = $('reportDefinition.organization');

	//clear off the select values
	elParent.options.length = 0;

	//fetch the options from backend & show it there. 
	if(elOrg.value){
		reportDef.fetchParentReportDefinitions(elOrg.value, function (ajaxOutput){
			for(var key in ajaxOutput.objectContent){
				elParent.options.add(new Option(ajaxOutput.objectContent[key], key));
			}
		});
	}

}
//===================================================================================================   
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