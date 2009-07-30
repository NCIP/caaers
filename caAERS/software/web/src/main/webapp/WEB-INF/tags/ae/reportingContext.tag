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
	<c:forEach var="rd" items="${allReportDefinitions}" >
		<div>
		<input class="rdContext${rd.id} rdCheckbox" type="checkbox" name="reportingContextRdId" value="${rd.id}" /> ${rd.label}
		</div>
	</c:forEach>
	<div style="clear:both; margin-top:5px;">
	<tags:button id="btn-apply-now" disabled="disabled" color="blue" value="Apply Now" onclick="closeContextMenu(this, true)" size="small" icon="check"></tags:button>
	</div>
</div>
<script>
	var checkedReports = new Array();
Event.observe(window, "load", function() {
	 <c:forEach var="rd" items="${selectedReportDefinitions}" varStatus="rowCounter">
	 $$('.rdContext${rd.id}')[0].checked=true;
	 checkedReports.push(${rd.id});
	 </c:forEach>
 });
 $$('.rdCheckbox').each(function(el){
     Event.observe(el, "click", toggleApplyBtn);
 });
 function toggleApplyBtn(){
 	$('btn-apply-now').disabled=true;
	checkedReports.each(function(rdId){
		if(!$$('.rdContext' + rdId)[0].checked){
			$('btn-apply-now').disabled=false;
		} 
	})
 }
</script>
</div>