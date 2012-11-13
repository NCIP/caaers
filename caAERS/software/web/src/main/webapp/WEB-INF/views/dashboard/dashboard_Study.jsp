<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="createStudy"/>

<c:if test="${not empty roles.study_creator or not empty roles.study_qa_manager or not empty roles.supplemental_study_information_manager or not empty roles.study_team_administrator or not empty roles.study_site_participation_administrator}">

  <chrome:boxIPhone title="Studies" style="width:700px;" id="dashboardStudies">
    <jsp:attribute name="additionalTitle">
        <c:if test="${param.loadAll == null}"><tags:indicator2 id="_loadAllStudies-indicator"/>&nbsp;<span style="color:white; text-decoration:underline; cursor:pointer;" onclick="loadAllStudies();" id="_loadAllStudies">Load all</span></c:if>
    </jsp:attribute>
  	<jsp:body>
  		<form action = "dummy">
  			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
                <div id="myStudies" class="<c:if test="${fn:length(studyList) > 6}">scrollerTask</c:if>">
                    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="100%">
                        <tr class="taskTitleRow">
                            <th width="20%">Study ID</th>
                            <th>Title</th>
                            <th width="80px">&nbsp;</th>
                        </tr>
                         <c:forEach var ="study" items="${studyList}" varStatus = "loopStatus">
                         	<tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                         		<td>${study.primaryIdentifier}</td>
                                <td><span title="<c:out value="${study.shortTitle}" escapeXml="true" />"><tags:substring s="${study.shortTitle}" l="100" /></span></td>
                                <td align="RIGHT">
                                    <img src="<c:url value="/images/orange-actions.gif" />"
                                         border="0"
                                         onmouseover='showDashboardStudiesMenuOptions(this, roles_map, "${study.fundingSponsorIdentifierValue}", "${study.id}","${not empty study.dataEntryStatus ? study.dataEntryStatus : false}")'
                                         id='_d_study_${study.primaryIdentifier}'
                                         style="cursor: pointer;
                                         margin-right: 15px;"></td>
                           </tr>
                         </c:forEach>
                        <c:if test="${fn:length(studyList) == 0}">
                            <tr><td colspan="5"><caaers:message code="dashboard.noResults" /></td></tr>
                        </c:if>
                     </table>
                </div>
          </form>      
  	</jsp:body>               
   </chrome:boxIPhone>

</c:if>
<div id="please_wait" style="display: none;" class="flash-message info" >
    <h3><img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<caaers:message code="LBL_please.wait" /></h3>
    <br><br>
    <div><caaers:message code="LBL_study.in.process" /></div>
</div>
<div id="error_page" style="display: none;" class="flash-message error" ><div><caaers:message code="LBL_study.process.error" /></div><br><span id="_errorMessage">.</span></div>

<%--
<form action="abc" name="_formStudy" method="get" id="_formStudy">
    <input type="hidden" name="" id="_tabName" />
    <input type="hidden" name="" id="_studyTarget" />
    <input type="hidden" name="_page" value="0" id="_page" />
</form>
--%>

<script>

    function doEdit(_id) {
        window.location = "<c:url value="/pages/study/edit?studyId=" />" + _id;
    }

    function getTabsHash(_studyIsComplete, _tabName) {
        var tabsHash = new Hash();
        var n = 0;
        var role_supplemental_study_information_manager = ${!empty roles.supplemental_study_information_manager};
        var role_study_qa_manager = ${!empty roles.study_qa_manager};
        var role_study_creator = ${!empty roles.study_creator};
        var role_study_site_participation_administrator = ${!empty roles.study_site_participation_administrator};
        var role_study_team_administrator = ${!empty roles.study_team_administrator};

        tabsHash.set('EmptyStudyTab', n++);

        if (role_supplemental_study_information_manager || (!_studyIsComplete && role_study_creator) || (_studyIsComplete && role_study_qa_manager)) {
            tabsHash.set('DetailsTab', n++);
        }

        if (role_supplemental_study_information_manager) {
            tabsHash.set('AgentsTab', n++);
            tabsHash.set('TreatmentAssignmentTab', n++);
            tabsHash.set('DiseaseTab', n++);
            tabsHash.set('SolicitedAdverseEventTab', n++);
            tabsHash.set('ExpectedAEsTab', n++);
        }

        if (role_study_site_participation_administrator) {
            tabsHash.set('SitesTab', n++);
        }

        if (role_study_team_administrator) {
            tabsHash.set('InvestigatorsTab', n++);
            tabsHash.set('PersonnelTab', n++);
        }

        if (role_supplemental_study_information_manager) {
            tabsHash.set('IdentifiersTab', n++);
        }

        return tabsHash.get(_tabName);
    }

    function addStudySite(_id, _complete) {
/*
        jQuery("#_formStudy").attr("action", "<c:url value="/pages/study/edit?studyId=" />" + _id);
        jQuery("#_studyTarget").attr("name", "_target" + getTabsHash(_complete, "SitesTab"));
        jQuery("#_formStudy").submit();
*/
        window.location = "<c:url value="/pages/study/edit?tabName=SitesTab&studyId=" />" + _id;
    }

    function addStudyInvestigators(_id, _complete) {
        window.location = "<c:url value="/pages/study/edit?tabName=InvestigatorsTab&studyId=" />" + _id;
    }

    function addStudyPersonnel(_id, _complete) {
        window.location = "<c:url value="/pages/study/edit?tabName=PersonnelTab&studyId=" />" + _id;
    }

    function doRegisterSubject(_id) {
        window.location = "<c:url value="/pages/participant/create?studyId=" />" + _id;
    }

    function loadAllStudies() {
        jQuery("#_loadAllStudies-indicator").removeClass('indicator');
        jQuery.ajax({
            url: "./dashboard/study?loadAll",
            success: function(data) {
                jQuery('#dashboardStudies').html(data);
            }
        });
    }
</script>