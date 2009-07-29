<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="dependentObject" type="gov.nih.nci.cabig.caaers.web.search.ui.DependentObject" required="true" description="The dependent object for which the criteria row is being added" %>
<%@attribute name="criteriaParameter" type="gov.nih.nci.cabig.caaers.web.search.AdvancedSearchCriteriaParameter" required="false" description="This is the criteria parameter row in command that needs to be rendered" %>
<%@attribute name="searchTargetObject" type="gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject" required="true" description="The search target object" %>

<c:if test="${criteriaParameter == null}">
	<tr id="criteria-${index }">
		<td align="center" id="attribute-td-${index }">
			<SELECT style="width:200px;" id="attribute-${index }" onChange="javascript:updateAttribute(${index });"
					name="criteriaParameters[${index }].attributeName">
				<OPTION selected value="none">Please select</OPTION>
				<c:forEach items="${dependentObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
					<OPTION value="${uiAttribute.name }">${uiAttribute.label }</OPTION>
				</c:forEach>
				<c:if test="${dependentObject.className == searchTargetObject.className}">
					<c:forEach items="${searchTargetObject.dependentObject}" var="dObject" varStatus="dObjectStatus">
						<c:if test="${dObject.hidden == true}">
							<c:forEach items="${dObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
								<OPTION value="${uiAttribute.name }">${uiAttribute.label }</OPTION>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:if>
			</SELECT> 
		</td>
		<td align="center" id="operator-td-${index }">
			<select style="width:200px;" id="operator-${index}" onChange="javascript:updateOperator(${index });"
					name="criteriaParameters[${index }].predicate">
				<option selected value="none">Please select</option>
			</style>
		</td>
		<td align="center" id="value-td-${index }">
		</td>
		<td align="center" id="delete-td-${index }">
			<img src="<c:url value="/images/checkno.gif" />" id="delete-${index}" onClick="javascript:deleteCriteria(${index });"/>
		</td>
	</tr>
</c:if>
<c:if test="${criteriaParameter != null}">
	<tr id="criteria-${index }">
		<td align="center" id="attribute-td-${index }">
			<SELECT style="width:200px;" id="attribute-${index }" onChange="javascript:updateAttribute(${index });"
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
				<c:if test="${dependentObject.className == searchTargetObject.className}">
					<c:forEach items="${searchTargetObject.dependentObject}" var="dObject" varStatus="dObjectStatus">
						<c:if test="${dObject.hidden == true}">
							<c:forEach items="${dObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
								<c:if test="${uiAttribute.name == criteriaParameter.attributeName}">
									<OPTION value="${uiAttribute.name }" selected>${uiAttribute.label }</OPTION>
								</c:if>
								<c:if test="${uiAttribute.name != criteriaParameter.attributeName}">
									<OPTION value="${uiAttribute.name }">${uiAttribute.label }</OPTION>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:if>
			</SELECT> 
		</td>
		<td align="center" id="operator-td-${index }">
			<select style="width:200px;" id="operator-${index}" onChange="javascript:updateOperator(${index });"
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
				
				<c:if test="${dependentObject.className == searchTargetObject.className}">
					<c:forEach items="${searchTargetObject.dependentObject}" var="dObject" varStatus="dObjectStatus">
						<c:if test="${dObject.hidden == true}">
							<c:forEach items="${dObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
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
						</c:if>
					</c:forEach>
				</c:if>
				
			</style>
		</td>
		<td align="center" id="value-td-${index }">
			<c:forEach items="${dependentObject.uiAttribute}" var="uiAttribute" varStatus="uiAttributeStatus">
				<c:if test="${uiAttribute.name == criteriaParameter.attributeName }">
					<search:renderValueColumn index="${index}" criteriaParameter="${criteriaParameter}" uiAttribute="${uiAttribute }"/>
				</c:if>
			</c:forEach>
			<c:if test="${dependentObject.className == searchTargetObject.className}">
				<c:forEach items="${searchTargetObject.dependentObject}" var="dObject" varStatus="dObjectStatus">
					<c:if test="${dObject.hidden == true}">
						<c:forEach items="${dObject.uiAttribute}" var="uiAttr" varStatus="uiAttrStatus">
							<c:if test="${uiAttr.name == criteriaParameter.attributeName}">
								<search:renderValueColumn index="${index}" criteriaParameter="${criteriaParameter }" uiAttribute="${uiAttr }" />
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:if>
		</td>
		<td align="center" id="delete-td-${index }">
			<img src="<c:url value="/images/checkno.gif" />" id="delete-${index}" onClick="javascript:deleteCriteria(${index });"/>
		</td>
	</tr>
</c:if>