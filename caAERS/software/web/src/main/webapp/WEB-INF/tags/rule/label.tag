<%-- TODO: why is this here?  It's just a copy of renderLabel. --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.rule.InputField"%>
<c:choose>
    <c:when test="${field.categoryName == 'autocompleter'}">
        <label for="${field.textfieldId}">${field.displayName}</label>
    </c:when>
    <c:otherwise>
        <form:label path="${field.propertyName}">${field.displayName}</form:label>
    </c:otherwise>
</c:choose>
