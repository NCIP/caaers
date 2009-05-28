<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="cssClass" required="true" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>

<c:set var="mainGroup">main${index}</c:set>
<c:set var="css">${cssClass} ${index % 2 ne 0 ? 'even' : 'odd'} ${sectionClass}</c:set>

<tr id="${cssClass}-${empty idSuffix ? index : idSuffix}" class="${css}" onmouseout="this.className='${css}'" onmouseover="this.className='highlight'" style="${style}" valign="top">
	 <td style="border-right:none;">
	 <c:set var="_invField" value="${fieldGroups[mainGroup].fields[0]}" />
	 <ui:autocompleter path="${_invField.propertyName}" 
	 		displayNamePath="${_invField.propertyName}.investigator.fullName"
			required="${_invField.required}" 
			validationJSClass="${_invField.validatorClassName}" 
			readonly="${readOnly}" 
			size="${_invField.attributes.size}"
			title="${field.displayName}"
			enableClearButton="${_invField.attributes.enableClear}" 
			initialDisplayValue="Begin typing here..." />
	 </td>
	 <td style="border-right:none;">
	 <c:set var="_invRoleField" value="${fieldGroups[mainGroup].fields[1]}" />
	 <%--
	 <c:if test="${readOnly}">
		<c:set var="_invRoleValue"><caaers:value path="${_invRoleField.propertyName}" /></c:set>
	 	${_invRoleField.attributes.options[_invRoleValue]}
	 </c:if>
	 <c:if test="${not readOnly}">
	 --%>
	 	<ui:select options="${_invRoleField.attributes.options}" 
	 		path="${_invRoleField.propertyName}" 
	 		required="true"
	 		validationJSClass="${_invRoleField.validatorClassName}" />
	<%--
	 </c:if>
	--%>		
	 </td>
	 <td style="border-right:none;">
	 <c:set var="_invStatusField" value="${fieldGroups[mainGroup].fields[2]}" />
	 <%--
	 <c:if test="${readOnly}">
	 	<c:set var="_invStatusValue"><caaers:value path="${_invStatusField.propertyName}" /></c:set>
		${_invStatusField.attributes.options[_invStatusValue]}
	 </c:if>
	 --%>
	 <%--
	 <c:if test="${not readOnly}">
	 --%>
	 	<ui:select options="${_invStatusField.attributes.options}" 
	 		path="${_invStatusField.propertyName}" 
	 		required="true"
	 		validationJSClass="${_invStatusField.validatorClassName}" />
	 <%--		
	 </c:if>
	 --%>		
	 </td>
	<td style="border-left:none;">
		<a id="del-${index}" class="del-${cssClass}" href="javascript:fireDelete(${index},'${cssClass}-${index}');">
			<img src="<chrome:imageUrl name="../checkno.gif"/>" border="0" alt="delete" style="vertical-align:middle">
		</a> 
	</td>
</tr>
