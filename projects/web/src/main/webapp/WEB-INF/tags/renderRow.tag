<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.ae.InputField"%>
<%@attribute name="cssClass"%>
<%@attribute name="style"%>
<%@attribute name="label" fragment="true" %>
<div class="row ${cssClass}" id="${field.propertyName}-row" <c:if test="${not empty style}">style="${style}"</c:if>>
    <div class="label">
        <c:choose>
            <c:when test="${not empty label}"><jsp:invoke fragment="label"/></c:when>
            <c:otherwise><tags:renderLabel field="${field}"/></c:otherwise>
        </c:choose>
    </div>
    <div class="value"><tags:renderInputs field="${field}"/></div>
    <c:if test="${not empty field.attributes.details}">
        <div class="extra">${field.attributes.details}</div>
    </c:if>
</div>
