<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="cssClass" required="true" %>
<%@attribute name="disableDelete" type="java.lang.Boolean"  %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>

<c:set var="mainGroup">main${index}</c:set>
<c:set var="css">${cssClass} ${index % 2 ne 0 ? 'even' : 'odd'} ${sectionClass}</c:set>

<tr id="${cssClass}-${empty idSuffix ? index : idSuffix}" class="${css}" onmouseout="this.className='${css}'" onmouseover="this.className='highlight'" style="${style}" valign="top" bgcolor="#ffffff">
	 <td style="border-right:none;">
	 <c:set var="_siteField" value="${fieldGroups[mainGroup].fields[0]}" />
	 <ui:autocompleter path="${_siteField.propertyName}" 
	 		displayNamePath="${_siteField.propertyName}.fullName"
			required="${_siteField.required}" 
			validationJSClass="${_siteField.validatorClassName}" 
			readonly="${readOnly}" 
			size="90"
			title="${field.displayName}"
			enableClearButton="${_siteField.attributes.enableClear}" 
			initialDisplayValue="Begin typing here" />
	 </td>
	<td style="border-left:none;"><tags:button id="${status.index}" color="red" type="button" value="" size="small" icon="x" onclick="javascript:fireDelete(${index},'${cssClass}-${index}');"/></td>
</tr>
