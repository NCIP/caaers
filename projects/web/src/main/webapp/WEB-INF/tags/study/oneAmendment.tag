<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="cssClass">amendment-row ${index % 2 ne 0 ? 'even' : 'odd'} ${sectionClass}</c:set>
<tr id="amendment-row-${index}" class="${cssClass}" onmouseout="this.className='${cssClass}'" onmouseover="this.className='highlight'" style="${style}">
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
		<td><tags:renderInputs field="${field}" /></td>
	</c:forEach>
	<td><input type="button" value="Delete" onClick="javascript:fireDelete(${index},'${divisionClass}-${index}');" /></td>
</tr>
