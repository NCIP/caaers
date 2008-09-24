<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="code" required="true" %>
<%@attribute name="heading" %>
<div class="instructions"><div class="summarylabel">${not empty heading ? heading : 'Instructions '}</div><div class="summaryvalue"><spring:message code="${code}" text="There are no instructions for this section." /></div></div>