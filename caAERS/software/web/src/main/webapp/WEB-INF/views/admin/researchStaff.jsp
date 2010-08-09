<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<jsp:useBean id="today" class="java.util.Date" scope="request" /><c:set var="editMode" value="${command.researchStaff.id > 0}" /><jsp:useBean id="date" class="java.util.Date" />

<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:CREATE" var="hasRSCreate"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:UPDATE" var="hasRSUpdate"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:READ" var="hasRSRead"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole:CREATE" var="hasSRSRCreate"/>
<%--<c:set var="hasRSCreate" value="false" />--%>

<html>
    <head>
    	<title>Research Staff</title>
        <tags:js name="ui/ajaxCRUD" />
        <tags:js name="tabbedflow"/>
        <tags:dwrJavascriptLink objects="createStudy,searchStudy"/>
    </head>
    <body>
        <style>
            .CSSDate { width: 80px; }
            .hand { cursor: pointer; }
			div.row div.label { width:13em; }
			div.row div.value { margin-left:14em; }
			.rightpanel { width:54% }
        </style>
        <script language="JavaScript">
            
            AE.PAGE_HELP_LINK = 'researchStaffDetails';

                            ajaxCRUD = new AJAX_CRUD_HELPER();

                            function unlockUser() {
                                var url = $('command').action + "?subview";
                                var page = ${tab.number};
                 				var target = '_target' + ${tab.number}; 
                 				var paramHash = new Hash();
                 				paramHash.set('_page', page);
                 				paramHash.set(target, page);
                 				paramHash.set('currentItem', "User");
                 				paramHash.set('task', "Unlock");
                 				paramHash.set('_asynchronous', true);
                 				paramHash.set('decorator', 'nullDecorator');
                                new Ajax.Updater($('unlock-confirmation'), url, {
                 					parameters: paramHash.toQueryString(), 
                 					onComplete: function() {
                                	 $('unlockBtn').hide();
                                 	}, 
                                 	insertion: Insertion.Top, 
                                 	evalScripts : true
                 				});
                 			}
                 			
                 			function addSiteResearchStaff() {
                                ajaxCRUD._addItem('siteResearchStaff', null, null, '_organizationsDIV', null, 0, 'Bottom');
                            }
                        
                            function postSiteSelected(siteResearchStaffIndex, organizationID) {
                                return 0;
                            }
                        
                            function refreshStudies(values, i) {
                            }

                            function activate(rsID, srsID, srsrID, i, j) {
                                if (confirm('Are you sure you want to activate ?')) {
                        
                                    if (j >= 0) {
                                        var startDateFieldString = 'siteResearchStaffCommandHelper[' + i + '].rsRoles[' + j + '].startDate';
                                        var endDateFieldString = 'siteResearchStaffCommandHelper[' + i + '].rsRoles[' + j + '].endDate';
                                        var dt = "<tags:formatDate value="${date}" />";
                                        $(startDateFieldString).value = dt;
                                        $(endDateFieldString).value = '';
                                    } else {
                                        $$('input.SiteResearchStaffRoleStartDateCSS' + i).each(function(field) {
                                            field.value = dt;
                                        })
                                        $$('input.SiteResearchStaffRoleEndDateCSS' + i).each(function(field) {
                                            field.value = '';
                                        })
                                    }
                                }
                            }
                        
                            function deactivate(rsID, srsID, srsrID, i, j) {
                                if (confirm('Are you sure you want to deactivate ?')) {
                                    var dt = "<tags:formatDate value="${date}" />";
                        
                                    if (i == -1 && j == -1) {
                                        $$('input.CSSEndDate').each(function(field) {
                                            field.value = dt;
                                        })
                                        return;
                                    }
                        
                                    if (j >= 0 ) {
                                        var endDateFieldString = 'siteResearchStaffCommandHelper[' + i + '].rsRoles[' + j + '].endDate';
                                        $(endDateFieldString).value = dt;
                                    } else {
                                        $$('input.SiteResearchStaffRoleEndDateCSS' + i).each(function(field) {
                                            field.value = dt;
                                        })
                                    }
                                }
                            }
                    
        </script>
        <script>
            function applyRole(srsIndex, roleIndex) {
                var dt = "";
                var field = $('siteResearchStaffCommandHelper[' + srsIndex + '].rsRoles[' + roleIndex + '].startDate');
                if ($('siteResearchStaffCommandHelper[' + srsIndex + '].rsRoles[' + roleIndex + '].checked').checked) {
                    dt = "<tags:formatDate value="${date}" />";
                    // field.enable();
                } else {
                    // field.disable();
                }
                field.value = dt;
            }

            var syncWin = null;
            function showPickCSMUserWindow(){
                //will show the pick existing CSM user window.
                 syncWin = new Window({className: "alphacube",
                    width:680, height:390, zIndex: 100,
                    resizable: true,
                    destroyOnClose:true,
                    recenterAuto:true,
                    draggable:false,
                    closable:false,
                    minimizable:false,
                    maximizable:false});
                syncWin.setContent('rs-exist-popup');
                syncWin.showCenter(true);
                syncWin.show(false);
            }

            function syncUserDetails(){
               $('canSyncInput').value = 'true';
               $('researchStaff.firstName').value =  '${command.csmUser.firstName}';
               $('researchStaff.lastName').value = '${command.csmUser.lastName}';
               $('researchStaff.loginId').value =  '${command.csmUser.loginName}';
               Windows.closeAll();
            }

            function cancelSync(){
               $('canSyncInput').value = 'false';
                if($('researchStaff.loginId')) $('researchStaff.loginId').value = '';
               Windows.closeAll();
            }

            Event.observe(document, "dom:loaded", function(){
                if(${command.shouldSync})   showPickCSMUserWindow();
            });



        </script>
        <div id="display_remote_rs" style="display:none;text-align:left">
            <chrome:box title="Please select a ResearchStaff to be saved in caAERS" id="popupId">
            </chrome:box>
        </div>


        <div class="tabpane">
            <div class="workflow-tabs2">
                <ul id="" class="tabs autoclear">
                    <c:if test="${hasRSCreate || hasRSUpdate}">
                        <li id="thirdlevelnav" class="selected">
                            <div>
                            <a href="${hasRSCreate ? 'createResearchStaff' : '#'}"><caaers:message code="researchstaff.menu.createEditResearchStaff"/></a>
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${hasRSRead}"><li id="thirdlevelnav" class=""><div><a href="searchResearchStaff"><caaers:message code="researchstaff.menu.searchResearchStaff"/></a></div></li></c:if>
                </ul>
            </div>

            <tags:tabForm tab="${tab}" flow="${flow}" formName="researchStaffForm" hideErrorDetails="false" hideBox="true">

                <jsp:attribute name="repeatingFields">

                    <c:if test="${command.PO && not command.UA}"><tags:instructions code="researchstaffdetailsForPO" /></c:if>
                    <c:if test="${not command.PO && command.UA}"><tags:instructions code="researchstaffdetailsForUA" /></c:if>
                    <c:if test="${command.PO && command.UA}"><tags:instructions code="researchstaffdetailsForPOUA" /></c:if>

                    <chrome:box title="Basic Details">
                    <input type="hidden" name="_action" value=""><input type="hidden" name="_selected" value="">
                    <input type="hidden" name="_finish" value="true"/>
                    <input type="hidden" name="srsID"/>
                    <input type="hidden" name="srsrID"/>

                    <input type="hidden" name="canSync" id="canSyncInput" value="${command.canSync}"/>
                    <input type="hidden" name="shouldSync" id="shouldSyncInput" value="${command.shouldSync}"/>

                    <caaers:message code="researchstaff.details.siteSection" var="siteSectionTitle"/>
                    <caaers:message code="researchstaff.details.detailsSection" var="detailsSectionTitle"/>

                    <chrome:division title="${detailsSectionTitle}" id="details">
                        <div style="height:100px;">
                        <%--<csmauthz:accesscontrol var="r" objectPrivilege=""/>--%>

                        <div class="leftpanel">
	                        <div class="row">
	                            <div class="label"><ui:label path="researchStaff.firstName" text="" labelProperty="firstName" required="${fieldGroups['researchStaff'].fields[0].required}"/></div>
	                            <div class="value"><ui:text path="researchStaff.firstName" cssClass="${not empty command.researchStaff.firstName ? 'valueOK' : 'required'}" required="${fieldGroups['researchStaff'].fields[0].required}" title="First name" readonly="${!hasRSCreate}"/></div>
	                        </div>

	                        <div class="row">
	                            <div class="label"><ui:label path="researchStaff.middleName" text="" labelProperty="middleName" required="${fieldGroups['researchStaff'].fields[1].required}"/></div>
	                            <div class="value"><ui:text path="researchStaff.middleName" title="Middle name" readonly="${!hasRSCreate}" required="${fieldGroups['researchStaff'].fields[1].required}"/></div>
	                        </div>

	                        <div class="row">
	                            <div class="label"><ui:label path="researchStaff.lastName" text="" labelProperty="lastName" required="${fieldGroups['researchStaff'].fields[2].required}"/></div>
	                            <div class="value"><ui:text path="researchStaff.lastName" cssClass="${not empty command.researchStaff.lastName ? 'valueOK' : 'required'}" required="${fieldGroups['researchStaff'].fields[2].required}" title="Last name" readonly="${!hasRSCreate}"/></div>
	                        </div>
                        </div>

                        <div class="rightpanel">
                        <div class="row">
                            <div class="label"><ui:label path="researchStaff.emailAddress" text="" labelProperty="emailAddress" required="${fieldGroups['researchStaff'].fields[3].required}"/></div>
                            <div class="value"><ui:text path="researchStaff.emailAddress" cssClass="${not empty command.researchStaff.emailAddress ? 'valueOK' : 'required'}" required="${fieldGroups['researchStaff'].fields[3].required}" title="Primary email" readonly="${!hasRSCreate}"/></div>
                        </div>

                        <div class="row">
                            <div class="label"><ui:label path="researchStaff.loginId" text="" labelProperty="loginId" required="${fieldGroups['researchStaff'].fields[4].required}"/></div>
                            <div class="value"><ui:text path="researchStaff.loginId" readonly="${((readonly || editMode) and not empty command.researchStaff.loginId) or !hasRSCreate}" cssClass="${fieldGroups['researchStaff'].fields[4].required ? 'required' : ''}" required="${fieldGroups['researchStaff'].fields[4].required}" title="Login ID"/></div>
                        </div>
                        <c:if test="${editMode}">
                            <div class="row">
                                <div class="label">Active Date</div>
                                <div class="value">
                                    <tags:formatDate value="${command.researchStaff.activeDate}" />&nbsp;
                                    <c:if test="${hasRSCreate || hasRSUpdate}">
                                        <c:if test="${!readOnly}">
                                            <c:if test="${command.researchStaff.active}">
                                                <tags:button type="button" color="${true ? 'red' : 'green'}" cssClass="" value="${true ? 'Deactivate' : 'Activate'}" size="small" onclick="${true ? 'de' : ''}activate(${command.researchStaff.id}, 0,0,-1,-1)" icon="${true ? 'x' : 'check'}"/>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>

                             <c:if test="${hasSRSRCreate && command.researchStaff.locked}">
                				<ui:row path="researchStaff.locked">
                    				<jsp:attribute name="value">
                    				<div id="unlock-confirmation">&nbsp;</div>
                        				<tags:button id="unlockBtn" color="blue" size="small" value="Unlock" onclick="unlockUser();" type="button"/>
                    				</jsp:attribute>
                				</ui:row>
                			</c:if>

                        </c:if>
                        </div>
                    </div>
                    </chrome:division>
					</chrome:box>
				    <chrome:box title="Associate Organizations">
						<caaers:message code="researchstaff.details.organizations" var="rsOrganizations"/>
	                    <div id="_organizationsDIV">
	                        <c:set var="size" value="${fn:length(command.researchStaff.siteResearchStaffs)}" />
	                        <c:forEach items="${command.researchStaff.siteResearchStaffs}" varStatus="status" var="rss">
	                            <c:set var="newIndex" value="${size - (status.index + 1)}" /><researchStaff:oneSiteResearchStaff index="${newIndex}" collapsed="false" />
	                        </c:forEach>
	                    </div>
                        <c:if test="${hasRSCreate}">
						    <div style="margin-left:20px;"><tags:button size="small" color="blue" icon="add" id="addOrg" type="button" value="Add Organization" onclick="addSiteResearchStaff();"/>&nbsp;<tags:indicator id="_organizationsDIV_indicator" /></div>
                        </c:if>
					</chrome:box>

                    <%-- Box showing CSM sync details--%>
                    <div style="display:none;">
                        <div id="rs-exist-popup" style="display:none;">

                            <tags:instructions code="user.exist.csm" heading="Alert" />

                            <ui:row path="csmUser.firstName">
                                <jsp:attribute name="label">
                                   <ui:label path="csmUser.firstName" text="" labelProperty="firstName" />
                                </jsp:attribute>
                                <jsp:attribute name="value">${command.csmUser.firstName}</jsp:attribute>
                            </ui:row>
                            <ui:row path="csmUser.lastName">
                                <jsp:attribute name="label">
                                   <ui:label path="csmUser.lastName" text="" labelProperty="lastName" />
                                </jsp:attribute>
                                <jsp:attribute name="value">${command.csmUser.lastName}</jsp:attribute>
                            </ui:row>
                            <ui:row path="csmUser.loginName">
                                <jsp:attribute name="label">
                                   <ui:label path="csmUser.loginName" text="" labelProperty="loginId" />
                                </jsp:attribute>
                                <jsp:attribute name="value">${command.csmUser.loginName}</jsp:attribute>
                            </ui:row>
                             <ui:row path="csmUser.emailId">
                                <jsp:attribute name="label">
                                   <ui:label path="csmUser.emailId" text="" labelProperty="emailAddress" />
                                </jsp:attribute>
                                <jsp:attribute name="value">${command.csmUser.emailId}</jsp:attribute>
                            </ui:row>
                            <div class="standard-nav-buttons">
				                <div class="content buttons autoclear">
    	                            <div>
                                      <span>
                                        <caaers:message code="LBL_csm_user_sync" var="_syncBTN" text="Sync" />
                                        <caaers:message code="LBL_csm_user_cancel" var="_cancelBTN" text="Cancel" />
                                        <tags:button color="blue" type="button" onclick="javascript:cancelSync()"  value="${_syncBTN}" />
                                      </span>
                                      <span>
                                        <tags:button color="green" type="button"  onclick="javascript:syncUserDetails()"  value="${_cancelBTN}" icon="check"/>
                                      </span>
                                    </div>
			                    </div>
                            </div>
                        </div>
                  </div>

                </jsp:attribute>

                <jsp:attribute name="tabControls">

                    <c:if test="${hasRSUpdate}">
                        <tags:tabControls tab="${tab}" flow="${flow}" willSave="false" saveButtonLabel="Save"></tags:tabControls>
                    </c:if>
                </jsp:attribute>

            </tags:tabForm>
            </body>
        </html>
