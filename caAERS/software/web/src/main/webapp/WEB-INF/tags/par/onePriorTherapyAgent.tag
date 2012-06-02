<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="index" required="true" %>
<%@attribute name="parentIndex" required="true" %>
<%@attribute name="style"%>
<%@attribute name="agent" type="gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapyAgent" %>

<%--
<c:if test="${command.assignment.priorTherapies[parentIndex].priorTherapyAgents[index].agent == null}">
</c:if>
--%>

<div class="${(index % 2 ) gt 0 ? 'odd' : 'even' }">
		<table width="100%">
 			<tr>
  				<td width="99%">
                      <c:set var="initValue" value="${not empty agent.agent ? agent.agent.displayName : 'Begin typing here'}"/>
                      <ui:autocompleter path="assignment.priorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent"
                      	initialDisplayValue="${initValue}"
                        title="PriorTherapy agent"
                        enableClearButton="true"
                      	readonly="${not empty agent.agent}"
                      	displayNamePath="assignment.priorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent.displayName" required="true">
                          <jsp:attribute name="populatorJS">
                              function(autocompleter, text){
                                  createAE.matchAgents(text, function(values) {
                                      autocompleter.setChoices(values)
                                  })
                              }
                          </jsp:attribute>
                          <jsp:attribute name="selectorJS">
                              function(agent) {
                                  return agent.displayName
                              }
                          </jsp:attribute>
                      </ui:autocompleter>

  				</td>
  				<td>
					<a href="#anchorPriorTherapies[${parentIndex}].priorTherapyAgents" onClick="mHistory.removeDetails('priorTherapyAgent', ${index}, 'anchorPriorTherapies[${parentIndex}].priorTherapyAgents', {parentIndex : ${parentIndex} })">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table>
</div>
