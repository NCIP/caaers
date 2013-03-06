<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp" %>

<c:if test="${not empty roles.study_creator or not empty roles.study_qa_manager or not empty roles.supplemental_study_information_manager or not empty roles.study_team_administrator or not empty roles.study_site_participation_administrator}">

  <chrome:boxIPhone title="Studies" style="width:700px;" id="dashboardStudies">
    <jsp:attribute name="additionalTitle">
        <tags:indicator2 id="_loadAllStudies-indicator"/>&nbsp;<span style="color:white; text-decoration:underline; cursor:pointer;" onclick="loadAllStudies();" id="_loadAllStudies">Load all</span>
    </jsp:attribute>
  	<jsp:body>
  		<form action = "dummy">
                <div id="myStudies" class="<c:if test="${fn:length(studyList) > 6}">scrollerTask</c:if>"">
                    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
                        <tr class="taskTitleRow">
                            <th>Study Id
                            <th width="50%">Title
                            <th>Status
                        </tr>
                         <c:forEach var ="study" items="${studyList}" varStatus = "loopStatus">
                         	<tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                         		<td><a style="text-decoration:underline; color:blue; font-weight:normal;" href="<c:url value="/pages/study/edit?studyId=${study.id}" />">${study.primaryIdentifier}</a></td>
                         		<td>${study.shortTitle}</td>
                         		<td>${study.status}</td>
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

<script>
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
