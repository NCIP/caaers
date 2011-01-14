<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:CREATE" var="hasRSCreate"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:UPDATE" var="hasRSUpdate"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:READ" var="hasRSRead"/>

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
            
    	<script type="text/javascript">

			ajaxCRUD = new AJAX_CRUD_HELPER();
			var sitesCount = new Array();
			var studiesCount = new Array();
		
	    	Event.observe(window, "load", function() {
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
				updateRoleSummary(index);
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
					makeFieldRequired('user.loginName');	
				}else {
					makeFieldOptional('user.loginName');
				}
			}

			function updateRoleSummary(index){

				if($('roleMembershipHelper['+index+'].checked').checked){
					
					var nSiteSummary = '';
					var nStudySummary = '';
					var nRoleSummary = '';
					var selectedImg = "&nbsp;&nbsp;&nbsp;&nbsp;<img src='/caaers/images/check.png' border='0'>";
					var eRoleSummary = $('summary-'+index).innerHTML;
					
					if(eRoleSummary.blank()){
						$('summary-'+index).innerHTML = selectedImg;
						$('membershipDiv-'+index).show();
					}else{
						if($('roleMembershipHelper['+index+'].allSiteAccess').checked){
							nSiteSummary = 'All Sites';
						}else{
							nSiteSummary = 'Sites('+sitesCount[index]+')';
						}
						if($('roleMembershipHelper['+index+'].allStudyAccess')){
							if($('roleMembershipHelper['+index+'].allStudyAccess').checked){
								nStudySummary = ' | All Studies';
							}else{
								nStudySummary = ' | Studies('+studiesCount[index]+')';
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
                if (_action == "removeSiteResearchStaff") {
                    removeSiteResearchStaff(_index);
                }
            }
            			
 			function addSiteResearchStaff() {
                ajaxCRUD._addItem('siteResearchStaff', null, null, '_organizationsDIV', null, 0, 'Bottom');
            }
        
 			function removeSiteResearchStaff(_index) {
                ajaxCRUD._deleteItem('siteResearchStaff', _index, '_organizationsDIV', 0);
            }			 	
	    	
    	</script>
    	<tags:dwrJavascriptLink objects="user,createStudy"/>
    
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
    				<tags:instructions code="personUserCreateInstructions" />
					<chrome:box title="Basic Details">
							<div style="height:100px;">
								 <div class="leftpanel">
								 	<ui:row path="researchStaff.firstName">
								 		<jsp:attribute name="label"><ui:label path="researchStaff.firstName" text="" labelProperty="firstName" required="true"/></jsp:attribute>
								 		<jsp:attribute name="value"><ui:text path="researchStaff.firstName" required="true" title="First name"/></jsp:attribute>
								 	</ui:row>
	                        		<ui:row path="researchStaff.middleName">
	                        			<jsp:attribute name="label"><ui:label path="researchStaff.middleName" text="" labelProperty="middleName" required="false"/></jsp:attribute>
	                        			<jsp:attribute name="value"><ui:text path="researchStaff.middleName" required="false" title="Middle name"/></jsp:attribute>
	                        		</ui:row>
									<ui:row path="researchStaff.lastName">
										<jsp:attribute name="label"><ui:label path="researchStaff.lastName" text="" labelProperty="lastName" required="true"/></jsp:attribute>
										<jsp:attribute name="value"><ui:text path="researchStaff.lastName" required="true" title="Last name"/></jsp:attribute>
									</ui:row>	                        		
								 </div>
								 <div class="rightpanel">
								 	<ui:row path="researchStaff.emailAddress">
								 		<jsp:attribute name="label"><ui:label path="researchStaff.emailAddress" text="" labelProperty="emailAddress" required="true"/></jsp:attribute>
								 		<jsp:attribute name="value"><ui:text path="researchStaff.emailAddress" required="true" title="Primary email" size="30"/></jsp:attribute>
								 	</ui:row>
									<ui:checkbox path="createAsPerson" onclick="showHidePersonDiv()"/>&nbsp;&nbsp;Create as Person<br><br>
									<ui:checkbox path="createAsUser" onclick="showHideUserDiv()"/>&nbsp;&nbsp;Create as User
								 </div>
							</div>
    				</chrome:box>
    				
    				<chrome:box title="Person Details" id="person-div" style="display:${command.createAsPerson ? '' : 'none'}" >
    					<ui:row path="personType">
    						<jsp:attribute name="label"><ui:label path="personType" text="" labelProperty="personType" required="${command.createAsPerson}"/></jsp:attribute>
    						<jsp:attribute name="value"><ui:select path="personType" options="${command.personTypeOptionsMap}" title="Type of Person" required="${command.createAsPerson}"></ui:select></jsp:attribute>
    					</ui:row>
						<ui:row path="researchStaff.nciIdentifier">
							<jsp:attribute name="label"><ui:label path="researchStaff.nciIdentifier" text="" labelProperty="personIdentifier" required="false"/></jsp:attribute>
							<jsp:attribute name="value"><ui:text path="researchStaff.nciIdentifier" title="Person Identifier" required="false"/></jsp:attribute>
						</ui:row>		    					
                        <chrome:division>
							<caaers:message code="researchstaff.details.organizations" var="rsOrganizations"/>
		                    <div id="_organizationsDIV">
		                        <c:set var="size" value="${fn:length(command.researchStaff.siteResearchStaffs)}" />
		                        <c:forEach items="${command.researchStaff.siteResearchStaffs}" varStatus="status" var="rss">
		                            <c:set var="newIndex" value="${size - (status.index + 1)}" />
		                            <researchStaff:oneSitePerson index="${newIndex}" collapsed="false" />
		                        </c:forEach>
		                    </div>
	                        <c:if test="${hasRSCreate}">
							    <div style="margin-left:20px;"><tags:button size="small" color="blue" icon="add" id="addOrg" type="button" value="Add Organization" onclick="addSiteResearchStaff();"/>&nbsp;<tags:indicator id="_organizationsDIV_indicator" /></div>
	                        </c:if>
						</chrome:division>
    				</chrome:box>
    				
    				<chrome:box title="User Details" id="user-div" style="display:${command.createAsUser ? '' : 'none'}" >
    					<tags:instructions code="userRolesForUA" />
    					<ui:row path="user.loginName">
    						<jsp:attribute name="label"><ui:label path="user.loginName" text="" labelProperty="loginId" required="${command.createAsUser}"/></jsp:attribute>
    						<jsp:attribute name="value"><ui:text path="user.loginName" required="${command.createAsUser}" title="Username"/></jsp:attribute>
    					</ui:row>
    						<div id="roles-div">
							<c:forEach var="roleMembership" items="${command.roleMembershipHelper}" varStatus="index">
								<chrome:division id="section-${index.index}" title="${roleMembership.suiteRole.displayName}" collapsed="true" collapsable="true">
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
											${_roleSummary} ${roleMembership.checked ? '&nbsp;&nbsp;&nbsp;&nbsp;<img src="/caaers/images/check.png" border="0">' : '' }
										</span>
									</jsp:attribute>
                                    <jsp:body>
									${roleMembership.suiteRole.description}<br>
									<ui:checkbox path="roleMembershipHelper[${index.index}].checked" onclick="updateRoleSummary('${index.index}');"/>&nbsp;&nbsp;Grant this user the ${roleMembership.suiteRole.displayName} role.<br><br>
									<div id="membershipDiv-${index.index}" style="display:${roleMembership.checked ? '' : 'none'}">
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
									</div>
                                    </jsp:body>
								</chrome:division>
							</c:forEach>
    						</div>
    				</chrome:box>
    			</jsp:attribute>
    		</tags:tabForm>
    	</div>
	</body>
</html>