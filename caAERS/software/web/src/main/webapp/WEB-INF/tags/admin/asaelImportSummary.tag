<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<chrome:division id="asael-id">
	<p id="instructions">
        <c:set var="terms">
            <jsp:attribute name="value">${results["missingTerms"]}</jsp:attribute>
        </c:set>
		<caaers:message code="asael.import.success" /><br><br>
        <caaers:message code="asael.import.success.agents" />: <b>${results["processedAgents"]}</b><br>
        <caaers:message code="asael.import.success.agent.terms" />: <b>${results["processedAgentTerms"]}</b><br>
        <caaers:message code="asael.import.success.missing.terms" />: ${fn:length(terms)}
	</p>
</chrome:division>