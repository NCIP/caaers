<%-- TODO: why is this here?  It's just a copy of renderRow. --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ruleTags" tagdir="/WEB-INF/tags/rule"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.rule.InputField"%>
<%@attribute name="cssStyle"%>
<div class="row" id="${field.propertyName}-row" <c:if test="${not empty cssStyle}">style="${cssStyle}"</c:if>>
    <div class="label">
        <ruleTags:label field="${field}"/>
    </div>
    <div class="value"><ruleTags:inputs field="${field}"/></div>
    <c:if test="${not empty field.extraInformation}">
        <div class="extra">${field.extraInformation}</div>
    </c:if>
</div>

