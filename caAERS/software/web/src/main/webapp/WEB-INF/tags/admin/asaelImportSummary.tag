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
		ASAEL Successfully imported.<br><br>
        Agents processed: <b>${results["processedAgents"]}</b><br>
        Agent Specific Terms processed: <b>${results["processedAgentTerms"]}</b><br>
        Missing Terms:
        <ol>
            <c:forEach items="${terms}" var="term">
                <li>${term}
            </c:forEach>
        </ol>
	</p>
</chrome:division>