<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags\ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="collapsed" required="true" type="java.lang.Boolean" %>
<%@attribute name="style"%>
<%@attribute name="adverseEvent" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" %>

<c:set var="ctcTermGroup">ctcTerm${index}</c:set>
<c:set var="ctcOtherGroup">ctcOther${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>

<c:set var="title_grade">${command.aeReport.adverseEvents[index].grade.code}</c:set>
<c:set var="title_lowlevel">${adverseEvent.lowLevelTerm.meddraTerm}</c:set>
<c:set var="v" value="aeReport.adverseEvents[${index}]" />
<c:set var="isAdeersReporting" value="${command.adverseEventReportingPeriod.study.adeersReporting}" />

<c:set var="title"><c:choose>
    <c:when test="${index == 0}">${command.aeReport.adverseEvents[index].adverseEventMeddraLowLevelTerm.fullName} (Primary)</c:when>
    <c:otherwise>${command.aeReport.adverseEvents[index].adverseEventMeddraLowLevelTerm.fullName}</c:otherwise>
</c:choose></c:set>

<c:set var="v" value="aeReport.adverseEvents[${index}]" />
<a name="adverseEventTerm-${command.aeReport.adverseEvents[index].adverseEventTerm.term.id}"></a>
<chrome:division title="${title} ${title_lowlevel}, Grade: ${title_grade}" id="ae-section-${index}" cssClass="ae-section aeID-${command.aeReport.adverseEvents[index].adverseEventTerm.term.id}" style="${style}" collapsable="true" collapsed="${!empties[v]}">

    <div id="main-fields-${index}" class="main-fields">
    	<!-- Verbatim -->
		<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}"/>
		<!-- Grade -->
		<tags:renderRow field="${fieldGroups[mainGroup].fields[1]}"/>
		<div class="row">
			<div class="leftpanel">
				<!-- Start Date -->
				<tags:renderRow field="${fieldGroups[mainGroup].fields[2]}"/>
				<!-- Attribution -->
				<tags:renderRow field="${fieldGroups[mainGroup].fields[4]}"/>
				<!-- Event Time -->
                <c:if test="${!isAdeersReporting}">
                    <tags:renderRow field="${fieldGroups[mainGroup].fields[5]}"/>
                </c:if>
            </div>
			<div class="rightpanel">
				<!-- End Date -->
				<tags:renderRow field="${fieldGroups[mainGroup].fields[3]}"/>
				<!-- Expected -->
				<tags:renderRow field="${fieldGroups[mainGroup].fields[8]}"/>
				<!-- Location -->
                <c:if test="${!isAdeersReporting}">
                    <tags:renderRow field="${fieldGroups[mainGroup].fields[6]}"/>
                </c:if>
            </div>
		</div>
        <c:if test="${!isAdeersReporting}">
            <!-- Hospitalization -->
            <tags:renderRow field="${fieldGroups[mainGroup].fields[7]}"/>
            <!-- Outcomes -->
            <ae:oneOutcome index="${index}" />
        </c:if>
    </div>
</chrome:division>

<script>
    var ae_title_one_${index} = '${title}';
    var ae_title_grade_${index} = ${not empty title_grade ? title_grade : 0};

    Event.observe('aeReport.adverseEvents[${index}].grade-longselect','click', function(evt){
        var val = evt.element().value;
        ae_title_grade_${index} = grades.indexOf(val);
        updateAETitle_${index}();
    });

    function updateAETitle_${index}() {
        $('titleOf_ae-section-${index}').innerHTML = ae_title_one_${index} + ", Grade: " + ae_title_grade_${index};
    }

</script>

