<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>

<c:set var="hasOtherMeddra" value="${not empty command.study.otherMeddra}" />
<c:set var="indexCorrection" value="${ae.adverseEventTerm.otherRequired ? 1  : 0}" />
<c:set var="mainGroup">main${index}</c:set>
<c:set var="title_term">${ae.adverseEventTerm.medDRA ? ae.adverseEventTerm.term.meddraTerm : ae.adverseEventTerm.term.fullName}</c:set>
<c:set var="title_otherMedDRA_term">${ae.lowLevelTerm.meddraTerm}</c:set>
<c:set var="title_grade">${ae.grade.code}</c:set>

<tags:noform>

    <c:if test="${indexCorrection gt 0}">
        <c:if test="${hasOtherMeddra}">

            <ui:row path="${fieldGroups[mainGroup].fields[0].propertyName}">
                <jsp:attribute name="label"><ui:label path="${fieldGroups[mainGroup].fields[0].propertyName}" text="${fieldGroups[mainGroup].fields[0].displayName}" required="true"/></jsp:attribute>
                <jsp:attribute name="value">
                    <ui:autocompleter path="${fieldGroups[mainGroup].fields[0].propertyName}" initialDisplayValue="${ae.lowLevelTerm.meddraTerm}">
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
                        <jsp:attribute name="optionsJS">
							{
								afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                                    $('adverseEvents[${index}].lowLevelTerm').value = selectedChoice.id;
                                    updateExpected(${index}, $('adverseEvents[${index}].ctcTerm').value, selectedChoice.id, '${ae.detailsForOther}', false);
								}
							}
						</jsp:attribute>
                    </ui:autocompleter>
                </jsp:attribute>
            </ui:row>
        </c:if>
        <c:if test="${not hasOtherMeddra}">
            <ui:row path="${fieldGroups[mainGroup].fields[0].propertyName}">
                <jsp:attribute name="label"><ui:label path="${fieldGroups[mainGroup].fields[0].propertyName}" text="${fieldGroups[mainGroup].fields[0].displayName}" required="true"/></jsp:attribute>
                <jsp:attribute name="value">
                    <ui:text path="${fieldGroups[mainGroup].fields[0].propertyName}" required="true" />
                </jsp:attribute>
            </ui:row>
        </c:if>


    </c:if>
    
    <tags:renderRow field="${fieldGroups[mainGroup].fields[1 + indexCorrection]}"/>
</tags:noform>

<script>
    Event.observe('${fieldGroups[mainGroup].fields[1 + indexCorrection].propertyName}-longselect', 'click', function(evt) {
        var val = evt.element().value;
        var chkDeath = $('outcomes[' + ${index} + '][1]');
        if(chkDeath){
            chkDeath.checked = (val == 'DEATH');
        }
        //update the heading
         $('titleOf_ae-section-${index}').innerHTML = "${title_term}${not empty title_otherMedDRA_term ? ':' : '' }${title_otherMedDRA_term} Grade: " + grades.indexOf(val) + " <c:if test="${ae.detailsForOther ne ''}">Verbatim: ${ae.detailsForOther}</c:if>";
    });
</script>
