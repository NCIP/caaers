<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="code" required="true" %>
<%@attribute name="heading" %>
<div class="row"><div class="summarylabel">${not empty heading ? heading : 'Instructions '}</div><div class="summaryvalue"><spring:message code="${code}" text="Instruction" /></div></div>