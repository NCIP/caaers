<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="cssClass"%>
<%@attribute name="style"%>
<%@attribute name="pair" type="gov.nih.nci.cabig.caaers.web.rule.notification.Pair" %>
<%@attribute name="preformatedValue" type="java.lang.Boolean" %>
<div class="row ${cssClass}" <c:if test="${not empty style}">style="${style}"</c:if>>
    <div class="label">${pair.key}</div>
    <div class="value" >
		<c:if test="${preformatedValue}"><pre width="96%"></c:if>${pair.value}<c:if test="${preformatedValue}"></pre></c:if>
		
    </div>
</div>
