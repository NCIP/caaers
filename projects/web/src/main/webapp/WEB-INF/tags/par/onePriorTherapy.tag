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

<c:set var="v" value="assignment.priorTherapies[${index}]" />

<div>
 <chrome:division id="assignment.priorTherapies[${index}]" collapsed="${collapsed && !empties[v]}" collapsable="true" deleteParams="'priorTherapy' ,${index}, 'anchorPriorTherapy', {}" enableDelete="true">
     <jsp:attribute name="title">
         ${priorTherapy.name}
     </jsp:attribute>

     <jsp:attribute name="titleFragment" />

     <jsp:body>
    <c:if test="${empty priorTherapy.name}">
        <ui:row path="assignment.priorTherapies[${index}].priorTherapy">
            <jsp:attribute name="label">Prior Therapy</jsp:attribute>
            <jsp:attribute name="value"><ui:select options="${priorTherapyOptions}" path="assignment.priorTherapies[${index}].priorTherapy" /></jsp:attribute>
        </ui:row>
    </c:if>
    <ui:row path="assignment.priorTherapies[${index}].other">
        <jsp:attribute name="label"><ui:label path="assignment.priorTherapies[${index}].other" text="Comments" /></jsp:attribute>
        <jsp:attribute name="value"><ui:textarea path="assignment.priorTherapies[${index}].other" cols="55" /></jsp:attribute>
    </ui:row>

     <div id="dates${index}" style="display:${priorTherapy.name eq 'No prior therapy' ? 'none;' : 'inline;'}">
        <ui:row path="assignment.priorTherapies[${index}].startDate">
            <jsp:attribute name="label"><ui:label path="assignment.priorTherapies[${index}].startDate" text="Therapy start Date" /></jsp:attribute>
            <jsp:attribute name="value"><ui:splitDate path="assignment.priorTherapies[${index}].startDate" /></jsp:attribute>
        </ui:row>
        <ui:row path="assignment.priorTherapies[${index}].endDate">
            <jsp:attribute name="label"><ui:label path="assignment.priorTherapies[${index}].endDate" text="Therapy end date" /></jsp:attribute>
            <jsp:attribute name="value"><ui:splitDate path="assignment.priorTherapies[${index}].endDate" /></jsp:attribute>
        </ui:row>
    </div>
     
    <c:if test="${priorTherapy.priorTherapy.agentsPossible}">
    <ui:row path="assignment.priorTherapies[${index}]">
	 <jsp:attribute name="label">Prior Therapy Agents</jsp:attribute>
	 <jsp:attribute name="value">
	   <table class="tablecontent" width="95%">
			<tr><td width="10%"><tags:button cssClass="foo" id="priortherapy[${index}].agent-btn" color="blue" value="Add" icon="Add" type="button" onclick="addPTAgents_${index}();" size="small"/></td></tr>
			<tr>
				<td colspan="3">
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
     </jsp:body>
 </chrome:division>
</div>
<script type="text/javascript">
    Event.observe($('assignment.priorTherapies[${index}].priorTherapy'), 'change' , function(evt) {
    var ptVal = evt.element().value;
    if(ptVal == 3 || ptVal == 4 || ptVal == 5 ||ptVal == 7 || ptVal == 8 || ptVal == 11){
        //$('aeReport.saeReportPriorTherapies[${index}]-row').show();
    } else{
        // $('aeReport.saeReportPriorTherapies[${index}]-row').hide();
    }
    if (ptVal == 13) {
        $('dates${index}').hide();
    } else {
        $('dates${index}').show();
    }
});
    

function addPTAgents_${index}() {
    mHistory.addDetails('priorTherapyAgent', null, null, 'anchorPriorTherapies[${index}].priorTherapyAgents', {parentIndex : ${index} });
    AE.resetAutocompleter('priorTherapyAgents[${index}]');
}

function initializePriorTherapy(){
	
	if($('priortherapy[${index}].agent-btn')){
		Element.observe('priortherapy[${index}].agent-btn', 'click', function(evt){
	 	});
	}
	
	//AE.registerCalendarPopups("contentOf-assignment.priorTherapies[${index}]");
	AE.registerCalendarPopups();
}

initializePriorTherapy.defer();

function setTitlePT_${index}() {
    var titleID = $('titleOf_assignment.priorTherapies[${index}]');
    var select = $("assignment.priorTherapies[${index}].priorTherapy");
    var value = select.options[select.selectedIndex].text;
    $(titleID).innerHTML = value;
}

Event.observe($("assignment.priorTherapies[${index}].priorTherapy"), "change", function() {
    setTitlePT_${index}();
});
</script>

