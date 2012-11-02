<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="agentFacade"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Agent:CREATE" var="hasAgentCreate"/>

<admin:agent3rdLevelMenu selected="createDevice" />

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false" hideTabControls="${!hasAgentCreate}">
    <jsp:attribute name="singleFields">
        <input type="hidden" name="_action" />

    <div class="content">
        <div class="row">
            <div class="label"><ui:label labelProperty="aeReport.medicalDevices.commonName" text="" path="device.commonName" required="true"/></div>
            <div class="value"><ui:text path="device.commonName" size="60" cssClass="${empty command.device.commonName ? 'required' : 'valueOK'} validate-NOTEMPTY$$MAXLENGTH2000" title="Device common name" readonly="${!hasAgentCreate}"/></div>
        </div>
        <div class="row">
            <div class="label"><ui:label labelProperty="aeReport.medicalDevices.brandName" text="" path="device.brandName"/></div>
            <div class="value"><ui:text path="device.brandName" size="60" cssClass="validate-MAXLENGTH2000" title="Device brand name" readonly="${!hasAgentCreate}"/></div>
        </div>
        <div class="row">
            <div class="label"><ui:label labelProperty="aeReport.medicalDevices.deviceType" text="" path="device.type" /></div>
            <div class="value"><ui:text path="device.type" size="60" cssClass="validate-MAXLENGTH2000" title="Device type" readonly="${!hasAgentCreate}"/></div>
        </div>
    </div>

    </jsp:attribute>
</tags:tabForm>
