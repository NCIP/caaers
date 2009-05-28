<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="path" description="Path to property for data binding on the associated field" required="true"%>
<%@attribute name="text" description="The text that is to be displayed as label" required="true" %>

<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="mandatory" type="java.lang.Boolean" description="Tells that this field is mandatory (green symbol)" %>
<spring:message var="_lblNameText" code="${path}" text="${text}." />
<form:label path="${path}">
<span id="${path}-indicator"><c:if test="${path eq 'aeReport.adverseEvents[0].startDate'}"><tags:requiredIndicator/></c:if></span>
<c:if test="${required or mandatory}"><tags:requiredIndicator/></c:if>&nbsp;${_lblNameText}
</form:label>