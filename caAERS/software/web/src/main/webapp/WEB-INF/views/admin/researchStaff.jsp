<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<jsp:useBean id="today" class="java.util.Date" scope="request" /><c:set var="editMode" value="${command.researchStaff.id > 0}" /><jsp:useBean id="date" class="java.util.Date" />
<html>
    <head>
        <script src="<c:url value="/js/ui/ajaxCRUD.js"/>"></script>
		<title>Research Staff</title>
        <tags:dwrJavascriptLink objects="createStudy,searchStudy"/>
    </head>
    <body>
        <style>
            
            .CSSDate {
                width: 80px;
            }
            
            .hand {
                cursor: pointer;
            }
			div.row div.label {
				width:13em;
			}
			div.row div.value {
				margin-left:14em;
			}
			.rightpanel {
				width:54%
			}
        </style>
        <script language="JavaScript">

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
                    
        </script>
        <div id="display_remote_rs" style="display:none;text-align:left">
            <chrome:box title="Please select a ResearchStaff to be saved in caAERS" id="popupId">
            </chrome:box>
        </div>
        <div class="tabpane">
            <div class="workflow-tabs2">
                <ul id="" class="tabs autoclear">
                    <li id="thirdlevelnav" class="tab selected">
                        <div>
                            <a href="createResearchStaff"><caaers:message code="researchstaff.menu.createEditResearchStaff"/></a>
                        </div>
                    </li>
                    <li id="thirdlevelnav" class="tab">
                        <div>
                            <a href="searchResearchStaff"><caaers:message code="researchstaff.menu.searchResearchStaff"/></a>
                        </div>
                    </li>
                </ul>
            </div>
            <tags:tabForm tab="${tab}" flow="${flow}" formName="researchStaffForm" hideErrorDetails="false" hideBox="true">
            	
                <jsp:attribute name="repeatingFields">
                	<chrome:box title="Basic Details">
                    <input type="hidden" name="_action" value=""><input type="hidden" name="_selected" value=""><input type="hidden" name="_finish" value="true"/><input type="hidden" name="srsID"/><input type="hidden" name="srsrID"/><tags:instructions code="researchstaffdetails" /><caaers:message code="researchstaff.details.siteSection" var="siteSectionTitle"/><caaers:message code="researchstaff.details.detailsSection" var="detailsSectionTitle"/>
                    <chrome:division title="${detailsSectionTitle}" id="details">
                        <c:forEach items="${fieldGroups.researchStaff.fields}" var="field" begin="0" end="3">
                            <tags:renderRow field="${field}"/>
                        </c:forEach>
                        <div class="row">
                            <div class="label"><ui:label path="researchStaff.loginId" text="" labelProperty="loginId" required="true"/></div>
                            <div class="value"><ui:text path="researchStaff.loginId" readonly="${readonly || editMode}" cssClass="required" required="true" title="Login ID"/></div>
                        </div>
                        <c:if test="${editMode}">
                            <div class="row">
                                <div class="label">Active Date</div>
                                <div class="value">
                                    <tags:formatDate value="${command.researchStaff.activeDate}" />&nbsp;
                                    <c:if test="${!readOnly}">
                                        <c:if test="${command.researchStaff.active}">
                                            <tags:button type="button" color="${true ? 'red' : 'green'}" cssClass="" value="${true ? 'Deactivate' : 'Activate'}" size="small" onclick="${true ? 'de' : ''}activate(${command.researchStaff.id}, 0,0,-1,-1)" icon="${true ? 'x' : 'check'}"/>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                            
                             <c:if test="${command.researchStaff.locked}">
                				<ui:row path="researchStaff.locked">
                    				<jsp:attribute name="value">
                    				<div id="unlock-confirmation">&nbsp;</div>
                        				<tags:button id="unlockBtn" color="blue" size="small" value="Unlock" onclick="unlockUser();" type="button"/>
                    				</jsp:attribute>
                				</ui:row>
                			</c:if>
                            
                        </c:if>
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
						<div style="margin-left:20px;"><tags:button size="small" color="blue" icon="add" id="addOrg" type="button" value="Add Organization" onclick="addSiteResearchStaff();"/>&nbsp;<tags:indicator id="_organizationsDIV_indicator" /></div>
					</chrome:box>
                </jsp:attribute>
                <jsp:attribute name="tabControls">
                    <tags:tabControls tab="${tab}" flow="${flow}" willSave="false" saveButtonLabel="Save">
                    </tags:tabControls>
                </jsp:attribute>
				
            </tags:tabForm>
            </body>
        </html>
