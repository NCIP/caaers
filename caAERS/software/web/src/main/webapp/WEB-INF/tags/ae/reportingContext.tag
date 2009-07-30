<%--
 Flow : EditAdverseEventController
 All the JSPs in the flow. to put the content. 
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<%@attribute name="allReportDefinitions" required="true" type="java.util.List" description="All report definitions to show" %>
<%@attribute name="selectedReportDefinitions" required="true" type="java.util.List" description="Selected report definitions to show" %>
<div id="contextMenuOuter">
<div id="contextMenuContent">
	<c:forEach var="rd" items="${allReportDefinitions}">
		<div style="clear:both;">
		<input class="rdContext${rd.id}" type="checkbox" name="reportingContextRdId" value="${rd.id}" /> ${rd.label}
		</div>
	</c:forEach>
	<div style="clear:both; margin-top:15px;">
	<tags:button color="red" value="Cancel" onclick="closeContextMenu(this, false)" size="small" icon="x"></tags:button>
	&nbsp;&nbsp;&nbsp;
	<tags:button color="blue" value="Apply Now" onclick="closeContextMenu(this, true)" size="small" icon="check"></tags:button>
	</div>
</div>
<script>
Event.observe(window, "load", function() {
	 <c:forEach var="rd" items="${selectedReportDefinitions}">
	 $$('.rdContext'+ '${rd.id}').each(function(el){el.checked=true;})
	 </c:forEach>
 });
</script>
</div>