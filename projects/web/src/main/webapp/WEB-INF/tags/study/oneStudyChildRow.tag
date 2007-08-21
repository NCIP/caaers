<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="cssClass" required="true" %>
<%@attribute name="disableDelete" type="java.lang.Boolean"  %>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="css">${cssClass} ${index % 2 ne 0 ? 'even' : 'odd'} ${sectionClass}</c:set>
<tr id="${cssClass}-${index}" class="${css}" onmouseout="this.className='${css}'" onmouseover="this.className='highlight'" style="${style}">
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
		<td><tags:renderInputs field="${field}" /></td>
	</c:forEach>
	<c:if test="${not disableDelete}">
	<td><input type="button" value="Delete" onClick="javascript:fireDelete(${index},'${cssClass}-${index}');" /></td>
	</c:if>
	<c:if test="${disableDelete}">
	<td>&nbsp;</td>
	</c:if>
	
</tr>
