<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
        .label {
            width: 12em;
            text-align: right;
        }
    </style>

    <tags:dwrJavascriptLink objects="createStudy"/>

<script language="JavaScript" type="text/JavaScript">
  var invListEditor;
  function fireDelete(selected, trClass){
		var confirmation = confirm("Do you really want to delete?");
		if(!confirmation) return; //return if not agreed.
	    fireAction('removeInv', selected);
  }

  function deactivate(index) {
      fireAction("deactivate", index);
  }

  function activate(index) {
      fireAction("activate", index);
  }

  function fireAction(action, selectedInvestigator){
      if (action == 'addInv') {

      } else {
          ValidationManager.validate = false;
          var form = document.getElementById('command')
          form._target.name = '_noname';
          form._action.value = action;
          form._selectedInvestigator.value = selectedInvestigator;
          form.submit();
      }
  }

  var jsInvestigator = Class.create();
  Object.extend(jsInvestigator.prototype, {
           initialize: function(index, siteInvestigatorName) {
                this.index = index;
            	this.siteIndex = $F('studySiteIndex');
                this.siteInvestigatorName = siteInvestigatorName;
            	this.siteInvestigatorPropertyName = "study.activeStudyOrganizations["  + this.siteIndex + "].studyInvestigators[" + index + "].siteInvestigator";

                this.siteInvestigatorInputId = this.siteInvestigatorPropertyName + "-input";
            	if (siteInvestigatorName) $(this.siteInvestigatorInputId).value = siteInvestigatorName;
            	AE.createStandardAutocompleter(this.siteInvestigatorPropertyName, this.siteInvestigatorPopulator.bind(this), this.siteInvestigagorSelector.bind(this));
            },
      
            siteInvestigatorPopulator: function(autocompleter, text) {
         		createStudy.matchSiteInvestigator(text, this.siteIndex, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	siteInvestigagorSelector: function(sInvestigator) { 
        			var image;
        	 	  if(sInvestigator.investigator.externalId != null){
                          image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
                  } else {
                          image = '';
                  }
                  
        		return (image + "" + sInvestigator.investigator.fullName);
        	}
        	
  });

  Event.observe(window, "load", function() {
                  
	//observe on the change event on study site dropdown.
	Event.observe('studySiteIndex',"change", function(event){
   	    selIndex = $F('studySiteIndex');
		fireAction('changeSite', selIndex);
	 });

	 //init the list editor
	 invListEditor = new ListEditor('ssi-table-row', createStudy, "Investigator",{
             addParameters: [],
             addFirstAfter: "ssi-empty-row",
             nextIndexCallback : function(){
     			return $('_ITEM_COUNT').value;
 			 },
             addCallback: function(nextIndex) {
		 	   $('_ITEM_COUNT').value = parseInt($('_ITEM_COUNT').value) + 1;
          	   new jsInvestigator(nextIndex);
          	   if ($('ssi-empty-row')){
                    Effect.Fade('ssi-empty-row');
               }
             }
         
    	});  
	 
  });

  function chooseSitesfromSummary(indx){
	var siteSelBox = $('studySiteIndex')
	siteSelBox.selectedIndex = indx + 1;
	fireAction('changeSite', indx);
  }
</script>
 
</head>
<body>
<study:summary />
<tags:tabForm tab="${tab}" flow="${flow}" formName="studyInvestigatorForm" hideErrorDetails="false" willSave="${not empty command.study.id}">
<jsp:attribute name="singleFields">
 <input type="hidden" name="_action" value="">
 <input type="hidden" name="_prevSite" value="${command.studySiteIndex}">
 <input type="hidden" name="_selectedInvestigator" value="">
 <input type="hidden" id="_ITEM_COUNT" name="_ITEM_COUNT" value="${fn:length(command.study.activeStudyOrganizations[command.studySiteIndex].studyInvestigators)}">

 <table border="0" id="table1" cellspacing="1" cellpadding="0" width="100%">
	<tr>
		<td width="65%" valign="top" >
		<p><tags:instructions code="study.study_investigators.top" /></p>
		<div class="value"><tags:renderInputs field="${fieldGroups.site.fields[0]}"/><tags:indicator id="ss-chg-indicator"/></div>
		<br />
		<hr>
		<div id="content-section">
            <span id="ssi-bookmark" />
            <c:if test="${command.studySiteIndex > -1 }">
				<study:oneStudySiteInvestigator index="${command.studySiteIndex}"/>
			</c:if>
            <div id="addInvBtn" style="${command.studySiteIndex > -1 ? '' : 'display:none'}"><tags:listEditorAddButton divisionClass="ssi-table-row" label="Add Investigator" /></div>
		</div>
	    </td>
      	<td valign="top" width="35%">
			<chrome:box title="Assigned Investigators" id="participant-entry2" autopad="true">
 				<c:forEach var="studySite" varStatus="status" items="${command.study.activeStudyOrganizations}">
 					<div class =""><a style="cursor:pointer;" href="javascript:chooseSitesfromSummary(${status.index});" title="click here to edit investigator assigned to study">${studySite.organization.name}</a> <b>(${fn:length(studySite.activeStudyInvestigators)})</b></div>
 				</c:forEach>
 				<div>
 				   <img src="<c:url value="/images/chrome/spacer.gif" />" width="1" height="150" />
 				</div>
                
 			</chrome:box>
		</td>
	  </tr>
	</table>
 </jsp:attribute>	

</tags:tabForm>
</body>
</html>
