<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="path" description="The path to bind" required="true"%>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>
<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="validationJSClass" description="The classes required for validation framework" %>
<%@attribute name="readonly" description="Specifies the readonly attribute" type="java.lang.Boolean" %>
<%@attribute name="required" description="Specifies the requiredness" type="java.lang.Boolean" %>
<%@attribute name="mandatory" description="" type="java.lang.Boolean" %>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>
<%@attribute name="embededJS" description="A piece of javascript, that if specified will be embeded along with this input"%>

<c:if test="${field != null}"><c:set var="mandatory" value="${field.attributes.mandatory}" /></c:if>

<c:set var="fieldValue"><jsp:attribute name="value"><caaers:value path="${path}" /></jsp:attribute></c:set>
<c:if test="${empty fieldValue && required}"><c:set var="cssValue" value="required" /></c:if>
<c:if test="${empty fieldValue && mandatory}"><c:set var="cssValue" value="mandatory" /></c:if>
<c:if test="${empty fieldValue && mandatory && required}"><c:set var="cssValue" value="mandatory required" /></c:if>
<c:if test="${not empty fieldValue && (mandatory || required)}"><c:set var="cssValue" value="valueOK" /></c:if>

<ui:fieldWrapper path="${path}" cssClass="${cssClass}" validationJSClass="${validationJSClass}" readonly="${readonly}" required="${required}" displayNamePath="${displayNamePath}" title="${title}" embededJS="${embededJS}" readonlyDisplayFormat="date">
    <jsp:attribute name="field">
    <tags:dateInput path="${path}" cssClass="${cssValue} ${validationCss} ${cssClass}" title="${title}" field="${field}"/>
        <c:if test="${not empty field.attributes.help and field.categoryName ne 'autocompleter'}">
            <tags:hoverHelp path="${field.propertyName}" code="${field.attributes.help}"/>
        </c:if>
    </jsp:attribute>
</ui:fieldWrapper>
