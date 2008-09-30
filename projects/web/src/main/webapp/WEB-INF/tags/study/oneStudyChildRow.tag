<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="idSuffix" %>
<%@attribute name="style"%>
<%@attribute name="cssClass" required="true" %>
<%@attribute name="disableDelete" type="java.lang.Boolean"  %>
<%@attribute name="identifiers" type="java.lang.Boolean"  %>
<%@attribute name="exclusions" %>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="css">${cssClass} ${index % 2 ne 0 ? 'even' : 'odd'} ${sectionClass}</c:set>
<tr id="${cssClass}-${empty idSuffix ? index : idSuffix}" class="${css}" onmouseout="this.className='${css}'" onmouseover="this.className='highlight'" style="${style}" valign="top">
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="fstatus">
		<c:if test="${not fn:contains(exclusions, field.displayName)}">
		<td style="border-right:none;"><tags:renderInputs field="${field}" disabled="${ identifiers and (index lt 2) and (fstatus.index ne 4)}"/></td>
		</c:if>
	</c:forEach>
	<c:if test="${not disableDelete}">
	<td style="border-left:none;"><input id="del-${empty idSuffix ? index : idSuffix}" class="del-${cssClass}" type="image" value="Delete" alt="Delete" src="/caaers/images/checkno.gif" style="vertical-align:middle"onClick="javascript:fireDelete(${index},'${cssClass}-${index}');" /></td>
	</c:if>
	<c:if test="${disableDelete}">
	<td>&nbsp;</td>
	</c:if>
</tr>
