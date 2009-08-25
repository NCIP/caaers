<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@attribute name="index" required="true" %>
<%@attribute name="parentIndex" required="true" %>
<%@attribute name="style"%>
<%@attribute name="agent" type="gov.nih.nci.cabig.caaers.domain.PriorTherapyAgent" %>

<table width="480px" border="0">
<tr>
    <td align="left">
        <%--${agent.chemoAgent.name}--%>
        <c:set var="initValue" value="${not empty agent.chemoAgent ? agent.chemoAgent.fullName : 'Begin typing here...'}"/>
          <ui:autocompleter path="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent"
            displayNamePath="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent.fullName"
            readonly="${not empty agent.chemoAgent}" required="true"
          	initialDisplayValue="${initValue}" enableClearButton="true" required="true" cssClass="validate-NOTEMPTY">
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
           <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' id="_c3_${parentIndex}_${index}" onclick="showShowAllTable('_c3_${parentIndex}_${index}', 'aeReportDOTsaeReportPriorTherapiesOPEN${parentIndex}CLOSEDOTpriorTherapyAgentsOPEN${index}CLOSEDOTchemoAgent')">Show All</a>
          </c:if>
      </td>
    <td align="right">
        <a href="#anchorPriorTherapies[${parentIndex}].priorTherapyAgents" onClick="mHistory.removeDetails('priorTherapyAgent', ${index}, 'anchorPriorTherapies[${parentIndex}].priorTherapyAgents', {parentIndex : ${parentIndex} })">
        <img src="<chrome:imageUrl name="../checkno.gif" />" />
        </a>
    </td>
</tr>
</table>

<div id="chemoAgentTableparent${parentIndex}index${index}-outer" style="position: absolute; display: none; left: 640px; width:400px; z-index:99;">
    <table width="100%" class="eXtremeTable" frame="border" border-color="blue" bgcolor="white">
        <tbody>
            <tr class="titleRow">
                <td align="left" class="title">Select Agent:</td>
                <td width="20px"><a href="javascript:hideShowAllTable('chemoAgentTableparent${parentIndex}index${index}-outer')"><img src="/caaers/images/rule/window-close.gif" id="close-image"/></a></td>
            </tr>
            <tr>
                <td colspan="2"><div id="chemoAgentTableparent${parentIndex}index${index}"/></td>
            </tr>
        </tbody>
    </table>
</div>
