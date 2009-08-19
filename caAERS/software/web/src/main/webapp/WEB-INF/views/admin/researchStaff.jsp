<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<jsp:useBean id="today" class="java.util.Date" scope="request" />
<c:set var="editMode" value="${command.researchStaff.id > 0}" />
<jsp:useBean id="date" class="java.util.Date" />

<html>
<head>
    <script src="<c:url value="/js/ui/researchStaffPage.js"/>"></script>
    <tags:dwrJavascriptLink objects="createStudy,searchStudy"/>
</head>
<body>

<style>
    .CSSDate {
        width:80px;
    }

    .hand {
        cursor:pointer;
    }
</style>

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

<div id="display_remote_rs" style="display:none;text-align:left" >
	<chrome:box title="Please select a ResearchStaff to be saved in caAERS" id="popupId">
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

<tags:tabForm tab="${tab}" flow="${flow}" formName="researchStaffForm" hideErrorDetails="false">

<jsp:attribute name="repeatingFields">
	<input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
	<input type="hidden" name="_finish" value="true"/>
    <input type="hidden" name="srsID"/>
    <input type="hidden" name="srsrID"/>


    <p><tags:instructions code="researchstaffdetails" /></p>
    
	<caaers:message code="researchstaff.details.siteSection" var="siteSectionTitle"/>
    <chrome:division title="${siteSectionTitle}">
<%--
	    <c:forEach items="${fieldGroups.site.fields}" var="field">
	        <csmauthz:accesscontrol domainObject="${command.researchStaff.organization}" hasPrivileges="ACCESS" authorizationCheckName="siteAuthorizationCheck">
	            <tags:renderRow field="${field}"/>
	        </csmauthz:accesscontrol>
	    </c:forEach>
--%>
    </chrome:division>

	<caaers:message code="researchstaff.details.detailsSection" var="detailsSectionTitle"/>
    <chrome:division title="${detailsSectionTitle}">
        <div class="leftpanel">
            <c:forEach items="${fieldGroups.researchStaff.fields}" var="field" begin="0" end="3"><tags:renderRow field="${field}"/></c:forEach>

            <div class="row">
                <div class="label">Login ID.</div>
                <div class="value"><ui:text path="researchStaff.loginId" readonly="${readonly || editMode}" cssClass="required"/>
                </div>
            </div>
            
            <c:if test="${editMode}">
                <div class="row">
                    <div class="label">Active Date</div>
                    <div class="value"><tags:formatDate value="${command.researchStaff.activeDate}" />&nbsp;
                        <c:if test="${!readOnly}">
                            <c:if test="${command.researchStaff.active}">
                                <tags:button type="button"
                                         color="${true ? 'red' : 'green'}"
                                         cssClass=""
                                         value="${true ? 'Deactivate' : 'Activate'}"
                                         size="small"
                                         onclick="${true ? 'de' : ''}activate(${command.researchStaff.id}, 0,0,-1,-1)"
                                         icon="${true ? 'x' : 'check'}"/>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>
    </chrome:division>

    <caaers:message code="researchstaff.details.organizations" var="rsOrganizations"/>
    <chrome:division title="${rsOrganizations}">
        <jsp:attribute name="titleFragment">
            <tags:button size="small" color="blue" icon="add" id="addOrg" type="button" value="Add Organization"  onclick="addSiteResearchStaff();" />&nbsp;<tags:indicator id="_organizationsDIV_indicator" />
        </jsp:attribute>
        <jsp:body>
                    <div style="padding-left:20px; border:0px blue dotted;">
                            <div id="_organizationsDIV">
                                <c:set var="size" value="${fn:length(command.researchStaff.siteResearchStaffs)}" />
                                <c:forEach items="${command.researchStaff.siteResearchStaffs}" varStatus="status" var="rss">
                                    <c:set var="newIndex" value="${size - (status.index + 1)}" />
                                    <researchStaff:oneSiteResearchStaff index="${newIndex}" collapsed="false" />
                                </c:forEach>
                            </div>
                    </div>
        </jsp:body>
    </chrome:division>

</jsp:attribute>

	<jsp:attribute name="tabControls">
	 	<tags:tabControls tab="${tab}" flow="${flow}" willSave="false" saveButtonLabel="Save">
<%--
	 		<jsp:attribute name="customNextButton">
	 			<c:if test="${command.researchStaff.id != null && command.researchStaff.class.name eq 'gov.nih.nci.cabig.caaers.domain.LocalResearchStaff'}">
	 				<tags:button type="submit" value="Sync" color="blue" id="sync-rs" onclick="javascript:syncResearchStaff();" />
				</c:if>
	 		</jsp:attribute>
--%>
	 	</tags:tabControls>
	 </jsp:attribute>

</tags:tabForm>

<script language="JavaScript">

    ajaxCRUD = new AJAX_CRUD_HELPER();
    function addSiteResearchStaff() {
        ajaxCRUD._addItem('siteResearchStaff', null, null, '_organizationsDIV', null, ${tab.number});
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

</body>
</html>
