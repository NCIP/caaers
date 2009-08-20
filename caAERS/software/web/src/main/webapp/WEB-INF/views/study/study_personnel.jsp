<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>

<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <title>${tab.longTitle}</title>
  
   <tags:dwrJavascriptLink objects="createStudy"/>
   <script language="JavaScript" type="text/JavaScript">
     var personnelListEditor;
 	 function fireDelete(selected, trClass){
 		var confirmation = confirm("Do you really want to delete?");
		if(!confirmation) return; //return if not agreed.
		fireAction('removeStudyPersonnel',selected);
  	 }
     function fireAction(action, selectedPersonnel){
	    if(action == 'addStudyPersonnel'){
		  
	    }else{
	       ValidationManager.validate = false; //dont validate in delete
		   var form = document.getElementById('command')
		   form._target.name='_noname';
		   form._action.value=action;
		   form._selectedPersonnel.value=selectedPersonnel;
		   form.submit();
	    }
     }

     function deactivate(index) {
         fireAction("deactivate", index);
     }

     function activate(index) {
         fireAction("activate", index);
     }     

     var jsPersonnel = Class.create();
     Object.extend(jsPersonnel.prototype, {
           initialize: function(index, sitePersonnelName) {
            	this.index = index;
            	this.siteIndex = $F('studySiteIndex');
            	this.sitePersonnelName = sitePersonnelName;
            	this.sitePersonnelPropertyName = "study.activeStudyOrganizations["  + this.siteIndex + "].studyPersonnels[" + index + "].siteResearchStaff";
            	this.sitePersonnelInputId = this.sitePersonnelPropertyName + "-input";
            	if(sitePersonnelName) $(this.sitePersonnelInputId).value = sitePersonnelName;
            	AE.createStandardAutocompleter(this.sitePersonnelPropertyName, 
            		this.sitePersonnelPopulator.bind(this),
            		this.sitePersonnelSelector.bind(this),
           {
               afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                   var hidden = inputElement.name.substr(0, inputElement.name.indexOf("-input"));
                   var rolesField = $(inputElement.name.substr(0, inputElement.name.indexOf(".siteResearchStaff-input")) + ".roleCode");
                   $(hidden).value = selectedChoice.id;
                   ValidationManager.setValidState(inputElement.name);

                    createStudy.fetchSiteReseachStaffActiveRoles(selectedChoice.id, function(ajaxResult) {
                        rolesField.options.length = 0;
                        rolesField.options.add(new Option("Please select...", "-1"))
                        for (i in ajaxResult.objectContent) {
                            rolesField.options.add(new Option(ajaxResult.objectContent[i], i));
                        }
                    });
                   
               }

           }
            	);
            },            
            sitePersonnelPopulator: function(autocompleter, text) {
         		createStudy.matchSiteResearchStaff(text,this.siteIndex, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	sitePersonnelSelector: function(sPersonnel) {
        	 	  var image = "";
<%--
        	 	  if(sPersonnel.researchStaff.externalId != null){
                          image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
                  } else {
                          image = '';
                  }
--%>
        		return (image + "" + sPersonnel.researchStaff.fullName)
        	}
        	
     });

     Event.observe(window, "load", function() {
                  
	    //observe on the change event on study site dropdown.
	    Event.observe('studySiteIndex',"change", function(event){
   	      selIndex = $F('studySiteIndex');
		  fireAction('changeSite', selIndex);
	    });
	    
	    //initialize the list editor
	     personnelListEditor = new ListEditor('ssi-table-row',createStudy, "StudyPersonnel",{
             addParameters: [],
             addFirstAfter: "ssi-table-head",
             nextIndexCallback : function(){
  				return $('_ITEM_COUNT').value;
			 },
             addCallback: function(nextIndex) {
			   $('_ITEM_COUNT').value = parseInt($('_ITEM_COUNT').value) + 1;
          	   new jsPersonnel(nextIndex);
          	   if($('ssi-empty-row')){
                	Effect.Fade('ssi-empty-row');
               }
             }
         
    	   });  
     })
     
 function chooseSitesfromSummary(indx){
	var siteSelBox = $('studySiteIndex')
	siteSelBox.selectedIndex = indx + 1;
	fireAction('changeSite', indx);
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
<p><tags:instructions code="study.study_personnel.top" /></p>
<tags:tabForm tab="${tab}" flow="${flow}" formName="form" willSave="${not empty command.study.id}">
  <jsp:attribute name="singleFields">
	<input type="hidden" name="_action" value="">
	<input type="hidden" name="_selectedPersonnel" value="">
	<input type="hidden" name="_prevSite" value="${command.studySiteIndex}">
	<input type="hidden" id="_ITEM_COUNT" name="_ITEM_COUNT" value="${fn:length(command.study.activeStudyOrganizations[command.studySiteIndex].studyPersonnels)}">

    
	<table border="0" id="table1" cellspacing="1" cellpadding="0" width="100%">
	<tr>
		<td width="65%" valign="top" >
			<tags:instructions code="study.study_personnel.site" />
			<div class="value"><tags:renderInputs field="${fieldGroups.site.fields[0]}"/><tags:indicator id="ss-chg-indicator"/></div>
			<br />
			<hr>
			<div id="content-section">
				<c:if test="${command.studySiteIndex > -1 }">
					<study:oneStudySitePersonnel index="${command.studySiteIndex}"/>
				</c:if>
				<span id="ss-bookmark" />
                <div id="addStaffBtn" style="${command.studySiteIndex > -1 ? '' : 'display:none'}"><tags:listEditorAddButton divisionClass="ssi-table-row" label="Add Research Staff" /></div>
			</div>
	    </td>
      	<td valign="top" width="35%">
			<chrome:box title="Assigned Personnel" id="participant-entry2"  autopad="true">
 				<c:forEach var="studySite" varStatus="status" items="${command.study.activeStudyOrganizations}">
 					<div class =""><a style="cursor:pointer;" onclick="javascript:chooseSitesfromSummary(${status.index});" title="click here to edit research staff assigned to study">${studySite.organization.name}</a> <b>(${fn:length(studySite.studyPersonnels)})</b></div>
 					<%--<div class="">Personnel Assigned: <b>  </b></div>--%>
 				</c:forEach>
 				<div><img src="<c:url value="/images/chrome/spacer.gif" />" width="1" height="150" /></div>
 			</chrome:box>
		</td>
	  </tr>
	</table>
 </jsp:attribute>	

  
</tags:tabForm>
</body>
</html>
