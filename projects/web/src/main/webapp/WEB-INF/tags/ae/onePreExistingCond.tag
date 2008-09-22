<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="index" required="true"%>
<%@attribute name="preExistingCondition" type="gov.nih.nci.cabig.caaers.domain.PreExistingCondition"%>

<c:set var="mainGroup">preExistingCondition${index}</c:set>
<c:set var="preCondField" value="${fieldGroups[mainGroup].fields[0]}" />
<div class="${(index % 2 ) gt 0 ? 'odd' : 'even' }">
		<table width="100%">
 			<tr>
  				<td width="99%">
					<c:if test="${not empty preExistingCondition}">
					<ui:text path="${preCondField.propertyName}" displayNamePath="${preCondField.propertyName}.text" readonly="true" />
					</c:if>
					<c:if test="${empty preExistingCondition}">
					<%-- Other, Specify--%>
					<c:set var="otherField" value="${fieldGroups[mainGroup].fields[1]}" />
					<c:if test="${otherField.required or otherField.attributes.mandatory}"><tags:requiredIndicator/></c:if>&nbsp;Other, specify <ui:text path="${otherField.propertyName}"/>
					</c:if>
  				</td>
  				<td>
					<a href="#anchorPreExistingCondition" onClick="mHistory.removeDetails('preExistingCondition', ${index}, 'anchorPreExistingCondition')">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table> 
</div>
<%--
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="conmed" index="${index}" style="${style}">
    <tags:errors path="aeReport.saeReportPreExistingConditions[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}">
        <jsp:attribute name="label">
            <label>

            	<c:if test="${fieldGroup.fields[0].required or fieldGroup.fields[0].attributes.mandatory}"><tags:requiredIndicator/></c:if>
                ${fieldGroup.fields[0].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
    <tags:renderRow field="${fieldGroup.fields[1]}"  style="display: none">
        <jsp:attribute name="label">
            <label>
                ${fieldGroup.fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
</ae:fieldGroupDivision>
--%>