<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@attribute name="key" required="true" %>
<spring:message code="${key}" text="<span style='font-size:8pt; color:red;'><b>${key}</b></span>" />