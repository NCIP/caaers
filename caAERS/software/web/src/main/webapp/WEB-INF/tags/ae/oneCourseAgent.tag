<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="agent" type="gov.nih.nci.cabig.caaers.domain.CourseAgent" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>

<c:set var="transientCA" value="${(empty agent.id) or (empty agent.studyAgent)}" />

<ae:fieldGroupDivision fieldGroupFactoryName="courseAgent" index="${index}" style="${style}"
		enableDelete="true" title="${agent.displayName}"
		deleteParams="'agent', ${index}, '_agents'" collapsed="${!empties[agent] && collapsed}">
    <tags:errors path="aeReport.treatmentInformation.courseAgents[${index}]"/>

    <c:if test="${not transientCA}">
        <div class="row">
            <div class="label">Study Agent</div>
            <div class="value">${agent.displayName}</div>
        </div>
    </c:if>
    <c:if test="${transientCA}">
        <tags:renderRow field="${fieldGroup.fields[0]}"/>
    </c:if>

 <tags:renderRow field="${fieldGroup.fields[1]}"/>
 <tags:renderRow field="${fieldGroup.fields[2]}"/>
 <tags:renderRow field="${fieldGroup.fields[3]}"/>
 <tags:renderRow field="${fieldGroup.fields[4]}"/>
 <tags:renderRow field="${fieldGroup.fields[5]}"/>
 <tags:renderRow field="${fieldGroup.fields[6]}"/>
 <tags:renderRow field="${fieldGroup.fields[7]}"/>

    <div id="modified-dose-fields-${index}">
        <c:forEach begin="8" end="${fn:length(fieldGroup.fields) - 1}" var="i">
            <tags:renderRow field="${fieldGroup.fields[i]}"/>
        </c:forEach>
    </div>

    <script language="JavaScript">
        AE.registerCalendarPopups();
    </script>

</ae:fieldGroupDivision>

<script>
    function setTitleCourse_${index}() {
        var titleID = "titleOf_courseAgent-" + ${index};
        var selectedAgentValue = "${agent.displayName}";
        var fieldAgentDoseAmountValue = "${agent.dose.amount}";
        var fieldAgentDoseUnitValue ="${agent.dose.units}";


        var fieldAgentName = $("aeReport.treatmentInformation.courseAgents[${index}].studyAgent");
        if(fieldAgentName){
            var selectedAgentOption = fieldAgentName.options[fieldAgentName.selectedIndex];
            selectedAgentValue = selectedAgentOption.text;
        }


        var fieldAgentDoseAmount = $("aeReport.treatmentInformation.courseAgents[${index}].dose.amount");
        if(fieldAgentDoseAmount){
            fieldAgentDoseAmountValue = fieldAgentDoseAmount.value;
        }


        var fieldAgentDoseUnit = $("aeReport.treatmentInformation.courseAgents[${index}].dose.units");
        if(fieldAgentDoseUnit){
            fieldAgentDoseUnitValue = fieldAgentDoseUnit.options[fieldAgentDoseUnit.selectedIndex].value;
        }


        $(titleID).innerHTML = "" + selectedAgentValue + " (" + fieldAgentDoseAmountValue + " " + fieldAgentDoseUnitValue + ")";
    }
    <c:if test="${transientCA}">
        setTitleCourse_${index}.defer();
    </c:if>
    Event.observe($("aeReport.treatmentInformation.courseAgents[${index}].studyAgent"), "change", function() {
         setTitleCourse_${index}();
    });
    Event.observe($("aeReport.treatmentInformation.courseAgents[${index}].dose.amount"), "change", function() {
         setTitleCourse_${index}();
    });
    Event.observe($("aeReport.treatmentInformation.courseAgents[${index}].dose.units"), "change", function() {
         setTitleCourse_${index}();
    });
</script>
