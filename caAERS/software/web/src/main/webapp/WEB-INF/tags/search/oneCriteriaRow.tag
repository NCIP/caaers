<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="dependentObject" type="gov.nih.nci.cabig.caaers.web.search.ui.DependentObject" required="true" description="The dependent object for which the criteria row is being added" %>
<%@attribute name="criteriaParameter" type="gov.nih.nci.cabig.caaers.web.search.AdvancedSearchCriteriaParameter" required="false" description="This is the criteria parameter row in command that needs to be rendered" %>

<c:if test="${criteriaParameter == null}">
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
</c:if>
<c:if test="${criteriaParameter != null}">
	<tr id="criteria-${index }">
		<td align="center" id="attribute-td-${index }">
			<SELECT style="width:200px;" id="attribute-${index }" onChange="javascript:advancedSearchHelper.updateAttribute(${index });"
					name="criteriaParameters[${index }].attributeName">
				<OPTION selected value="none">Please select</OPTION>
				<c:forEach items="${dependentObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
					<c:if test="${uiAttribute.name == criteriaParameter.attributeName}">
						<OPTION value="${uiAttribute.name }" selected>${uiAttribute.label }</OPTION>
					</c:if>
					<c:if test="${uiAttribute.name != criteriaParameter.attributeName}">
						<OPTION value="${uiAttribute.name }">${uiAttribute.label }</OPTION>
					</c:if>
				</c:forEach>
			</SELECT> 
		</td>
		<td align="center" id="operator-td-${index }">
			<select style="width:200px;" id="operator-${index}" onChange="javascript:advancedSearchHelper.updateOperator(${index });"
					name="criteriaParameters[${index }].predicate">
				<option selected value="none">Please select</option>
				<c:forEach items="${dependentObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
					<c:if test="${uiAttribute.name == criteriaParameter.attributeName }">
						<c:forEach items="${uiAttribute.operator}" var="operator" varStatus="operatorStatus">
							<c:if test="${operator.name == criteriaParameter.predicate}">
								<option value="${operator.name }" selected>${operator.displayUri }</option>
							</c:if>
							<c:if test="${operator.name != criteriaParameter.predicate}">
								<option value="${operator.name }">${operator.displayUri }</option>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</style>
		</td>
		<td align="center" id="value-td-${index }">
			<c:forEach items="${dependentObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
				<c:if test="${uiAttribute.name == criteriaParameter.attributeName }">
					<search:renderValueColumn index="${index}" criteriaParameter="${criteriaParameter}" uiAttribute="${uiAttribute }"/>
				</c:if>
			</c:forEach>
		</td>
		<td align="center" id="delete-td-${index }">
			<img src="<c:url value="/images/checkno.gif" />" id="delete-${index}" onClick="javascript:advancedSearchHelper.deleteCriteria(${index });"/>
		</td>
	</tr>
</c:if>