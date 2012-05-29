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
                            <%--<th>Primary Id--%>
                            <th>First Name
                            <th>Last Name
                            <th>Assignments
                            <th width="80px">
                        </tr>
                         <c:forEach var ="subject" items="${subjectList}" varStatus = "loopStatus">
                         	<tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                                <%--<td><a href="<c:url value="/pages/participant/edit?participantId=${subject.id}"/>">${subject.assignments[0].studySubjectIdentifier}</a></td>--%>
                                <td><a href="<c:url value="/pages/participant/edit?participantId=${subject.id}"/>"><c:out value="${subject.firstName}" escapeXml="true" /></a></td>
                                <td><a href="<c:url value="/pages/participant/edit?participantId=${subject.id}"/>"><c:out value="${subject.lastName}" escapeXml="true" /></a></td>
                                <td>${fn:length(subject.assignments)}</td>
                                <td align="RIGHT"><img src="<c:url value="/images/orange-actions.gif" />" border="0" onmouseover='showDashboardSubjectsMenuOptions("${subject.id}")' id='_d_subject_${subject.id}' style="cursor: pointer;"></td>
                           </tr>
                           <c:if test="${fn:length(subject.assignments) > 0}">
                               <c:forEach var="assignment" items="${subject.assignments}" varStatus="i">
                                <tr style="background-color: #fff;">
                                    <td align="RIGHT">assignment #${i.index + 1}</td>
                                    <td colspan="2">
                                        <span style="color:black;"><b>Study Primary Identifier:</b></span>&nbsp;<c:out value="${assignment.studySite.study.primaryIdentifier}" escapeXml="true" />
                                        <br>
                                        <span style="color:black;"><b>Study Subject Identifier:</b></span>&nbsp;<c:out value="${assignment.studySubjectIdentifier}" escapeXml="true" />
                                    </td>
                                    <td align="RIGHT"><img src="<c:url value="/images/orange-actions.gif" />" border="0" onmouseover='showDashboardSubjectsAssignmentsMenuOptions(${subject.id}, ${assignment.studySite.study.id}, ${assignment.id})' id='_d_assignment_${assignment.id}' style="cursor: pointer;"></td>
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

    function doSubject() {
    }

    function showDashboardSubjectsMenuOptions(_id) {
        var _el = jQuery("#_d_subject_" + _id);
        var html = "<div><ul style='font-family:tahoma;'>" +
                "<li><a class='submitter-blue' href='#' onclick='doSubject()'>Edit Subject Details</a></li>" +
//                "<li><a class='submitter-blue' href='#' onclick='doSubject()'>Edit Medical History</a></li>" +
//                "<li><a class='submitter-blue' href='#' onclick='doSubject()'>Enter Adverse Events</a></li>" +
                "<li><a class='submitter-blue' href='#' onclick='doSubject()'>Assign to Study</a></li>" +
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

    function showDashboardSubjectsAssignmentsMenuOptions(_subjectId, _studyId, _assignmentId) {
        var _el = jQuery("#_d_assignment_" + _assignmentId);
        var html = "<div><ul style='font-family:tahoma;'>" +
                "<li><a class='submitter-blue' href='#' onclick='doSubject()'>Edit Medical History</a></li>" +
                "<li><a class='submitter-blue' href='#' onclick='doSubject()'>Enter Adverse Events</a></li>" +
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