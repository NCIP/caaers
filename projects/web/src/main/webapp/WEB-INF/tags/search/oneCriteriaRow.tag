<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="dependentObject" type="gov.nih.nci.cabig.caaers.web.search.ui.DependentObject" required="true" description="The dependent object for which the criteria row is being added" %>

<tr id="criteria-${index }">
	<td align="center" id="attribute-td-${index }">
		<SELECT style="width:200px;" id="attribute-${index }" onChange="javascript:advancedSearchHelper.updateAttribute(${index });"
				name="criteriaParameters[${index }].attributeName">
			<OPTION selected value="none">Please select</OPTION>
			<c:forEach items="${dependentObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
				<OPTION value="${uiAttribute.name }">${uiAttribute.label }</OPTION>
			</c:forEach>
		</SELECT> 
	</td>
	<td align="center" id="operator-td-${index }">
		<select style="width:200px;" id="operator-${index}" onChange="javascript:advancedSearchHelper.updateOperator(${index });"
				name="criteriaParameters[${index }].predicate">
			<option selected value="none">Please select</option>
		</style>
	</td>
	<td align="center" id="value-td-${index }">
	</td>
	<td align="center" id="delete-td-${index }">
		<img src="<c:url value="/images/checkno.gif" />" id="delete-${index}" onClick="javascript:advancedSearchHelper.deleteCriteria(${index });"/>
	</td>
</tr>