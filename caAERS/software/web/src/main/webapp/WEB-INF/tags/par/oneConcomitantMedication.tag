<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@attribute name="index" required="true"%>
<%@attribute name="collapsed" required="true" description="Tells whether to display collapsed"%>
<%@attribute name="concomitantMedication" type="gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication" required="true" %>

<c:set var="mainGroup">conmed${index}</c:set>
<c:set var="conMedField" value="${fieldGroups[mainGroup].fields[0]}" />
<c:set var="v" value="assignment.concomitantMedications[${index}]" />

<chrome:division id="assignment.concomitantMedications[${index}]" collapsable="true" collapsed="${collapsed && !empties[v]}" enableDelete="true" deleteParams="'concomitantMedication' ,${index}, 'anchorConcomitantMedication', {}">

    <jsp:attribute name="title">
		${concomitantMedication.agentName}
	</jsp:attribute>

    <jsp:attribute name="titleFragment">
	</jsp:attribute>

    <jsp:body>

<div>

                <c:if test="${empty concomitantMedication.name}">
                        <ui:row path="assignment.concomitantMedications[${index}].agentName">
                            <jsp:attribute name="label">
                                <ui:label path="assignment.concomitantMedications[${index}].agentName" text="Medication name" />
                            </jsp:attribute>
                            <jsp:attribute name="value">
                                <ui:text path="assignment.concomitantMedications[${index}].agentName" />
                            </jsp:attribute>
                        </ui:row>
                </c:if>

                <ui:row path="assignment.concomitantMedications[${index}].startDate">
                    <jsp:attribute name="label">
                        <ui:label path="assignment.concomitantMedications[${index}].startDate" text="Start date" />
                    </jsp:attribute>
                    <jsp:attribute name="value">
                        <ui:splitDate path="assignment.concomitantMedications[${index}].startDate" />
                    </jsp:attribute>
                </ui:row>

                <ui:row path="assignment.concomitantMedications[${index}].stillTakingMedications">
                    <jsp:attribute name="label">
                        <ui:label path="assignment.concomitantMedications[${index}].stillTakingMedications" text="Still taking medication?" />
                    </jsp:attribute>
                    <jsp:attribute name="value">
                        <ui:checkbox path="assignment.concomitantMedications[${index}].stillTakingMedications" />
                    </jsp:attribute>
                </ui:row>

                <ui:row path="assignment.concomitantMedications[${index}].endDate">
                    <jsp:attribute name="label">
                        <ui:label path="assignment.concomitantMedications[${index}].endDate" text="End date" />
                    </jsp:attribute>
                    <jsp:attribute name="value">
                        <ui:splitDate path="assignment.concomitantMedications[${index}].endDate" />
                    </jsp:attribute>
                </ui:row>

<%--</ae:fieldGroupDivision>--%>



<script>

        function setFields_${index}() {
                   var edYrField = $('assignment.concomitantMedications[${index}].endDate.yearString')
                   if(edYrField) {
                       var edDdField = $('assignment.concomitantMedications[${index}].endDate.dayString');
                       var edMmField = $('assignment.concomitantMedications[${index}].endDate.monthString');

                       if ($('assignment.concomitantMedications[${index}].stillTakingMedications').checked) {
                           edYrField.value = '';
                           edDdField.value = '';
                           edMmField.value = '';
                           edYrField.clear(); edYrField.readonly = true; edYrField.disable();
                           edDdField.clear(); edDdField.readonly = true; edDdField.disable();
                           edMmField.clear(); edMmField.readonly = true; edMmField.disable();
                       } else {
                           edYrField.readonly = false; edYrField.enable();
                           edDdField.readonly = false; edDdField.enable();
                           edMmField.readonly = false; edMmField.enable();
                       }

                   }
        }

     function initializeConMed_${index}(){
         $('assignment.concomitantMedications[${index}].stillTakingMedications').observe('click', function(evt){
             setFields_${index}.defer();
         });
         AE.registerCalendarPopups();
         setFields_${index}.defer();
     }
     initializeConMed_${index}.defer();

    function setTitleCM_${index}() {
        var titleID = $('titleOf_assignment.concomitantMedications[${index}]');
        var name = $("assignment.concomitantMedications[${index}].agentName");
        var value = name.value;
        $(titleID).innerHTML = value;
    }

    Event.observe($("assignment.concomitantMedications[${index}].agentName"), "keyup", function() {
        setTitleCM_${index}();
    });


</script>

    </jsp:body>
</chrome:division>
