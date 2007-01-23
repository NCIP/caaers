<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.ae.InputField"%>
<%@attribute name="cssStyle"%>
<div class="row" id="${field.propertyName}-row" <c:if test="${not empty cssStyle}">style="${cssStyle}"</c:if>>
    <div class="label">
        <tags:renderLabel field="${field}"/>
    </div>
    <div class="value"><tags:renderInputs field="${field}"/></div>
    <c:if test="${not empty field.extraInformation}">
        <div class="extra">${field.extraInformation}</div>
    </c:if>
</div>

