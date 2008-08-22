<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true"%>
<%@attribute name="preExistingCondition" type="gov.nih.nci.cabig.caaers.domain.PreExistingCondition"%>
<div>
		<table width="100%">
 			<tr>
  				<td width="99%">
					<c:if test="${not empty preExistingCondition}">
					<ui:text path="assignment.preExistingConditions[${index}].preExistingCondition" 
					displayNamePath="assignment.preExistingConditions[${index}].preExistingCondition.text" readonly="true" />
					</c:if>
					<c:if test="${empty preExistingCondition}">
					<%-- Other, Specify--%>
					Other, specify <ui:text path="assignment.preExistingConditions[${index}].other"/>
					</c:if>
  				</td>
  				<td>
					<a href="#anchorPreExistingCondition" onClick="mHistory.removeDetails('preExistingCondition', ${index}, 'anchorPreExistingCondition')">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table> 
</div>