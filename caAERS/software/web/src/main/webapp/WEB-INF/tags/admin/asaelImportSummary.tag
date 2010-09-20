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
        <caaers:message code="asael.import.success.agent.missing" />: <b>${results["missingAgents"]}</b><br>
        <caaers:message code="asael.import.success.agentTermsDuplicates" />: <b>${results["duplicateAgentTerms"]}</b><br>
        <caaers:message code="asael.import.success.terms.missing" />: <b>${results["missingTerms"]}</b><br>
	</p>
</chrome:division>