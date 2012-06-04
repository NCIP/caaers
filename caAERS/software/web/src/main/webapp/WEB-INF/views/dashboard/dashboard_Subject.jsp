<%@include file="/WEB-INF/views/taglibs.jsp" %>
<c:if test="${not empty roles.registration_qa_manager or not empty roles.subject_manager or not empty roles.registrar}">

  <chrome:boxIPhone title="Subjects" style="width:700px;" id="dashboardSubjects">
      <jsp:attribute name="additionalTitle">
          <c:if test="${param.loadAll == null}"><tags:indicator2 id="_loadAllSubjects-indicator"/>&nbsp;<span style="color:white; text-decoration:underline; cursor:pointer;" onclick="loadAllSubjects();" id="_loadAllSubjects">Load all</span></c:if>
      </jsp:attribute>

  	<jsp:body>
  		<form action = "dummy">
                <div id="subjects" class="<c:if test="${fn:length(subjectList) > 10}">scrollerTask</c:if>">
                    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="100%">
                        <tr class="taskTitleRow">
                            <th>Full name</th>
                            <th>Study ID</th>
                            <th>Study Subject Identifier</th>
                            <th width="80px">&nbsp;</th>
                        </tr>
                        <c:set var="_i" value="0" />
                        <c:forEach var="subject" items="${subjectList}" varStatus="i">
                            <c:if test="${fn:length(subject.assignments) > 0}">
                                <c:forEach var="assignment" items="${subject.assignments}" varStatus="j">
                                    <c:set var="_i" value="${_i + 1}"/>
                                    <tr class="${_i % 2 == 0 ? 'alt' : ''}">
                                        <td><c:out value="${subject.firstName}" escapeXml="true"/>&nbsp;<c:out value="${subject.lastName}" escapeXml="true"/></td>
                                        <td><span title="${assignment.studySite.study.shortTitle}"><c:out value="${assignment.studySite.study.primaryIdentifier}" escapeXml="true"/></span><td><span title="${assignment.studySite.study.shortTitle}"><c:out value="${assignment.studySubjectIdentifier}" escapeXml="true"/></span></td>
                                        <td align="RIGHT">
                                            <img src="<c:url value="/images/orange-actions.gif" />"
                                                 border="0"
                                                 onmouseover='showDashboardSubjectsAssignmentsMenuOptions(this, roles_map, ${subject.id}, ${assignment.studySite.study.id}, ${assignment.id})'
                                                 id='_d_assignment_${assignment.id}'
                                                 style="cursor: pointer;
                                                 margin-right: 15px;">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        <c:if test="${fn:length(subjectList) == 0}">
                            <tr><td colspan="5"><caaers:message code="dashboard.noResults" /></td></tr>
                        </c:if>
                     </table>
                </div>
          </form>      
  	</jsp:body>               
  </chrome:boxIPhone>
</c:if>

<script>

    function enterAdverseEvents(_studyId, _subjectId) {
        document.location = "<c:url value="/pages/ae/captureRoutine?" />" + "studyId=" + _studyId + "&subjectId=" + _subjectId;
    }

    function editMedicalHistory(_studyId, _subjectId, _assignmentId) {
        document.location = "<c:url value="/pages/participant/edit?" />" + "participantId=" + _subjectId + "&assignmentId=" + _assignmentId + "&tabName=EditSubjectMedHistoryTab";
    }

    function editSubjectDetails(_studyId, _subjectId) {
        document.location = "<c:url value="/pages/participant/edit?" />" + "participantId=" + _subjectId;
    }

    function assignToStudy(_studyId, _subjectId) {
        document.location = "<c:url value="/pages/participant/assignParticipant?" />" + "participantId=" + _subjectId;
    }

    function loadAllSubjects() {
        jQuery("#_loadAllSubjects-indicator").removeClass('indicator');
        jQuery.ajax({
            url: "./dashboard/subject?loadAll",
            success: function(data) {
                jQuery('#dashboardSubjects').html(data);
            }
        });
    }
</script>