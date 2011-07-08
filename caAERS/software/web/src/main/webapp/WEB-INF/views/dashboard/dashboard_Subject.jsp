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
                            <th>Primary Id
                            <th>First Name
                            <th>Last Name
                            <th>Study Id
                        </tr>
                         <c:forEach var ="subject" items="${subjectList}" varStatus = "loopStatus">
                         	<tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                                <td><a href="<c:url value="/pages/participant/edit?participantId=${subject.id}"/>">${subject.assignments[0].studySubjectIdentifier}</a></td>
                                <td><c:out value="${subject.firstName}" escapeXml="true" /> </td>
                                <td><c:out value="${subject.lastName}" escapeXml="true" /> </td>
                                <td><c:out value="${subject.assignments[0].studySite.study.primaryIdentifier}" escapeXml="true" /> </td>
                           </tr>
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