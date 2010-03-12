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
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="adverseEvent" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" required="true" description="The adverse event that is being rendered" %>
<%@attribute name="index" required="true" type="java.lang.Integer" description="The index of the AE that is rendered" %>
<%@attribute name="collapsed" required="true" type="java.lang.Boolean" description="If true, will display the box collapsed"%>
<%@attribute name="enableDelete" description="If true, the delete button will be enabled" %>
<%@attribute name="style" %>
<%@attribute name="isSolicited" type="java.lang.Boolean" %>
<%@attribute name="hasOtherMeddra" type="java.lang.Boolean" %>


<c:set var="mainGroup">main${index}</c:set>
<c:set var="indexCorrection" value="${adverseEvent.adverseEventTerm.otherRequired and hasOtherMeddra ? 1  : 0}" />
<c:set var="title_term">${adverseEvent.adverseEventTerm.medDRA ? adverseEvent.adverseEventTerm.term.meddraTerm : adverseEvent.adverseEventTerm.term.fullName}</c:set>
<c:set var="title_otherMedDRA_term">${adverseEvent.lowLevelTerm.meddraTerm}</c:set>
<c:set var="title_grade">${adverseEvent.grade.code}</c:set>

<c:set var="v" value="adverseEvents[${index}]" />
<c:set var="collapsedCheck" value="${!command.errorsForFields[v] && (isSolicited ? 'true' : adverseEvent.grade != null) && (adverseEvent.adverseEventTerm.otherRequired ? adverseEvent.lowLevelTerm != null : true)}" />
<c:if test="${command.adverseEvents[index].report.id > 0}"><c:set var="inReport"><jsp:attribute name="value"> <span class="inReport"> (Currently Included in a Report)</span></jsp:attribute></c:set></c:if>
<a name="adverseEventTerm-${adverseEvent.adverseEventTerm.term.id}"></a>

<c:set var="_TITLE">
    <jsp:attribute name="value">
        ${title_term}${not empty title_otherMedDRA_term ? ':' : '' }
        ${title_otherMedDRA_term}
        <c:if test="${title_grade > 0}">Grade: ${title_grade}</c:if>
        ${inReport}
        <c:if test="${adverseEvent.detailsForOther ne ''}">Verbatim: ${adverseEvent.detailsForOther}</c:if>
    </jsp:attribute>
</c:set>    

<chrome:division title="${_TITLE}" id="ae-section-${index}" cssClass="ae-section aeID-${adverseEvent.adverseEventTerm.term.id}" style="${style}"
	collapsable="true" deleteParams="${index}" enableDelete="${enableDelete}" collapsed="${collapsedCheck}">
   <jsp:attribute name="additionalInfo"></jsp:attribute>

    <jsp:body>
        <%-- Verbatim --%>
        <c:if test="${!command.study.verbatimFirst}">
            <tags:renderRow field="${fieldGroups[mainGroup].fields[0 + indexCorrection]}" />
        </c:if>
        <c:if test="${command.study.verbatimFirst && !isSolicited}">
            <div class="row">
                <div class="label"><ui:label path="*" text="*" labelProperty="aeReport.adverseEvents.detailsForOther"/></div>
                <div class="value"><caaers:value path="${fieldGroups[mainGroup].fields[0 + indexCorrection].propertyName}" /></div>
            </div>
            <div class="row">
                <div class="label">
                    <c:if test="${command.study.aeTerminology.term eq 'CTC'}">CTC Term</c:if>
                    <c:if test="${command.study.aeTerminology.term eq 'MEDDRA'}">MEDDRA Term</c:if>
                </div>
                <div class="value">
                    <c:if test="${command.study.aeTerminology.term eq 'CTC'}">
                    <ui:autocompleter path="adverseEvents[${index}].ctcTerm" initialDisplayValue="${command.adverseEvents[index].ctcTerm.fullName}">
                        <jsp:attribute name="populatorJS">function(autocompleter, text) {
                            createAE.matchTerms(text, ${command.study.aeTerminology.ctcVersion.id}, '', 25, function(values) {
                                autocompleter.setChoices(values);
            				});
                        }
                        </jsp:attribute>
                        <jsp:attribute name="selectorJS">function(obj) {
                            return obj.fullName;
                        }
                        </jsp:attribute>
                        <jsp:attribute name="optionsJS">
							{
								afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                                    $('adverseEvents[${index}].ctcTerm').value = selectedChoice.id; 
                                    refreshGrades(${index});
								}
							}
						</jsp:attribute>
                    </ui:autocompleter>
                </c:if>
                <c:if test="${command.study.aeTerminology.term eq 'MEDDRA'}">
                    <ui:autocompleter path="adverseEvents[${index}].meddraTerm">
                        <jsp:attribute name="initialDisplayValue">
                            <c:if test="${command.adverseEvents[index].meddraTerm.fullName ne ''}">${command.adverseEvents[index].meddraTerm.fullName}</c:if>
                        </jsp:attribute>
                        <jsp:attribute name="populatorJS">function(autocompleter, text) {
                            createAE.matchLowLevelTermsByCode(${command.study.aeTerminology.meddraVersion.id}, text, function(values) {
                                autocompleter.setChoices(values)
                            })
                        }
                        </jsp:attribute>
                        <jsp:attribute name="selectorJS">function(lowLevelTerm) {
                            return lowLevelTerm.meddraTerm;
                        }
                        </jsp:attribute>
                    </ui:autocompleter>
                </c:if>
                </div>
            </div>
        </c:if>


        <div id="GRADES_AND_MEDDRA_${index}">
        <%-- Other MedDRA --%>
        <c:if test="${indexCorrection gt 0}">

            <ui:row path="${fieldGroups[mainGroup].fields[0].propertyName}">
                <jsp:attribute name="label"><ui:label path="${fieldGroups[mainGroup].fields[0].propertyName}" text="${fieldGroups[mainGroup].fields[0].displayName}"/></jsp:attribute>
                <jsp:attribute name="value">
                    <ui:autocompleter path="${fieldGroups[mainGroup].fields[0].propertyName}" initialDisplayValue="${adverseEvent.lowLevelTerm.meddraTerm}">
                        <jsp:attribute name="populatorJS">
                            function(autocompleter, text) {
                                var terminologyVersionId = ${empty command.adverseEventReportingPeriod.study.otherMeddra.id ? 0 :command.adverseEventReportingPeriod.study.otherMeddra.id};
                                createAE.matchLowLevelTermsByCode(terminologyVersionId, text, function(values) {
                                    autocompleter.setChoices(values)})
                            }
                        </jsp:attribute>
                        <jsp:attribute name="selectorJS">
                            function(lowLevelTerm) {
                                $('titleOf_ae-section-${index}').innerHTML = '${title_term} Grade: ${title_grade}';
                                return lowLevelTerm.meddraTerm;
                            }
                        </jsp:attribute>
                    </ui:autocompleter>
                </jsp:attribute>
            </ui:row>

            <%--<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}"/>--%>
        </c:if>

        
		<%-- Grade --%>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[1 + indexCorrection]}"/>
        </div>

		<script>
		<%-- 
			Logic that handles the grade changes 
		--%>
			Event.observe('${fieldGroups[mainGroup].fields[1 + indexCorrection].propertyName}-longselect','click', function(evt) {
				var val = evt.element().value;
				var chkDeath = $('outcomes[' + ${index} + '][1]');
				if(chkDeath){
					chkDeath.checked = (val == 'DEATH');
				}
				//update the heading
				 $('titleOf_ae-section-${index}').innerHTML = "${title_term}${not empty title_otherMedDRA_term ? ':' : '' }${title_otherMedDRA_term} Grade: " + grades.indexOf(val) + " <c:if test="${adverseEvent.detailsForOther ne ''}">Verbatim: ${adverseEvent.detailsForOther}</c:if>";
			});
		</script>
	<div class="row" style="margin-top:0;margin-bottom:0;">
		<div class="leftpanel">
			<%-- Start Date --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[2 + indexCorrection]}" />
			<%-- Attribution --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[4 + indexCorrection]}" />
			<%-- Event time --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[7 + indexCorrection]}" />
			<%-- Hospitalization --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[5 + indexCorrection]}" />
		</div>
		<div class="rightpanel">
			<%-- End Date --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[3 + indexCorrection]}" />
			<%-- Expectedness --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[6 + indexCorrection]}" />
			<%-- Location --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[9 + indexCorrection]}" />
			<%-- Risk --%>
			<tags:renderRow field="${fieldGroups[mainGroup].fields[8 + indexCorrection]}" />
		</div>
	</div>
	
	<%-- Outcome--%>
	<ae:oneOutcome index="${index}" isRoutineFlow="true" isMandatory="${outcomesMandatory && not adverseEvent.solicited}"/>
		<!--  field to store the sig -->
		<input type="hidden" id="ae-section-${index}-signature" name="ae-section-${index}-signature" value="${adverseEvent.signature}"/>
		<c:if test="${adverseEvent.submitted == true}">
			<input name="submittedAERow" type="hidden" class="submittedAERow" value="${index}" id="ae-section-${index}-submittedAERow"/>
			<input name="ae-section-${index}-reportID" type="hidden" id="ae-section-${index}-reportID" value="${adverseEvent.report.id}" />
		</c:if>	
		
		<%-- Script to register calendar controls --%>
		<script>
			AE.registerCalendarPopups('ae-section-${index}');

            function refreshGrades(index) {
                var paramHash = new Hash(); //parameters to post to server
                paramHash.set('index', index);
                var page = ${tab.number};
                var target = '_target' + ${tab.number};
                paramHash.set('_page', page);
                paramHash.set(target, page);
                paramHash.set('_asynchronous', true);
                paramHash.set('decorator', 'nullDecorator');
                paramHash.set('ajax', 'true');
                paramHash.set('action', 'refreshGrades');
                paramHash.set('adverseEvents[' + index + '].ctcTerm', $('adverseEvents[' + index + '].ctcTerm').value);

                var url = $('command').action + "?subview"

                new Ajax.Request(url, {
                       parameters : paramHash.toQueryString(),
                       onSuccess: function(transport) {
                           $('GRADES_AND_MEDDRA_' + index).update(transport.responseText);
                       },
                       evalScripts : true
                });
                
            }
		</script>
		
    </jsp:body>
</chrome:division>

