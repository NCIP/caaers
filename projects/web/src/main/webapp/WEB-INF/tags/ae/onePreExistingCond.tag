<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@attribute name="index" required="true"%>
<%@attribute name="preExistingCondition" type="gov.nih.nci.cabig.caaers.domain.PreExistingCondition"%>

<c:set var="mainGroup">preExistingCondition${index}</c:set>
<c:set var="preCondField" value="${fieldGroups[mainGroup].fields[0]}" />

<chrome:division id="aeReport.saeReportPreExistingConditions[${index}]" collapsable="false" deleteParams="'preExistingCondition', ${index}, '_preExistingConditions', {}" enableDelete="true" collapsed="false">

    <jsp:attribute name="titleFragment">
		${anatomicSite.name}
	</jsp:attribute>

    <jsp:body>

<%--<ae:fieldGroupDivision fieldGroupFactoryName="preExistingCondition" index="${index}" enableDelete="true" deleteParams="'preExistingCondition', ${index}, '_preExistingConditions'">--%>
<ui:row path="aeReport.saeReportPreExistingConditions[${index}]">
    <jsp:attribute name="label">${preCondField.displayName}</jsp:attribute>
    <jsp:attribute name="value">
        <ui:select options="${preExistingConditionOptions}" path="aeReport.saeReportPreExistingConditions[${index}].preExistingCondition"></ui:select>&nbsp;
            <c:set var="display" value="${empty preExistingCondition ? 'inline' : 'none'}" />
            <span id="other_${index}" style="display:${display};">
                <%-- Other, Specify--%>
                <c:set var="otherField" value="${fieldGroups[mainGroup].fields[1]}" />
                <c:if test="${otherField.required or otherField.attributes.mandatory}"><tags:requiredIndicator/></c:if>&nbsp;Other, specify <ui:text path="${otherField.propertyName}"/>
            </span>
    </jsp:attribute>
</ui:row>
<%--</ae:fieldGroupDivision>--%>

<script language="JavaScript">
    Event.observe("aeReport.saeReportPreExistingConditions[${index}].preExistingCondition", "change", function() {
        if (($('aeReport.saeReportPreExistingConditions[${index}].preExistingCondition').options[$('aeReport.saeReportPreExistingConditions[${index}].preExistingCondition').selectedIndex].value) == "")
            $('other_${index}').show();
        else
            $('other_${index}').hide();
    })
</script>

</jsp:body>
</chrome:division>    