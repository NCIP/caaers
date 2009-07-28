<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<jsp:useBean id="today" class="java.util.Date" scope="request" />

<html>
<head>
    <script src="<c:url value="/js/ui/researchStaffPage.js"/>"></script>
    <tags:dwrJavascriptLink objects="createStudy,searchStudy"/>
</head>
<body>

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

<tags:tabForm tab="${tab}" flow="${flow}" formName="researchStaffForm">

<jsp:attribute name="repeatingFields">
	<input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
	<input type="hidden" name="_finish" value="true"/>

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
        <div class="leftpanel"><c:forEach items="${fieldGroups.researchStaff.fields}" var="field"><tags:renderRow field="${field}"/></c:forEach></div>
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
	 	
	 		<jsp:attribute name="customNextButton">
	 			<c:if test="${command.researchStaff.id != null && command.researchStaff.class.name eq 'gov.nih.nci.cabig.caaers.domain.LocalResearchStaff'}">
	 				<tags:button type="submit" value="Sync" color="blue" id="sync-rs" onclick="javascript:syncResearchStaff();" />
				</c:if>
	 		</jsp:attribute>
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
//        var _sts;
/*
        searchStudy.getObjects("st", "%", organizationID, false, function(values) {
            refreshStudies(values, siteResearchStaffIndex);
        });
*/
    }

    function refreshStudies(values, i) {
        // $('_studies_' + i).innerHTML = values.length;
    }

</script>

</body>
</html>
