<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp" %>

<admin:agent3rdLevelMenu selected="createDevice" />

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
    <jsp:attribute name="singleFields">
        <input type="hidden" name="_action" />

    <div class="content">

        <%--<chrome:box title="Agent Edit Form" autopad="false">--%>
            <div class="row">
                <div class="label"><ui:label labelProperty="aeReport.medicalDevices.commonName" text="" path="device.commonName" /></div>
                <div class="value"><ui:text path="device.commonName" size="60" cssClass="${empty command.device.commonName ? 'required' : 'valueOK'} validate-NOTEMPTY$$MAXLENGTH2000" title="Device common name" readonly="false"/></div>
            </div>
            <div class="row">
                <div class="label"><ui:label labelProperty="aeReport.medicalDevices.brandName" text="" path="device.brandName" /></div>
                <div class="value"><ui:text path="device.brandName" size="60" cssClass="${empty command.device.brandName ? 'required' : 'valueOK'} validate-NOTEMPTY$$MAXLENGTH2000" title="Device brand name" readonly="false"/></div>
            </div>
            <div class="row">
                <div class="label"><ui:label labelProperty="aeReport.medicalDevices.deviceType" text="" path="device.type" /></div>
                <div class="value"><ui:text path="device.type" size="60" cssClass="${empty command.device.type ? 'required' : 'valueOK'} validate-NOTEMPTY$$MAXLENGTH2000" title="Device type" readonly="false"/></div>
            </div>

    </div>

    </jsp:attribute>
</tags:tabForm>

