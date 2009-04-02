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
 <c:set var="v" value="aeReport.saeReportPriorTherapies[${index}]" />
 <c:set var="mainGroup">priorTherapy${index}</c:set>
 <chrome:division id="aeReport.saeReportPriorTherapies[${index}]" collapsed="${!empties[v]}" collapsable="true" deleteParams="'priorTherapy',${index}, 'anchorPriorTherapy', {}" enableDelete="true">

	<jsp:attribute name="title">
		${priorTherapy.name}
	</jsp:attribute>

	<jsp:attribute name="titleFragment">
	</jsp:attribute>

    <jsp:body>


        <c:if test="${empty priorTherapy.name}">
            <tags:renderRow field="${fieldGroups[mainGroup].fields[0]}" />
            <script>
                            Event.observe('${fieldGroups[mainGroup].fields[0].propertyName}', 'change' , function(evt){
                            var ptVal = evt.element().value;
                            if(ptVal == 3 || ptVal == 4 || ptVal == 5 ||ptVal == 7 || ptVal == 8 || ptVal == 11){
                                $('aeReport.saeReportPriorTherapies[${index}]-row').show();
                            } else{
                                $('aeReport.saeReportPriorTherapies[${index}]-row').hide();
                            }
                            if (ptVal == 13) {
                                $('aeReport.saeReportPriorTherapies[${index}].startDate-row').hide();
                                $('aeReport.saeReportPriorTherapies[${index}].endDate-row').hide();
                            } else {
                                $('aeReport.saeReportPriorTherapies[${index}].startDate-row').show();
                                $('aeReport.saeReportPriorTherapies[${index}].endDate-row').show();
                            }
                        });
            </script>
        </c:if>

        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="lpStatus" begin="1">
            <c:if test="${not (priorTherapy.name eq 'No prior therapy' && (lpStatus.index == 2 || lpStatus.index == 3)) }">
                <tags:renderRow field="${field}" />
            </c:if>
        </c:forEach>

		<c:if test="${priorTherapy.priorTherapy.agentsPossible or empty priorTherapy.name}">

	    <ui:row path="aeReport.saeReportPriorTherapies[${index}]" style="${empty priorTherapy.name ? 'display:none;' : ''}">
		 <jsp:attribute name="label"></jsp:attribute>
		 <jsp:attribute name="value">
             
           <table class="tablecontent" border="0">
				<tr>
					<td colspan="2">
                        <table width="100%" border="1" width="400px">
                        <tr><td align="left"><b color="#2E3257">Therapy agent(s)</b>&nbsp;<td align="right"><input id="priortherapy[${index}].agent-btn" type="button" value="Add"/></td></tr>
                        </table>

                        <a name="anchorPriorTherapies[${index}].priorTherapyAgents" />
						<div id="anchorPriorTherapies[${index}].priorTherapyAgents">
							<c:set var="size" value="${fn:length(priorTherapy.priorTherapyAgents)}" />
                            <c:forEach items="${priorTherapy.priorTherapyAgents}" varStatus="status" var="agent">
								<c:set var="newIndex" value="${size - (status.index + 1)}" />
								<ae:onePriorTherapyAgent index="${newIndex}" parentIndex="${index}" agent="${agent}" />
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
		 	mHistory.addDetails('priorTherapyAgent', evt.element(), null, 'anchorPriorTherapies[${index}].priorTherapyAgents', {parentIndex : ${index} });
	 	});
	 	
	}
	
	AE.registerCalendarPopups();
}

function checkNoPriorTherapy_${index}() {
}

function setTitlePT_${index}() {
    var titleID = $('titleOf_aeReport.saeReportPriorTherapies[${index}]');
    var select = $("aeReport.saeReportPriorTherapies[${index}].priorTherapy");
    var value = select.options[select.selectedIndex].text;
    $(titleID).innerHTML = value;
}

Event.observe($("aeReport.saeReportPriorTherapies[${index}].priorTherapy"), "change", function() {
    setTitlePT_${index}();
});


    initializePriorTherapy.defer();
</script>
