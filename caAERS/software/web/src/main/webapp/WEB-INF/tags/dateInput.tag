<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="path" %>
<%@attribute name="title" %>
<%@attribute name="cssClass" %>
<%@attribute name="size" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField" %>
<%@attribute name="readOnly" type="java.lang.Boolean"%>
<%@attribute name="disabled" type="java.lang.Boolean"%>

<c:if test="${field != null}">
    <c:set var="fieldValue"><jsp:attribute name="value"><caaers:value path="${field.propertyName}" /></jsp:attribute></c:set>
    <c:if test="${empty fieldValue && field.required}"><c:set var="cssValue" value="required" /></c:if>
    <c:if test="${empty fieldValue && field.attributes.mandatory}"><c:set var="cssValue" value="mandatory" /></c:if>
    <c:if test="${empty fieldValue && field.attributes.mandatory && field.required}"><c:set var="cssValue" value="mandatory required" /></c:if>
    <c:if test="${not empty fieldValue && (field.attributes.mandatory || field.required)}"><c:set var="cssValue" value="valueOK" /></c:if>
</c:if>

<c:choose>
	<c:when test="${readOnly}">
		<c:out value="${fieldValue}"></c:out>
	</c:when>
	<c:otherwise>
		<form:input disabled="${disabled}" path="${path}" size="${size}" cssClass="date ${cssValue} ${cssClass}" title="${title}" id="${path}"/>
		<a id="${path}-calbutton" style="cursor:pointer;">
    		<img src="<chrome:imageUrl name="b-calendar.gif"/>" alt="Calendar" width="17" height="16" border="0" align="absmiddle"/>
		</a>
		<i>(mm/dd/yyyy)</i>	
	</c:otherwise>
</c:choose>

