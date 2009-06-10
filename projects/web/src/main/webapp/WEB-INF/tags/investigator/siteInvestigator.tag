<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@attribute name="index" required="true" type="java.lang.Integer"%>
<%@attribute name="style"%>
<%@attribute name="title"%>
<%@attribute name="sectionClass" required="true"%>
<%@attribute name="enableDelete" type="java.lang.Boolean"%>
<%@attribute name="active" type="java.lang.Boolean"%>
<%@attribute name="orgName" type="java.lang.String"%>


<c:set var="mainGroup">main${index}</c:set>

<tr id="${sectionClass}-${index}" class="${sectionClass}" style="${not empty style? style : ''}" >

		<td>
         	<ui:autocompleter 
         			path="${fieldGroups[mainGroup].fields[0].propertyName}" 
					enableClearButton="false" 
					initialDisplayValue="${not empty orgName ? orgName : 'Begin typing here...'}"
					readonly="${not enableDelete}"
					displayNamePath="${fieldGroups[mainGroup].fields[0].propertyName}.name"/>
		</td>
                
    <c:forEach begin="1" items="${fieldGroups[mainGroup].fields}" var="field">
    		<td><tags:renderInputs field="${field}" /></td>
    </c:forEach>
	
    <td align="right">
       <c:if test="${enableDelete}">
        	<a href="javascript:fireAction(${index});">
        	<img src="/caaers/images/checkno.gif" border="0" alt="delete"></a>
	   </c:if>
	   <c:if test="${not enableDelete}">
	   		<tags:button id="${sectionClass}-${listEditorIndex}_btn" 
	   					 size="small" 
	   					 onclick="javascript:toggleDate('${active ? 'Deactivate' : 'Activate'}','${index}');" 
	   					 color="${active ? 'red' : 'green'}" 
	   					 value="${active ? 'Deactivate' : 'Activate'}" 
	   					 icon="${active ? 'x' : 'check'}"
	   					 type="button" />
	   </c:if>
	</td>
</tr>
