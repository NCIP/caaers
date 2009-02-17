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

<ae:fieldGroupDivision fieldGroupFactoryName="courseAgent" index="${index}" style="${style}" enableDelete="true" deleteParams="'agent', ${index}, '_agents'" collapsed="${collapsed}">
    <tags:errors path="aeReport.treatmentInformation.courseAgents[${index}]"/>
    
 <tags:renderRow field="${fieldGroup.fields[0]}"/>
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

        var fieldAgentName = $("aeReport.treatmentInformation.courseAgents[${index}].studyAgent");
        var selectedAgentOption = fieldAgentName.options[fieldAgentName.selectedIndex];
        var selectedAgentValue = selectedAgentOption.text;

        var fieldAgentDoseAmount = $("aeReport.treatmentInformation.courseAgents[${index}].dose.amount");
        var fieldAgentDoseAmountValue = fieldAgentDoseAmount.value;

        var fieldAgentDoseUnit = $("aeReport.treatmentInformation.courseAgents[${index}].dose.units");
        var fieldAgentDoseUnitValue = fieldAgentDoseUnit.options[fieldAgentDoseUnit.selectedIndex].value;

        $(titleID).innerHTML = "" + selectedAgentValue + " (" + fieldAgentDoseAmountValue + " " + fieldAgentDoseUnitValue + ")";
    }

    setTitleCourse_${index}.defer();
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
