<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>
<c:choose>
    <c:when test="${field.categoryName == 'autocompleter'}">
        <label for="${field.propertyName}-input" class="${field.required ? 'required' : ''} ${field.attributes.mandatory ? 'mandatory' : '' }">
            <c:if test="${field.required or field.attributes.mandatory}"><tags:requiredIndicator/></c:if>&nbsp;${field.displayName}
        </label>
    </c:when>
    <c:otherwise>
         <form:label path="${field.propertyName}" cssClass="${field.required ? 'required' : ''} ${field.attributes.mandatory ? 'mandatory' : '' }">
            <tags:putSpecialRequiredIndicator field="${field}" />
            <c:if test="${field.required or field.attributes.mandatory}"><tags:requiredIndicator/></c:if>&nbsp;${field.displayName}
        </form:label>
    </c:otherwise>
</c:choose>
