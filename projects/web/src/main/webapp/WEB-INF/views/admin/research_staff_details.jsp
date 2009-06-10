<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="today" class="java.util.Date" scope="request" />
<html>
<head>
<title><caaers:message code="researchStaff.details.pageTitle"/></title>
    <tags:stylesheetLink name="tabbedflow"/>
    <tags:includeScriptaculous/>
    <tags:stylesheetLink name="extremecomponents"/>
    <tags:includePrototypeWindow />
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

    <style type="text/css">
        /* Override default lable length */
        div.row div.label2 {
            width: 16em;
        }

        div.row div.value2 {
            margin-left: 17em;
        }

        div.content {
            padding: 5px 15px;
        }
    </style>

    <tags:dwrJavascriptLink objects="createIND"/>
    <script type="text/javascript">

    var today = '<tags:formatDate value="${today}"/>'
    
    Event.observe(window, "load", function() {
       if('${command.organization.name}'){
    	   if($('organization')){
    		   $('organization-input').value = '${command.organization.fullName}';
    	   }
    	   if(${fn:length(command.externalResearchStaff) gt 0}){
    		   displayRemoteResearchStaff();
			}
       }
       
  	 //initialze the auto completer field.
  	 if($('organization')){
  		AE.createStandardAutocompleter('organization', 
  		     	function(autocompleter, text) {
  		    		createIND.restrictOrganization(text, function(values) {
  		      	 		autocompleter.setChoices(values)
  		      		})
  		    	},
  		        function(organization) { 
  		        
  		                var image;            	
				    	if(organization.externalId != null){
				                  image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
				        } else {
				                  image = '';
				        }
        
  		    		   var nciInstituteCode = organization.nciInstituteCode == null ? "" : 
  		            							 " ( " + organization.nciInstituteCode + " ) ";
  					   return image + organization.name + nciInstituteCode  
  		    	}
  		);
  	 }
	 
     
    }); 

    function displayRemoteResearchStaff(){
			var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"remoteRS-popup-id", width:550,  height:200, top: 30, left: 300});
			contentWin.setContent( 'display_remote_rs' );
	        contentWin.showCenter(true);
	       popupObserver = {
	      			onDestroy: function(eventName, win) {
	      				if (win == contentWin) {
	      					$('display_remote_rs').style.display='none';
	      					
	      					contentWin = null;
	      					Windows.removeObserver(this);
	      				}
	      			}
	      		}
	        Windows.addObserver(popupObserver);
		}

	function submitRemoteRsForSave(){
		var form = document.getElementById('command');
		form._action.value="saveRemoteRs";
		form.submit();
	}

	function selectResearchStaff(selectedIndex){
		var form = document.getElementById('command')
		form._selected.value=selectedIndex;
		document.getElementById('save-yes').disabled = false;
	}

	function syncResearchStaff(){
		var form = document.getElementById('command');
		form._action.value="syncResearchStaff";
		form.submit();
	}

	function activateResearchStaff(){
		$('startdate_as_label').style.display="none";
		$('startdate_as_cal').style.display="";
		$('enddate_as_label').style.display="none";
		$('enddate_as_cal').style.display="";
		$('startDate').value=today;
		$('endDate').value="";
	}

	function deActivateResearchStaff(){
		$('endDate').value = today;
	}

    </script>
</head>
<body>

<div id="display_remote_rs" style="display:none;text-align:left" >
	<chrome:box title="Please select a ResearchStaff to be saved in caAERS" id="popupId">
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
            <c:forEach items="${command.externalResearchStaff}"  var="remRs" varStatus="rdStatus">
              <tr>
              	<td><input type="radio" name="remotersradio" value=${rdStatus.index} id="remoters-radio" onClick="javascript:selectResearchStaff('${rdStatus.index}');"/></td>
                <td align="left">${remRs.firstName}</td>
                <td align="left">${remRs.lastName}</td>
                <td align="left">${remRs.emailAddress}</td>
              </tr>
            </c:forEach>
          </table>
		</div>
		<br><br>
   		<table width="100%">	
   			<tr>
   				<td align="left">
   					<input type="submit" value="Cancel" id="save-no" onClick="javascript:window.parent.Windows.close('remoteRS-popup-id');"/>
   				</td>
   				<td align="right">
    				<input type="submit" disabled value="Ok" id="save-yes" onClick="javascript:window.parent.submitRemoteRsForSave();"/>
   				</td>
   			<tr>	
   		</table>
	</chrome:box>
</div>

<div class="tabpane">

<div class="workflow-tabs2">
    <ul id="" class="tabs autoclear">
        <li id="thirdlevelnav" class="tab selected">
            <div><a href="createResearchStaff"><caaers:message code="researchstaff.menu.createEditResearchStaff"/></a></div>
        </li>
        <li id="thirdlevelnav" class="tab">
            <div><a href="searchResearchStaff"><caaers:message code="researchstaff.menu.searchResearchStaff"/></a></div>
        </li>
    </ul>
</div>

<tags:tabForm tab="${tab}" flow="${flow}" formName="researchStaffForm">

<jsp:attribute name="repeatingFields">
	<input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
	<input type="hidden" name="_finish" value="true"/>

    <p><tags:instructions code="researchstaffdetails" /></p>
    
	<caaers:message code="researchstaff.details.siteSection" var="siteSectionTitle"/>
    <chrome:division title="${siteSectionTitle}">
	    <c:forEach items="${fieldGroups.site.fields}" var="field">
	        <csmauthz:accesscontrol domainObject="${organization}" hasPrivileges="ACCESS" authorizationCheckName="siteAuthorizationCheck">
	            <tags:renderRow field="${field}"/>
	        </csmauthz:accesscontrol>
	    </c:forEach>
    </chrome:division>

	<caaers:message code="researchstaff.details.detailsSection" var="detailsSectionTitle"/>
    <chrome:division title="${detailsSectionTitle}">
    <div class="leftpanel">
            <c:forEach begin="0" end="3" items="${fieldGroups.researchStaff.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
    </div>
    <div class="rightpanel">
         <c:forEach begin="5" end="8" items="${fieldGroups.researchStaff.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    </div>
    </chrome:division>

	<caaers:message code="researchStaff.statusSection" var="statusSection"/>
	<chrome:division title="${statusSection} -- (${command.status})">
		<div class="leftpanel">
			<tags:renderRow field="${fieldGroups.researchStaff.fields[4]}">
            	<jsp:attribute name="value">
            			<div style="${not empty command.startDate ? '':'display:none;'}" id="startdate_as_label">
            				<tags:formatDate value="${command.startDate}" />
            			</div>
            			<div style="${empty command.startDate ? '':'display:none;'}" id="startdate_as_cal">
            				<tags:renderInputs field="${fieldGroups.researchStaff.fields[4]}"/>
            			</div>	
            	</jsp:attribute>
            </tags:renderRow>	
		</div>
		<div class="rightpanel">
        	<tags:renderRow field="${fieldGroups.researchStaff.fields[9]}">
            	<jsp:attribute name="value">
            			<div style="display:none" id="enddate_as_cal">
            				<tags:renderInputs field="${fieldGroups.researchStaff.fields[9]}"/>
            			</div>	
            	</jsp:attribute>
            </tags:renderRow>
	</chrome:division>

<caaers:message code="researchstaff.details.rolesSection" var="roleSectionTitle"/>
<chrome:division id="staff-details" title="${roleSectionTitle}">

<div>

    <div class="row">
        <div class="label label2"><caaers:message code="role.subjectCoordinator"/></div>
        <div class="value value2">
            <input type="checkbox" onclick="this.checked?$('caaers_participant_cd').value='true':$('caaers_participant_cd').value='false';" ${caaers_participant_cd ? 'checked':'' }/>
            <input id="caaers_participant_cd" type="hidden" name="caaers_participant_cd" value="${caaers_participant_cd}" />
        </div>
    </div>

    <div class="row">
        <div class="label label2"><caaers:message code="role.studyCoordinator"/></div>
        <div class="value value2">
            <input type="checkbox" onclick="this.checked?$('caaers_study_cd').value='true':$('caaers_study_cd').value='false';" ${caaers_study_cd ? 'checked':''} />
            <input id="caaers_study_cd" type="hidden" name="caaers_study_cd" value="${caaers_study_cd}"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2"><caaers:message code="role.adverseEventCoordinator"/></div>
        <div class="value value2">
            <input type="checkbox" onclick="this.checked?$('caaers_ae_cd').value='true':$('caaers_ae_cd').value='false';" ${caaers_ae_cd ? 'checked':''} />
            <input id="caaers_ae_cd" type="hidden" name="caaers_ae_cd" value="${caaers_ae_cd}"/>
        </div>
    </div>
    
    <div class="row">
        <div class="label label2"><caaers:message code="role.centralOfficeReportReviewer"/></div>
        <div class="value value2">
            <input type="checkbox" onclick="this.checked?$('caaers_central_office_sae_cd').value='true':$('caaers_central_office_sae_cd').value='false';" ${caaers_central_office_sae_cd ? 'checked':''} />
            <input id="caaers_central_office_sae_cd" type="hidden" name="caaers_central_office_sae_cd" value="${caaers_central_office_sae_cd}"/>
        </div>
    </div>
    
    <div class="row">
        <div class="label label2"><caaers:message code="role.dataCoordinator"/></div>
        <div class="value value2">
            <input type="checkbox" onclick="this.checked?$('caaers_data_cd').value='true':$('caaers_data_cd').value='false';" ${caaers_data_cd ? 'checked':''} />
            <input id="caaers_data_cd" type="hidden" name="caaers_data_cd" value="${caaers_data_cd}"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2"><caaers:message code="role.siteCoordinator"/></div>
        <div class="value value2">
            <input type="checkbox" onclick="this.checked?$('caaers_site_cd').value='true':$('caaers_site_cd').value='false';" ${caaers_site_cd ? 'checked' :''} />
            <input id="caaers_site_cd" type="hidden" name="caaers_site_cd" value="${caaers_site_cd}"/>
        </div>
    </div>

</div>

</chrome:division>

</jsp:attribute>

	<jsp:attribute name="tabControls">
	 	<tags:tabControls tab="${tab}" flow="${flow}" willSave="false" saveButtonLabel="Save">
	 	
	 		<jsp:attribute name="customNextButton">
	 			<c:if test="${command.id != null && command.class.name eq 'gov.nih.nci.cabig.caaers.domain.LocalResearchStaff'}">
	 				<tags:button type="submit" value="Sync" color="blue"
									id="sync-rs" onclick="javascript:syncResearchStaff();" />
				</c:if>
				
				<c:if test="${command.id != null && command.active}">
	 				<tags:button type="button" value="Deactivate" color="blue"
									id="deactivate-rs" onclick="javascript:deActivateResearchStaff();" />
	 			</c:if>
	 			
	 			<c:if test="${command.id != null && command.inActive}">
	 				<tags:button type="button" value="Activate" color="blue"
									id="activate-rs" onclick="javascript:activateResearchStaff();" />
	 			</c:if>					
	 		</jsp:attribute>
	 	</tags:tabControls>
	 </jsp:attribute>

</tags:tabForm>
</body>
</html>
