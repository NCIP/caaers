<%@include file="/WEB-INF/views/taglibs.jsp" %>

  <chrome:boxIPhone title="Subjects" style="width:700px;">
  	<jsp:body>
  		<form action = "dummy">
                <div id="subjects" class="scrollerTask">
                    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
                        <tr class="taskTitleRow">
                            <th>Primary Id
                            <th width="50%">Name
                            <th>Study Id
                            <th>Last Updated
                            <th>Action
                        </tr>
                         <c:forEach var ="subject" items="${subjectList}" varStatus = "loopStatus">
                         	<tr class="${loopStatus.index % 2 == 0 ? 'alt' : ''}">
                         		<td>${subject.studySubjectIdentifier}</td>


                           </tr>
                         </c:forEach>
                     </table>
                </div>
          </form>      
  	</jsp:body>               
  </chrome:boxIPhone>
