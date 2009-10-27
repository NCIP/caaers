<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="index" required="true" type="java.lang.Integer"%>
<%@attribute name="ta" required="true" type="gov.nih.nci.cabig.caaers.domain.TreatmentAssignment"%>
<%@attribute name="title"%>
<%@attribute name="collapsed" type="java.lang.Boolean" %>

<c:set var="mainGroup">main${index}</c:set>

<chrome:division title="&nbsp;${title}" id="OneTA-${index}" collapsable="false" enableDelete="true" deleteParams="'delete', ${index}">
    <ui:row path="${fieldGroups[mainGroup].fields[0].propertyName}">
		<jsp:attribute name="label">
			<ui:label path="${fieldGroups[mainGroup].fields[0].propertyName}" text="${fieldGroups[mainGroup].fields[0].displayName}"/>
		</jsp:attribute>
		<jsp:attribute name="value">
			<ui:text path="${fieldGroups[mainGroup].fields[0].propertyName}" size="${fieldGroups[mainGroup].fields[0].attributes.size}" readonly="${readOnly}" required="${fieldGroups[mainGroup].fields[0].required}">
				<jsp:attribute name="embededJS">
					Event.observe('${fieldGroups[mainGroup].fields[0].propertyName}', 'keyup', function(){
						$('titleOf_OneTA-${index}').innerHTML = $('${fieldGroups[mainGroup].fields[0].propertyName}').value;
					})
				</jsp:attribute>
            </ui:text>
		</jsp:attribute>
    </ui:row>
    <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="1">
        <tags:renderRow field="${field}"/>
    </c:forEach>
</chrome:division>
