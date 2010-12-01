<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
    <head>
    	<title>User</title>
    </head>
    
    <body>
    
    	<script type="text/javascript">
			var sitesCount = new Array();
			var studiesCount = new Array();
		
	    	Event.observe(window, "load", function() {
	    		theAccordion = new Accordion("roles-div", 1);
	    	});

	    	var tableRow = "<tr id='#{trId}'><td>#{selectedChoiceForDisplay}<input type='hidden' id='#{fldName}' name='#{fldName}' value='#{identifier}' /></td><td>#{deleteBtn}</td></tr>";
	    	
			
			function addSite(el, index){

				Form.Element.disable('addSite_btn['+index+']');
				
				var _selectedSiteForDisplay = $(el+".selectedSiteForDisplay").value;
				var _nciCode = $(el+".nciCode").value;
				var _tableId = el+"-sitesTable";
				var _sitesFldName = el + '.sites';
				var _trId = el + '-site-' +_nciCode;
				var _deleteBtn = "<a href=\"javascript:removeSite('" + el + "-site-" +_nciCode + "','" +index+ "');\"><img src='/caaers/images/buttons/button_icons/small/trash.gif' border='0' alt='delete'></a>"
				
				$(_tableId).down('tr').insert({
					after: tableRow.interpolate({selectedChoiceForDisplay:_selectedSiteForDisplay, identifier : _nciCode ,fldName : _sitesFldName, deleteBtn : _deleteBtn, trId : _trId })
					});
				sitesCount[index] = sitesCount[index] + 1;
				
				AE.resetAutocompleter(el+".selectedSiteForDisplay");
			}

			function addStudy(el,index){
				Form.Element.disable('addStudy_btn['+index+']');
				var _selectedStudyForDisplay = $(el+".selectedStudyForDisplay").value;	
				var _studyId = $(el+".studyId").value;
				var _tableId = el+"-studiesTable";
				var _studiesFldName = el + '.studies';
				var _trId = el + '-study-' +_studyId;
				var _deleteBtn = "<a href=\"javascript:removeStudy('" + el + "-study-" +_studyId + "','" +index+ "');\"><img src='/caaers/images/buttons/button_icons/small/trash.gif' border='0' alt='delete'></a>"

				$(_tableId).down('tr').insert({
					after: tableRow.interpolate({selectedChoiceForDisplay : _selectedStudyForDisplay, identifier:_studyId, fldName : _studiesFldName, deleteBtn : _deleteBtn, trId : _trId })
					});

				studiesCount[index] = studiesCount[index] + 1;
				AE.resetAutocompleter(el+".selectedStudyForDisplay");
			}

			function removeSite(el,index){
				$(el).remove();
				sitesCount[index] = sitesCount[index] - 1;
			}
			
			function removeStudy(el,index){
				$(el).remove();
				studiesCount[index] = studiesCount[index] - 1;
			}
			
			
			function showHideDiv(index,suffix){
				var _div = "roleMembershipHelper["+index+"]-"+suffix;
				if($(_div).style.display == 'none'){
					$(_div).show();
				}else{
					$(_div).hide();
				}
			}
				
	    	
    	</script>
    	<tags:dwrJavascriptLink objects="user"/>
    
    	<div class="tabpane">
    		
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
					<chrome:box title="Basic Details">
						<tags:instructions code="userdetailsForUA" />
						<chrome:division title="${detailsSectionTitle}" id="details">
							<div style="height:100px;">
								 <div class="leftpanel">
									<div class="row">
	                            		<div class="label"><ui:label path="csmUser.firstName" text="" labelProperty="firstName" required="true"/></div>
	                            		<div class="value"><ui:text path="csmUser.firstName" required="true" title="First name"/></div>
	                        		</div>
	                        		
			                        <div class="row">
			                            <div class="label"><ui:label path="csmUser.lastName" text="" labelProperty="lastName" required="true"/></div>
			                            <div class="value"><ui:text path="csmUser.lastName" required="true" title="Last name"/></div>
			                        </div>
	                        		
								 </div>
								 <div class="rightpanel">
									<div class="row">
										<div class="label"><ui:label path="csmUser.emailAddress" text="" labelProperty="emailAddress" required="true"/></div>
										<div class="value"><ui:text path="csmUser.emailAddress" required="true" title="Primary email"/></div>
									</div>
		                        <div class="row">
		                            <div class="label"><ui:label path="csmUser.loginId" text="" labelProperty="loginId" required="true"/></div>
		                            <div class="value"><ui:text path="csmUser.loginId" required="true" title="Login ID"/></div>
		                        </div>									
								 </div>
							</div>
						</chrome:division>
    				</chrome:box>
    				
    				<chrome:box title="User Roles">
    					<tags:instructions code="userRolesForUA" />
    					<chrome:division title="${detailsSectionTitle}" id="details">
    						<div id="roles-div">
							<c:forEach var="roleMembership" items="${command.roleMembershipHelper}" varStatus="index">
								<c:set var="noOfSites" value="${fn:length(roleMembership.sites)}" />
								<c:set var="noOfStudies" value="${fn:length(roleMembership.studies)}" />
								<c:if test="${roleMembership.scoped && roleMembership.checked}">
									<c:set var="_title" value=" -----  Sites(${noOfSites}) | Studies(${noOfStudies})"/>
								</c:if>
								<c:if test="${! roleMembership.checked}">
									<c:set var="_title" value=" "/>
								</c:if>
								<c:if test="${!roleMembership.scoped}">
									<c:set var="_title" value=" "/>
								</c:if>
								
								<chrome:accordion id="section-${index.index}" title="${roleMembership.suiteRole.displayName} ${roleMembership.checked ? 'X' : '' }">
									${roleMembership.suiteRole.description}<br>
									<ui:checkbox path="roleMembershipHelper[${index.index}].checked" />&nbsp;&nbsp;Grant this user the ${roleMembership.suiteRole.displayName} role.<br><br>
									<c:if test="${roleMembership.scoped}">
										<ui:checkbox path="roleMembershipHelper[${index.index}].allSiteAccess" onclick="showHideDiv(${index.index},'siteSelector')"/>&nbsp;&nbsp;All Site access.<br><br>
											<div class="row" id="roleMembershipHelper[${index.index}]-siteSelector" style="display:${roleMembership.allSiteAccess ? 'none' : ''}">
												<div class="label"><caaers:message code="LBL_organization"/>&nbsp; </div>
												<div class="value">
													<ui:autocompleter path="roleMembershipHelper[${index.index}].selectedSiteForDisplay"
														initialDisplayValue="Begin typing here..." enableClearButton="true">
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
																		<a href="javascript:removeSite('roleMembershipHelper[${index.index}]-site-${site}','${index.index}');">
       																	<img src="/caaers/images/buttons/button_icons/small/trash.gif" border="0" alt="delete"></a>
       																	
																	</td>
																</tr>	
															</c:forEach>
														</tbody>	
													</table>
												</div>
							            	</div>
										
										<c:if test="${roleMembership.studyScoped}">
											<ui:checkbox path="roleMembershipHelper[${index.index}].allStudyAccess" onclick="showHideDiv(${index.index},'studySelector')"/>&nbsp;&nbsp;All Study access.<br><br>
												<div class="row" id="roleMembershipHelper[${index.index}]-studySelector" style="display:${roleMembership.allStudyAccess ? 'none' : ''}">
													<div class="label"><caaers:message code="LBL_Study"/>&nbsp; </div>
													<div class="value">
														<ui:autocompleter path="roleMembershipHelper[${index.index}].selectedStudyForDisplay"
															initialDisplayValue="Begin typing here..." enableClearButton="true">
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
																			<a href="javascript:removeStudy('roleMembershipHelper[${index.index}]-study-${study}','${index.index}');">
	       																	<img src="/caaers/images/buttons/button_icons/small/trash.gif" border="0" alt="delete"></a>
																		</td>
																	</tr>	
																</c:forEach>
															</tbody>	
														</table>
													</div>													
												</div>
										</c:if>
									</c:if>
								</chrome:accordion>
							</c:forEach>
    						</div>
    					</chrome:division>
    				</chrome:box>
    			</jsp:attribute>
    		</tags:tabForm>
    	</div>
	</body>
</html>