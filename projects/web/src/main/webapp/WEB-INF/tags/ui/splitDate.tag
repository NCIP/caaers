<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="path" description="The path to bind" required="true"%>
<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="yearRequired" type="java.lang.Boolean" description="Set the requiredness of Year" %>
<%@attribute name="monthRequired" type="java.lang.Boolean" description="Set the requiredness of month" %>
<%@attribute name="dayRequired" type="java.lang.Boolean" description="Set the requiredness of day" %>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>
<%@attribute name="embededJS" description="A piece of javascript, that if specified will be embeded along with this input"%>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>

<c:if test="${field != null}"><c:set var="mandatory" value="${field.attributes.mandatory}" /></c:if>

<c:set var="fieldValue"><jsp:attribute name="value"><caaers:value path="${path}" /></jsp:attribute></c:set>
<c:if test="${empty fieldValue && required}"><c:set var="cssValue" value="required" /></c:if>
<c:if test="${empty fieldValue && mandatory}"><c:set var="cssValue" value="mandatory" /></c:if>
<c:if test="${empty fieldValue && mandatory && required}"><c:set var="cssValue" value="mandatory required" /></c:if>
<c:if test="${not empty fieldValue && (mandatory || required)}"><c:set var="cssValue" value="valueOK" /></c:if>

<ui:fieldWrapper path="${path}" cssClass="${cssClass}"
  validationJSClass="${validationJSClass}" readonly="${readonly}"  required="${required}"
  displayNamePath="${displayNamePath}" title="${title}" embededJS="${embededJS}" readonlyDisplayFormat="split_date">
<jsp:attribute name="field">
    <tags:splitDateInput cssClass="${cssClass}" 
    dayRequired="${dayRequired}" monthRequired="${monthRequired}" yearRequired="${yearRequired}" 
    required="${yearRequired}" path="${path}" />
</jsp:attribute>
</ui:fieldWrapper>