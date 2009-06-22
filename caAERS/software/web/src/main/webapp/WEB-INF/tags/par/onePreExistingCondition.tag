<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true"%>
<%@attribute name="preExistingCondition" type="gov.nih.nci.cabig.caaers.domain.PreExistingCondition"%>
<%@attribute name="otherValue" type="java.lang.String"%>

<chrome:division id="assignment.preExistingConditions[${index}]" collapsable="false" deleteParams="'preExistingCondition', ${index}, 'anchorPreExistingCondition', {}" enableDelete="true" collapsed="false">

    <jsp:attribute name="title">
		<c:out value="${not empty preExistingCondition.text ? preExistingCondition.text : otherValue }" />
	</jsp:attribute>
    <jsp:attribute name="titleFragment" />

    <jsp:body>

<%--<ae:fieldGroupDivision fieldGroupFactoryName="preExistingCondition" index="${index}" enableDelete="true" deleteParams="'preExistingCondition', ${index}, '_preExistingConditions'">--%>
<c:if test="${empty preExistingCondition.text && empty otherValue}">
<ui:row path="assignment.preExistingConditions[${index}]">
    <jsp:attribute name="label">Pre-existing condition</jsp:attribute>
    <jsp:attribute name="value">
        <ui:select options="${preExistingConditionOptions}" path="assignment.preExistingConditions[${index}].preExistingCondition"></ui:select>&nbsp;
            <c:set var="display" value="${empty preExistingCondition ? 'inline' : 'none'}" />
            <span id="other_${index}" style="display:none;">
                Other, specify <ui:text path="assignment.preExistingConditions[${index}].other"/>
            </span>
        <tags:errors path="assignment.preExistingConditions[${index}]"/>
    </jsp:attribute>
</ui:row>
</c:if>
<%--</ae:fieldGroupDivision>--%>

<script language="JavaScript">
    Event.observe("assignment.preExistingConditions[${index}].preExistingCondition", "change", function() {
        if (($('assignment.preExistingConditions[${index}].preExistingCondition').options[$('assignment.preExistingConditions[${index}].preExistingCondition').selectedIndex].value) == "")
            $('other_${index}').show();
        else
            $('other_${index}').hide();
    })
</script>

<script>

    function setTitlePEC_${index}() {
        var titleID = $('titleOf_assignment.preExistingConditions[${index}]');
        var select = $("assignment.preExistingConditions[${index}].preExistingCondition");
        var value = select.options[select.selectedIndex].text;
        $(titleID).innerHTML = value;
    }

    Event.observe($("assignment.preExistingConditions[${index}].preExistingCondition"), "change", function() {
        setTitlePEC_${index}();
    });
</script>

</jsp:body>
</chrome:division>
