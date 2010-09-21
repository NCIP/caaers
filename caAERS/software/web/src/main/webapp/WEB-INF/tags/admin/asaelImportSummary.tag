<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<chrome:division id="asael-id">
	<p id="instructions">
		<caaers:message code="asael.import.success" /><br><br>
        <caaers:message code="asael.import.success.agents" />: <b>${results["processedAgents"]}</b><br>
        <caaers:message code="asael.import.success.agent.terms" />: <b>${results["processedAgentTerms"]}</b><br>
        <caaers:message code="asael.import.success.agent.missing" />: <b>${fn:length(results["missingAgents"])}</b><br>
        <caaers:message code="asael.import.success.agentTermsDuplicates" />: <b>${results["duplicateAgentTerms"]}</b><br>
        <caaers:message code="asael.import.success.terms.missing" />: <b>${fn:length(results["missingTerms"])}</b><br>
	</p>
</chrome:division>

<c:if test="${fn:length(results['missingAgents']) > 0}">
    <chrome:division collapsable="true" collapsed="false" title="Missing Agents (NSC)" id="_mA">
        <ol>
        <c:forEach items="${results['missingAgents']}" var="i">
            <li>${i.key}
        </c:forEach>
        </ol>
    </chrome:division>
</c:if>

<c:if test="${fn:length(results['missingTerms']) > 0}">
    <chrome:division collapsable="true" collapsed="false" title="Missing Terms" id="_mT">
        <ol>
        <c:forEach items="${results['missingTerms']}" var="i">
            <li>${i}
        </c:forEach>
        </ol>
    </chrome:division>
</c:if>