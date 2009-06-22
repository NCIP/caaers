<%-- 
	This page is used by Expedited AE flow. 
	Author : Biju Joseph
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="collapsed" required="true" type="java.lang.Boolean" %>
<%@attribute name="style"%>
<%@attribute name="adverseEvent" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" %>

<c:set var="ctcTermGroup">ctcTerm${index}</c:set>
<c:set var="ctcOtherGroup">ctcOther${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="title">${adverseEvent.adverseEventCtcTerm.ctcTerm.term}</c:set>
<c:set var="title_grade">${command.aeReport.adverseEvents[index].grade.code}</c:set>
<c:set var="title_lowlevel">${adverseEvent.lowLevelTerm.meddraTerm}</c:set>
<c:set var="v" value="aeReport.adverseEvents[${index}]" />

<a name="adverseEventTerm-${command.aeReport.adverseEvents[index].adverseEventTerm.term.id}"></a>
<chrome:division title="${title} ${title_lowlevel}, Grade: ${title_grade}" id="ae-section-${index}" 
	cssClass="ae-section aeID-${adverseEvent.adverseEventTerm.term.id}" style="${style}" collapsed="${!empties[v]}" collapsable="true">
	<jsp:attribute name="titleFragment">&nbsp;<span id="title-frag-${index}" class="primary-indicator">${ index gt 0 ? '' : '[Primary]' }</span> </jsp:attribute>
	<jsp:body>	
    <div id="${v}.ctc-details" class="ctc-details">
  
        <div class="row" style="display:none;">
            <div class="label"><label for="${v}.ctc-category">CTC category</label></div>
            <div class="value">

              <div class="ctcCategoryValueDiv">
                <select id="${v}.ctc-category" class="ctcCategoryClass" onchange="javascript:enableDisableAjaxTable(${index})" >
                    <option value="">Any</option>
                </select>

              </div>
            </div>
        </div>

        <tags:renderRow field="${fieldGroups[ctcTermGroup].fields[0]}" style="display:none;" extraParams="<a id=\"showAllTerm${index}\" href=\"javascript:showAjaxTable(this,$F('${v}.ctc-category'),'table${index}','table${index}-outer')\">Show All</a>" />
		</div>
		
		<!--  Other MedDRA -->
        <div style="display:${adverseEvent.adverseEventTerm.otherRequired ? 'block' : 'none'}">
        <ui:row path="${fieldGroups[ctcOtherGroup].fields[0].propertyName}">
            <jsp:attribute name="label"><ui:label path="${fieldGroups[ctcOtherGroup].fields[0].propertyName}" text="${fieldGroups[ctcOtherGroup].fields[0].displayName}"/></jsp:attribute>
            <jsp:attribute name="value">
                <ui:autocompleter path="${fieldGroups[ctcOtherGroup].fields[0].propertyName}" initialDisplayValue="${empty adverseEvent.lowLevelTerm ? '(Begin typing here)' : adverseEvent.lowLevelTerm.meddraTerm}">
                    <jsp:attribute name="populatorJS">
                        function(autocompleter, text) {
                            var terminologyVersionId = ${empty command.adverseEventReportingPeriod.study.otherMeddra.id ? 0 :command.adverseEventReportingPeriod.study.otherMeddra.id};
                            createAE.matchLowLevelTermsByCode(terminologyVersionId, text, function(values) {
                                autocompleter.setChoices(values)})
                        }
                    </jsp:attribute>
                    <jsp:attribute name="selectorJS">
                        function(lowLevelTerm) {
                            return lowLevelTerm.meddraTerm;
                        }
                    </jsp:attribute>
                    
                </ui:autocompleter>
            </jsp:attribute>
        </ui:row>
        </div>
<%--
        <tags:renderRow field="${fieldGroups[ctcOtherGroup].fields[0]}" style="display: none">
        <jsp:attribute name="label">
            <label>${fieldGroups[ctcOtherGroup].fields[0].displayName}</label>
        </jsp:attribute>
   		</tags:renderRow>
--%>
  
    <div id="main-fields-${index}" class="main-fields">
		<%-- Verbatim --%>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}"/>
		<%-- Grade --%>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[1]}"/>
		<div class="row">
			<div class="leftpanel">
				<%-- Start Date --%>
				<tags:renderRow field="${fieldGroups[mainGroup].fields[2]}" />
				<%-- Attribution --%>
				<tags:renderRow field="${fieldGroups[mainGroup].fields[4]}" />
                        <%-- Event Time --%>
                            <tags:renderRow field="${fieldGroups[mainGroup].fields[5]}"/>
            </div>
			<div class="rightpanel">
				<%-- End Date --%>
				<tags:renderRow field="${fieldGroups[mainGroup].fields[3]}" />
				<%-- Expected --%>
				<tags:renderRow field="${fieldGroups[mainGroup].fields[8]}"/>
    				<%-- Location --%>
                 <tags:renderRow field="${fieldGroups[mainGroup].fields[6]}"/>
            </div>
		</div>
		  <%-- Hospitalization --%>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[7]}"/>
		<%-- Outcomes --%>
		<ae:oneOutcome index="${index}" />
    </div>
    </jsp:body>
</chrome:division>

<script>
    var ae_title_one_${index} = "${title}";
    var ae_title_lowlevel_${index} = '${adverseEvent.lowLevelTerm.meddraTerm}';
    var ae_title_grade_${index} = ${not empty title_grade ? title_grade : 0};

    Event.observe($('aeReport.adverseEvents[${index}].adverseEventCtcTerm.ctcTerm-input'), "blur", function() {
        // $('titleOf_ae-section-${index}').innerHTML = $('aeReport.adverseEvents[${index}].adverseEventCtcTerm.ctcTerm-input').value;
        updateAETitle_${index}();
    });
    
    Event.observe($('aeReport.adverseEvents[${index}].lowLevelTerm-input'), "blur", function() {
        // $('titleOf_ae-section-${index}').innerHTML = $('aeReport.adverseEvents[${index}].lowLevelTerm-input').value;
        ae_title_lowlevel_${index} = $('aeReport.adverseEvents[${index}].lowLevelTerm-input').value;
        updateAETitle_${index}();
    });

    Event.observe('aeReport.adverseEvents[${index}].grade-longselect','click', function(evt){
        var val = evt.element().value;
        ae_title_grade_${index} = grades.indexOf(val);
        updateAETitle_${index}();
    });

    function updateAETitle_${index}() {
        $('titleOf_ae-section-${index}').innerHTML = ae_title_one_${index} + "&nbsp;" + ae_title_lowlevel_${index}  + ", Grade: " + ae_title_grade_${index};
    }

</script>
