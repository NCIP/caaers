<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true" %>
<%@attribute name="collapsed" required="true" description="Tells whether to display collapsed"%>
<%@attribute name="priorTherapy" required="true" type="gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapy" %>

<div>
 <chrome:division title="${priorTherapy.name}" id="assignment.priorTherapies[${index}]" collapsed="${collapsed}" collapsable="true"
  deleteParams="'priorTherapy' ,${index}, 'anchorPriorTherapy', {}" enableDelete="true">

     <ui:select options="${priorTherapyOptions}" path="assignment.priorTherapies[${index}].priorTherapy" />
     
    <ui:row path="assignment.priorTherapies[${index}].other">
	 <jsp:attribute name="label">
		<ui:label path="assignment.priorTherapies[${index}].other" text="Comments" />
	 </jsp:attribute>
	 <jsp:attribute name="value">
		<ui:textarea path="assignment.priorTherapies[${index}].other" cols="55" />
	 </jsp:attribute>
	</ui:row>

	<ui:row path="assignment.priorTherapies[${index}].startDate">
	 <jsp:attribute name="label">
		<ui:label path="assignment.priorTherapies[${index}].startDate" text="Therapy start Date" />
	 </jsp:attribute>
	 <jsp:attribute name="value">
		<ui:splitDate path="assignment.priorTherapies[${index}].startDate" />
	 </jsp:attribute>
	 
	</ui:row>

	<ui:row path="assignment.priorTherapies[${index}].endDate">
	 <jsp:attribute name="label">
		<ui:label path="assignment.priorTherapies[${index}].endDate" text="Therapy end date" />
	 </jsp:attribute>
	 <jsp:attribute name="value">
		<ui:splitDate path="assignment.priorTherapies[${index}].endDate" />
	 </jsp:attribute>
	 
	</ui:row>
    
	<c:if test="${priorTherapy.priorTherapy.agentsPossible}">
    <ui:row path="assignment.priorTherapies[${index}]">
	 <jsp:attribute name="label">Prior Therapy Agents</jsp:attribute>
	 <jsp:attribute name="value">
	   <table class="tablecontent" width="95%">
			<tr>
				<td width="10%">
					<input id="priortherapy[${index}].agent-btn" type="button" value="Add"/>
                </td>
                <%--<td width="20%"><a href="#anchorPriorTherapyAgents_${index}_" onClick="showShowAllTable('_c33', 'priorTherapyAgents__${index}_')" id="_c33">Show All</a></td>--%>
            </tr>
			<tr>
				<td colspan="3">
					<a name="anchorPriorTherapies[${index}].priorTherapyAgents" />
					<div id="anchorPriorTherapies[${index}].priorTherapyAgents">
						<c:set var="size" value="${fn:length(priorTherapy.priorTherapyAgents)}" />
						<c:forEach items="${priorTherapy.priorTherapyAgents}" varStatus="status" var="agent">
							<c:set var="newIndex" value="${size - (status.index + 1)}" />
							<par:onePriorTherapyAgent index="${newIndex}" parentIndex="${index}" agent="${agent}" />
						</c:forEach>
					</div>
				</td>
			</tr>
	   </table>
	 </jsp:attribute>
	  </ui:row>
	</c:if>
 </chrome:division>
</div>
<script type="text/javascript">
function initializePriorTherapy(){
	
	if($('priortherapy[${index}].agent-btn')){
		
		Element.observe('priortherapy[${index}].agent-btn', 'click', function(evt){
		 	mHistory.addDetails('priorTherapyAgent', evt.element(), null, 'anchorPriorTherapies[${index}].priorTherapyAgents', {parentIndex : ${index} });
		 	
		 	//clear the fields
		 	AE.resetAutocompleter('priorTherapyAgents[${index}]');
	 	});	
	 	
	}
	
	//AE.registerCalendarPopups("contentOf-assignment.priorTherapies[${index}]");
	AE.registerCalendarPopups();
}

initializePriorTherapy.defer();
</script>