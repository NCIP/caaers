<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>
<%@attribute name="size"%>
<c:choose>
    <c:when test="${field.categoryName == 'text'}"><form:input path="${field.propertyName}" size="${size}"/></c:when>
    <c:when test="${field.categoryName == 'date'}"><tags:dateInput path="${field.propertyName}"/></c:when>
    <c:when test="${field.categoryName == 'textarea'}"><form:textarea path="${field.propertyName}"/></c:when>
    <c:when test="${field.categoryName == 'select'}">
        <form:select path="${field.propertyName}" items="${field.attributes.options}"/>
    </c:when>
    <c:when test="${field.categoryName == 'autocompleter'}">
        <input size="${empty size ? '50' : size}" id="${field.propertyName}-input"/>
        <tags:indicator id="${field.propertyName}-indicator"/>
        <div id="${field.propertyName}-choices" class="autocomplete" style="display: none"></div>
        <form:hidden path="${field.propertyName}"/>
    </c:when>
    <c:otherwise>
        UNIMPLEMENTED FIELD TYPE ${field.categoryName} for ${field.propertyName}
    </c:otherwise>
</c:choose>
<tags:errors path="${field.propertyName}"/>
