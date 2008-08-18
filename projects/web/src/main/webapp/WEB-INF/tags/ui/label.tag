<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="path" description="Path to property for data binding on the associated field" required="true"%>
<%@attribute name="text" description="The text that is to be displayed as label" required="true" %>

<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="mandatory" type="java.lang.Boolean" description="Tells that this field is mandatory (green symbol)" %>
<form:label path="${path}">
<c:if test="${required or mandatory}"><tags:requiredIndicator/></c:if>&nbsp;${text}
</form:label>