<%-- 
	This page renders one adverse event row in Capture AE flow.
	Author : Biju Joseph
--%>

<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="adverseEvent" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" required="true" description="The adverse event that is being rendered" %>
<%@attribute name="index" required="true" type="java.lang.Integer" description="The index of the AE that is rendered" %>
<%@attribute name="collapsed" required="true" type="java.lang.Boolean" description="If true, will display the box collapsed"%>
<%@attribute name="enableDelete" description="If true, the delete button will be enabled" %>
<%@attribute name="style" %>

<c:set var="mainGroup">main${index}</c:set>
<c:set var="indexCorrection" value="${adverseEvent.adverseEventTerm.otherRequired ? 1  : 0}" />
<c:set var="title_term">${adverseEvent.adverseEventTerm.fullName}</c:set>
<c:set var="title_otherMedDRA_term">${adverseEvent.lowLevelTerm.meddraTerm}</c:set>
<c:set var="title_grade">${adverseEvent.grade.code}</c:set>

<c:set var="collapsedCheck" value="${adverseEvent.grade != null && (adverseEvent.grade != null && (adverseEvent.adverseEventTerm.otherRequired ? adverseEvent.lowLevelTerm != null : true))}" />

<chrome:division title="${title_term}${not empty title_otherMedDRA_term ? ':' : '' }${title_otherMedDRA_term}, Grade:${title_grade}" id="ae-section-${index}" cssClass="ae-section" style="${style}" 
	collapsable="true" deleteParams="${index}" enableDelete="${enableDelete}" collapsed="${collapsedCheck}">
	<jsp:body>	
	
		<%-- Other MedDRA --%>
		<c:if test="${indexCorrection gt 0}">
			<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}"/>
			<script>
                if(${adverseEvent.lowLevelTerm != null})
                    $('${fieldGroups[mainGroup].fields[0].propertyName}' + '-input').value = '${adverseEvent.lowLevelTerm.meddraTerm}';

                var terminologyVersionId = ${empty command.adverseEventReportingPeriod.study.otherMeddra.id ? 0 :command.adverseEventReportingPeriod.study.otherMeddra.id};
                AE.createStandardAutocompleter('${fieldGroups[mainGroup].fields[0].propertyName}',
                function(autocompleter, text) {
                    createAE.matchLowLevelTermsByCode(terminologyVersionId, text, function(values) {
                        autocompleter.setChoices(values)})
                },
                function(lowLevelTerm) { 
                	<%-- populate the heading--%>
                    $('titleOf_ae-section-${index}').innerHTML = '${title_term} :' + lowLevelTerm.meddraTerm +', Grade: ${title_grade}';
                    return lowLevelTerm.meddraTerm;
                });

            </script>
		</c:if>
		<%-- Verbatim --%>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[0 + indexCorrection]}"/>
		<%-- Grade --%>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[1 + indexCorrection]}"/>
		<script>
		<%-- 
			Logic that selects the DEATH outcome when grade DEATH is checked. 
		--%>
			Event.observe('${fieldGroups[mainGroup].fields[1 + indexCorrection].propertyName}-row','click', function(evt){
				var val = evt.element().value;
				var chkDeath = $('outcomes[' + ${index} + '][1]');
				if(chkDeath){
					chkDeath.checked = (val == 'DEATH');
				}
				//update the heading
				 $('titleOf_ae-section-${index}').innerHTML = "${title_term}${not empty title_otherMedDRA_term ? ':' : '' }${title_otherMedDRA_term}, Grade:" + grades.indexOf(val);
			});
		</script>
		<div class="row">
		<div class="leftpanel">
			<%-- Start Date --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[2 + indexCorrection]}" />
			<%-- Attribution --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[4 + indexCorrection]}" />
			<%-- Time Of Event --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[5 + indexCorrection]}" />
			<%-- Hospitalization --%>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[7 + indexCorrection]}" />
		</div>
		<div class="rightpanel">
			<%-- End Date --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[3 + indexCorrection]}" />
			<%-- Expectedness --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[8 + indexCorrection]}" />
			<%-- Event Location --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[6 + indexCorrection]}" />
			<%-- Outcome--%>
			<ae:oneOutcome index="${index}" isRoutineFlow="true" />
		</div>
</div>
		<!--  field to store the sig -->
		<input type="hidden" id="ae-section-${index}-signature" name="ae-section-${index}-signature" value="${adverseEvent.signature}"/>
		<c:if test="${adverseEvent.submitted == true}">
			<input name="submittedAERow" type="hidden" class="submittedAERow" value="${index}" id="ae-section-${index}-submittedAERow"/>
			<input name="ae-section-${index}-reportID" type="hidden" id="ae-section-${index}-reportID" value="${adverseEvent.report.id}" />
		</c:if>	
		
		<%-- Script to register calendar controls --%>
		<script>
			AE.registerCalendarPopups('ae-section-${index}');
		</script>
		
    </jsp:body>
</chrome:division>

