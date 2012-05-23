<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="createStudy"/>

<c:if test="${not empty roles.study_creator or not empty roles.study_qa_manager or not empty roles.supplemental_study_information_manager or not empty roles.study_team_administrator or not empty roles.study_site_participation_administrator}">

  <chrome:boxIPhone title="Studies" style="width:700px;" id="dashboardStudies">
    <jsp:attribute name="additionalTitle">
        <c:if test="${param.loadAll == null}"><tags:indicator2 id="_loadAllStudies-indicator"/>&nbsp;<span style="color:white; text-decoration:underline; cursor:pointer;" onclick="loadAllStudies();" id="_loadAllStudies">Load all</span></c:if>
    </jsp:attribute>
  	<jsp:body>
  		<form action = "dummy">
                <div id="myStudies" class="<c:if test="${fn:length(studyList) > 6}">scrollerTask</c:if>">
                    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="100%">
                        <tr class="taskTitleRow">
                            <th width="60%">Title</th>
                            <th width="25%">Status</th>
                            <th width="15%">&nbsp;</th>
                        </tr>
                         <c:forEach var ="study" items="${studyList}" varStatus = "loopStatus">
                         	<tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                         		<td><a href="<c:url value="/pages/study/edit?studyId=${study.id}" />">${study.primaryIdentifier}</a>&nbsp;<span title="<c:out value="${study.shortTitle}" escapeXml="true" />"><c:out value="${fn:substring(study.shortTitle, 0, 100)}" escapeXml="true" />...</span></td>
                         		<td><c:out value="${study.status}" escapeXml="true" /></td>
                         		<td><a onmouseover='showDashboardStudiesMenuOptions("${study.fundingSponsorIdentifierValue}", "${study.id}")' id='_d_study_${study.primaryIdentifier}' class='submitterButton submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all' style="color:white; font-family: Arial; font-size: 13px;">Actions<span class='ui-icon ui-icon-triangle-1-s'></span></a></td>
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

<script>

    function doEdit(_id) {
        window.location = "<c:url value="/pages/study/edit?studyId=" />" + _id;
    }

    function addStudySite(_id) {
        window.location = "<c:url value="/pages/study/edit?studyId=" />" + _id + "&tab=SitesTab";
    }

    function doRegisterSubject(_id) {
        window.location = "<c:url value="/pages/participant/create?studyId=" />" + _id;
    }

    function doUpdate(_sfsId, _id) {
        var mp = showMessagePopup("please_wait");
        createStudy.syncStudyWithAdEERS(_sfsId, _id, function(_resultId) {
            mp.close();
            if (_resultId.error) {
                showTimerPopup("error_page", 3);
            }
        })
    }

    function showDashboardStudiesMenuOptions(_ssi, _id) {
        var _el = jQuery("#_d_study_" + _ssi);
        var html = "<div><ul style='font-family:tahoma;'>" +
                "<li><a class='submitter-blue' href='#' onclick='doEdit(\"" + _id + "\")'>Edit study details</a></li>" +
                "<li><a class='submitter-blue' href='#' onclick='addStudySite(\"" + _id + "\")'>Add Study Site</a></li>" +
                "<li><a class='submitter-blue' href='#' onclick='doRegisterSubject(\"" + _id + "\")'>Register Subject</a></li>" +
                "<li><a class='submitter-blue' href='#' onclick='doUpdate(\"" + _ssi + "\", \"" + _id + "\")'>Synchronize with CTEP</a></li>" +
                "</ul></div>";
        _el.menu({
                content: html,
                maxHeight: 180,
                width: 180,
                positionOpts: {
                    directionV: 'down',
                    posX: 'left',
                    posY: 'bottom',
                    offsetX: 0,
                    offsetY: 0
                },
                showSpeed: 300
            });
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