<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="device" type="gov.nih.nci.cabig.caaers.domain.MedicalDevice" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>

<ae:fieldGroupDivision fieldGroupFactoryName="medicalDevice" index="${index}" style="${style}" enableDelete="true" deleteParams="'device', ${index}, '_devices'" collapsed="${collapsed}">
    <tags:errors path="aeReport.medicalDevices[${index}]"/>
<%--
    <c:forEach items="${fieldGroup.fields}" var="field">
            <tags:renderRow field="${field}"/>
     </c:forEach>
--%>

<table width="100%" border="0">
    <tr>
        <td>
            <ui:row path="aeReport.medicalDevices[${index}].brandName">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[0].propertyName}" text="${fieldGroup.fields[0].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[0].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].commonName">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[1].propertyName}" text="${fieldGroup.fields[1].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[1].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].deviceType">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[2].propertyName}" text="${fieldGroup.fields[2].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[2].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].manufacturerName">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[3].propertyName}" text="${fieldGroup.fields[3].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[3].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].manufacturerCity">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[4].propertyName}" text="${fieldGroup.fields[4].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[4].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].manufacturerState">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[5].propertyName}" text="${fieldGroup.fields[5].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[5].propertyName}" options="${fieldGroup.fields[5].attributes.options}"/></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].modelNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[6].propertyName}" text="${fieldGroup.fields[6].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[6].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].lotNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[7].propertyName}" text="${fieldGroup.fields[7].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[7].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].catalogNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[8].propertyName}" text="${fieldGroup.fields[8].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[8].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].expirationDate">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[9]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:date path="aeReport.medicalDevices[${index}].expirationDate" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].serialNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[10].propertyName}" text="${fieldGroup.fields[10].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[10].propertyName}" /></jsp:attribute>
            </ui:row>
        </td>
        <td>
            <ui:row path="aeReport.medicalDevices[${index}].otherNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[11].propertyName}" text="${fieldGroup.fields[11].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[11].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].deviceOperator">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[12]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[12].propertyName}" options="${fieldGroup.fields[12].attributes.options}"/></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].otherDeviceOperator">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[13].propertyName}" text="${fieldGroup.fields[13].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[13].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].implantedDate">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[14]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:date path="aeReport.medicalDevices[${index}].implantedDate" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].explantedDate">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[15]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:date path="aeReport.medicalDevices[${index}].explantedDate" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].deviceReprocessed">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[16].propertyName}" text="${fieldGroup.fields[16].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[16].propertyName}" options="${fieldGroup.fields[16].attributes.options}"/></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].reprocessorName">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[17].propertyName}" text="${fieldGroup.fields[17].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[17].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].reprocessorAddress">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[18].propertyName}" text="${fieldGroup.fields[18].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[18].propertyName}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].evaluationAvailability">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[19].propertyName}" text="${fieldGroup.fields[19].displayName}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[19].propertyName}" options="${fieldGroup.fields[19].attributes.options}"/></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].returnedDate">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[20]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:date path="aeReport.medicalDevices[${index}].returnedDate" /></jsp:attribute>
            </ui:row>
        </td>
    </tr>
</table>

<script language="JavaScript">
    AE.registerCalendarPopups();
</script>
    
</ae:fieldGroupDivision>

<script>
    function setTitleDevice_${index}() {
        var titleID = "titleOf_medicalDevice-" + ${index};
        var fieldObject = $("aeReport.medicalDevices[${index}].brandName");
        var selectedValue = fieldObject.value;
        $(titleID).innerHTML = selectedValue;
    }

    setTitleDevice_${index}.defer();
    Event.observe($("aeReport.medicalDevices[${index}].brandName"), "change", function() {
        setTitleDevice_${index}();
    });
</script>