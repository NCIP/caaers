<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@attribute name="index" required="true" %>
<%@attribute name="collapsed" required="true" description="Tells whether to display collapsed"%>
<%@attribute name="priorTherapy" required="true" type="gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy" %>
<div>
<c:set var="mainGroup">priorTherapy${index}</c:set>
 <chrome:division id="aeReport.saeReportPriorTherapies[${index}]" collapsed="${collapsed or empty priorTherapy.name}" collapsable="true"
  deleteParams="'priorTherapy',${index}, 'anchorPriorTherapy', {}" enableDelete="true">
	
	<jsp:attribute name="titleFragment">
		${priorTherapy.name}
		<c:if test="${empty priorTherapy.name}">
			<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}" />
			<script>
			Event.observe('${fieldGroups[mainGroup].fields[0].propertyName}', 'change' , function(evt){
				var ptVal = evt.element().value;
				if(ptVal == 3 || ptVal == 4 || ptVal == 5 ||ptVal == 7 || ptVal == 8 || ptVal == 11){
					$('aeReport.saeReportPriorTherapies[${index}]-row').show();
				}else{	
					$('aeReport.saeReportPriorTherapies[${index}]-row').hide();
				}
			});
			</script>
		</c:if>
	</jsp:attribute>
	<jsp:body>
		<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="lpStatus" begin="1">
		<tags:renderRow field="${field}" />
		</c:forEach>
	   
		<c:if test="${priorTherapy.priorTherapy.agentsPossible or empty priorTherapy.name}">
	    <ui:row path="aeReport.saeReportPriorTherapies[${index}]" style="${empty priorTherapy.name ? 'display:none;' : ''}">
		 <jsp:attribute name="label">
		 </jsp:attribute>
		 <jsp:attribute name="value">
		   <a name="anchorPriorTherapyAgents_${index}_"></a>
		   <table class="tablecontent" width="95%">
				<tr>
				 	<td colspan="2" align="left"><b color="#2E3257">Therapy agent(s)</b></td>
				</tr>
				<tr>
					<td width="90%">
						<ui:autocompleter path="priorTherapyAgents[${index}]" >
							<jsp:attribute name="populatorJS">
								function(autocompleter, text){
									createAE.matchChemoAgents(text, function(values) {
	        							autocompleter.setChoices(values)
	    							})
								}
							</jsp:attribute>
							<jsp:attribute name="selectorJS">
								function(agent) {
	            					return agent.name
	        					}
							</jsp:attribute>
						</ui:autocompleter>
						&nbsp; 
						<a href="#anchorPriorTherapyAgents_${index}_" onClick="showShowAllTable('_c33', 'priorTherapyAgents__${index}_')" id="_c33">Show All</a>
					</td>
					<td width="10%">
						<input id="priortherapy[${index}].agent-btn" type="button" value="Add"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a name="anchorPriorTherapies[${index}].priorTherapyAgents" />
						<div id="anchorPriorTherapies[${index}].priorTherapyAgents">
							<c:set var="size" value="${fn:length(priorTherapy.priorTherapyAgents)}" />
							<c:forEach items="${priorTherapy.priorTherapyAgents}" varStatus="status">
								<c:set var="newIndex" value="${size - (status.index + 1)}" />
								<ae:onePriorTherapyAgent index="${newIndex}" parentIndex="${index}" />
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
function initializePriorTherapy(){
	
	if($('priortherapy[${index}].agent-btn')){
		
		Element.observe('priortherapy[${index}].agent-btn', 'click', function(evt){
			var inField = $('priorTherapyAgents[${index}]');
			if(inField.value == '') return;
		 	mHistory.addDetails('priorTherapyAgent', evt.element(), inField.value, 'anchorPriorTherapies[${index}].priorTherapyAgents', {parentIndex : ${index} });
		 	
		 	//clear the fields
		 	AE.resetAutocompleter('priorTherapyAgents[${index}]');
	 	});	
	 	
	}
	
	//AE.registerCalendarPopups("contentOf-assignment.priorTherapies[${index}]");
	AE.registerCalendarPopups();
}

initializePriorTherapy.defer();
</script>

<%--
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="agentCount" type="java.lang.Integer" %>

<ae:fieldGroupDivision fieldGroupFactoryName="priorTherapy" index="${index}" style="${style}">
    <tags:errors path="aeReport.saeReportPriorTherapies[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}" />
    <tags:renderRow field="${fieldGroup.fields[1]}" />
    <tags:renderRow field="${fieldGroup.fields[2]}"/>
    <tags:renderRow field="${fieldGroup.fields[3]}"/>

    <c:forEach begin="${1}" end="${agentCount}" var="s">
         <ae:onePriorTherapyAgent index="${s - 1}" parentIndex="${index}"/>
    </c:forEach>
      
    <div id="pptAgent${index}" style="display: none">
        <tags:listEditorAddButton divisionClass="ptAgent${index}" label="List an Agent" buttonCssClass="ae-list-editor-button"/>
    </div>
</ae:fieldGroupDivision>
--%>