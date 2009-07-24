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
	 <c:set var="_staffField" value="${fieldGroups[mainGroup].fields[0]}" />
	 <ui:autocompleter path="${_staffField.propertyName}" 
	 		displayNamePath="${_staffField.propertyName}.fullName"
			required="${_staffField.required}" 
			validationJSClass="${_staffField.validatorClassName}" 
			readonly="${readOnly}" 
			size="${_staffField.attributes.size}"
			title="${field.displayName}"
			enableClearButton="${_staffField.attributes.enableClear}" 
			initialDisplayValue="Begin typing here..." />
	 </td>
	 <td style="border-right:none;">
	 <c:set var="_staffRoleField" value="${fieldGroups[mainGroup].fields[1]}" />
	 <%-- 
	 	<ui:select options="${_staffRoleField.attributes.options}" 
	 		path="${_staffRoleField.propertyName}" 
	 		required="true"
	 		validationJSClass="${_staffRoleField.validatorClassName}" />
	 --%>		
	 		
	 </td>
	 <td style="border-right:none;">
	 <c:set var="_staffStatusField" value="${fieldGroups[mainGroup].fields[2]}" />
	 <%-- 
	 	<ui:select options="${_staffStatusField.attributes.options}" 
	 		path="${_staffStatusField.propertyName}" 
	 		required="true"
	 		validationJSClass="${_staffStatusField.validatorClassName}" />
	 --%>		
	 </td>
	<td style="border-left:none;">
		<a id="del-${index}" class="del-${cssClass}" href="javascript:fireDelete(${index},'${cssClass}-${index}');">
			<img src="<chrome:imageUrl name="../checkno.gif"/>" border="0" alt="delete" style="vertical-align:middle">
		</a> 
	</td>
</tr>
