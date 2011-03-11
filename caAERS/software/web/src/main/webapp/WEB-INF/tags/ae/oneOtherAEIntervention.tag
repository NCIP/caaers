<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="otherAE" type="gov.nih.nci.cabig.caaers.domain.OtherAEIntervention" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>

<c:set var="v" value="aeReport.otherAEIntervention[${index}]" />

<ae:fieldGroupDivision fieldGroupFactoryName="otherAEIntervention" index="${index}" enableDelete="true" deleteParams="'otherAE', ${index}, '_otherAEs'" collapsed="${!empties[v]}">
    <tags:errors path="aeReport.otherAEInterventions[${index}]"/>

    <ui:row path="aeReport.otherAEInterventions[${index}].studySurgery">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[0].propertyName}" text="${fieldGroup.fields[0].displayName}" mandatory="${fieldGroup.fields[0].attributes.mandatory}" required="true"/></jsp:attribute>
         <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[0].propertyName}" options="${fieldGroup.fields[0].attributes.options}" field="${fieldGroup.fields[0]}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.otherAEInterventions[${index}].description">
        <jsp:attribute name="label">
            <caaers:message code="LBL_aeReport.otherAEInterventions.description" text="Study other AE description" />
        </jsp:attribute>
        <jsp:attribute name="value"><ui:textarea path="aeReport.otherAEInterventions[${index}].description" /></jsp:attribute>
    </ui:row>

<script language="JavaScript">
    AE.registerCalendarPopups();
</script>

</ae:fieldGroupDivision>

<script>
/*
    function setTitleSurgery_${index}() {
        var titleID = "titleOf_surgeryIntervention-" + ${index};
        var fieldObject = $("aeReport.surgeryInterventions[${index}].interventionSite-input");
        var selectedValue = fieldObject.value;
        var surgeryInterventionDate = $("aeReport.surgeryInterventions[${index}].interventionDate").value;
        
        if (selectedValue != "Begin typing here...")
            $(titleID).innerHTML = "" + selectedValue + " (" + surgeryInterventionDate + ")";

    }

    setTitleSurgery_${index}.defer();
    Event.observe($("aeReport.surgeryInterventions[${index}].interventionSite-input"), "blur", function() {
        setTitleSurgery_${index}();
    });

    Event.observe($("aeReport.surgeryInterventions[${index}].interventionDate"), "change", function() {
        setTitleSurgery_${index}();
    });

    Event.observe($("aeReport.surgeryInterventions[${index}].studySurgery"), "change", function(event) {
        updateOtherInterventionDescription(Event.element(event), "aeReport.surgeryInterventions[${index}].studySurgery.description_content" );
    });
*/

Event.observe($("aeReport.otherAEInterventions[${index}].studyIntervention"), "change", function(event) {
    var selBox = $('aeReport.otherAEInterventions[${index}].studyIntervention');
    var Idtext = selBox.options[selBox.selectedIndex].value;
          if(Idtext.toString().empty())  {
            $('aeReport.otherAEInterventions[${index}].description').value = "";
          }
         else {
           $('aeReport.otherAEInterventions[${index}].description').value = otherAEMap.get(Idtext);
       }

});

function setTitleOtherAE_${index}() {
       var titleID = "titleOf_otherAEIntervention-" + ${index};
       var selBox = $('aeReport.otherAEInterventions[${index}].studyIntervention');

       var title = selBox.options[selBox.selectedIndex].text;
       $(titleID).innerHTML = "" + title;
   }

   setTitleOtherAE_${index}.defer();
   Event.observe($("aeReport.otherAEInterventions[${index}].studyIntervention"), "change", function() {
         setTitleOtherAE_${index}();
    });


</script>
