<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="radiation" type="gov.nih.nci.cabig.caaers.domain.RadiationIntervention" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>

<c:set var="v" value="aeReport.radiationInterventions[${index}]" />
<ae:fieldGroupDivision fieldGroupFactoryName="radiationIntervention" index="${index}" style="${style}" enableDelete="true" deleteParams="'radiation', ${index}, '_radiations'" collapsed="${!empties[v] && collapsed}">
    <tags:errors path="aeReport.radiationInterventions[${index}]"/>

    <ui:row path="aeReport.radiationInterventions[${index}].studyRadiation">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[7].propertyName}" text="${fieldGroup.fields[7].displayName}"
                                               mandatory="${fieldGroup.fields[7].attributes.mandatory}" required="false"/></jsp:attribute>
         <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[7].propertyName}" options="${fieldGroup.fields[7].attributes.options}" field="${fieldGroup.fields[7]}" /></jsp:attribute>
    </ui:row>
    <ui:row path="aeReport.radiationInterventions[${index}].studyRadiation.description">
        <jsp:attribute name="label">
            <caaers:message code="LBL_aeReport.radiationInterventions.studyRadiation.description" text="Study radiation description" />
        </jsp:attribute>
        <jsp:attribute name="value"><span id="aeReport.radiationInterventions[${index}].studyRadiation.description_content">
           ${empty radiation.studyRadiation.description ? '' : radiation.studyRadiation.description}
           </span>
        </jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.radiationInterventions[${index}].administration">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[0].propertyName}" text="${fieldGroup.fields[0].displayName}"
                                               mandatory="${fieldGroup.fields[0].attributes.mandatory}"  /></jsp:attribute>
         <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[0].propertyName}" options="${fieldGroup.fields[0].attributes.options}" field="${fieldGroup.fields[0]}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.radiationInterventions[${index}].dosage">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[1].propertyName}" text="${fieldGroup.fields[1].displayName}"
                                               mandatory="${fieldGroup.fields[1].attributes.mandatory}" /></jsp:attribute>
         <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[1].propertyName}" field="${fieldGroup.fields[1]}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.radiationInterventions[${index}].dosageUnit">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[2].propertyName}" text="${fieldGroup.fields[2].displayName}"
                                               mandatory="${fieldGroup.fields[2].attributes.mandatory}" /></jsp:attribute>
         <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[2].propertyName}" options="${fieldGroup.fields[2].attributes.options}" field="${fieldGroup.fields[2]}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.radiationInterventions[${index}].lastTreatmentDate">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[3].propertyName}" text="${fieldGroup.fields[3].displayName}"
                                               mandatory="${fieldGroup.fields[3].attributes.mandatory}" /></jsp:attribute>
         <jsp:attribute name="value"><ui:date path="${fieldGroup.fields[3].propertyName}" field="${fieldGroup.fields[3]}" title="${fieldGroup.fields[3].displayName}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.radiationInterventions[${index}].fractionNumber">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[4].propertyName}" text="${fieldGroup.fields[4].displayName}"
                                               mandatory="${fieldGroup.fields[4].attributes.mandatory}" /></jsp:attribute>
         <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[4].propertyName}" field="${fieldGroup.fields[4]}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.radiationInterventions[${index}].daysElapsed">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[5].propertyName}" text="${fieldGroup.fields[5].displayName}"
                                               mandatory="${fieldGroup.fields[5].attributes.mandatory}" /></jsp:attribute>
         <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[5].propertyName}" field="${fieldGroup.fields[5]}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.radiationInterventions[${index}].adjustment">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[6].propertyName}" text="${fieldGroup.fields[6].displayName}"
                                               mandatory="${fieldGroup.fields[6].attributes.mandatory}" /></jsp:attribute>
         <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[6].propertyName}" options="${fieldGroup.fields[6].attributes.options}" field="${fieldGroup.fields[6]}"/></jsp:attribute>
    </ui:row>

    <script language="JavaScript">
        AE.registerCalendarPopups();
    </script>

</ae:fieldGroupDivision>

<script>
    function setTitleRadiation_${index}() {
        var titleID = "titleOf_radiationIntervention-" + ${index};
        var fieldRadiationType = $("aeReport.radiationInterventions[${index}].administration");
        var radiationTypeValue = fieldRadiationType.options[fieldRadiationType.selectedIndex].text;

        var radiationDosage = $("aeReport.radiationInterventions[${index}].dosage");
        var radiationDosageValue = radiationDosage.value;

        var radiationDosageUnit = $("aeReport.radiationInterventions[${index}].dosageUnit");
        var radiationDosageUnitValue = radiationDosageUnit.options[radiationDosageUnit.selectedIndex].value;

        $(titleID).innerHTML = "" + radiationTypeValue + " (" + radiationDosageValue + " " + radiationDosageUnitValue + ")";
    }

    setTitleRadiation_${index}.defer();
    Event.observe($("aeReport.radiationInterventions[${index}].administration"), "change", function() {
        setTitleRadiation_${index}();
    });
    Event.observe($("aeReport.radiationInterventions[${index}].dosage"), "change", function() {
        setTitleRadiation_${index}();
    });
    Event.observe($("aeReport.radiationInterventions[${index}].dosageUnit"), "change", function() {
        setTitleRadiation_${index}();
    });

    Event.observe($("aeReport.radiationInterventions[${index}].studyRadiation"), "change", function(event) {
        updateOtherInterventionDescription(Event.element(event), "aeReport.radiationInterventions[${index}].studyRadiation.description_content" );
    });
</script>
