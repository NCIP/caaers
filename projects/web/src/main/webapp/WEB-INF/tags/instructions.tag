<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="code" required="true" %>
<%@attribute name="heading" %>
<div id="instruction"><b>${not empty heading ? heading : 'Instructions: '}</b><spring:message code="${code}" text="Instruction" /></div>