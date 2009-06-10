<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<%@attribute name="index" required="true" %>
<%@attribute name="parentIndex" required="true" %>
<%@attribute name="style"%>
<%@attribute name="agent" type="gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapyAgent" %>

<div class="${(index % 2 ) gt 0 ? 'odd' : 'even' }">
		<table width="100%">
 			<tr>
  				<td width="99%">
                      <c:set var="initValue" value="${not empty agent.chemoAgent ? agent.chemoAgent.fullName : 'Begin typing here...'}"/>
                      <ui:autocompleter path="assignment.priorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent" 
                      	initialDisplayValue="${initValue}"
                      	readonly="${not empty agent.chemoAgent}"
                      	displayNamePath="assignment.priorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent.fullName">
                          <jsp:attribute name="populatorJS">
                              function(autocompleter, text){
                                  createAE.matchChemoAgents(text, function(values) {
                                      autocompleter.setChoices(values)
                                  })
                              }
                          </jsp:attribute>
                          <jsp:attribute name="selectorJS">
                              function(agent) {
                                  return agent.fullName
                              }
                          </jsp:attribute>
                      </ui:autocompleter>
                       <c:if test="${ empty agent.chemoAgent}">
                        <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' id="_c3_${parentIndex}_${index}" onclick="showShowAllTable('_c3_${parentIndex}_${index}', 'assignmentDOTpriorTherapiesOPEN${parentIndex}CLOSEDOTpriorTherapyAgentsOPEN${index}CLOSEDOTchemoAgent')">Show All</a>
  					  </c:if>
  				</td>
  				<td>
					<a href="#anchorPriorTherapies[${parentIndex}].priorTherapyAgents" onClick="mHistory.removeDetails('priorTherapyAgent', ${index}, 'anchorPriorTherapies[${parentIndex}].priorTherapyAgents', {parentIndex : ${parentIndex} })">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table> 
</div>