<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.ae.InputField"%>
<c:choose>
    <c:when test="${field.categoryName == 'autocompleter'}">
        <label for="${field.attributes.textfieldId}">${field.displayName}</label>
    </c:when>
    <c:otherwise>
        <form:label path="${field.propertyName}">${field.displayName}</form:label>
    </c:otherwise>
</c:choose>
