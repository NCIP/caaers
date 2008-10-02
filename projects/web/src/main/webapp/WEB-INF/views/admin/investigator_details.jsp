<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Investigator</title>
<tags:includeScriptaculous />
<tags:stylesheetLink name="tabbedflow"/>
 	 <style type="text/css">
        div.content {
            padding: 5px 15px;
        }
    </style>

<tags:dwrJavascriptLink objects="createInvestigator" />
<tags:dwrJavascriptLink objects="createIND"/>
<script language="JavaScript" type="text/JavaScript">


var addInvestigatorEditor;
var jsInvestigator = Class.create();
Object.extend(jsInvestigator.prototype, {
		
	initialize: function(index, orgName) {
	 this.index = index;
	 this.orgField = 'siteInvestigators['+ index + '].organization';
	 this.orgInputField = this.orgField + '-input';
	 
	 //initialze the auto completer field.
	 AE.createStandardAutocompleter(this.orgField, 
     	this.sitePopulator.bind(this),
        this.siteSelector.bind(this)
     );
     
	 if(orgName) $(this.orgInputField).value = orgName;
	 
	},sitePopulator: function(autocompleter, text) {
    	createIND.matchOrganization(text, function(values) {
      	 autocompleter.setChoices(values)
      })
    },siteSelector: function(organization) { 
    	return organization.name + " (" + organization.nciInstituteCode + ")";
    }
	
   });
    
   function fireAction(action, selected){
		if(action == 'addInvestigator'){
			addInvestigatorEditor.add.bind(addInvestigatorEditor)();
		}else{
			document.getElementById('command')._target.name='_noname';
			document.createInvestigatorForm._action.value=action;
			document.createInvestigatorForm._selected.value=selected;
			document.createInvestigatorForm._finish.name='xyz';		
			document.createInvestigatorForm.submit();
		}
	};
	
	function clearField(field){
		field.value="";
	};
	  
Event.observe(window, "load", function() {
  <c:forEach varStatus="status" items="${command.siteInvestigators}" var="si">
	new jsInvestigator(${status.index}, '${si.organization.fullName}');
  </c:forEach>
      		
  //This is added for Add Site Investigator button
  new ListEditor("site-investigator-row", createInvestigator, "SiteInvestigator", {
	addFirstAfter: "site-investigator",
	addCallback: function(nextIndex) {
	 new jsInvestigator(nextIndex);
	 if($('empty-inv-row')){
     	Effect.Fade('empty-inv-row');
     }
    }
   });
   
});
</script>

</head>
<body>

<div class="tabpane">
    <div class="workflow-tabs2">
        <ul id="" class="tabs autoclear">
            <li id="thirdlevelnav" class="tab selected">
                <div>
                    <a href="createInvestigator">Create/Edit Investigator</a>
                </div>
            </li>
            <li id="thirdlevelnav" class="tab">
                <div>
                    <a href="searchInvestigator">Search Investigator</a>
                </div>
            </li>
        </ul>
    </div>
</div>
  <br />
 

<tags:tabForm tab="${tab}" flow="${flow}" formName="createInvestigatorForm"
	hideErrorDetails="true" willSave="false">

	<jsp:attribute name="singleFields">
	<div><input type="hidden" name="_action" id="_action" value="">
	<input type="hidden" name="_selected" id="_selected" value="-1">
	    <c:if test="${(empty id) or ( id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
	</div>

    </jsp:attribute>
	<jsp:attribute name="repeatingFields">
    
    
    	<chrome:division title="Investigator Details" id="investigator">
		<div class="leftpanel">
			<c:forEach begin="0" end="3" items="${fieldGroups.investigator.fields}" var="field">
               <tags:renderRow field="${field}"  />
            </c:forEach>
		</div>
		<div class="rightpanel">
		    <c:forEach begin="4" end="6" items="${fieldGroups.investigator.fields}" var="field">
              <tags:renderRow field="${field}" />
            </c:forEach>
		</div>
		<div id="test-row" class="row"></div>
	</chrome:division>
	<chrome:division title="Associate Organizations">
	  
	  <table class="tablecontent" width="78%">
    			<tr id="site-investigator">
    				<th class="tableHeader"><tags:requiredIndicator />Organization</th>
    				<th class="tableHeader"><tags:requiredIndicator />Status</th>
    			</tr>
            	<c:forEach varStatus="status" items="${command.siteInvestigators}">
					<investigator:siteInvestigator 	title="Associated Sites ${status.index + 1}" enableDelete="${status.index > 0}"
						sectionClass="site-investigator-row"
						removeButtonAction="removeInvestigator" index="${status.index}" />
				</c:forEach>
            	<c:if test="${empty command.siteInvestigators}">
            		<tr id="empty-inv-row">
            			<td colspan="2" align="center">The investigator is not assigned to any organization</td>
            		</tr>
            	</c:if>
       </table>
	
	</chrome:division>
         
     </jsp:attribute>

	<jsp:attribute name="localButtons"> 
	      	<chrome:division title="">          	
	      		<tags:listEditorAddButton divisionClass="site-investigator-row" label="Add Organization" />   
            </chrome:division>                                                                                                                                                                                                                                                             
	</jsp:attribute>

</tags:tabForm>

</body>
</html>
