<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="index" required="true" type="java.lang.Integer"%>
<%@attribute name="identifier" required="true"
	type="gov.nih.nci.cabig.caaers.domain.Identifier"%>
<%@attribute name="style"%>
<%@attribute name="title"%>
<%@attribute name="sectionClass" required="true"%>
<%@attribute name="removeButtonAction"%>
<%@attribute name="enableDelete" type="java.lang.Boolean"%>
<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>

<chrome:division title="${title}" id="${sectionClass}-${index}"
	cssClass="${sectionClass}" style="${style}"
	enableDelete="${enableDelete}" deleteParams="${deleteParams}">
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field">

		<c:if test="${(fn:endsWith(field.propertyName, 'type'))}">
			<tags:renderRow field="${field}" />
		</c:if>

		<c:if test="${(fn:endsWith(field.propertyName, 'value'))}">
			<tags:renderRow field="${field}" />
		</c:if>

		<c:if test="${(fn:endsWith(field.propertyName, 'primaryIndicator'))}">
			<tags:renderRow field="${field}" />
		</c:if>


		<c:if
			test="${(fn:endsWith(field.propertyName, 'systemName')) &&  (identifier.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
			<tags:renderRow field="${field}" />
		</c:if>


		<c:if
			test="${(fn:endsWith(field.propertyName, 'organization')) &&  (identifier.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
			<tags:renderRow field="${field}" />
		</c:if>

	</c:forEach>
</chrome:division>
