<%--
	This page renders one Medical Device from AE Flow.
	Author : Ion C. Olaru
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="device" type="gov.nih.nci.cabig.caaers.domain.MedicalDevice" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<c:set var="v" value="aeReport.medicalDevices[${index}]" />

<ae:fieldGroupDivision fieldGroupFactoryName="medicalDevice" title="${device.studyDevice.displayName}" index="${index}" style="${style}" enableDelete="true" deleteParams="'device', ${index}, '_devices'" collapsed="${!empties[v] && collapsed}">
    <tags:errors path="aeReport.medicalDevices[${index}]"/>
<%--
    <c:forEach items="${fieldGroup.fields}" var="field">
            <tags:renderRow field="${field}"/>
     </c:forEach>
--%>

<table width="100%" border="0">
    <tr>
        <td colspan="2">

            <ui:row path="aeReport.medicalDevices[${index}].studyDevice">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[21].propertyName}" text="${fieldGroup.fields[21].displayName}" required="true"
                          mandatory="${fieldGroup.fields[21].attributes.mandatory}"/>
                 </jsp:attribute>
                 <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[21].propertyName}" options="${fieldGroup.fields[21].attributes.options}" field="${fieldGroup.fields[21]}" 
                 readonly ="${not empty device.id and not empty device.studyDevice}" displayNamePath="${v}.studyDevice.displayName" /></jsp:attribute>
            </ui:row>

        </td>
    </tr>
    <tr>

        <td valign="top">
            <ui:row path="aeReport.medicalDevices[${index}].brandName">
                 <jsp:attribute name="label">
                     <ui:label path="${fieldGroup.fields[0].propertyName}" text="${fieldGroup.fields[0].displayName}" required="false"
                               mandatory="${fieldGroup.fields[0].attributes.mandatory}"/>
                 </jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:text path="${fieldGroup.fields[0].propertyName}" field="${fieldGroup.fields[0]}"
                                                      readonly="true"
                                                      displayNamePath="${v}.studyDevice.brandName" />
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].commonName">
                 <jsp:attribute name="label">
                     <ui:label path="${fieldGroup.fields[1].propertyName}" text="${fieldGroup.fields[1].displayName}"
                                                        mandatory="${fieldGroup.fields[1].attributes.mandatory}" required="false"/>
                 </jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:text path="${fieldGroup.fields[1].propertyName}" field="${fieldGroup.fields[1]}"
                                                      readonly="true"
                                                      displayNamePath="${v}.studyDevice.commonName"
                             />
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].deviceType">
                 <jsp:attribute name="label">
                     <ui:label path="${fieldGroup.fields[2].propertyName}" text="${fieldGroup.fields[2].displayName}"
                                                        mandatory="${fieldGroup.fields[2].attributes.mandatory}" required="false"/>
                 </jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:text path="${fieldGroup.fields[2].propertyName}" field="${fieldGroup.fields[2]}"
                                                      readonly="true"
                                                      displayNamePath="${v}.studyDevice.deviceType"/>
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].manufacturerName">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[3].propertyName}" text="${fieldGroup.fields[3].displayName}"
                                                       mandatory="${fieldGroup.fields[3].attributes.mandatory}" required="false"/>
                 </jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[3].propertyName}" field="${fieldGroup.fields[3]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].manufacturerCity">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[4].propertyName}" text="${fieldGroup.fields[4].displayName}"
                                                       mandatory="${fieldGroup.fields[4].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[4].propertyName}" field="${fieldGroup.fields[4]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].manufacturerState">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[5].propertyName}" text="${fieldGroup.fields[5].displayName}"
                                                       mandatory="${fieldGroup.fields[5].attributes.mandatory}" required="false"/>
                 </jsp:attribute>
                 <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[5].propertyName}" options="${fieldGroup.fields[5].attributes.options}"
                                                        field="${fieldGroup.fields[5]}"/>
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].modelNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[6].propertyName}" text="${fieldGroup.fields[6].displayName}"
                                                       mandatory="${fieldGroup.fields[6].attributes.mandatory}"  required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[6].propertyName}" field="${fieldGroup.fields[6]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].lotNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[7].propertyName}" text="${fieldGroup.fields[7].displayName}"
                                                       mandatory="${fieldGroup.fields[7].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[7].propertyName}" field="${fieldGroup.fields[7]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].catalogNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[8].propertyName}" text="${fieldGroup.fields[8].displayName}"
                                                       mandatory="${fieldGroup.fields[8].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[8].propertyName}" field="${fieldGroup.fields[8]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].expirationDate">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[9]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:date path="aeReport.medicalDevices[${index}].expirationDate" field="${fieldGroup.fields[9]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].serialNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[10].propertyName}" text="${fieldGroup.fields[10].displayName}"
                                                       mandatory="${fieldGroup.fields[10].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[10].propertyName}" field="${fieldGroup.fields[10]}" /></jsp:attribute>
            </ui:row>
        </td>
        <td valign="top">
            <ui:row path="aeReport.medicalDevices[${index}].otherNumber">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[11].propertyName}" text="${fieldGroup.fields[11].displayName}"
                                                       mandatory="${fieldGroup.fields[11].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[11].propertyName}" field="${fieldGroup.fields[11]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].deviceOperator">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[12]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[12].propertyName}" options="${fieldGroup.fields[12].attributes.options}"
                                                        field="${fieldGroup.fields[12]}"/>
                 </jsp:attribute>
            </ui:row>

            <c:set var="_operator"><jsp:attribute name="value"><caaers:value path="${fieldGroup.fields[12].propertyName}" /></jsp:attribute></c:set>
            <c:set var="_other_display" value="none" />
            <c:if test="${_operator eq 'OTHER'}"><c:set var="_other_display" value="''" /></c:if>

            <ui:row path="aeReport.medicalDevices[${index}].otherDeviceOperator" style="display: ${_other_display}">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[13].propertyName}" text="${fieldGroup.fields[13].displayName}"
                                                       mandatory="${fieldGroup.fields[13].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[13].propertyName}" field="${fieldGroup.fields[13]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].implantedDate">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[14]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:date path="aeReport.medicalDevices[${index}].implantedDate" field="${fieldGroup.fields[14]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].explantedDate">
                 <jsp:attribute name="label"><tags:renderLabel field="${fieldGroup.fields[15]}"/></jsp:attribute>
                 <jsp:attribute name="value"><ui:date path="aeReport.medicalDevices[${index}].explantedDate" field="${fieldGroup.fields[15]}" /></jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].deviceReprocessed">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[16].propertyName}" text="${fieldGroup.fields[16].displayName}"
                                                       mandatory="${fieldGroup.fields[16].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:select path="${fieldGroup.fields[16].propertyName}" options="${fieldGroup.fields[16].attributes.options}" field="${fieldGroup.fields[16]}">
                         <jsp:attribute name="embededJS">
                             $('${fieldGroup.fields[16].propertyName}').observe("change", function(evt){
                               if($('${fieldGroup.fields[16].propertyName}').value == 'YES'){
                                  makeFieldMandatory('${fieldGroup.fields[17].propertyName}');
                                  makeFieldMandatory('${fieldGroup.fields[18].propertyName}');
                                  showFieldAndRow('${fieldGroup.fields[17].propertyName}');
                                  showFieldAndRow('${fieldGroup.fields[18].propertyName}');
                               }else{
                                  hideFieldAndRow('${fieldGroup.fields[17].propertyName}');
                                  hideFieldAndRow('${fieldGroup.fields[18].propertyName}');
                                  makeFieldOptional('${fieldGroup.fields[17].propertyName}');
                                  makeFieldOptional('${fieldGroup.fields[18].propertyName}');
                               }
                             });
                         </jsp:attribute>
                     </ui:select>
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].reprocessorName" style="${device.deviceReprocessed eq 'YES' ? '' : 'display:none;'}">
                 <jsp:attribute name="label">
                     <ui:label path="${fieldGroup.fields[17].propertyName}" text="${fieldGroup.fields[17].displayName}" mandatory="${device.deviceReprocessed eq 'YES'}" />
                 </jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:text path="${fieldGroup.fields[17].propertyName}"  field="${fieldGroup.fields[17]}" mandatory="${device.deviceReprocessed eq 'YES'}" />
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].reprocessorAddress" style="${device.deviceReprocessed eq 'YES' ? '' : 'display:none;'}">
                 <jsp:attribute name="label">
                     <ui:label path="${fieldGroup.fields[18].propertyName}" text="${fieldGroup.fields[18].displayName}" mandatory="${device.deviceReprocessed eq 'YES'}" />
                 </jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:text path="${fieldGroup.fields[18].propertyName}" field="${fieldGroup.fields[18]}"  mandatory="${device.deviceReprocessed eq 'YES'}" />
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].evaluationAvailability">
                 <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[19].propertyName}" text="${fieldGroup.fields[19].displayName}"
                                                       mandatory="${fieldGroup.fields[19].attributes.mandatory}" required="false"/></jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:select path="${fieldGroup.fields[19].propertyName}" options="${fieldGroup.fields[19].attributes.options}" field="${fieldGroup.fields[19]}" >
                         <jsp:attribute name="embededJS">
                             $('${fieldGroup.fields[19].propertyName}').observe("change", function(evt){
                               if($('${fieldGroup.fields[19].propertyName}').value == 'RETURNED'){
                                  makeFieldMandatory('${fieldGroup.fields[20].propertyName}');
                                  showFieldAndRow('${fieldGroup.fields[20].propertyName}');
                               }else{
                                  hideFieldAndRow('${fieldGroup.fields[20].propertyName}');
                                  makeFieldOptional('${fieldGroup.fields[20].propertyName}');
                               }
                             });
                         </jsp:attribute>
                     </ui:select>
                 </jsp:attribute>
            </ui:row>
            <ui:row path="aeReport.medicalDevices[${index}].returnedDate" style="${device.evaluationAvailability eq 'RETURNED' ? '' : 'display:none;'}">
                 <jsp:attribute name="label">
                     <ui:label path="${fieldGroup.fields[20].propertyName}" text="${fieldGroup.fields[20].displayName}" mandatory="${device.evaluationAvailability eq 'RETURNED'}" />
                 </jsp:attribute>
                 <jsp:attribute name="value">
                     <ui:date path="aeReport.medicalDevices[${index}].returnedDate" field="${fieldGroup.fields[20]}" mandatory="${device.evaluationAvailability eq 'RETURNED'}"/>
                 </jsp:attribute>
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
        var selBox = $('aeReport.medicalDevices[${index}].studyDevice');

        var title = selBox.options[selBox.selectedIndex].text;
        $(titleID).innerHTML = "" + title;
    }

    setTitleDevice_${index}.defer();
    if($("aeReport.medicalDevices[${index}].studyDevice")) {
            Event.observe($("aeReport.medicalDevices[${index}].studyDevice"), "change", function() {
                setTitleDevice_${index}();
                updateMedicalDevice(${index}, $('aeReport.medicalDevices[${index}].studyDevice').value);
            });
    }


    var dOperator = $('aeReport.medicalDevices[${index}].deviceOperator');
    if (dOperator) {
        Event.observe(dOperator, "change", function() {
            // alert(${index});
            if (!$('aeReport.medicalDevices[${index}].otherDeviceOperator-row')) return;

            if (dOperator.options[dOperator.selectedIndex].value != 'OTHER') {
                $('aeReport.medicalDevices[${index}].otherDeviceOperator-row').hide();
                $('aeReport.medicalDevices[${index}].otherDeviceOperator').value = '';
            } else {
                $('aeReport.medicalDevices[${index}].otherDeviceOperator-row').show();
            }

        });
    }
</script>