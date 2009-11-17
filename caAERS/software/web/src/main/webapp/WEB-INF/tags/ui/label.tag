<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="path" description="Path to property for data binding on the associated field" required="true"%>
<%@attribute name="text" description="The text that is to be displayed as label" required="true" %>
<%@attribute name="labelProperty" description="If provided will use this to render the label of the field if not defaults to path" required="false" %>

<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="mandatory" type="java.lang.Boolean" description="Tells that this field is mandatory (green symbol)" %>
<caaers:message var="_lblNameText" code="LBL_${not empty labelProperty ? labelProperty : path}" text="${text}." />
<form:label path="${path}">
<span id="${path}-requiredIndicator"><c:if test="${path eq 'aeReport.adverseEvents[0].startDate'}"><tags:requiredIndicator/></c:if></span>
<c:if test="${required or mandatory}"><tags:requiredIndicator/></c:if>&nbsp;${_lblNameText}
</form:label>