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
<%@attribute name="showNoPriorTherapy" type="java.lang.Boolean" %>

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
            <jsp:attribute name="embededJS">
             Event.observe($('assignment.priorTherapies[${index}].priorTherapy'), 'change' , function(evt) {
    			var ptVal = evt.element().value;
    			if(ptVal == ${_priorTherapy_surgery_id} || ptVal == ${_priorTherapy_radiation_id} || ptVal == ${_priorTherapy_nopriortherapy_id}){
        			$('assignment.priorTherapies[${index}]-row').hide();
                    AE.removeAllAgents_${index}();
    			} else{
        			$('assignment.priorTherapies[${index}]-row').show();
    			}
    			
    			if (ptVal == ${_priorTherapy_nopriortherapy_id}) {
        			$('dates${index}').hide();
        			 $('priortherapy-btn').disabled = true;
    			} else {
        			$('dates${index}').show();
        			 $('priortherapy-btn').disabled = false;
    			}
			});
			
			//special case, add no prior therapy only if needed
            if(${showNoPriorTherapy}){
               var _ptSelBox = $('assignment.priorTherapies[${index}].priorTherapy');
               _ptSelBox.options[_ptSelBox.length - 1] = new Option('No prior therapy', ${_priorTherapy_nopriortherapy_id});
            }
			
            </jsp:attribute>
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
     
    <c:if test="${priorTherapy.priorTherapy.agentsPossible or empty priorTherapy.name}">
    <ui:row path="assignment.priorTherapies[${index}]" style="${empty priorTherapy.name ? 'display:none;' : ''}">
	 <jsp:attribute name="label"></jsp:attribute>
	 <jsp:attribute name="value">
	  	<table class="tablecontent" border="0">
			<tr>
				<td colspan="2">
				  <table  border="1" width="450px">
                     <tr>
                    	<td align="left"><b color="#2E3257">Therapy agent(s)</b></td>
                    	<td align="right">
                    		<tags:button id="priortherapy[${index}].agent-btn"
                    			 color="blue" value="Add" icon="Add" type="button" 
                    			 onclick="AE.addPTAgents_${index}();" size="small" />
                    	</td>
                     </tr>
                   </table>
                   
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
AE.addPTAgents_${index}=function() {
    mHistory.addDetails('priorTherapyAgent', null, null, 'anchorPriorTherapies[${index}].priorTherapyAgents', {parentIndex : ${index} });
    AE.resetAutocompleter('priorTherapyAgents[${index}]');
}

AE.removeAllAgents_${index}= function() {
    mHistory.removeAllAgents('priorTherapyAgent', null, null, 'anchorPriorTherapies[${index}].priorTherapyAgents', {parentIndex : ${index} });
}

function initializePriorTherapy(){
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

