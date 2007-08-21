<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="index" required="true" type="java.lang.Integer"%>
<%@attribute name="identifier" required="true"
	type="gov.nih.nci.cabig.caaers.domain.Identifier"%>
<%@attribute name="style"%>
<%@attribute name="sectionClass" required="true"%>
<%@attribute name="removeButtonAction"%>
<%@attribute name="enableDelete" type="java.lang.Boolean"%>
<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>


<tr id="${sectionClass}-${listEditorIndex}" class="${sectionClass}">
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field">


		
			<c:if test="${(fn:endsWith(field.propertyName, 'value'))}">
				 <td>
				<tags:renderInputs field="${field}" />
				</td>
			</c:if>
		
		
		
    	    <c:if test="${(fn:endsWith(field.propertyName, 'type'))}">
				<td>
				<tags:renderInputs field="${field}" />
				</td>
			</c:if>
		
		
		
			<c:if
				test="${(fn:endsWith(field.propertyName, 'systemName')) &&  (identifier.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
				<td>
				<tags:renderInputs field="${field}"/>
				</td>
			</c:if>
			<c:if
				test="${(fn:endsWith(field.propertyName, 'organization')) &&  (identifier.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
				<td>
				<tags:renderInputs field="${field}" />
				</td>
			</c:if>
		
		
		
		<c:if test="${(fn:endsWith(field.propertyName, 'primaryIndicator'))}">
			<td>
			<tags:renderInputs field="${field}" />
			</td>
			
		</c:if>
		
		
        

	</c:forEach>
            <td align="right">
        	<a href="javascript:fireAction(${deleteParams},'${sectionClass}-${index}','${cssClass}');"><img 
	   			src="/caaers/images/checkno.gif" border="0" alt="delete"></a></td>
</tr>
