<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@attribute name="index" required="true" %>
<%@attribute name="collapsed" required="true" description="Tells whether to display collapsed"%>
<%@attribute name="showNoPriorTherapy" type="java.lang.Boolean" %>
<%@attribute name="priorTherapy" required="true" type="gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy" %>


<div>
 <c:set var="v" value="aeReport.saeReportPriorTherapies[${index}]" />
 <c:set var="mainGroup">priorTherapy${index}</c:set>
 <chrome:division id="aeReport.saeReportPriorTherapies[${index}]" 
 	collapsed="${!empties[v]}" collapsable="true" 
 	deleteParams="'priorTherapy',${index}, 'anchorPriorTherapy', {}" enableDelete="true">

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
                            if(ptVal == ${_priorTherapy_surgery_id} || ptVal == ${_priorTherapy_radiation_id} || ptVal == ${_priorTherapy_nopriortherapy_id}){
                                $('aeReport.saeReportPriorTherapies[${index}]-row').hide();
                            } else{
                                $('aeReport.saeReportPriorTherapies[${index}]-row').show();
                            }
                            if (ptVal == ${_priorTherapy_nopriortherapy_id}) {
                                $('aeReport.saeReportPriorTherapies[${index}].startDate-row').hide();
                                $('aeReport.saeReportPriorTherapies[${index}].endDate-row').hide();
                                $('priortherapy-btn').disabled = true;
                            } else {
                                $('aeReport.saeReportPriorTherapies[${index}].startDate-row').show();
                                $('aeReport.saeReportPriorTherapies[${index}].endDate-row').show();
                                $('priortherapy-btn').disabled = false;
                            }
                        });
                        
						//sepcial case, add no prior therapy only if needed
                        if(${showNoPriorTherapy}){
                            var _ptSelBox = $('${fieldGroups[mainGroup].fields[0].propertyName}');
                        	_ptSelBox.options[_ptSelBox.length - 1] = new Option('No prior therapy', ${_priorTherapy_nopriortherapy_id});
                        }
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
                        <table border="1" width="450px">
                         <tr>
                        	<td align="left"><b color="#2E3257">Therapy agent(s)</b></td>
                        	<td align="right">
                        		<tags:button cssClass="foo" id="priortherapy[${index}].agent-btn" color="blue" value="Add" icon="Add" type="button" onclick="AE.addPTAgents_${index}();" size="small"/>
                        	</td>
                         </tr>
                        </table>

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

AE.addPTAgents_${index}= function() {
    mHistory.addDetails('priorTherapyAgent', null, null, 'anchorPriorTherapies[${index}].priorTherapyAgents', {parentIndex : ${index} });
    AE.resetAutocompleter('priorTherapyAgents[${index}]');
}
    
function initializePriorTherapy(){
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
