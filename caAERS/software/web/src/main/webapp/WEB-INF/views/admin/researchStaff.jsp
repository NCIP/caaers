<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<jsp:useBean id="today" class="java.util.Date" scope="request" />
<c:set var="editMode" value="${command.researchStaff.id > 0}" />

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
                            <tags:button type="button" color="green" cssClass="" value="Deactivate"size="small" onclick="deactivate(${command.researchStaff.id}, 0, 0)"/>
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

    function activate(rsID, srsID, srsrID) {
        if (confirm('Are you sure you want to deactivate the element ?')) {

            var _f = $('command');
            _f._target.name = '_noname';
            _f._action.value = 'activate';
            _f.srsID.value = srsID;
            _f.srsrID.value = srsrID;
            _f.submit();
        }
    }

    function deactivate(rsID, srsID, srsrID) {
        if (confirm('Are you sure you want to deactivate the element ?')) {

            var _f = $('command');
            _f._target.name = '_noname';
            _f._action.value = 'deactivate';
            _f.srsID.value = srsID;
            _f.srsrID.value = srsrID;
            _f.submit();
        }
    }
</script>

</body>
</html>
