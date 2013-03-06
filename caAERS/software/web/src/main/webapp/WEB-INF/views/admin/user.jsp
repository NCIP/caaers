<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<csmauthz:accesscontrol var="hasRSCreate" objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:CREATE" />
<csmauthz:accesscontrol var="hasRSUpdate" objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:UPDATE" />
<csmauthz:accesscontrol var="hasRSRead"   objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:READ" />
<csmauthz:accesscontrol var="investigatorCreate" objectPrivilege="gov.nih.nci.cabig.caaers.domain.Investigator:CREATE"/>
<csmauthz:accesscontrol var="investigatorUpdate" objectPrivilege="gov.nih.nci.cabig.caaers.domain.Investigator:UPDATE"/>
<csmauthz:accesscontrol var="userCreate" objectPrivilege="gov.nih.nci.cabig.caaers.domain.CSMUser:CREATE"/>
<csmauthz:accesscontrol var="userUpdate" objectPrivilege="gov.nih.nci.cabig.caaers.domain.CSMUser:UPDATE"/>

<style>
    .autocomplete {
        width : 510px;
    }
</style>

<html>
    <head>
    	<title>User</title>
    	<tags:js name="ui/ajaxCRUD" />
    </head>
    
    <body>
        <style>
            .CSSDate { width: 80px; }
            .hand { cursor: pointer; }
			div.row div.label { width:13em; }
			div.row div.value { margin-left:14em; }
			.rightpanel { width:54% }
        </style>

        <tags:dwrJavascriptLink objects="user,createStudy"/>

        <script type="text/javascript">
            var siteAdded = false;
            Event.observe(window, "load", function() {
		        //remove the query string from form url
		        removeQueryStringFromForm('command');

                jQuery("#personType").change(function() {
                    var value = jQuery(this).val();
                    if (!siteAdded && value != "") {
                        addSitePerson();
                        siteAdded = true;
                    }
                });
             });

			ajaxCRUD = new AJAX_CRUD_HELPER();
			var sitesCount = new Array();
			var studiesCount = new Array();
            AE.sitePersonOrgs = new Array();
            <c:forEach items="${command.sitePersonnel}" var="sp">
               <c:if test="${not empty sp.organization}">
                 AE.sitePersonOrgs.push(${empty sp.organization.id ? 0 : sp.organization.id});  
               </c:if>
            </c:forEach>


	    	var tableRow = "<tr id='#{trId}'><td>#{selectedChoiceForDisplay}<input type='hidden' id='#{fldName}' name='#{fldName}' value='#{identifier}' /></td><td>#{deleteBtn}</td></tr>";
	    	
			function addSite(el, index){

				Form.Element.disable('addSite_btn['+index+']');
				
				var _selectedSiteForDisplay = $(el+".selectedSiteForDisplay").value;
				var _nciCode = $(el+".nciCode").value;
				var _tableId = el+"-sitesTable";
				var _sitesFldName = el + '.sites';
				var _trId = el + '-site-' +_nciCode;
				var _deleteBtn = "<a href=\"javascript:removeSite('" + el + "-site-" +_nciCode + "','" +index+ "');\"><img src='<c:url value="/images/buttons/button_icons/small/x_icon_small.png" />' border='1' alt='delete'></a>"
				
				$(_tableId).down('tr').insert({
					after: tableRow.interpolate({selectedChoiceForDisplay:_selectedSiteForDisplay, identifier : _nciCode ,fldName : _sitesFldName, deleteBtn : _deleteBtn, trId : _trId })
					});
				sitesCount[index] = sitesCount[index] + 1;
				
				AE.resetAutocompleter(el+".selectedSiteForDisplay");
				updateRoleSummary(index);
			}

			function addStudy(el,index){
				Form.Element.disable('addStudy_btn['+index+']');
				var _selectedStudyForDisplay = $(el+".selectedStudyForDisplay").value;	
				var _studyId = $(el+".studyId").value;
				var _tableId = el+"-studiesTable";
				var _studiesFldName = el + '.studies';
				var _trId = el + '-study-' +_studyId;
				var _deleteBtn = "<a href=\"javascript:removeStudy('" + el + "-study-" +_studyId + "','" +index+ "');\"><img src='<c:url value="/images/buttons/button_icons/small/x_icon_small.png" />' border='0' alt='delete'></a>"

				$(_tableId).down('tr').insert({
					after: tableRow.interpolate({selectedChoiceForDisplay : _selectedStudyForDisplay, identifier:_studyId, fldName : _studiesFldName, deleteBtn : _deleteBtn, trId : _trId })
					});

				studiesCount[index] = studiesCount[index] + 1;
				AE.resetAutocompleter(el+".selectedStudyForDisplay");
				updateRoleSummary(index);
			}

			function removeSite(el,index){
				$(el).remove();
				sitesCount[index] = sitesCount[index] - 1;
				updateRoleSummary(index);
			}
			
			function removeStudy(el,index){
				$(el).remove();
				studiesCount[index] = studiesCount[index] - 1;
				updateRoleSummary(index);
			}
			
			function showHideDiv(index,suffix){
				var _div = "roleMembershipHelper["+index+"]-"+suffix;
				if($(_div).style.display == 'none'){
					$(_div).show();
				}else{
					$(_div).hide();
				}
				updateRoleSummary(index);
			}

			function showHidePersonDiv(){
				var divElement = $('person-div');
				divElement.toggle();
				
				if(divElement.visible()){
					makeFieldRequired('personType');	
				}else {
					makeFieldOptional('personType');
				}
			}

			function showHideUserDiv(){
				var divElement = $('user-div');
				divElement.toggle();
				
				if(divElement.visible()){
					makeFieldRequired('userName');	
				}else {
					makeFieldOptional('userName');
				}
			}


            function checkBoxRoleClicked(sectionId, index) {
                if (document.getElementById('roleMembershipHelper[' + index + '].checked').checked) {
                    OpenCollapsable('contentOf-' + sectionId, sectionId);
                } else {
                    CloseCollapsable('contentOf-' + sectionId, sectionId);
                }
                updateRoleSummary(index);
            }

			function updateRoleSummary(index){

				if($('roleMembershipHelper['+index+'].checked').checked){
					
					var nSiteSummary = '';
					var nStudySummary = '';
					var nRoleSummary = '';
					var selectedImg = "&nbsp;&nbsp;&nbsp;&nbsp;<img src='<c:url value="/images/check.png" />' border='0'>";
					var eRoleSummary = $('summary-'+index).innerHTML;
					
					if(eRoleSummary.blank()){
						$('summary-'+index).innerHTML = selectedImg;
						$('membershipDiv-'+index).show();
					}else{
						if($('roleMembershipHelper['+index+'].allSiteAccess').checked){
							nSiteSummary = 'All Sites';
						}else{
                            var _c = sitesCount[index];
                            if (_c == 0) _c = "none";
							nSiteSummary = 'Sites('+ _c +')';
						}
						if($('roleMembershipHelper['+index+'].allStudyAccess')){
							if($('roleMembershipHelper['+index+'].allStudyAccess').checked){
								nStudySummary = ' | All Studies';
							}else{
                                var _c = studiesCount[index];
                                if (_c == 0) _c = "none";
								nStudySummary = ' | Studies(' + _c + ')';
							}
						}
						nRoleSummary = nSiteSummary+nStudySummary+selectedImg;
						$('summary-'+index).innerHTML = nRoleSummary;						
						$('summary-'+index).show();
						$('membershipDiv-'+index).show();
					}
				}else{
					$('summary-'+index).hide();
					$('membershipDiv-'+index).hide();
				}
			}

            function fireAction(_action, _index) {
                if (_action == "removeSitePerson") {
                    removeSitePerson(_index);
                }
            }
            			
 			function addSitePerson() {
                ajaxCRUD._addItem('sitePerson', null, null, '_organizationsDIV', null, 0, 'Bottom');
            }
        
 			function removeSitePerson(_index) {
                ajaxCRUD._deleteItem('sitePerson', _index, '_organizationsDIV', 0);
            }

            /* Will show the popup, where the user is allowed to Search */
            function showLinkPopup(popupRequestType){
               var url = "searchUser?popupRequest=true&popupRequestType=#{requestType}&subview".interpolate({requestType:popupRequestType});
                AE.popupWin = new Window({className:"alphacube",
                    destroyOnClose:true,
                    title:"",
                    url: url,
                    width: 900,
                    height: 500,
                    recenterAuto:true});
                AE.popupWin.showCenter(true);
            }
            //Will show the ajax loading indicator
            function showLoadingIndicator(){
                try {
                     var elIndicator =  $('ajax-loading-indictor');
                     if(elIndicator)
                        elIndicator.show();
                } catch(e) {
                }
            }
            
            /* Will refresh the page after linking */
            function updateAfterLinking(_linkedId, _linkedUserName, _linkedRecordType, _linkType){
                showLoadingIndicator();
                var _id = ${param.id} + '';
                var _recordType = '${param.recordType}';
                var _userName = '${param.userName}';
                if(_linkType == 'user'){
                    _userName = _linkedUserName;
                }
                var url = "editUser?id=#{id}&linkType=#{linkType}&userName=#{userName}&recordType=#{recordType}&linkedId=#{linkedId}&linkedRecordType=#{linkedRecordType}&linkedUserName=#{linkedUserName}".interpolate(
                 {
                     id:_id,
                     linkType:_linkType,
                     linkedId :_linkedId,
                     linkedRecordType:_linkedRecordType,
                     linkedUserName:_linkedUserName,
                     userName:_userName,
                     recordType:_recordType
                 });

                window.location = url;
            }

            function deActivatePerson(button){
  
              //will put todays date as end date on all the site researchstaff end dates.
              $$('.date').each(function (_el){
                  var val = _el.value;
                  if( (val == '') || _el.name.indexOf('endDate') > 0){
                      _el.value = '${command.toDay}';
                  }
                  
              });
              alert('<caaers:message code="MSG_deactivate_effect" text="Please click Save to complete deactivation" />');
              button.disabled = true;
            }
	    	function activatePerson(button){
              $$('.date').each(function (_el){
                  var val = _el.value;
                  if( _el.name.indexOf('endDate') > 0){
                      _el.value = '';
                  }

              });
              alert('<caaers:message code="MSG_activate_effect" text="Please click Save to complete activation" />');
              button.disabled = true;
            }

            function deActivateUser(){
                
            }

            function unlockUser(){
               user.unlockUser(function(){
                   alert('<caaers:message code="MSG_user.unlocked" text="User account unlocked" />');
                   $('unlock-btn-div').hide();
               });
            }

        //updates the forms action, by chopping off the query string in action.
        function removeQueryStringFromForm(frm){
            var frmObj = $(frm)
            var _action = frmObj.action;
            var queryIndex = _action.indexOf('?');
            if(queryIndex > 0){
                var _newAction = _action.substring(0, queryIndex);
                frmObj.action = _newAction;
            }
        }
    	</script>

    		<div class="workflow-tabs2">
    			<ul id="" class="tabs autoclear">
                        <li id="thirdlevelnav" class="selected">
                            <div>
                            	<a href="createUser"><caaers:message code="user.menu.createUser"/></a>
                            </div>
                        </li>
                    	<li id="thirdlevelnav" class="">
                    		<div>
                    			<a href="searchUser"><caaers:message code="user.menu.searchUser"/></a>
                    		</div>
                    	</li>
                </ul>
    		</div>
			    		
    		<tags:tabForm tab="${tab}" flow="${flow}" formName="userForm" hideErrorDetails="false" hideBox="true">
    			<jsp:attribute name="repeatingFields">
    				<input type="hidden" name="_finish" value="true"/>
    				<tags:instructions code="personUserCreateInstructions" />
					<chrome:box title="Basic Details">
							<div style="height:100px;">
								 <div class="leftpanel">
								 	<ui:row path="firstName">
								 		<jsp:attribute name="label"><ui:label path="firstName" text="" labelProperty="firstName" required="true"/></jsp:attribute>
								 		<jsp:attribute name="value"><ui:text path="firstName" required="true" title="First name"/></jsp:attribute>
								 	</ui:row>
	                        		<ui:row path="middleName">
	                        			<jsp:attribute name="label"><ui:label path="middleName" text="" labelProperty="middleName" required="false"/></jsp:attribute>
	                        			<jsp:attribute name="value"><ui:text path="middleName" required="false" title="Middle name"/></jsp:attribute>
	                        		</ui:row>
									<ui:row path="lastName">
										<jsp:attribute name="label"><ui:label path="lastName" text="" labelProperty="lastName" required="true"/></jsp:attribute>
										<jsp:attribute name="value"><ui:text path="lastName" required="true" title="Last name"/></jsp:attribute>
									</ui:row>	                        		
								 </div>

								 <div class="rightpanel">
								 	<ui:row path="emailAddress">
								 		<jsp:attribute name="label"><ui:label path="emailAddress" text="" labelProperty="emailAddress" required="true"/></jsp:attribute>
								 		<jsp:attribute name="value"><ui:text path="emailAddress" required="true" title="Primary email" size="30"/></jsp:attribute>
								 	</ui:row>

                                    <c:if test="${not empty command.person and not empty command.person.id}">
                                        <div class="row">
                                            <form:hidden path="createAsPerson" />
                                            <div class="value">
                                                 <c:if test="${command.PO}">
                                               <c:if test="${command.person.active}">
                                                    <tags:button value="Deactivate Person" color="red" type="button" size="small" onclick="javascript:deActivatePerson(this)" />
                                               </c:if>
                                               <c:if test="${not command.person.active}">
                                                   <tags:button value="Activate Person" color="green" type="button" size="small" onclick="javascript:activatePerson(this)" />
                                               </c:if>
                                            </c:if>
                                            </div>
                                        </div>
                                    </c:if>

                                    <c:if test="${not empty command.user and not empty command.user.id}">
                                        <form:hidden path="createAsUser" />
                                        <div class="row">
                                            <div class="value">
                                                <c:if test="${command.UA}">
                                                <c:if test="${command.user.locked}">
                                                  <div id="unlock-btn-div"><tags:button value="Unlock User" color="blue" type="button" size="small" onclick="unlockUser()" /></div>
                                                </c:if>
                                                <c:if test="${command.user.active}">
                                                  <%--<tags:button value="Deactivate User" color="red" type="button" size="small" onclick="deActivateUser()" />--%>
                                                </c:if>

                                            </c:if>
                                            </div>
                                        </div>
                                    </c:if>

                                    <c:if test="${ empty command.person}">
                                        <div class="row">
                                            <div class="label"><caaers:message code="LBL_createAsPerson" /></div>
                                            <div class="value"><ui:checkbox path="createAsPerson" disabled="${!command.PO}" onclick="showHidePersonDiv()"/></div>
                                        </div>
                                    </c:if>

                                    <c:if test="${ empty command.user}">
                                        <div class="row">
                                            <div class="label"><caaers:message code="LBL_createAsUser" /></div>
                                            <div class="value"><ui:checkbox path="createAsUser"  disabled="${!command.UA}" onclick="showHideUserDiv()"/></div>
                                        </div>
                                    </c:if>

								 </div>
							</div>
    				</chrome:box>
    				
    				<chrome:box title="Person Details" id="person-div" style="display:${command.createAsPerson ? '' : 'none'}" >
    					<ui:row path="personType">
    						<jsp:attribute name="label"><ui:label path="personType" text="" labelProperty="personType" required="${command.createAsPerson}"/></jsp:attribute>
    						<jsp:attribute name="value"><ui:select path="personType" options="${command.personTypeOptionsMap}" title="Type of Person" required="${command.createAsPerson}" readonly="${command.editMode && not empty command.person}"></ui:select></jsp:attribute>
    					</ui:row>
						<ui:row path="researchStaff.nciIdentifier">
							<jsp:attribute name="label"><ui:label path="nciIdentifier" text="" labelProperty="personIdentifier" required="false"/></jsp:attribute>
							<jsp:attribute name="value"><ui:text path="nciIdentifier" title="Person Identifier" required="false" readonly="${not command.PO}"/>
                                <c:if test="${command.editMode and  empty command.person and command.PO}">
                                     <caaers:message code="LBL_Or" text="| OR |" />
                                     <caaers:message code="LBL_link.person" var="linkaperson" text="Link a person" />
                                     <tags:button value="${linkaperson}" color="blue" icon="search" onclick="showLinkPopup('person')" size="small" type="button"/>
                                </c:if>

                            </jsp:attribute>
						</ui:row>		    					
                        <chrome:division>
							<caaers:message code="researchstaff.details.organizations" var="rsOrganizations"/>
		                    <div id="_organizationsDIV">
		                        <c:set var="size" value="${fn:length(command.sitePersonnel)}" />
		                        <c:forEach items="${command.sitePersonnel}" varStatus="status" var="rss">
		                            <c:set var="newIndex" value="${size - (status.index + 1)}" />
		                            <researchStaff:oneSitePerson index="${newIndex}" collapsed="false" readOnly="${not command.PO}" />
		                        </c:forEach>
		                    </div>
                            <c:if test="${command.PO}">
                                <div style="margin-left:20px;"><tags:button size="small" color="blue" icon="add" id="addOrg" type="button" value="Add Organization" onclick="addSitePerson();"/>&nbsp;<tags:indicator id="_organizationsDIV_indicator" /></div>
                            </c:if>
						</chrome:division>
    				</chrome:box>
    				
    				<chrome:box title="User Details" id="user-div" style="display:${command.createAsUser ? '' : 'none'}" >
    					<tags:instructions code="userRolesForUA" />
    					<ui:row path="loginName">
    						<jsp:attribute name="label"><ui:label path="userName" text="" labelProperty="loginId" required="${command.createAsUser}"/></jsp:attribute>
    						<jsp:attribute name="value"><ui:text path="userName" required="${command.createAsUser}" title="Username" readonly="${ (command.editMode and (not empty command.user.id) ) or  (not command.UA)}"/>

                                <c:if test="${command.editMode and (empty command.userName) and command.PO}">
                                    <caaers:message code="LBL_Or" text="| OR |" />
                                    <caaers:message code="LBL_link.user" var="linkauser" text="Link a user" />
                                    <tags:button value="${linkauser}" color="blue" icon="search" onclick="showLinkPopup('user')" size="small" type="button"/>
                                </c:if>

                            </jsp:attribute>
    					</ui:row>
    						<div id="roles-div" style="padding-left:90px;">
							<c:forEach var="roleMembership" items="${command.roleMembershipHelper}" varStatus="index">
								<chrome:division id="section-${index.index}" collapsed="true" collapsable="true">
                                    <jsp:attribute name="title">
                                        <ui:checkbox path="roleMembershipHelper[${index.index}].checked" disabled="${not command.UA}" onclick="checkBoxRoleClicked('section-${index.index}', ${index.index});"/>&nbsp;<span title="${roleMembership.suiteRole.description}">${roleMembership.suiteRole.displayName}</span>
                                    </jsp:attribute>
									<jsp:attribute name="additionalInfo">
										<c:set var="noOfSites" value="${fn:length(roleMembership.sites)}" />
										<c:set var="noOfStudies" value="${fn:length(roleMembership.studies)}" />
										<c:set var="_sitesSummary" value=""/>
										<c:set var="_studiesSummary" value=""/>
										<c:set var="_roleSummary" value=""/>
										<c:if test="${roleMembership.scoped && roleMembership.checked}">
											<c:choose>
												<c:when test="${roleMembership.allSiteAccess}">
													<c:set var="_sitesSummary" value="All Sites"/>
												</c:when>
												<c:otherwise>
													<c:set var="_sitesSummary" value="Sites(${noOfSites})"/>
												</c:otherwise>
											</c:choose>
											<c:if test="${roleMembership.studyScoped}">
												<c:choose>
													<c:when test="${roleMembership.allStudyAccess}">
														<c:set var="_studiesSummary" value=" | All Studies"/>
													</c:when>
													<c:otherwise>
														<c:set var="_studiesSummary" value=" | Studies(${noOfStudies})"/>
													</c:otherwise>
												</c:choose>													
											</c:if>
											<c:set var="_roleSummary" value="${_sitesSummary}${_studiesSummary}"/>
										</c:if>
										<span id="summary-${index.index}">
                                            <c:url value="/images/check.png" var="chkPng" />
											${_roleSummary}
											<c:if test="${roleMembership.checked}">
                                                &nbsp;&nbsp;&nbsp;&nbsp;<img src="<c:url value="/images/check.png" />" border="0">
											</c:if>

										</span>
									</jsp:attribute>
                                    <jsp:body>

									<div id="membershipDiv-${index.index}" style="display:${roleMembership.checked ? '' : 'none'}">
									<c:if test="${roleMembership.scoped}">

                                            <div class="row">
                                                <div class="label"><caaers:message code="LBL_allSitesAccess" /></div>
                                                <div class="value"><ui:checkbox path="roleMembershipHelper[${index.index}].allSiteAccess" onclick="showHideDiv(${index.index},'siteSelector')" disabled="${not command.UA || not command.UAAllSite}" /></div>
                                            </div>
											<div class="row" id="roleMembershipHelper[${index.index}]-siteSelector" style="display:${roleMembership.allSiteAccess ? 'none' : ''}">
												<c:if test="${command.UA}">
                                                <div class="label"><caaers:message code="LBL_organization"/>&nbsp; </div>
												<div class="value">
													<ui:autocompleter path="roleMembershipHelper[${index.index}].selectedSiteForDisplay"
														initialDisplayValue="Begin typing here" enableClearButton="true">
														<jsp:attribute name="populatorJS">
															function(autocompleter, text) {
											         				user.restrictOrganization(text, function(values) {
											    					autocompleter.setChoices(values)
											  					})
											     			}
														</jsp:attribute>
														<jsp:attribute name="selectorJS">
															function(organization){
												    			return organization.name + " (" + organization.nciInstituteCode + ")";
															}
														</jsp:attribute>
														<jsp:attribute name="optionsJS">
															{
																afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
																	var _fieldHelper = 'roleMembershipHelper[${index.index}]';
																	$(_fieldHelper + '.selectedSiteForDisplay').value = " ("+selectedChoice.nciInstituteCode+") "	+ selectedChoice.name ;
																	$(_fieldHelper + '.nciCode').value = selectedChoice.nciInstituteCode;
																	Form.Element.enable($('addSite_btn[${index.index}]'));
																}
															}
														</jsp:attribute>														
													</ui:autocompleter>
													<form:hidden path="roleMembershipHelper[${index.index}].nciCode" id="roleMembershipHelper[${index.index}].nciCode"/>
													<tags:button  disabled="true" cssClass="foo" id="addSite_btn[${index.index}]" color="blue" value="Add" icon="Add" type="button" onclick="addSite('roleMembershipHelper[${index.index}]', '${index.index}')" size="small"/>
												</div>
                                                </c:if>

                                                <div class="label"><caaers:message code="LBL_selectedSites" /></div>
                                                <div class="value">
													<script>sitesCount[${index.index}] = ${fn:length(roleMembership.sites)};</script>
													<table id="roleMembershipHelper[${index.index}]-sitesTable">
														<tbody>
															<tr style="display:none;">
																<td> </td>
																<td> </td>
															</tr>
															<c:forEach var="site" items="${roleMembership.sites}" varStatus="siteIndex">
																<tr id="roleMembershipHelper[${index.index}]-site-${site}">
																	<td>
																		${command.siteMap[site]}
																	<input class='roleMembershipHelper[${index.index}]' type='hidden' id='roleMembershipHelper[${index.index}].sites' name='roleMembershipHelper[${index.index}].sites' value='${site}' /><td>
																	<td>
                                                                        <c:if test="${command.UA}">
																		<a href="javascript:removeSite('roleMembershipHelper[${index.index}]-site-${site}','${index.index}');">
       																	<img src="<c:url value="/images/buttons/button_icons/small/trash.gif" />" border="0" alt="delete"></a>
       																	</c:if>
																	</td>
																</tr>	
															</c:forEach>
														</tbody>	
													</table>
												</div>
							            	</div>
										
										<c:if test="${roleMembership.studyScoped}">

                                            <div class="row">
                                                <div class="label"><caaers:message code="LBL_allStudiesAccess" /></div>
                                                <div class="value"><ui:checkbox path="roleMembershipHelper[${index.index}].allStudyAccess" onclick="showHideDiv(${index.index},'studySelector')" disabled="${not command.UA}"/></div>
                                            </div>

												<div class="row" id="roleMembershipHelper[${index.index}]-studySelector" style="display:${roleMembership.allStudyAccess ? 'none' : ''}">
													<c:if test="${command.UA}">
                                                    <div class="label"><caaers:message code="LBL_Study"/>&nbsp; </div>
													<div class="value">
														<ui:autocompleter path="roleMembershipHelper[${index.index}].selectedStudyForDisplay"
															initialDisplayValue="Begin typing here" enableClearButton="true">
															<jsp:attribute name="populatorJS">
																
																function(autocompleter, text) {
																			var roleSites = new Array();
																			$('roleMembershipHelper[${index.index}]-sitesTable').select('[type="hidden"]').each( function(e){
																				roleSites.push(e.value);
																			})
                															user.matchStudies(text,roleSites,function(values) {
                    														autocompleter.setChoices(values)
																		})
												     			}
															</jsp:attribute>
															<jsp:attribute name="selectorJS">
																function(obj) {
                													return obj.displayName;
																}
															</jsp:attribute>
															<jsp:attribute name="optionsJS">
																{
																	afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
																		var _fieldHelper = 'roleMembershipHelper[${index.index}]';
																		$(_fieldHelper + '.selectedStudyForDisplay').value = 	selectedChoice.truncatedDisplayName;
																		$(_fieldHelper + '.studyId').value = selectedChoice.ccIdentifierValue;
																		Form.Element.enable($('addStudy_btn[${index.index}]'));	
																	}
																}
															</jsp:attribute>														
														</ui:autocompleter>
														<form:hidden path="roleMembershipHelper[${index.index}].studyId" id="roleMembershipHelper[${index.index}].studyId"/>
														<tags:button disabled="true" cssClass="foo" id="addStudy_btn[${index.index}]" color="blue" value="Add" icon="Add" type="button" onclick="addStudy('roleMembershipHelper[${index.index}]','${index.index}')" size="small"/>
													</div>
                                                    </c:if>

                                                    <div class="label"><caaers:message code="LBL_selectedStudies" /></div>
													<div class="value">
														<script>studiesCount[${index.index}] = ${fn:length(roleMembership.studies)};</script>
														<table id="roleMembershipHelper[${index.index}]-studiesTable">
															<tbody>
																<tr style="display:none;">
																	<td> </td>
																	<td> </td>
																</tr>
																<c:forEach var="study" items="${roleMembership.studies}" varStatus="studyIndex">
																	<tr id="roleMembershipHelper[${index.index}]-study-${study}">
																		<td>
																		${command.studyMap[study]}
																		<input class='roleMembershipHelper[${index.index}]' type='hidden' id='roleMembershipHelper[${index.index}].studies' name='roleMembershipHelper[${index.index}].studies' value='${study}' />
																		<td>
																		<td>
                                                                        <c:if test="${command.UA}">
																			<a href="javascript:removeStudy('roleMembershipHelper[${index.index}]-study-${study}','${index.index}');">
	       																	<img src="<c:url value="/images/buttons/button_icons/small/trash.gif" />" border="0" alt="delete"></a>
                                                                        </c:if>
																		</td>
																	</tr>	
																</c:forEach>
															</tbody>	
														</table>
													</div>													
												</div>
										</c:if>
									</c:if>
									</div>
                                    </jsp:body>
								</chrome:division>
							</c:forEach>
    						</div>
    				</chrome:box>
    			</jsp:attribute>
    		</tags:tabForm>

	</body>
</html>
