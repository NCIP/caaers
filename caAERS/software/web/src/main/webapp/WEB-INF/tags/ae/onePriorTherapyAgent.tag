<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="index" required="true" %>
<%@attribute name="parentIndex" required="true" %>
<%@attribute name="style"%>
<%@attribute name="agent" type="gov.nih.nci.cabig.caaers.domain.PriorTherapyAgent" %>

<table width="510px" border="0">
<tr>
    <td align="left">
        <%--${agent.agent.name}--%>
        <c:set var="initValue" value="${not empty agent.agent ? agent.agent.displayName : 'Begin typing here'}"/>
          <b><caaers:message code="LBL_agent.name" /></b>
          <ui:autocompleter path="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent" title="PriorTherapy agent"
            displayNamePath="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent.displayName"
            readonly="${not empty agent.agent}"
          	initialDisplayValue="${initValue}" enableClearButton="true" required="true" cssClass="validate-NOTEMPTY">
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
    <td align="right">
        <a href="#anchorPriorTherapies[${parentIndex}].priorTherapyAgents" onClick="mHistory.removeDetails('priorTherapyAgent', ${index}, 'anchorPriorTherapies[${parentIndex}].priorTherapyAgents', {parentIndex : ${parentIndex} })">
        <img src="<chrome:imageUrl name="../checkno.gif" />" />
        </a>
    </td>
</tr>
</table>

