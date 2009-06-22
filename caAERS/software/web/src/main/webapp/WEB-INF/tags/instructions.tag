<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="code" required="true" %>
<%@attribute name="heading" %>
<spring:message code="${code}" text="NA" var="msgTxt"/>
<c:if test="${msgTxt ne 'NA'}">
<div class="instructions row"><div class="label">${not empty heading ? heading : 'Instructions '}</div><div class="value">${msgTxt}</div></div>
</c:if>