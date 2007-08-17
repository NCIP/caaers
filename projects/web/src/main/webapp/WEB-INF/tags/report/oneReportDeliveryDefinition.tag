<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="rdd" required="true" type="gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="originalIndex" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<c:set var="mainGroup">main${originalIndex}</c:set>
<c:set var="divisionClass">${(rdd.entityType == 1)? 'rdd-sys-row':'rdd-email-row'}</c:set>
<c:set var="cssClass">${divisionClass} ${index % 2 ne 0 ? 'even' : 'odd'} ${sectionClass}</c:set>
<tr id="${divisionClass}-${index}" class="${cssClass}" onmouseout="this.className='${cssClass}'" onmouseover="this.className='highlight'">
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
	 <c:if test="${fn:endsWith(field.propertyName, 'endPoint')}" >
	 	<c:if test="${((rdd.entityType == 3) && (field.displayName=='Role')) || ((rdd.entityType != 3) && (field.displayName=='Address'))}">
	 	<td><tags:renderInputs field="${field}" /></td>
	 	</c:if>
	 </c:if>
	 <c:if test="${!(fn:endsWith(field.propertyName, 'endPoint'))}">
		<td><tags:renderInputs field="${field}" /></td>
	 </c:if>
	</c:forEach>
	<td><input type="button" value="Delete" onClick="javascript:fireDelete(${originalIndex},'${divisionClass}-${index}');" /></td>
</tr>
