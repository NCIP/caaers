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

<div class="${(index % 2 ) gt 0 ? 'odd' : 'even' }">
		<table width="100%">
 			<tr>
  				<td width="99%">
                      <c:set var="initValue" value="${not empty agent.chemoAgent ? agent.chemoAgent.name : 'Begin typing here...'}"/>
                      <ui:autocompleter path="assignment.priorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent" initialDisplayValue="${initValue}">
                          <jsp:attribute name="populatorJS">
                              function(autocompleter, text){
                                  createAE.matchChemoAgents(text, function(values) {
                                      autocompleter.setChoices(values)
                                  })
                              }
                          </jsp:attribute>
                          <jsp:attribute name="selectorJS">
                              function(agent) {
                                  return agent.name
                              }
                          </jsp:attribute>
                      </ui:autocompleter>

                    <%--<ui:text path="assignment.priorTherapies[${parentIndex}].priorTherapyAgents[${index}].name" readonly="true"/>--%>
  				</td>
  				<td>
					<a href="#anchorPriorTherapies[${parentIndex}].priorTherapyAgents" onClick="mHistory.removeDetails('priorTherapyAgent', ${index}, 'anchorPriorTherapies[${parentIndex}].priorTherapyAgents', {parentIndex : ${parentIndex} })">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table> 
</div>