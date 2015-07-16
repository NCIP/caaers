<%@attribute name="entry" type="gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty" required="true" description="A config entry" %>
<%@attribute name="options" type="java.util.Map" description="The control will be a drop down and select options that is to be displayed"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<%@ attribute name="cssClass" required="false" %>

<c:set var="readOnly" value="true"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.tools.configuration.Configuration:UPDATE"><c:set var="readOnly" value="false"/></csmauthz:accesscontrol>
<div class="row">
    <div class="label"><form:label path="conf[${entry.key}].value" id="conf[${entry.key}].value">${entry.name}</form:label></div>
    <div class="value">
        <c:set var="beanPath">conf[${entry.key}].value</c:set>
        <c:choose>
            <c:when test="${not empty options}">
                <div><form:select path="${beanPath}" id="${beanPath}" items="${options}" readonly="${readOnly}"/></div>
            </c:when>
            <c:when test="${entry.controlType == 'boolean'}">
                <div>
                    <label><form:radiobutton path="${beanPath}" id="${beanPath}" value="true" disabled="${readOnly}"/> Yes</label>
                    <label><form:radiobutton path="${beanPath}" id="${beanPath}" value="false" disabled="${readOnly}"/> No</label>
                </div>
            </c:when>
            <c:when test="${entry.controlType == 'text'}">
                <div><form:input path="${beanPath}" id="${beanPath}" cssClass="${cssClass}" readonly="${readOnly}"/></div>
            </c:when>
            <c:otherwise>
                <div>Unimplemented control type ${entry.controlType} for <c:out value="${beanPath}" escapeXml="true"/></div>
            </c:otherwise>
        </c:choose>
        <p class="description">${entry.description}</p>
        <c:if test="${not empty entry['default']}"><p class="description">(Default: ${entry['default']})</p></c:if>
    </div>
</div>