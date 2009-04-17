<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>Investigator</title>
<tags:includeScriptaculous />
<tags:stylesheetLink name="extremecomponents"/>
<tags:includePrototypeWindow />
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

  if(${fn:length(command.externalInvestigators) gt 0}){
		 displayRemoteInvestigator();
  }
  		
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

	function displayRemoteInvestigator(){
		var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"remoteInv-popup-id", width:550,  height:200, top: 30, left: 300});
		contentWin.setContent( 'display_remote_inv' );
	    contentWin.showCenter(true);
	   popupObserver = {
	  			onDestroy: function(eventName, win) {
	  				if (win == contentWin) {
	  					$('display_remote_inv').style.display='none';
	  					
	  					contentWin = null;
	  					Windows.removeObserver(this);
	  				}
	  			}
	  		}
	    Windows.addObserver(popupObserver);
	}

	function submitRemoteInvForSave(){
		var form = document.getElementById('command');
		form._action.value="saveRemoteInv";
		form.submit();
	}

	function selectInvestigator(selectedIndex){
		var form = document.getElementById('command')
		form._selected.value=selectedIndex;
		document.getElementById('save-yes').disabled = false;
	}

	function syncInvestigator(){
		var form = document.getElementById('command');
		form._action.value="syncInvestigator";
		form.submit();
	}

</script>

</head>
<body>

<div id="display_remote_inv" style="display:none;text-align:left" >
	<chrome:box title="Please select a Investigator to be saved in caAERS" id="popupId">
		<div class="eXtremeTable">
          <table width="100%" border="0" cellspacing="0"  class="tableRegion">
            <thead>
              <tr align="center" class="label">
              	<td/>
                <td class="tableHeader">First Name</td>
                <td class="tableHeader">Last Name</td>
                <td class="tableHeader">Email Address</td>
              </tr>
            </thead>
            <c:forEach items="${command.externalInvestigators}"  var="remInv" varStatus="rdStatus">
              <tr>
              	<td><input type="radio" name="remotersradio" value=${rdStatus.index} id="remoters-radio" onClick="javascript:selectInvestigator('${rdStatus.index}');"/></td>
                <td align="left">${remInv.firstName}</td>
                <td align="left">${remInv.lastName}</td>
                <td align="left">${remInv.emailAddress}</td>
              </tr>
            </c:forEach>
          </table>
		</div>
		<br><br>
   		<table width="100%">	
   			<tr>
   				<td align="left">
   					<input type="submit" value="Cancel" id="save-no" onClick="javascript:window.parent.Windows.close('remoteInv-popup-id');"/>
   				</td>
   				<td align="right">
    				<input type="submit" disabled value="Ok" id="save-yes" onClick="javascript:window.parent.submitRemoteInvForSave();"/>
   				</td>
   			<tr>	
   		</table>
	</chrome:box>
</div>



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
	 willSave="false">

	<jsp:attribute name="singleFields">
	<div>
	<c:if test="${(command.externalId != null)}"> <img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/> </c:if>
	
	<input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
	<input type="hidden" name="_finish" value="true"/>
	</div>

    </jsp:attribute>
	<jsp:attribute name="repeatingFields">
            <p>
        <tags:instructions code="investigatordetails" />
        </p>
		
    	
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
            
            <c:if test="${authenticationMode == 'webSSO'}">
               <tags:renderRow field="${fieldGroups.investigator.fields[7]}"/>
            </c:if>
		</div>
		<div id="test-row" class="row"></div>
	<c:if test="${(command.id gt 0) }">
	<div class="row">
		<div class="value">
			<input type="submit" value="Sync" id="sync-rs" onClick="javascript:syncInvestigator();"/>
		</div>
	</div>
	</c:if>
		
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
