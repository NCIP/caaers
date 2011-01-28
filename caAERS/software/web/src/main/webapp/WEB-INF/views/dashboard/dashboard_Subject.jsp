<%@include file="/WEB-INF/views/taglibs.jsp" %>
<c:if test="${not empty roles.registration_qa_manager or not empty roles.subject_manager or not empty roles.registrar}">

  <chrome:boxIPhone title="Subjects" style="width:700px;">
  	<jsp:body>
  		<form action = "dummy">
                <div id="subjects" class="<c:if test="${fn:length(subjectList) > 10}">scrollerTask</c:if>">
                    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
                        <tr class="taskTitleRow">
                            <th>Primary Id
                            <th>First Name
                            <th>Last Name
                            <th>Study Id
                        </tr>
                         <c:forEach var ="subject" items="${subjectList}" varStatus = "loopStatus">
                         	<tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                                <td><a style="text-decoration:underline; color:blue; font-weight:normal;" href="<c:url value="/pages/participant/edit?participantId=${subject.id}"/>">${subject.assignments[0].studySubjectIdentifier}</a></td>
                                <td>${subject.firstName}</td>
                                <td>${subject.lastName}</td>
                                <td>${subject.assignments[0].studySite.study.primaryIdentifier}</td>
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