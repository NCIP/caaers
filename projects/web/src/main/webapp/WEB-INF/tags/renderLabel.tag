<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>
<c:choose>
    <c:when test="${field.categoryName == 'autocompleter'}">
    	<ui:label path="${field.propertyName}" text="${field.displayName}" mandatory="${field.attributes.mandatory}" required="${field.required}"/>
    </c:when>
    <c:otherwise>
    	<ui:label path="${field.propertyName}" text="${field.displayName}" mandatory="${field.attributes.mandatory}" required="${field.required}" />
    </c:otherwise>
</c:choose>
