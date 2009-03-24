<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@attribute name="index" required="true"%>
<%@attribute name="collapsed" required="true" description="Tells whether to display collapsed"%>
<%@attribute name="concomitantMedication" type="gov.nih.nci.cabig.caaers.domain.ConcomitantMedication" required="true" %>

<c:set var="mainGroup">conmed${index}</c:set>
<c:set var="conMedField" value="${fieldGroups[mainGroup].fields[0]}" />

<div>
<ae:fieldGroupDivision fieldGroupFactoryName="conmed" index="${index}" enableDelete="true" deleteParams="'concomitantMedication', ${index}, '_conMeds'" id="aeReport.concomitantMedications[${index}]">

<%--<ui:row path="aeReport.concomitantMedications[${index}]">
    <jsp:attribute name="label">${fieldGroups[mainGroup].fields[0].displayName}</jsp:attribute>
    <jsp:attribute name="value"><ui:text path="aeReport.concomitantMedications[${index}].agentName" /></jsp:attribute>
</ui:row>--%>

<table >
    <tr>
        <td>
                <tags:renderRow field="${fieldGroups[mainGroup].fields[0]}"/>
                <tags:renderRow field="${fieldGroups[mainGroup].fields[2]}"/>
        <td>
                <tags:renderRow field="${fieldGroups[mainGroup].fields[1]}"/>
                <tags:renderRow field="${fieldGroups[mainGroup].fields[3]}"/>

</table>    

</ae:fieldGroupDivision>



<script>

    function setFields_${index}(checked) {
        var edYrField = $('aeReport.concomitantMedications[${index}].endDate.yearString')
        var edDdField = $('aeReport.concomitantMedications[${index}].endDate.dayString');
        var edMmField = $('aeReport.concomitantMedications[${index}].endDate.monthString');

        if ($('aeReport.concomitantMedications[${index}].stillTakingMedications').checked) {
            edYrField.value = '';
            edDdField.value = '';
            edMmField.value = '';
            edYrField.clear(); edYrField.readOnly = true; edYrField.disable();
            edDdField.clear(); edDdField.readOnly = true; edDdField.disable();
            edMmField.clear(); edMmField.readOnly = true; edMmField.disable();
        } else {
            edYrField.readOnly = false; edYrField.enable();
            edDdField.readOnly = false; edDdField.enable();
            edMmField.readOnly = false; edMmField.enable();
        }
    }
    
    function initializeConMed_${index}() {
        if ($('aeReport.concomitantMedications[${index}].stillTakingMedications')) { // if the control is on the page
            $('aeReport.concomitantMedications[${index}].stillTakingMedications').observe('click', function(evt) {
                setFields_${index}();
            });
            AE.registerCalendarPopups();
        }

    }
    setFields_${index}.defer();
    initializeConMed_${index}.defer();
</script>

